package com.wallee.android.till.sdk.data;

import java.text.ParseException;
import java.util.Date;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 *
 */
public final class Cancellation {

    private final State state;

    private final ResultCode resultCode;
    private final String terminalId;
    private final Long sequenceCount;
    private final Long cancelledSequenceCount;
    private final String transactionTime;

    /**
     * Ctor for Builder
     */
    private Cancellation(State state, ResultCode resultCode, String terminalId, Long sequenceCount, Long cancelledSequenceCount, String transactionTime) {
        // FIXME: For read only properties we need a solution to prevent public modification
        this.state = requireNonNull(state, "state");
        this.resultCode = resultCode;
        this.terminalId = terminalId;
        this.sequenceCount = sequenceCount;
        this.cancelledSequenceCount = cancelledSequenceCount;
        this.transactionTime = transactionTime;
    }

    public State getState() {
        return state;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public Long getSequenceCount() {
        return sequenceCount;
    }

    public Long getCancelledSequenceCount() {
        return cancelledSequenceCount;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public Date getParsedTransactionTime() throws ParseException {
        return Utils.parseTime(transactionTime, "transactionTime");
    }

    public static class Builder {
        private State state = State.PENDING;

        private ResultCode resultCode;
        private String terminalId;
        private Long sequenceCount;
        private Long cancelledSequenceCount;
        private String transactionTime;

        public Builder() {
        }

        /**
         * Copy ctor
         * @param cancellation
         */
        public Builder(Cancellation cancellation) {
            this.state = cancellation.state;
            this.resultCode = cancellation.resultCode;
            this.terminalId = cancellation.terminalId;
            this.sequenceCount = cancellation.sequenceCount;
            this.cancelledSequenceCount = cancellation.cancelledSequenceCount;
            this.transactionTime = cancellation.transactionTime;
        }

        public Builder setState(State state) {
            this.state = state;
            return this;
        }

        public Builder setResultCode(ResultCode resultCode) {
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

        public Builder setCancelledSequenceCount(Long cancelledSequenceCount) {
            this.cancelledSequenceCount = cancelledSequenceCount;
            return this;
        }

        public Builder setTransactionTime(String transactionTime) {
            this.transactionTime = transactionTime;
            return this;
        }

        public Cancellation build() {
            Cancellation cancellation = new Cancellation(this.state, this.resultCode, this.terminalId, this.sequenceCount, this.cancelledSequenceCount, this.transactionTime);
            return cancellation;
        }
    }
}
