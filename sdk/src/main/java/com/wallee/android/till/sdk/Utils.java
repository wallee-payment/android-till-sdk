package com.wallee.android.till.sdk;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wallee.android.till.sdk.data.CancelationResult;
import com.wallee.android.till.sdk.data.FinalBalanceResult;
import com.wallee.android.till.sdk.data.SubmissionResult;
import com.wallee.android.till.sdk.data.Transaction;
import com.wallee.android.till.sdk.data.TransactionCompletion;
import com.wallee.android.till.sdk.data.TransactionCompletionResponse;
import com.wallee.android.till.sdk.data.TransactionResponse;
import com.wallee.android.till.sdk.data.TransactionVoid;
import com.wallee.android.till.sdk.data.TransactionVoidResponse;
import com.wallee.android.till.sdk.data.TransmissionResult;

import java.io.Serializable;

public class Utils {
    private static final Gson GSON = new GsonBuilder().setVersion(1.0).create();
    private static final String KEY_SDK_VERSION = "sdkVersion";
    private static final String KEY_SERIALIZABLE = "serializable";
    private static final String KEY_TRANSACTION_JSON = "transaction";
    private static final String KEY_TRANSACTION_RESPONSE_JSON = "transactionResponse";
    private static final String KEY_TRANSACTION_COMPLETION_JSON = "transactionCompletion";
    private static final String KEY_TRANSACTION_COMPLETION_RESPONSE_JSON = "transactionCompletionResponse";
    private static final String KEY_TRANSACTION_VOID_JSON = "transactionVoid";
    private static final String KEY_TRANSACTION_VOID_RESPONSE_JSON = "transactionVoidResponse";
    private static final String KEY_CANCELATION_RESULT_JSON = "cancelationResult";
    private static final String KEY_SUBMISSION_RESULT_JSON = "submissionResult";
    private static final String KEY_TRANSMISSION_RESULT_JSON = "transmissionResult";
    private static final String KEY_FINAL_BALANCE_RESULT_JSON = "finalBalanceResult";

    public static String getSdkVersion(Bundle bundle) {
        return bundle.getString(KEY_SDK_VERSION);
    }

    public static Serializable getSerializable(Bundle bundle) {
        return bundle.getSerializable(KEY_SERIALIZABLE);
    }

    public static Bundle toBundle(Serializable data) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Utils.KEY_SERIALIZABLE, data);
        bundle.putString(Utils.KEY_SDK_VERSION, ApiClient.VERSION);
        return bundle;
    }

    public static Transaction getTransaction(Bundle bundle) {
        String json = bundle.getString(KEY_TRANSACTION_JSON);
        return GSON.fromJson(json, Transaction.class);
    }

    public static Bundle toBundle(Transaction transaction) {
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_TRANSACTION_JSON, Utils.GSON.toJson(transaction));
        bundle.putString(Utils.KEY_SDK_VERSION, ApiClient.VERSION);
        return bundle;
    }

    public static TransactionResponse getTransactionResponse(Bundle bundle) {
        String json = bundle.getString(KEY_TRANSACTION_RESPONSE_JSON);
        return GSON.fromJson(json, TransactionResponse.class);
    }

    public static Bundle toBundle(TransactionResponse transaction) {
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_TRANSACTION_RESPONSE_JSON, Utils.GSON.toJson(transaction));
        bundle.putString(Utils.KEY_SDK_VERSION, ApiClient.VERSION);
        return bundle;
    }

    public static TransactionCompletion getTransactionCompletion(Bundle bundle) {
        String json = bundle.getString(KEY_TRANSACTION_COMPLETION_JSON);
        return GSON.fromJson(json, TransactionCompletion.class);
    }

    public static Bundle toBundle(TransactionCompletion transaction) {
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_TRANSACTION_COMPLETION_JSON, Utils.GSON.toJson(transaction));
        bundle.putString(Utils.KEY_SDK_VERSION, ApiClient.VERSION);
        return bundle;
    }

    public static TransactionCompletionResponse getTransactionCompletionResponse(Bundle bundle) {
        String json = bundle.getString(KEY_TRANSACTION_COMPLETION_RESPONSE_JSON);
        return GSON.fromJson(json, TransactionCompletionResponse.class);
    }

    public static Bundle toBundle(TransactionCompletionResponse transaction) {
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_TRANSACTION_COMPLETION_RESPONSE_JSON, Utils.GSON.toJson(transaction));
        bundle.putString(Utils.KEY_SDK_VERSION, ApiClient.VERSION);
        return bundle;
    }

    public static TransactionVoid getTransactionVoid(Bundle bundle) {
        String json = bundle.getString(KEY_TRANSACTION_VOID_JSON);
        return GSON.fromJson(json, TransactionVoid.class);
    }

    public static Bundle toBundle(TransactionVoid transactionVoid) {
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_TRANSACTION_VOID_JSON, Utils.GSON.toJson(transactionVoid));
        bundle.putString(Utils.KEY_SDK_VERSION, ApiClient.VERSION);
        return bundle;
    }

    public static TransactionVoidResponse getTransactionVoidResponse(Bundle bundle) {
        String json = bundle.getString(KEY_TRANSACTION_VOID_RESPONSE_JSON);
        return GSON.fromJson(json, TransactionVoidResponse.class);
    }

    public static Bundle toBundle(TransactionVoidResponse transaction) {
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_TRANSACTION_VOID_RESPONSE_JSON, Utils.GSON.toJson(transaction));
        bundle.putString(Utils.KEY_SDK_VERSION, ApiClient.VERSION);
        return bundle;
    }

    public static CancelationResult getCancelationResult(Bundle bundle) {
        String json = bundle.getString(KEY_CANCELATION_RESULT_JSON);
        return GSON.fromJson(json, CancelationResult.class);
    }

    public static Bundle toBundle(CancelationResult result) {
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_CANCELATION_RESULT_JSON, Utils.GSON.toJson(result));
        bundle.putString(Utils.KEY_SDK_VERSION, ApiClient.VERSION);
        return bundle;
    }

    public static SubmissionResult getSubmissionResult(Bundle bundle) {
        String json = bundle.getString(KEY_SUBMISSION_RESULT_JSON);
        return GSON.fromJson(json, SubmissionResult.class);
    }

    public static Bundle toBundle(SubmissionResult result) {
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_SUBMISSION_RESULT_JSON, Utils.GSON.toJson(result));
        bundle.putString(Utils.KEY_SDK_VERSION, ApiClient.VERSION);
        return bundle;
    }

    public static TransmissionResult getTransmissionResult(Bundle bundle) {
        String json = bundle.getString(KEY_TRANSMISSION_RESULT_JSON);
        return GSON.fromJson(json, TransmissionResult.class);
    }

    public static Bundle toBundle(TransmissionResult result) {
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_TRANSMISSION_RESULT_JSON, Utils.GSON.toJson(result));
        bundle.putString(Utils.KEY_SDK_VERSION, ApiClient.VERSION);
        return bundle;
    }

    public static FinalBalanceResult getFinalBalanceResult(Bundle bundle) {
        String json = bundle.getString(KEY_FINAL_BALANCE_RESULT_JSON);
        return GSON.fromJson(json, FinalBalanceResult.class);
    }

    public static Bundle toBundle(FinalBalanceResult result) {
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_FINAL_BALANCE_RESULT_JSON, Utils.GSON.toJson(result));
        bundle.putString(Utils.KEY_SDK_VERSION, ApiClient.VERSION);
        return bundle;
    }
}
