package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

public class Ep2TerminalConfigData {
    private final CommAddrGateWay commAddrGateWay;
    private final String trmId;
    private final String dccProvider;

    public Ep2TerminalConfigData(CommAddrGateWay commAddrGateWay, String trmId, String dccProvider) {
        this.commAddrGateWay = commAddrGateWay;
        this.trmId = trmId;
        this.dccProvider = dccProvider;
    }

    public CommAddrGateWay getCommAddrGateWay() {
        return commAddrGateWay;
    }

    public String getTrmId() {
        return trmId;
    }

    public String getDccProvider() {
        return dccProvider;
    }

    @NonNull
    @Override
    public String toString() {
        return "Ep2TerminalConfigData{" +
                "commAddrGateWay=" + commAddrGateWay +
                ", trmId='" + trmId + '\'' +
                ", dccProvider='" + dccProvider + '\'' +
                '}';
    }
}
