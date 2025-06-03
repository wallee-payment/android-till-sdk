package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 * The reservation void response data from {@link com.wallee.android.till.sdk.ApiClient#voidTransaction(TransactionVoid)} API call.
 */
public final class TransactionVoidResponse {
    private final TransactionVoid transactionVoid;

    private final State state;
    private final ResultCode resultCode;
    private final String terminalId;
    private final String sequenceCount;
    private final String transactionTime;
    private String acquirerId;
    private String transactionRefNumber;
    private String amountAuth;
    private String amountAuthCurr;
    private final List<Receipt> receipts;

    public TransactionVoidResponse(TransactionVoid transactionVoid, @NonNull State state,
                                   @NonNull ResultCode resultCode, @Nullable String terminalId,
                                   @Nullable String sequenceCount, @Nullable String transactionTime,
                                   @NonNull List<Receipt> receipts) {
        this.transactionVoid = transactionVoid;
        this.state = requireNonNull(state, "state");
        this.resultCode = requireNonNull(resultCode, "resultCode");
        this.terminalId = terminalId;
        this.sequenceCount = sequenceCount;
        this.transactionTime = transactionTime;
        this.receipts = requireNonNull(receipts, "receipts");
    }

    public TransactionVoidResponse(TransactionVoid transactionVoid, @NonNull State state,
                                   @NonNull ResultCode resultCode, @Nullable String terminalId,
                                   @Nullable String sequenceCount, @Nullable String transactionTime,
                                   @Nullable String acquirerId, @Nullable String transactionRefNumber,
                                   @Nullable String amountAuth, @Nullable String amountAuthCurr,
                                   @NonNull List<Receipt> receipts) {
        this.transactionVoid = transactionVoid;
        this.state = requireNonNull(state, "state");
        this.resultCode = requireNonNull(resultCode, "resultCode");
        this.terminalId = terminalId;
        this.sequenceCount = sequenceCount;
        this.transactionTime = transactionTime;
        this.acquirerId = acquirerId;
        this.transactionRefNumber = transactionRefNumber;
        this.amountAuth = amountAuth;
        this.amountAuthCurr = amountAuthCurr;
        this.receipts = requireNonNull(receipts, "receipts");
    }

    @NonNull
    public TransactionVoid getTransactionVoid() {
        return transactionVoid;
    }

    @NonNull
    public State getState() {
        return state;
    }

    @NonNull
    public ResultCode getResultCode() {
        return resultCode;
    }

    @Nullable
    public String getTerminalId() {
        return terminalId;
    }

    @Nullable
    public String getSequenceCount() {
        return sequenceCount;
    }

    @Nullable
    public String getTransactionTime() {
        return transactionTime;
    }

    @Nullable
    public String getAcquirerId() {
        return acquirerId;
    }

    @Nullable
    public String getTransactionRefNumber() {
        return transactionRefNumber;
    }

    @Nullable
    public String getAmountAuth() {
        return amountAuth;
    }

    @Nullable
    public String getAmountAuthCurr() {
        return amountAuthCurr;
    }

    @NonNull
    public List<Receipt> getReceipts() {
        return receipts;
    }

    public Date getParsedTransactionTime() throws ParseException {
        return Utils.parseTime(transactionTime, "transactionTime");
    }

    public static class Builder {
        private TransactionVoid transactionVoid;
        private State state;
        private ResultCode resultCode;
        private String terminalId;
        private String sequenceCount;
        private String transactionTime;
        private String acquirerId;
        private String transactionRefNumber;
        private String amountAuth;
        private String amountAuthCurr;
        private List<Receipt> receipts;

        public Builder(TransactionVoidResponse response) {
            this.transactionVoid = response.transactionVoid;
            this.state = requireNonNull(response.state, "state");
            this.resultCode = requireNonNull(response.resultCode, "resultCode");
            this.terminalId = response.terminalId;
            this.sequenceCount = response.sequenceCount;
        }

        public Builder(@NonNull TransactionVoid transactionVoid, @NonNull State state, @NonNull ResultCode resultCode, @NonNull List<Receipt> receipts) {
            this.transactionVoid = transactionVoid;
            this.state = state;
            this.resultCode = resultCode;
            this.receipts = receipts;
        }

        // for reprintReceipt response
        public Builder(@NonNull State state, @NonNull ResultCode resultCode, @NonNull List<Receipt> receipts) {
            this.state = state;
            this.resultCode = resultCode;
            this.receipts = receipts;
        }

        public Builder setTransactionVoid(@NonNull TransactionVoid transactionVoid) {
            this.transactionVoid = transactionVoid;
            return this;
        }

        public Builder setState(@NonNull State state) {
            this.state = state;
            return this;
        }

        public Builder setResultCode(@NonNull ResultCode resultCode) {
            this.resultCode = resultCode;
            return this;
        }

        public Builder setTerminalId(@Nullable String terminalId) {
            this.terminalId = terminalId;
            return this;
        }

        public Builder setSequenceCount(@Nullable String sequenceCount) {
            this.sequenceCount = sequenceCount;
            return this;
        }

        public Builder setTransactionTime(@Nullable String transactionTime) {
            this.transactionTime = transactionTime;
            return this;
        }

        public Builder setAcquirerId(@Nullable String acquirerId) {
            this.acquirerId = acquirerId;
            return this;
        }

        public Builder setTransactionRefNumber(@Nullable String transactionRefNumber) {
            this.transactionRefNumber = transactionRefNumber;
            return this;
        }

        public Builder setAmountAuth(@Nullable String amountAuth) {
            this.amountAuth = amountAuth;
            return this;
        }

        public Builder setAmountAuthCurr(@Nullable String amountAuthCurr) {
            this.amountAuthCurr = amountAuthCurr;
            return this;
        }

        public Builder setReceipts(@NonNull List<Receipt> receipts) {
            this.receipts = receipts;
            return this;
        }

        public TransactionVoid getTransactionVoid() {
            return transactionVoid;
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

        public String getSequenceCount() {
            return sequenceCount;
        }

        public String getTransactionTime() {
            return transactionTime;
        }

        public List<Receipt> getReceipts() {
            return receipts;
        }

        public TransactionVoidResponse build() {
            return new TransactionVoidResponse(transactionVoid, state, resultCode, terminalId, sequenceCount, transactionTime, acquirerId, transactionRefNumber, amountAuth, amountAuthCurr, receipts);
        }
    }
}
