package com.wallee.android.till.apiapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.wallee.android.till.sdk.ApiMessage;

/**
 * Processing all API calls.
 */
final class ApiHandler extends Handler {
    private static final String TAG = "ServiceHandler";

    private final ApiService apiService;

    public ApiHandler(ApiService apiService, Looper looper) {
        super(looper);
        this.apiService = apiService;
    }

    @Override
    public void handleMessage(Message msg) {
        Log.d(TAG, "handleMessage:" + msg);
        ApiMessage message = ApiMessage.values()[msg.arg1];
        switch (message) {
            case AUTHORIZE_TRANSACTION:
                handleAuthorizeTransaction(msg);
                break;
            case RESERVE_TRANSACTION:
                break;
        }
    }

    // all possible API calls below
    private void handleAuthorizeTransaction(Message msg) {
        Intent intent = new Intent(apiService, BeforeAuthorizeTransactionActivity.class);
        intent.putExtra("replyTo", msg.replyTo);
        intent.putExtra("msg", msg);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        apiService.startActivity(intent);
    }
}
