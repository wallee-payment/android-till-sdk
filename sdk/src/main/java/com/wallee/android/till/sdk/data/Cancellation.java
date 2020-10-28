package com.wallee.android.till.sdk.data;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 *
 */
public final class Cancellation {

    private final State state;

    private final CancellationResponse response;

    private Cancellation(State state, CancellationResponse response) {
        this.state = requireNonNull(state, "state");
        this.response = response;
    }

    public State getState() {
        return state;
    }

    public CancellationResponse getResponse() {
        return response;
    }
}
