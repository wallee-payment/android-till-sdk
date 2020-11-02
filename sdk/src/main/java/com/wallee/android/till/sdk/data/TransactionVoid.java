package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 * The reservation void data for {@link com.wallee.android.till.sdk.ApiClient#voidTransaction(TransactionVoid)} API call.
 */
public final class TransactionVoid {

    private final Long reserveReference;

    private TransactionVoid(@NonNull Long reserveReference) {
        this.reserveReference = requireNonNull(reserveReference, "reserveReference");
    }

    @NonNull
    public Long getReserveReference() {
        return reserveReference;
    }
}
