package com.wallee.android.till.sdk.data;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 *
 */
public final class Cancelation {

    private final State state;

    private final CancelationResponse response;

    private Cancelation(State state, CancelationResponse response) {
        this.state = requireNonNull(state, "state");
        this.response = response;
    }

    public State getState() {
        return state;
    }

    public CancelationResponse getResponse() {
        return response;
    }
}
