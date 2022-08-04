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
import java.util.LinkedList;

public class TillLog {

    private static final String TAG = "TillLog";
    private static Messenger myService;
    private static LinkedList<Message> waitingMessages;
    private static final TillLog instance = new TillLog();

    public static TillLog getInstance() {
        return instance;
    }

    private static ServiceConnection con = new ServiceConnection() {
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
     * Instantiate an {@link TillLog}
     */
    public TillLog() {
        waitingMessages = new LinkedList<>();
    }

    /**
     * Bind the API server to the given {@link Activity}. This will initialize the API server and enable calling API methods.
     * @param activity the activity the service will get bound to. I.e. the lifecycle of the API service will be the same as this {@link Activity}.
     */
    public void bind(Activity activity) {
        Intent intent = new Intent()
                .setClassName("com.wallee.android.pinpad", "com.wallee.android.ApiService");
        boolean started = activity.bindService(intent, con, Context.BIND_AUTO_CREATE);
        android.util.Log.d(TAG, "Service started: "+started);
    }

    /**
     * Unbind the API server from the given {@link Activity}.
     * @param activity the activity the service will get unbound from. Must be the same activity that was passed into {@link ApiClient#bind(Activity)}.
     */
    public void unbind(Activity activity) {
        activity.unbindService(con);
    }


    /**
     * Send Debug Logs
     * @param logMessage
     */
    public static void debug(String logMessage) {
        try {
            sendLogLine(android.util.Log.DEBUG, logMessage);
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
    }

    /**
     * Send Error Logs
     * @param logMessage
     */
    public static void error(String logMessage) {
        try {
            sendLogLine(android.util.Log.ERROR, logMessage);
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
    }

    /**
     * Send Info Logs
     * @param logMessage
     */
    public static void info(String logMessage) {
        try {
            sendLogLine(android.util.Log.INFO, logMessage);
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
    }

    /**
     * Send Warning Logs
     * @param logMessage
     */
    public static void warning(String logMessage) {
        try {
            sendLogLine(android.util.Log.WARN, logMessage);
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
    }

    /**
     * Send Assert Logs (Wallee Payment App)
     */
    public static void lAssert (String logMessage) {
        try {
            sendLogLine(android.util.Log.ASSERT, logMessage);
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
    }

    private static void executeWaitingMessages() {
        try {
            while (waitingMessages.iterator().hasNext()) {
                Message message = waitingMessages.iterator().next();
                myService.send(message);
                Log.d(TAG, "Message was sent successfully");
                waitingMessages.remove(message);
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to send message: ", e);
        }
    }

    /**
     * Send logLine to PayDroid VSD (Wallee Payment App)
     * @throws RemoteException any errors while communicating with the API server.
     */
    private static void sendLogLine(int logType, String logMessage) throws RemoteException {
        Message msg = Message.obtain();
        msg.arg1 = ApiMessageType.SEND_LOG_LINE.ordinal();
        Bundle bundle = Utils.logToBundle(logType ,logMessage);
        msg.setData(bundle);
        sendMessage(msg);
    }

    private static void sendMessage(Message msg) throws RemoteException {
        if (myService != null) {
            myService.send(msg);
            Log.d(TAG, "Message was sent successfully");
        } else {
            waitingMessages.add(msg);
            Log.d(TAG, "Client not connected. Message queued for later delivery.");
        }
    }


}
