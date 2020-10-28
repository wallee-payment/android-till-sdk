package com.wallee.android.till.sdk.data;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 *
 */
public final class VoidReservation {

    private final Long deferredReference;

    private final State state;

    private final VoidReservationResponse response;

    /**
     * Ctor for Builder
     */
    private VoidReservation(Long deferredReference, State state, VoidReservationResponse response) {
        this.deferredReference = deferredReference;

        // FIXME: For read only properties we need a solution to prevent public modification
        this.state = requireNonNull(state, "state");
        this.response = response;
    }

    public Long getDeferredReference() {
        return deferredReference;
    }

    public State getState() {
        return state;
    }

    public VoidReservationResponse getResponse() {
        return response;
    }

    public static class Builder {
        private Long deferredReference;

        private State state = State.PENDING;

        private VoidReservationResponse response;

        public Builder(Long deferredReference) {
            this.deferredReference = deferredReference;
        }

        /**
         * Copy ctor
         * @param voidReservation
         */
        public Builder(VoidReservation voidReservation) {
            this.deferredReference = voidReservation.deferredReference;
            this.state = voidReservation.state;
            this.response = voidReservation.response;
        }

        public Builder setDeferredReference(Long deferredReference) {
            this.deferredReference = deferredReference;
            return this;
        }

        public Builder setState(State state) {
            this.state = state;
            return this;
        }

        public Builder setResponse(VoidReservationResponse response) {
            this.response = response;
            return this;
        }

        public VoidReservation build() {
            return new VoidReservation(this.deferredReference, this.state, this.response);
        }
    }
}
