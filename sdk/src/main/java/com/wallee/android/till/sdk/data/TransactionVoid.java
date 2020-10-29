package com.wallee.android.till.sdk.data;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 *
 */
public final class TransactionVoid {

    private final Long reserveReference;

    private final State state;

    private final TransactionVoidResponse response;

    /**
     * Ctor for Builder
     */
    private TransactionVoid(Long reserveReference, State state, TransactionVoidResponse response) {
        this.reserveReference = reserveReference;

        // FIXME: For read only properties we need a solution to prevent public modification
        this.state = requireNonNull(state, "state");
        this.response = response;
    }

    public Long getReserveReference() {
        return reserveReference;
    }

    public State getState() {
        return state;
    }

    public TransactionVoidResponse getResponse() {
        return response;
    }

    public static class Builder {
        private Long reserveReference;

        private State state = State.PENDING;

        private TransactionVoidResponse response;

        public Builder(Long reserveReference) {
            this.reserveReference = reserveReference;
        }

        /**
         * Copy ctor
         * @param transactionVoid
         */
        public Builder(TransactionVoid transactionVoid) {
            this.reserveReference = transactionVoid.reserveReference;
            this.state = transactionVoid.state;
            this.response = transactionVoid.response;
        }

        public Builder setReserveReference(Long reserveReference) {
            this.reserveReference = reserveReference;
            return this;
        }

        public Builder setState(State state) {
            this.state = state;
            return this;
        }

        public Builder setResponse(TransactionVoidResponse response) {
            this.response = response;
            return this;
        }

        public TransactionVoid build() {
            return new TransactionVoid(this.reserveReference, this.state, this.response);
        }
    }
}
