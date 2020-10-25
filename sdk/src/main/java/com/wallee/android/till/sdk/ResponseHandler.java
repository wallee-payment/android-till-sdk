package com.wallee.android.till.sdk;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.wallee.android.till.sdk.data.Reserve;
import com.wallee.android.till.sdk.data.Cancellation;
import com.wallee.android.till.sdk.data.Transaction;

/**
 * Callbacks from the service API.
 * The ApiClient needs a concrete implementation of this class to dispatch the API responses.
 */
public abstract class ResponseHandler extends Handler {
    private static final String TAG = "ResponseHandler";

    public ResponseHandler() {
    }

    /**
     * generic message handler that dispatches to the specialized methods below.
     * @param msg
     */
    @Override
    public final void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        Log.d("HandleReply", "" + msg.arg1);
        if (msg.arg1 == ApiMessageType.AUTHORIZE_TRANSACTION.ordinal()) {
            Bundle bundle = msg.getData();
            Transaction transaction = Utils.GSON.fromJson(bundle.getString(Utils.KEY_TRANSACTION_JSON), Transaction.class);
            authorizeTransactionReply(transaction);
        } else if (msg.arg1 == ApiMessageType.CANCEL_TRANSACTION.ordinal()) {
            Bundle bundle = msg.getData();
            Cancellation cancellation = Utils.GSON.fromJson(bundle.getString(Utils.KEY_CANCELLATION_JSON), Cancellation.class);
            cancelTransactionReply(cancellation);
        } else if (msg.arg1 == ApiMessageType.RESERVE_TRANSACTION.ordinal()) {
            Bundle bundle = msg.getData();
            Reserve reserve = Utils.GSON.fromJson(bundle.getString(Utils.KEY_RESERVE_JSON), Reserve.class);
            reserveTransactionReply(reserve);
        } else {
            Log.e(TAG, "Unknown message type: " + msg.arg1);
        }
    }

    /**
     * The response from an 'authorizeTransaction' call.
     * Check transaction.getState() and in case of errors also transaction.getFailureReason()
     * @param transaction the transaction as it was processed.
     */
    public abstract void authorizeTransactionReply(Transaction transaction);

    /**
     * The response from an 'cancelTransaction' call.
     * Check transaction.getState() and in case of errors also transaction.getFailureReason()
     * @param cancellation the cancellation as it was processed.
     */
    public abstract void cancelTransactionReply(Cancellation cancellation);

    /**
     * The response from an 'reserveTransaction' call.
     * Check transaction.getState() and in case of errors also transaction.getFailureReason()
     * @param reserve the reserve as it was processed.
     */
    public abstract void reserveTransactionReply(Reserve reserve);
}
