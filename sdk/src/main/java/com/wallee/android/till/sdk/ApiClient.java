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
import com.wallee.android.till.sdk.data.TransactionCompletion;
import com.wallee.android.till.sdk.data.VoidReservation;

/**
 * The public interface to the service API.
 * The activity that uses this class should call 'bind' in onCreate and 'unbind' in onDestroy.
 * Currently the only API call is 'authorizeTransaction'.
 */
public class ApiClient {
    private static final String TAG = "ApiClient";
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

    /**
     * Instantiate an ApiClient with the given ResponseHandler.
     * @param handler the handler for any responses from the API server.
     */
    public ApiClient(ResponseHandler handler) {
        this.handler = handler;
        callback = new Messenger(handler);
    }

    /**
     * Bind the API server to the given Activity. This will initialize the API server and enable calling API methods.
     * @param activity the activity the service will get bound to. I.e. the lifecycle of the API service will be the same as this Activity.
     */
    public void bind(Activity activity) {
        Intent intent = new Intent()
                .setClassName("com.wallee.android.till.apiapp", "com.wallee.android.till.apiapp.ApiService");
        boolean started = activity.bindService(intent, this.con, Context.BIND_AUTO_CREATE);
        Log.d(TAG, "Service started: "+started);
    }

    /**
     * Unbind the API server from the given Activity.
     * @param activity the activity the service will get unbound from. Must be the same activity that was passed into 'bind'.
     */
    public void unbind(Activity activity) {
        activity.unbindService(this.con);
    }

    /**
     * Authorize a transaction.
     * @param transaction the transaction that should be authorized.
     * @throws RemoteException any errors while communicating with the API server.
     */
    public void authorizeTransaction(Transaction transaction) throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.AUTHORIZE_TRANSACTION.ordinal();
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_TRANSACTION_JSON, Utils.GSON.toJson(transaction));

        msg.setData(bundle);
        msg.replyTo = callback;
        myService.send(msg);
    }

    /**
     * Complete a deferred transaction.
     * @param transaction the transaction that should be completed.
     * @throws RemoteException any errors while communicating with the API server.
     */
    public void completeTransaction(TransactionCompletion transaction) throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.COMPLETE_TRANSACTION.ordinal();
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_TRANSACTION_COMPLETION_JSON, Utils.GSON.toJson(transaction));

        msg.setData(bundle);
        msg.replyTo = callback;
        myService.send(msg);
    }

    /**
     * Cancel last authorized (not deferred) transaction.
     * @throws RemoteException any errors while communicating with the API server.
     */
    public void cancelLastTransaction() throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.CANCEL_LAST_TRANSACTION.ordinal();

        msg.replyTo = callback;
        myService.send(msg);
    }

    /**
     * Void a deferred transaction.
     * @param voidReservation the void that should be processed.
     * @throws RemoteException any errors while communicating with the API server.
     */
    public void voidReservation(VoidReservation voidReservation) throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.VOID_RESERVATION.ordinal();
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_VOID_RESERVATION_JSON, Utils.GSON.toJson(voidReservation));

        msg.setData(bundle);
        msg.replyTo = callback;
        myService.send(msg);
    }
}
