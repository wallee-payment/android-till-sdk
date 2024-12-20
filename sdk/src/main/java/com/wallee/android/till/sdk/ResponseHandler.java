package com.wallee.android.till.sdk;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.wallee.android.till.sdk.data.CancelationResult;
import com.wallee.android.till.sdk.data.ConfigurationResult;
import com.wallee.android.till.sdk.data.FinalBalanceResult;
import com.wallee.android.till.sdk.data.GeneratePanTokenResponse;
import com.wallee.android.till.sdk.data.GetConfigDataResponse;
import com.wallee.android.till.sdk.data.GetPinpadInformationResponse;
import com.wallee.android.till.sdk.data.InitialisationResult;
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
        if (msg.arg1 == ApiMessageType.CHECK_API_SERVICE_COMPATIBILITY.ordinal()) {
            Bundle bundle = msg.getData();
            Boolean isCompatible = (Boolean) Utils.getSerializable(bundle);
            String apiServiceVersion = Utils.getSdkVersion(bundle);
            checkApiServiceCompatibilityReply(isCompatible, apiServiceVersion);
        } else if (msg.arg1 == ApiMessageType.SDK_VERSION_NOT_SUPPORTED_REPLY.ordinal()) {
            Bundle bundle = msg.getData();
            String message = (String) Utils.getSerializable(bundle);
            serviceApiSdkVersionNotSupportedReply(message);
        } else if (msg.arg1 == ApiMessageType.AUTHORIZE_TRANSACTION.ordinal()) {
            Bundle bundle = msg.getData();
            TransactionResponse response = Utils.getTransactionResponse(bundle);
            authorizeTransactionReply(response);
        } else if (msg.arg1 == ApiMessageType.COMPLETE_TRANSACTION.ordinal()) {
            Bundle bundle = msg.getData();
            TransactionCompletionResponse response = Utils.getTransactionCompletionResponse(bundle);
            completeTransactionReply(response);
        } else if (msg.arg1 == ApiMessageType.VOID_TRANSACTION.ordinal()) {
            Bundle bundle = msg.getData();
            TransactionVoidResponse response = Utils.getTransactionVoidResponse(bundle);
            voidTransactionReply(response);
        } else if (msg.arg1 == ApiMessageType.CANCEL_LAST_TRANSACTION_OPERATION.ordinal()) {
            Bundle bundle = msg.getData();
            CancelationResult result = Utils.getCancelationResult(bundle);
            cancelLastTransactionOperationReply(result);
        } else if (msg.arg1 == ApiMessageType.EXECUTE_SUBMISSION.ordinal()) {
            Bundle bundle = msg.getData();
            SubmissionResult result = Utils.getSubmissionResult(bundle);
            executeSubmissionReply(result);
        } else if (msg.arg1 == ApiMessageType.EXECUTE_TRANSMISSION.ordinal()) {
            Bundle bundle = msg.getData();
            TransmissionResult result = Utils.getTransmissionResult(bundle);
            executeTransmissionReply(result);
        } else if (msg.arg1 == ApiMessageType.EXECUTE_FINAL_BALANCE.ordinal()) {
            Bundle bundle = msg.getData();
            FinalBalanceResult result = Utils.getFinalBalanceResult(bundle);
            executeFinalBalanceReply(result);
        } else if (msg.arg1 == ApiMessageType.GENERATE_PANTOKEN.ordinal()) {
            Bundle bundle = msg.getData();
            GeneratePanTokenResponse result = Utils.getGeneratePanTokenResponse(bundle);
            executeGeneratePanTokenResponse(result);
        } else if (msg.arg1 == ApiMessageType.GET_PINPAD_INFORMATION.ordinal()) {
            Bundle bundle = msg.getData();
            GetPinpadInformationResponse result = Utils.getPinpadInformationResponse(bundle);
            executeGetConfigInfoResponse(result);
        } else if (msg.arg1 == ApiMessageType.EXECUTE_CONFIGURATION.ordinal()) {
            Bundle bundle = msg.getData();
            ConfigurationResult result = Utils.getConfigurationResult(bundle);
            executeConfigurationReply(result);
        } else if (msg.arg1 == ApiMessageType.EXECUTE_INITIALISATION.ordinal()) {
            Bundle bundle = msg.getData();
            InitialisationResult result = Utils.getInitialisationResult(bundle);
            executeInitialisationReply(result);
        } else if (msg.arg1 == ApiMessageType.GET_CONFIG_DATA.ordinal()){
            Bundle bundle = msg.getData();
            GetConfigDataResponse result = Utils.getConfigDataResponse(bundle);
            executeGetConfigDataResponse(result);
        } else {
            Log.e(TAG, "Unknown message type: " + msg.arg1);
        }
    }

    /**
     * The response from an {@link ApiClient#checkApiServiceCompatibility()} call.
     * @param isCompatible is the current SDK is compatible with the service API, or not.
     * @param apiServiceVersion the SDK version number from the service API.
     */
    public void checkApiServiceCompatibilityReply(Boolean isCompatible, String apiServiceVersion) {}

    /**
     * The response from the API in case if the current SDK version is not supported by the API service.
     * @param message the detailed message with an explanation.
     */
    public void serviceApiSdkVersionNotSupportedReply(String message) {}

    /**
     * The response from an {@link ApiClient#authorizeTransaction(Transaction)} call.
     * @param response the transaction as it was processed.
     */
    public void authorizeTransactionReply(TransactionResponse response) {}

    /**
     * The response from an {@link ApiClient#completeTransaction(TransactionCompletion)} call.
     * @param response the transaction as it was processed.
     */
    public void completeTransactionReply(TransactionCompletionResponse response) {}

    /**
     * The response from an {@link ApiClient#voidTransaction(TransactionVoid)} call.
     * @param response the void as it was processed.
     */
    public void voidTransactionReply(TransactionVoidResponse response) {}

    /**
     * The result from an {@link ApiClient#cancelLastTransactionOperation()} call.
     * @param result the cancelation as it was processed.
     */
    public void cancelLastTransactionOperationReply(CancelationResult result) {}

    /**
     * The result from an {@link ApiClient#executeSubmission()} call.
     * @param result the submission as it was processed.
     */
    public void executeSubmissionReply(SubmissionResult result) {}

    /**
     * The result from an {@link ApiClient#executeTransmission()} call.
     * @param result the transmission as it was processed.
     */
    public void executeTransmissionReply(TransmissionResult result) {}

    /**
     * The result from an {@link ApiClient#executeFinalBalance()} call.
     * @param result the final balance as it was processed.
     */
    public void executeFinalBalanceReply(FinalBalanceResult result) {}

    /**
     * The result from an {@link ApiClient#executeGeneratePanToken()} call.
     * @param result the panToken as it was processed.
     */
    public void executeGeneratePanTokenResponse(GeneratePanTokenResponse result) {}

    /**
     * The result from an {@link ApiClient#getPinPadInformation()} call.
     * @param result the pinpadinformation as it was processed.
     */
    public void executeGetConfigInfoResponse(GetPinpadInformationResponse result) {}

    /**
     * The result from an {@link ApiClient#getConfigData()} call.
     * @param result the configdata as it was processed.
     */
    public void executeGetConfigDataResponse(GetConfigDataResponse result) {}

    /**
     * The result from an {@link ApiClient#executeConfiguration()} ()} call.
     * @param result the configuration as it was processed.
     */
    public void executeConfigurationReply(ConfigurationResult result) {}

    /**
     * The result from an {@link ApiClient#executeInitialisation()} ()} ()} call.
     * @param result the initialisation as it was processed.
     */
    public void executeInitialisationReply(InitialisationResult result) {}

}
