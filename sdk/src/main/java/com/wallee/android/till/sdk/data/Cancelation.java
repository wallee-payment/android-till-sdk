package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 * The cancellation data for {@link com.wallee.android.till.sdk.ApiClient#cancelLastTransactionOperation()} API call.
 */
public final class Cancelation {

    private final State state;

    private final CancelationResponse response;

    private Cancelation(@NonNull  State state, @NonNull CancelationResponse response) {
        this.state = requireNonNull(state, "state");
        this.response = requireNonNull(response, "response");
    }

    @NonNull
    public State getState() {
        return state;
    }

    @NonNull
    public CancelationResponse getResponse() {
        return response;
    }
}
