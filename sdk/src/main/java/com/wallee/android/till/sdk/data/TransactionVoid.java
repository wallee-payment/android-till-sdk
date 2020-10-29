package com.wallee.android.till.sdk.data;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 *
 */
public final class TransactionVoid {

    private final Long deferredReference;

    private final State state;

    private final TransactionVoidResponse response;

    /**
     * Ctor for Builder
     */
    private TransactionVoid(Long deferredReference, State state, TransactionVoidResponse response) {
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

    public TransactionVoidResponse getResponse() {
        return response;
    }

    public static class Builder {
        private Long deferredReference;

        private State state = State.PENDING;

        private TransactionVoidResponse response;

        public Builder(Long deferredReference) {
            this.deferredReference = deferredReference;
        }

        /**
         * Copy ctor
         * @param transactionVoid
         */
        public Builder(TransactionVoid transactionVoid) {
            this.deferredReference = transactionVoid.deferredReference;
            this.state = transactionVoid.state;
            this.response = transactionVoid.response;
        }

        public Builder setDeferredReference(Long deferredReference) {
            this.deferredReference = deferredReference;
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
            return new TransactionVoid(this.deferredReference, this.state, this.response);
        }
    }
}
