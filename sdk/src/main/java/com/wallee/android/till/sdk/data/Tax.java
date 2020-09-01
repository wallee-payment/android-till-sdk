package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import static com.wallee.android.till.sdk.data.Utils.check;

/**
 * A tax that has been applied to a LineItem.
 */
public class Tax {
    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
    private final String title;
    private final BigDecimal rate;

    public Tax(@NonNull String title, @NonNull BigDecimal rate) {
        this.title = check(title, "title", 40);
        this.rate = check(rate, "rate");
        if (rate.compareTo(BigDecimal.ZERO) < 0 || rate.compareTo(ONE_HUNDRED) > 0) throw new IllegalArgumentException("'rate' must be a value between 0 and 100!");
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
