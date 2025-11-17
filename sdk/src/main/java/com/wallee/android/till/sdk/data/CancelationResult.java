package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wallee.android.till.sdk.ApiClient;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 * The cancelation result data for {@link ApiClient#cancelLastTransactionOperation()} API call.
 */
public final class CancelationResult {
    private final State state;
    private final ResultCode resultCode;
    private final String terminalId;
    private final String sequenceCount;
    private final String cancelledSequenceCount;
    private String transactionAmount;
    private String transactionCurrency;
    private String acquirerId;
    private String cardNumber;
    private String cardSeqNumber;
    private String cardExpDate;
    private String cardAppLabel;
    private String cardAppId;
    private final String transactionTime;
    private final List<Receipt> receipts;

    public CancelationResult(@NonNull State state, @NonNull ResultCode resultCode, @Nullable String terminalId, @Nullable String sequenceCount, @Nullable String cancelledSequenceCount, @Nullable String transactionTime, @NonNull List<Receipt> receipts) {
        this.state = requireNonNull(state, "state");
        this.resultCode = requireNonNull(resultCode, "resultCode");
        this.terminalId = terminalId;
        this.sequenceCount = sequenceCount;
        this.cancelledSequenceCount = cancelledSequenceCount;
        this.transactionTime = transactionTime;
        this.receipts = requireNonNull(receipts, "receipts");
    }

    public CancelationResult(@NonNull State state, @NonNull ResultCode resultCode,
                             @Nullable String terminalId, @Nullable String sequenceCount,
                             @Nullable String cancelledSequenceCount, @Nullable String transactionAmount,
                             @Nullable String transactionCurrency, @Nullable String acquirerId,
                             @Nullable String cardNumber, @Nullable String cardSeqNumber,
                             @Nullable String cardExpDate, @Nullable String cardAppLabel,
                             @Nullable String cardAppId, @Nullable String transactionTime, @NonNull List<Receipt> receipts
    ) {
        this.state = requireNonNull(state, "state");
        this.resultCode = requireNonNull(resultCode, "resultCode");
        this.terminalId = terminalId;
        this.sequenceCount = sequenceCount;
        this.cancelledSequenceCount = cancelledSequenceCount;
        this.transactionAmount = transactionAmount;
        this.transactionCurrency = transactionCurrency;
        this.acquirerId = acquirerId;
        this.cardNumber = cardNumber;
        this.cardSeqNumber = cardSeqNumber;
        this.cardExpDate = cardExpDate;
        this.cardAppLabel = cardAppLabel;
        this.cardAppId = cardAppId;
        this.transactionTime = transactionTime;
        this.receipts = requireNonNull(receipts, "receipts");
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
    public String getCancelledSequenceCount() {
        return cancelledSequenceCount;
    }

    @Nullable
    public String getTransactionAmount() {
        return transactionAmount;
    }

    @Nullable
    public String getTransactionCurrency() {
        return transactionCurrency;
    }

    @Nullable
    public String getAcquirerId() {
        return acquirerId;
    }

    @Nullable
    public String getCardNumber() {
        return cardNumber;
    }

    @Nullable
    public String getCardSeqNumber() {
        return cardSeqNumber;
    }

    @Nullable
    public String getCardExpDate() {
        return cardExpDate;
    }

    @Nullable
    public String getCardAppLabel() {
        return cardAppLabel;
    }

    @Nullable
    public String getCardAppId() {
        return cardAppId;
    }

    @Nullable
    public String getTransactionTime() {
        return transactionTime;
    }

    @NonNull
    public List<Receipt> getReceipts() {
        return receipts;
    }

    public Date getParsedTransactionTime() throws ParseException {
        return Utils.parseTime(transactionTime, "transactionTime");
    }

    public static class Builder {
        private @NonNull State state;
        private @NonNull ResultCode resultCode;
        private String terminalId;
        private String sequenceCount;
        private String cancelledSequenceCount;
        private String transactionAmount;
        private String transactionCurrency;
        private String acquirerId;
        private String cardNumber;
        private String cardSeqNumber;
        private String cardExpDate;
        private String cardAppLabel;
        private String cardAppId;
        private String transactionTime;
        private @NonNull List<Receipt> receipts;

        public Builder(@NonNull CancelationResult cancelationResult) {
            this.state = requireNonNull(cancelationResult.state, "state");
            this.resultCode = requireNonNull(cancelationResult.resultCode, "resultCode");
            this.terminalId = cancelationResult.terminalId;
            this.sequenceCount = cancelationResult.sequenceCount;
            this.cancelledSequenceCount = cancelationResult.cancelledSequenceCount;
            this.transactionAmount = cancelationResult.transactionAmount;
            this.transactionCurrency = cancelationResult.transactionCurrency;
            this.acquirerId = cancelationResult.acquirerId;
            this.cardNumber = cancelationResult.cardNumber;
            this.cardSeqNumber = cancelationResult.cardSeqNumber;
            this.cardExpDate = cancelationResult.cardExpDate;
            this.cardAppLabel = cancelationResult.cardAppLabel;
            this.cardAppId = cancelationResult.cardAppId;
            this.transactionTime = cancelationResult.transactionTime;
            this.receipts = requireNonNull(cancelationResult.receipts, "receipts");
        }

        public Builder(@NonNull State state, @NonNull ResultCode resultCode, @NonNull List<Receipt> receipts) {
            this.state = state;
            this.resultCode = resultCode;
            this.receipts = receipts;
        }

        public Builder setTerminalId(String terminalId) {
            this.terminalId = terminalId;
            return this;
        }

        public Builder setSequenceCount(String sequenceCount) {
            this.sequenceCount = sequenceCount;
            return this;
        }

        public Builder setCancelledSequenceCount(String cancelledSequenceCount) {
            this.cancelledSequenceCount = cancelledSequenceCount;
            return this;
        }

        public Builder setTransactionAmount(String transactionAmount) {
            this.transactionAmount = transactionAmount;
            return this;
        }

        public Builder setTransactionCurrency(String transactionCurrency) {
            this.transactionCurrency = transactionCurrency;
            return this;
        }

        public Builder setAcquirerId(String acquirerId) {
            this.acquirerId = acquirerId;
            return this;
        }

        public Builder setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder setCardSeqNumber(String cardSeqNumber) {
            this.cardSeqNumber = cardSeqNumber;
            return this;
        }

        public Builder setCardExpDate(String cardExpDate) {
            this.cardExpDate = cardExpDate;
            return this;
        }

        public Builder setCardAppLabel(String cardAppLabel) {
            this.cardAppLabel = cardAppLabel;
            return this;
        }

        public Builder setCardAppId(String cardAppId) {
            this.cardAppId = cardAppId;
            return this;
        }

        public Builder setTransactionTime(String transactionTime) {
            this.transactionTime = transactionTime;
            return this;
        }

        public CancelationResult build() {
            return new CancelationResult(this.state, this.resultCode, this.terminalId, this.sequenceCount, this.cancelledSequenceCount, this.transactionAmount, this.transactionCurrency, this.acquirerId, this.cardNumber, this.cardSeqNumber, this.cardExpDate, this.cardAppLabel, this.cardAppId, this.transactionTime, this.receipts);
        }
    }
}
