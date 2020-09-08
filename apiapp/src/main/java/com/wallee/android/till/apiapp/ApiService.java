package com.wallee.android.till.apiapp;

import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Messenger;
import android.os.Process;
import android.util.Log;

/**
 * The API service. Runs in a separate thread. This service receives calls from the 'sdk' module.
 */
public class ApiService extends Service {
    private static final String TAG = "ApiService";

    private ApiHandler apiHandler;
    private Messenger messenger;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_MORE_FAVORABLE);
        thread.start();
        this.apiHandler = new ApiHandler(this, thread.getLooper());
        this.messenger = new Messenger(this.apiHandler);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "bind");
        return this.messenger.getBinder();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
    }
}