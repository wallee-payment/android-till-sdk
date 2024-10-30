package com.wallee.android.till.sdk.data;

public class CommAddressSCConf {
    private final String internetPortNo;
    private final String internetAddress;

    public CommAddressSCConf(String internetPortNo, String internetAddr) {
        this.internetPortNo = internetPortNo;
        this.internetAddress = internetAddr;
    }

    public String getInternetPortNo() {
        return internetPortNo;
    }

    public String getInternetAddress() {
     return internetAddress;
    }
}
