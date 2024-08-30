package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

/**
 * Represents the communication address gateway information for a terminal.
 */
public class CommAddrGateWay {
    private final String internetPortNo;
    private final String internetAddr;

    public CommAddrGateWay(String internetPortNo, String internetAddr){
        this.internetPortNo = internetPortNo;
        this.internetAddr = internetAddr;
    }

    public String getInternetPortNo(){
        return internetPortNo;
    }

    public String getInternetAddr(){
        return internetAddr;
    }

    @NonNull
    @Override
    public String toString() {
        return "CommAddrGateWay{" +
                "internetPortNo='" + internetPortNo + '\'' +
                ", internetAddr='" + internetAddr + '\'' +
                '}';
    }
}
