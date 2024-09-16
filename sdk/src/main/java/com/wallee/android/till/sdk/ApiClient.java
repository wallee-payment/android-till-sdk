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

import com.wallee.android.till.sdk.data.CancelationResult;
import com.wallee.android.till.sdk.data.ConfigurationResult;
import com.wallee.android.till.sdk.data.FinalBalanceResult;
import com.wallee.android.till.sdk.data.GeneratePanTokenResponse;
import com.wallee.android.till.sdk.data.GetConfigDataResponse;
import com.wallee.android.till.sdk.data.GetPinpadInformationResponse;
import com.wallee.android.till.sdk.data.InitialisationResult;
import com.wallee.android.till.sdk.data.SubmissionResult;
import com.wallee.android.till.sdk.data.Transaction;
import com.wallee.android.till.sdk.data.TransactionResponse;
import com.wallee.android.till.sdk.data.TransactionCompletion;
import com.wallee.android.till.sdk.data.TransactionCompletionResponse;
import com.wallee.android.till.sdk.data.TransactionVoid;
import com.wallee.android.till.sdk.data.TransactionVoidResponse;
import com.wallee.android.till.sdk.data.TransmissionResult;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The public interface to the service API.
 * The activity that uses this class should call {@link ApiClient#bind(Activity)} in onCreate and {@link ApiClient#unbind(Activity)} in onDestroy.
 */
public class ApiClient {
    public static final String VERSION = BuildConfig.SDK_VERSION;

    private static final String TAG = "ApiClient";

    private Messenger myService;
    private ArrayList<Message> waitingMessages;

    private final Messenger callback;
    private ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "Connected");
            myService = new Messenger(service);
            executeWaitingMessages();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "Disconnected");
            myService = null;
            waitingMessages.clear();
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
     * Instantiate an {@link ApiClient} with the given {@link ResponseHandler}.
     * @param handler the handler for any responses from the API server.
     */
    public ApiClient(ResponseHandler handler) {
        callback = new Messenger(handler);
        this.waitingMessages = new ArrayList<>();
    }

    /**
     * Bind the API server to the given {@link Activity}. This will initialize the API server and enable calling API methods.
     * @param activity the activity the service will get bound to. I.e. the lifecycle of the API service will be the same as this {@link Activity}.
     */
    public boolean bind(Activity activity) {
        Intent intent = new Intent()
                .setClassName("com.wallee.android.pinpad", "com.wallee.android.ApiService");
       boolean started = activity.bindService(intent, this.con, Context.BIND_AUTO_CREATE);
        Log.d(TAG, "Service started: "+started);
     return started;
    }

    /**
     * Unbind the API server from the given {@link Activity}.
     * @param activity the activity the service will get unbound from. Must be the same activity that was passed into {@link ApiClient#bind(Activity)}.
     */
    public void unbind(Activity activity) {
        activity.unbindService(this.con);
    }

    /**
     * Bind the API server to the given {@link Context}. This will initialize the API server and enable calling API methods.
     * @param context the context the service will get bound to. Depending on the type of context provided (Application, Activity, or Service), the API service will have a different lifecycle:
     *  Application context: The API service will have the same lifetime as the application and will persist across all activities and services.
     *  Activity context: The API service will have the same lifetime as the activity and will be destroyed when the activity is destroyed.
     *  Service context: The API service will have the same lifetime as the service and will be destroyed when the service is destroyed.
     */
    public boolean bind(Context context) {
        Intent intent = new Intent()
                .setClassName("com.wallee.android.pinpad", "com.wallee.android.ApiService");
        boolean started = context.bindService(intent, this.con, Context.BIND_AUTO_CREATE);
        Log.d(TAG, "Service started: " + started);
        return started;
    }

    /**
     * Unbind the API server from the given {@link Context}.
     * @param context the context the service will get unbound from. Must be the same context that was passed into {@link ApiClient#bind(Context)}.
     */
    public void unbind(Context context) {
        context.unbindService(this.con);
    }

    /**
     * Checks if the current SDK version {@link ApiClient#VERSION} is compatible with the service API.
     * When the operation will be finished a {@link ResponseHandler#checkApiServiceCompatibilityReply(Boolean, String)} method will be called.
     * @throws RemoteException any errors while communicating with the API server.
     */
    public void checkApiServiceCompatibility() throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.CHECK_API_SERVICE_COMPATIBILITY.ordinal();
        Bundle bundle = Utils.toBundle((Serializable) null);

        msg.setData(bundle);
        msg.replyTo = callback;
        sendMessage(msg);
    }

    /**
     * Authorize a transaction. A dedicated transaction application will take the focus after calling this function.
     * When the operation will be finished a {@link ResponseHandler#authorizeTransactionReply(TransactionResponse)} method will be called,
     * and the caller application will receive focus back.
     * <p>
     * Supported transaction types for authorization include:
     * - Purchase
     * - Credit
     * - Reservation
     * - Reservation Adjustment
     *
     * @param transaction the transaction that should be authorized.
     * @throws RemoteException any errors while communicating with the API server.
     */
    public void authorizeTransaction(Transaction transaction) throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.AUTHORIZE_TRANSACTION.ordinal();
        Bundle bundle = Utils.toBundle(transaction);

        msg.setData(bundle);
        msg.replyTo = callback;
        sendMessage(msg);
    }

    /**
     * Complete a reserved transaction. A dedicated transaction application will take the focus after calling this function.
     * When the operation will be finished a {@link ResponseHandler#completeTransactionReply(TransactionCompletionResponse)} method will be called,
     * and the caller application will receive focus back.
     * @param transaction the transaction that should be completed.
     * @throws RemoteException any errors while communicating with the API server.
     */
    public void completeTransaction(TransactionCompletion transaction) throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.COMPLETE_TRANSACTION.ordinal();
        Bundle bundle = Utils.toBundle(transaction);

        msg.setData(bundle);
        msg.replyTo = callback;
        sendMessage(msg);
    }

    /**
     * Void a reserved transaction. A dedicated transaction application will take the focus after calling this function.
     * When the operation will be finished a {@link ResponseHandler#voidTransactionReply(TransactionVoidResponse)} method will be called,
     * and the caller application will receive focus back.
     * @param transactionVoid the void that should be processed.
     * @throws RemoteException any errors while communicating with the API server.
     */
    public void voidTransaction(TransactionVoid transactionVoid) throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.VOID_TRANSACTION.ordinal();
        Bundle bundle = Utils.toBundle(transactionVoid);

        msg.setData(bundle);
        msg.replyTo = callback;
        sendMessage(msg);
    }

    /**
     * Cancel last authorized transaction operation. The operation will be processed in background.
     * When the operation will be finished a {@link ResponseHandler#cancelLastTransactionOperationReply(CancelationResult)} method will be called.
     * @throws RemoteException any errors while communicating with the API server.
     */
    public void cancelLastTransactionOperation() throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.CANCEL_LAST_TRANSACTION_OPERATION.ordinal();
        Bundle bundle = Utils.toBundle((Serializable) null);

        msg.setData(bundle);
        msg.replyTo = callback;
        sendMessage(msg);
    }

    /**
     * Start a submission operation. The operation will be processed in background.
     * When the operation will be finished a {@link ResponseHandler#executeSubmissionReply(SubmissionResult)} method will be called.
     * @throws RemoteException any errors while communicating with the API server.
     */
    public void executeSubmission() throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.EXECUTE_SUBMISSION.ordinal();
        Bundle bundle = Utils.toBundle((Serializable) null);

        msg.setData(bundle);
        msg.replyTo = callback;
        sendMessage(msg);
    }

    /**
     * Start a transmission operation. The operation will be processed in background.
     * When the operation will be finished a {@link ResponseHandler#executeTransmissionReply(TransmissionResult)} method will be called.
     * @throws RemoteException any errors while communicating with the API server.
     */
    public void executeTransmission() throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.EXECUTE_TRANSMISSION.ordinal();
        Bundle bundle = Utils.toBundle((Serializable) null);

        msg.setData(bundle);
        msg.replyTo = callback;
        sendMessage(msg);
    }

    /**
     * Start a final balance operation. The operation will be processed in background.
     * When the operation will be finished a {@link ResponseHandler#executeFinalBalanceReply(FinalBalanceResult)} method will be called.
     * @throws RemoteException any errors while communicating with the API server.
     */
    public void executeFinalBalance() throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.EXECUTE_FINAL_BALANCE.ordinal();
        Bundle bundle = Utils.toBundle((Serializable) null);

        msg.setData(bundle);
        msg.replyTo = callback;
        sendMessage(msg);
    }

    /**
     * Start a generate pantoken operation. This will initiate payment trx for 0 amount in order to read card data and extract pan.
     * When the operation will be finished a {@link ResponseHandler#executeGeneratePanTokenResponse(GeneratePanTokenResponse)} method will be called.
     * @throws RemoteException any errors while communicating with the API server.
     */
    public void executeGeneratePanToken() throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.GENERATE_PANTOKEN.ordinal();
        Bundle bundle = Utils.toBundle((Serializable) null);

        msg.setData(bundle);
        msg.replyTo = callback;
        sendMessage(msg);
    }

    /**
     * Start an operation to get the pinpadinformation
     * When the operation will be finished a {@link ResponseHandler#executeGetConfigInfoResponse(GetPinpadInformationResponse)} method will be called.
     * @throws RemoteException any errors while communicating with the API server.
     */
    public void getPinPadInformation() throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.GET_PINPAD_INFORMATION.ordinal();
        Bundle bundle = Utils.toBundle((Serializable) null);
        msg.setData(bundle);
        msg.replyTo = callback;
        sendMessage(msg);
    }

    /**
     * Start an operation to get the configuration data
     * When the operation will be finished a {@link ResponseHandler#executeGetConfigDataResponse(GetConfigDataResponse)} method will be called.
     * @throws RemoteException any errors while communicating with the API server.
     */
    public void getConfigData() throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.GET_CONFIG_DATA.ordinal();
        Bundle bundle = Utils.toBundle((Serializable) null);
        msg.setData(bundle);
        msg.replyTo = callback;
        sendMessage(msg);
    }

    /**
     * Start a configuration operation. The operation will be processed in background.
     * When the operation will be finished a {@link ResponseHandler#executeConfigurationReply(ConfigurationResult)} method will be called.
     * @throws RemoteException any errors while communicating with the API server.
     */
    public void executeConfiguration() throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.EXECUTE_CONFIGURATION.ordinal();
        Bundle bundle = Utils.toBundle((Serializable) null);
        msg.setData(bundle);
        msg.replyTo = callback;
        sendMessage(msg);
    }

    /**
     * Start a initialization operation. The operation will be processed in background.
     * When the operation will be finished a {@link ResponseHandler#executeInitialisationReply(InitialisationResult)} method will be called.
     * @throws RemoteException any errors while communicating with the API server.
     */
    public void executeInitialisation() throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.EXECUTE_INITIALISATION.ordinal();
        Bundle bundle = Utils.toBundle((Serializable) null);
        msg.setData(bundle);
        msg.replyTo = callback;
        sendMessage(msg);
    }

    private void sendMessage(Message msg) throws RemoteException {
        if (myService != null) {
            myService.send(msg);
            Log.d(TAG, "Message was sent successfully");
        } else {
            waitingMessages.add(msg);
            Log.d(TAG, "Client not connected. Message queued for later delivery.");
        }
    }

    private void executeWaitingMessages() {
        try {
            for (Message message: waitingMessages) {
                myService.send(message);
                Log.d(TAG, "Message was sent successfully");
                waitingMessages.remove(message);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message: ", e);
        }
    }
}
