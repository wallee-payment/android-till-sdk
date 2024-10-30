package com.wallee.android.till.sdk.data;

public class Ep2CtlessTrmCapPerKernel {
    private final String ctlessAddTrmCap;
    private final String ctlessKernelId;
    private final String dataExchangeFlag;
    private final String kernelVersion;
    private final String trxMode;

    public Ep2CtlessTrmCapPerKernel(String ctlessAddTrmCap, String ctlessKernelId, String dataExchangeFlag, String kernelVersion, String trxMode) {
        this.ctlessAddTrmCap = ctlessAddTrmCap;
        this.ctlessKernelId = ctlessKernelId;
        this.dataExchangeFlag = dataExchangeFlag;
        this.kernelVersion = kernelVersion;
        this.trxMode = trxMode;
    }

    public String getCtlessAddTrmCap() {
        return ctlessAddTrmCap;
    }

    public String getCtlessKernelId() {
        return ctlessKernelId;
    }

    public String getDataExchangeFlag() {
        return dataExchangeFlag;
    }

    public String getKernelVersion() {
        return kernelVersion;
    }

    public String getTrxMode() {
        return trxMode;
    }
}
