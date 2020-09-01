package com.wallee.android.till.sdk;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.wallee.android.till.sdk.data.Transaction;

/**
 * The public interface to the service API.
 * The activity that uses this class should call 'bind' in onCreate and 'unbind' in onDestroy.
 * Currently the only real API call is authorizeTransaction.
 */
public class ApiConnection {
    private static final String TAG = "ApiConnection";
    private Messenger myService;
    private final Messenger callback;
    private final ResponseHandler handler;
    private final ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "Connected");
            myService = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "Disconnected");
            myService = null;
        }
        @Override
        public void onBindingDied(ComponentName name) {
            Log.d(TAG, "Died");
        }

        @Override
        public void onNullBinding(ComponentName name) {
            Log.d(TAG, "Null");
        }
    };


    public ApiConnection(ResponseHandler handler) {
        this.handler = handler;
        callback = new Messenger(handler);
    }

    /**
     * Initialize connection to remote service.
     * @param activity the activity the service will get bound to
     */
    public void bind(Activity activity) {
        Intent intent = new Intent()
                .setClassName("com.wallee.android.till.apiapp", "com.wallee.android.till.apiapp.ApiService");
        boolean started = activity.bindService(intent, this.con, Context.BIND_AUTO_CREATE);
        Log.d(TAG, "Service started: "+started);
    }

    public void unbind(Activity activity) {
        activity.unbindService(this.con);
    }

    /**
     * Example API call.
     * @param transaction the transaction
     * @throws RemoteException
     */
    public void authorizeTransaction(Transaction transaction) throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessage.AUTHORIZE_TRANSACTION.ordinal();
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_TRANSACTION_JSON, Utils.GSON.toJson(transaction));

        msg.setData(bundle);
        msg.replyTo = callback;
        myService.send(msg);
    }

}
