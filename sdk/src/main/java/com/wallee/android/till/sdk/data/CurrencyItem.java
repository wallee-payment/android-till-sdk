package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

public class CurrencyItem {
    private final CurrencyType currencyType;
    private final String currency;

    public CurrencyItem(CurrencyType currencyType, String currency) {
        this.currencyType = currencyType;
        this.currency = currency;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public String getCurrency() {
        return currency;
    }

    @NonNull
    @Override
    public String toString() {
        return "CurrencyItem{" +
                "currencyType=" + currencyType +
                ", currency='" + currency + '\'' +
                '}';
    }
}
