package com.wallee.android.till.sdk.data;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 *
 */
public final class VoidReservation {

    private final Long deferredReference;

    private final State state;

    private final String resultCode;
    private final String terminalId;
    private final Long sequenceCount;
    private final String transactionTime;

    /**
     * Ctor for Builder
     */
    private VoidReservation(Long deferredReference, State state, String resultCode, String terminalId, Long sequenceCount, String transactionTime) {
        this.deferredReference = deferredReference;

        // FIXME: For read only properties we need a solution to prevent public modification
        this.state = requireNonNull(state, "state");
        this.resultCode = resultCode;
        this.terminalId = terminalId;
        this.sequenceCount = sequenceCount;
        this.transactionTime = transactionTime;
    }

    public Long getDeferredReference() {
        return deferredReference;
    }

    public State getState() {
        return state;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public Long getSequenceCount() {
        return sequenceCount;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public static class Builder {
        private Long deferredReference;

        private State state = State.PENDING;

        private String resultCode;
        private String terminalId;
        private Long sequenceCount;
        private String transactionTime;

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
            this.resultCode = voidReservation.resultCode;
            this.terminalId = voidReservation.terminalId;
            this.sequenceCount = voidReservation.sequenceCount;
            this.transactionTime = voidReservation.transactionTime;
        }

        public Builder setDeferredReference(Long deferredReference) {
            this.deferredReference = deferredReference;
            return this;
        }

        public Builder setState(State state) {
            this.state = state;
            return this;
        }

        public Builder setResultCode(String resultCode) {
            this.resultCode = resultCode;
            return this;
        }

        public Builder setTerminalId(String terminalId) {
            this.terminalId = terminalId;
            return this;
        }

        public Builder setSequenceCount(Long sequenceCount) {
            this.sequenceCount = sequenceCount;
            return this;
        }

        public Builder setTransactionTime(String transactionTime) {
            this.transactionTime = transactionTime;
            return this;
        }

        public VoidReservation build() {
            VoidReservation voidReservation = new VoidReservation(this.deferredReference, this.state, this.resultCode, this.terminalId, this.sequenceCount, this.transactionTime);
            return voidReservation;
        }
    }
}
