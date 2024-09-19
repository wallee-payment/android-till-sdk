package com.wallee.android.till.sdk.data;

public class CommAddressPMS {
    private final String internetPortNo;
    private final String internetAddress;

    public CommAddressPMS(String internetPortNo, String internetAddress) {
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
