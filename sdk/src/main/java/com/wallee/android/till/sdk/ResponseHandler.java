package com.wallee.android.till.sdk;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.wallee.android.till.sdk.data.Cancelation;
import com.wallee.android.till.sdk.data.FinalBalanceResult;
import com.wallee.android.till.sdk.data.SubmissionResult;
import com.wallee.android.till.sdk.data.Transaction;
import com.wallee.android.till.sdk.data.TransactionCompletion;
import com.wallee.android.till.sdk.data.TransmissionResult;
import com.wallee.android.till.sdk.data.TransactionVoid;

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
        } else if (msg.arg1 == ApiMessageType.COMPLETE_TRANSACTION.ordinal()) {
            Bundle bundle = msg.getData();
            TransactionCompletion transaction = Utils.GSON.fromJson(bundle.getString(Utils.KEY_TRANSACTION_COMPLETION_JSON), TransactionCompletion.class);
            completeTransactionReply(transaction);
        } else if (msg.arg1 == ApiMessageType.CANCEL_LAST_TRANSACTION_OPERATION.ordinal()) {
            Bundle bundle = msg.getData();
            Cancelation cancelation = Utils.GSON.fromJson(bundle.getString(Utils.KEY_CANCELATION_JSON), Cancelation.class);
            cancelLastTransactionOperationReply(cancelation);
        } else if (msg.arg1 == ApiMessageType.VOID_TRANSACTION.ordinal()) {
            Bundle bundle = msg.getData();
            TransactionVoid transactionVoid = Utils.GSON.fromJson(bundle.getString(Utils.KEY_TRANSACTION_VOID_JSON), TransactionVoid.class);
            voidTransactionReply(transactionVoid);
        } else if (msg.arg1 == ApiMessageType.EXECUTE_SUBMISSION.ordinal()) {
            Bundle bundle = msg.getData();
            SubmissionResult result = Utils.GSON.fromJson(bundle.getString(Utils.KEY_SUBMISSION_RESULT_JSON), SubmissionResult.class);
            executeSubmissionReply(result);
        } else if (msg.arg1 == ApiMessageType.EXECUTE_TRANSMISSION.ordinal()) {
            Bundle bundle = msg.getData();
            TransmissionResult result = Utils.GSON.fromJson(bundle.getString(Utils.KEY_TRANSMISSION_RESULT_JSON), TransmissionResult.class);
            executeTransmissionReply(result);
        } else if (msg.arg1 == ApiMessageType.EXECUTE_FINAL_BALANCE.ordinal()) {
            Bundle bundle = msg.getData();
            FinalBalanceResult result = Utils.GSON.fromJson(bundle.getString(Utils.KEY_FINAL_BALANCE_RESULT_JSON), FinalBalanceResult.class);
            executeFinalBalanceReply(result);
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
     * The response from an 'completeTransaction' call.
     * Check transaction.getState() and in case of errors also transaction.getFailureReason()
     * @param transaction the transaction as it was processed.
     */
    public abstract void completeTransactionReply(TransactionCompletion transaction);

    /**
     * The response from an 'cancelLastTransactionOperation' call.
     * Check cancelation.getState() and in case of errors also cancelation.getFailureReason()
     * @param cancelation the cancelation as it was processed.
     */
    public abstract void cancelLastTransactionOperationReply(Cancelation cancelation);

    /**
     * The response from an 'voidTransaction' call.
     * Check transactionVoid.getState() and in case of errors also transactionVoid.getFailureReason()
     * @param transactionVoid the void as it was processed.
     */
    public abstract void voidTransactionReply(TransactionVoid transactionVoid);

    /**
     * The response from an 'executeSubmission' call.
     * @param result the submission as it was processed.
     */
    public abstract void executeSubmissionReply(SubmissionResult result);

    /**
     * The response from an 'executeTransmission' call.
     * @param result the transmission as it was processed.
     */
    public abstract void executeTransmissionReply(TransmissionResult result);

    /**
     * The response from an 'executeFinalBalance' call.
     * @param result the final balance as it was processed.
     */
    public abstract void executeFinalBalanceReply(FinalBalanceResult result);
}
