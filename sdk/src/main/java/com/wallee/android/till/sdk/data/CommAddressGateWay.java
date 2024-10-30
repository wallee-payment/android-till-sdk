package com.wallee.android.till.sdk.data;

public class CommAddressGateWay {
    private final String internetPortNo;
    private final String internetAddress;

    public CommAddressGateWay(String internetPortNo, String internetAddress) {
        this.internetPortNo = internetPortNo;
        this.internetAddress = internetAddress;
    }

    public String getInternetPortNo() {
        return internetPortNo;
    }

    public String getInternetAddress() {
        return internetAddress;
    }
}
