package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

/**
 * Represents the brand information for a terminal.
 * The brand typically refers to the payment brand (e.g., Visa, Mastercard)
 */
public class Brand {
    private final String brandName;
    private final String lastInitDate;
    private final String paymentProtocol;

    public Brand(String brandName, String lastInitDate,
                 String paymentProtocol) {
        this.brandName = brandName;
        this.lastInitDate = lastInitDate;
        this.paymentProtocol = paymentProtocol;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getLastInitDate() {
        return lastInitDate;
    }

    public String getPaymentProtocol() {
        return paymentProtocol;
    }

    @NonNull
    @Override
    public String toString() {
        return "Brand{" +
                "brandName='" + brandName + '\'' +
                ", lastInitDate='" + lastInitDate + '\'' +
                ", paymentProtocol='" + paymentProtocol + '\'' +
                '}';
    }
}
