package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

public class TerminalApplicationConfigData {
    private final String aid;
    private final String brand;

    public TerminalApplicationConfigData(String aid, String brand) {
        this.aid = aid;
        this.brand = brand;

    }

    public String getAid() {
        return aid;
    }

    public String getBrand() {
        return brand;
    }

    @NonNull
    @Override
    public String toString() {
        return "TerminalApplicationConfigData{" +
                "aid='" + aid + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
