package com.wallee.android.till.sdk;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.wallee.android.till.sdk.data.CancelationResult;
import com.wallee.android.till.sdk.data.FinalBalanceResult;
import com.wallee.android.till.sdk.data.SubmissionResult;
import com.wallee.android.till.sdk.data.Transaction;
import com.wallee.android.till.sdk.data.TransactionCompletion;
import com.wallee.android.till.sdk.data.TransactionCompletionResponse;
import com.wallee.android.till.sdk.data.TransactionResponse;
import com.wallee.android.till.sdk.data.TransactionVoidResponse;
import com.wallee.android.till.sdk.data.TransmissionResult;
import com.wallee.android.till.sdk.data.TransactionVoid;

/**
 * Callbacks from the service API.
 * The {@link ApiClient} needs a concrete implementation of this class to dispatch the API responses.
 */
public abstract class ResponseHandler extends Handler {
    private static final String TAG = "ResponseHandler";

    public ResponseHandler() {
    }

    /**
     * Generic message handler that dispatches to the specialized methods below.
     * @param msg - API message.
     */
    @Override
    public final void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        Log.d("HandleReply", "" + msg.arg1);
        if (msg.arg1 == ApiMessageType.AUTHORIZE_TRANSACTION.ordinal()) {
            Bundle bundle = msg.getData();
            TransactionResponse response = Utils.GSON.fromJson(bundle.getString(Utils.KEY_TRANSACTION_RESPONSE_JSON), TransactionResponse.class);
            authorizeTransactionReply(response);
        } else if (msg.arg1 == ApiMessageType.COMPLETE_TRANSACTION.ordinal()) {
            Bundle bundle = msg.getData();
            TransactionCompletionResponse response = Utils.GSON.fromJson(bundle.getString(Utils.KEY_TRANSACTION_COMPLETION_RESPONSE_JSON), TransactionCompletionResponse.class);
            completeTransactionReply(response);
        } else if (msg.arg1 == ApiMessageType.VOID_TRANSACTION.ordinal()) {
            Bundle bundle = msg.getData();
            TransactionVoidResponse response = Utils.GSON.fromJson(bundle.getString(Utils.KEY_TRANSACTION_VOID_RESPONSE_JSON), TransactionVoidResponse.class);
            voidTransactionReply(response);
        } else if (msg.arg1 == ApiMessageType.CANCEL_LAST_TRANSACTION_OPERATION.ordinal()) {
            Bundle bundle = msg.getData();
            CancelationResult result = Utils.GSON.fromJson(bundle.getString(Utils.KEY_CANCELATION_RESULT_JSON), CancelationResult.class);
            cancelLastTransactionOperationReply(result);
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
     * The response from an {@link ApiClient#authorizeTransaction(Transaction)} call.
     * @param response the transaction as it was processed.
     */
    public abstract void authorizeTransactionReply(TransactionResponse response);

    /**
     * The response from an {@link ApiClient#completeTransaction(TransactionCompletion)} call.
     * @param response the transaction as it was processed.
     */
    public abstract void completeTransactionReply(TransactionCompletionResponse response);

    /**
     * The response from an {@link ApiClient#voidTransaction(TransactionVoid)} call.
     * @param response the void as it was processed.
     */
    public abstract void voidTransactionReply(TransactionVoidResponse response);

    /**
     * The result from an {@link ApiClient#cancelLastTransactionOperation()} call.
     * @param result the cancelation as it was processed.
     */
    public abstract void cancelLastTransactionOperationReply(CancelationResult result);

    /**
     * The result from an {@link ApiClient#executeSubmission()} call.
     * @param result the submission as it was processed.
     */
    public abstract void executeSubmissionReply(SubmissionResult result);

    /**
     * The result from an {@link ApiClient#executeTransmission()} call.
     * @param result the transmission as it was processed.
     */
    public abstract void executeTransmissionReply(TransmissionResult result);

    /**
     * The result from an {@link ApiClient#executeFinalBalance()} call.
     * @param result the final balance as it was processed.
     */
    public abstract void executeFinalBalanceReply(FinalBalanceResult result);
}
