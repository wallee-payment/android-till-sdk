package com.wallee.android.till.sdk.data;

import android.util.Log;

public enum PaymentEntryMethod {
    MAGSTRIPE,
    ICC,
    CTLS,
    QR,
    MANUAL,
    NOT_APPLICABLE,
    QRC_SCAN;


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
            case "QRC-SCAN":
                return QRC_SCAN;
            default:
                return null;
        }
    }
}
