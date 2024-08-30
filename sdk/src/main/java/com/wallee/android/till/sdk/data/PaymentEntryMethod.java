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
            case "0":
                return MAGSTRIPE;
            case "1":
                return ICC;
            case "2":
                return CTLS;
            case "3":
                return QR;
            case "4":
                return MANUAL;
            default:
                return null;
        }
    }
}
