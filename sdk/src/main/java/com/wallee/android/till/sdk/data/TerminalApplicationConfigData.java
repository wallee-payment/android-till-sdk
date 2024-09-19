package com.wallee.android.till.sdk.data;

public class TerminalApplicationConfigData {
    private final String aid;
    private final String brand;
    private final String readerTechnology;

    public TerminalApplicationConfigData(String aid, String brand, String readerTechnology) {
        this.aid = aid;
        this.brand = brand;
        this.readerTechnology = readerTechnology;

    }

    public String getAid() {
        return aid;
    }

    public String getBrand() {
        return brand;
    }

    public String getReaderTechnology() {
        return readerTechnology;
    }

}
