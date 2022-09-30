package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 * The reservation void data for {@link com.wallee.android.till.sdk.ApiClient#voidTransaction(TransactionVoid)} API call.
 */
public final class TransactionVoid {

    private final String reserveReference;
    private final String acquirerId;

    public TransactionVoid(@NonNull String reserveReference, @NonNull String acquirerId) {
        this.reserveReference = requireNonNull(reserveReference, "reserveReference");
        this.acquirerId = requireNonNull(acquirerId, "acquirerId");
    }

    @NonNull
    public String getReserveReference() {
        return reserveReference;
    }

    @NonNull
    public String getAcquirerId() {
        return acquirerId;
    }
}
