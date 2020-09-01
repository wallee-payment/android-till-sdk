package com.wallee.android.till.sdk;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.wallee.android.till.sdk.data.Transaction;

public class Utils {
    private static final String TAG = "Utils";

    public static final Gson GSON = new GsonBuilder().setVersion(1.0).create();
    public static final String KEY_TRANSACTION_JSON = "transaction";

    public static Transaction getTransaction(Intent intent) {
        String json = intent.getStringExtra(KEY_TRANSACTION_JSON);
        return GSON.fromJson(json, Transaction.class);
    }

    /**
     * Check whether a certain transaction is processable. Should be called in onCreate.
     * @param a
     * @param checker
     */
    public static void checkTransaction(Activity a, TransactionChecker checker) {
        Transaction transaction = getTransaction(a.getIntent());
        if (! checker.check(transaction)) {
            a.setResult(ReturnCode.NOT_PROCESSED.ordinal());
            a.finish();
        }
    }

    /**
     * Process a Transaction.
     * @param a
     * @param p
     */
    public static Transaction processTransaction(Activity a, TransactionProcessor p) {
        Transaction transaction = getTransaction(a.getIntent());
        try {
            Transaction processedTransaction = p.process(transaction);
            a.setResult(0, new Intent().putExtra(KEY_TRANSACTION_JSON, GSON.toJson(processedTransaction)));
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
