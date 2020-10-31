package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 * The reservation void data for {@link com.wallee.android.till.sdk.ApiClient#voidTransaction(TransactionVoid)} API call.
 */
public final class TransactionVoid {

    private final Long reserveReference;

    private final State state;

    private final TransactionVoidResponse response;

    /**
     * Ctor for Builder
     */
    private TransactionVoid(@NonNull Long reserveReference, @NonNull State state, @Nullable TransactionVoidResponse response) {
        this.reserveReference = requireNonNull(reserveReference, "reserveReference");

        // FIXME: For read only properties we need a solution to prevent public modification
        this.state = requireNonNull(state, "state");
        this.response = response;
    }

    @NonNull
    public Long getReserveReference() {
        return reserveReference;
    }

    @NonNull
    public State getState() {
        return state;
    }

    @Nullable
    public TransactionVoidResponse getResponse() {
        return response;
    }

    public static class Builder {
        private Long reserveReference;

        private State state = State.PENDING;

        private TransactionVoidResponse response;

        public Builder(@NonNull Long reserveReference) {
            this.reserveReference = reserveReference;
        }

        /**
         * Copy ctor
         * @param transactionVoid
         */
        public Builder(@NonNull TransactionVoid transactionVoid) {
            this.reserveReference = transactionVoid.reserveReference;
            this.state = transactionVoid.state;
            this.response = transactionVoid.response;
        }

        @NonNull
        public Builder setReserveReference(@NonNull Long reserveReference) {
            this.reserveReference = reserveReference;
            return this;
        }

        @NonNull
        public Builder setState(@NonNull State state) {
            this.state = state;
            return this;
        }

        @NonNull
        public Builder setResponse(@Nullable TransactionVoidResponse response) {
            this.response = response;
            return this;
        }

        @NonNull
        public TransactionVoid build() {
            return new TransactionVoid(this.reserveReference, this.state, this.response);
        }
    }
}
