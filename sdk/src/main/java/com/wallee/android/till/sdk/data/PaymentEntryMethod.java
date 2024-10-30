package com.wallee.android.till.sdk.data;

import android.util.Log;

public enum PaymentEntryMethod {
    MAGSTRIPE,
    ICC,
    CTLS,
    QR,
    MANUAL,
    NOT_APPLICABLE;


    public static PaymentEntryMethod getValue(String value) {
        if (value == null){
            Log.d("PaymentEntryMethod", "PaymentEntryMethod value is null");
            return NOT_APPLICABLE;
        }
        switch (value){
            case "MAGSTRIPE":
                return MAGSTRIPE;
            case "ICC":
                return ICC;
            case "CTLS":
                return CTLS;
            case "QR":
                return QR;
            case "MANUAL":
                return MANUAL;
            default:
                return null;
        }
    }
}
