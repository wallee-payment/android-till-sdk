package com.wallee.android.till.sdk;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

public class Utils {
    private static final String TAG = "Utils";

    public static final Gson GSON = new GsonBuilder().setVersion(1.0).create();
    public static final String KEY_TRANSACTION_JSON = "transaction";
    public static final String KEY_TRANSACTION_RESPONSE_JSON = "transactionResponse";
    public static final String KEY_TRANSACTION_COMPLETION_JSON = "transactionCompletion";
    public static final String KEY_TRANSACTION_COMPLETION_RESPONSE_JSON = "transactionCompletionResponse";
    public static final String KEY_TRANSACTION_VOID_JSON = "transactionVoid";
    public static final String KEY_TRANSACTION_VOID_RESPONSE_JSON = "transactionVoidResponse";
    public static final String KEY_CANCELATION_RESULT_JSON = "cancelationResult";
    public static final String KEY_SUBMISSION_RESULT_JSON = "submissionResult";
    public static final String KEY_TRANSMISSION_RESULT_JSON = "transmissionResult";
    public static final String KEY_FINAL_BALANCE_RESULT_JSON = "finalBalanceResult";

    public static Transaction getTransaction(Intent intent) {
        String json = intent.getStringExtra(KEY_TRANSACTION_JSON);
        return GSON.fromJson(json, Transaction.class);
    }

    public static TransactionResponse getTransactionResponse(Intent intent) {
        String json = intent.getStringExtra(KEY_TRANSACTION_RESPONSE_JSON);
        return GSON.fromJson(json, TransactionResponse.class);
    }

    public static TransactionCompletion getTransactionCompletion(Intent intent) {
        String json = intent.getStringExtra(KEY_TRANSACTION_COMPLETION_JSON);
        return GSON.fromJson(json, TransactionCompletion.class);
    }

    public static TransactionCompletionResponse getTransactionCompletionResponse(Intent intent) {
        String json = intent.getStringExtra(KEY_TRANSACTION_COMPLETION_RESPONSE_JSON);
        return GSON.fromJson(json, TransactionCompletionResponse.class);
    }

    public static TransactionVoid getTransactionVoid(Intent intent) {
        String json = intent.getStringExtra(KEY_TRANSACTION_VOID_JSON);
        return GSON.fromJson(json, TransactionVoid.class);
    }

    public static TransactionVoidResponse getTransactionVoidResponse(Intent intent) {
        String json = intent.getStringExtra(KEY_TRANSACTION_VOID_RESPONSE_JSON);
        return GSON.fromJson(json, TransactionVoidResponse.class);
    }

    public static CancelationResult getCancelationResult(Intent intent) {
        String json = intent.getStringExtra(KEY_CANCELATION_RESULT_JSON);
        return GSON.fromJson(json, CancelationResult.class);
    }

    public static SubmissionResult getSubmissionResult(Intent intent) {
        String json = intent.getStringExtra(KEY_SUBMISSION_RESULT_JSON);
        return GSON.fromJson(json, SubmissionResult.class);
    }

    public static TransmissionResult getTransmissionResult(Intent intent) {
        String json = intent.getStringExtra(KEY_TRANSMISSION_RESULT_JSON);
        return GSON.fromJson(json, TransmissionResult.class);
    }

    public static FinalBalanceResult getFinalBalanceResult(Intent intent) {
        String json = intent.getStringExtra(KEY_FINAL_BALANCE_RESULT_JSON);
        return GSON.fromJson(json, FinalBalanceResult.class);
    }

    /**
     * Check whether a certain transaction is processable. Should be called in onCreate.
     * @param a the 3rd party activity that is being checked.
     * @param checker SAM-type interface to do the check. If the check returns false the activity will be terminated without processing the Transaction.
     */
    public static void checkTransaction(Activity a, TransactionChecker checker) {
        Transaction transaction = getTransaction(a.getIntent());
        if (! checker.check(transaction)) {
            a.setResult(ReturnCode.NOT_PROCESSED.ordinal());
            a.finish();
        }
    }

    /**
     * 3rd party processing of a Transaction.
     * @param a the 3rd party activity
     * @param p SAM-type interface for processing the activity.
     */
    public static Transaction processTransaction(Activity a, TransactionProcessor p) {
        Transaction transaction = getTransaction(a.getIntent());
        try {
            Transaction processedTransaction = p.process(transaction);
            a.setResult(ReturnCode.OK.ordinal(), new Intent().putExtra(KEY_TRANSACTION_JSON, GSON.toJson(processedTransaction)));
            a.finish();
            return processedTransaction;
        } catch (Exception ex) {
            Log.e(TAG, "Transaction failed", ex);
            a.setResult(ReturnCode.ERROR.ordinal());
            a.finish();
            return transaction;
        }
    }

    public interface TransactionProcessor {
        Transaction process(Transaction t);
    }

    public interface TransactionChecker {
        boolean check(Transaction t);
    }
}
