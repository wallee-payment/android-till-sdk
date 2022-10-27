package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 * The transaction response data from {@link com.wallee.android.till.sdk.ApiClient#authorizeTransaction(Transaction)} API call.
 */
public final class TransactionResponse {
    private final Transaction transaction;

    private final State state;
    private final ResultCode resultCode;
    private final String authorizationCode;
    private final String terminalId;
    private final Long sequenceCount;
    private final String transactionTime;
    private final String reserveReference;
    private final String acquirerId;
    private final String cardNumber;
    private final List<Receipt> receipts;
    private final String cardIssuingCountry;
    private final String cardAppLabel;
    private final String cardAppId;
    private final String amountTip;
    private final String panToken;

    public TransactionResponse(@NonNull Transaction transaction, @NonNull State state, @NonNull ResultCode resultCode,
                               @Nullable String authorizationCode, @Nullable String terminalId,
                               @Nullable Long sequenceCount, @Nullable String transactionTime,
                               @Nullable String reserveReference, @Nullable String acquirerId,
                               @NonNull List<Receipt> receipts, @Nullable String cardNumber,
                               @Nullable String cardIssuingCountry, @Nullable String cardAppLabel,
                               @Nullable String  cardAppId, @Nullable String amountTip, @Nullable String panToken) {
        this.transaction = requireNonNull(transaction, "transaction");
        this.state = requireNonNull(state, "state");
        this.resultCode = requireNonNull(resultCode, "resultCode");
        this.authorizationCode = authorizationCode;
        this.terminalId = terminalId;
        this.sequenceCount = sequenceCount;
        this.transactionTime = transactionTime;
        this.reserveReference = reserveReference;
        this.acquirerId = acquirerId;
        this.receipts = requireNonNull(receipts, "receipts");
        this.cardNumber = cardNumber;
        this.cardIssuingCountry = cardIssuingCountry;
        this.cardAppLabel = cardAppLabel;
        this.cardAppId = cardAppId;
        this.amountTip = amountTip;
        this.panToken = panToken;
    }

    @NonNull
    public Transaction getTransaction() {
        return transaction;
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
    public String getAuthorizationCode() {
        return authorizationCode;
    }

    @Nullable
    public String getTerminalId() {
        return terminalId;
    }

    @Nullable
    public Long getSequenceCount() {
        return sequenceCount;
    }

    @Nullable
    public String getTransactionTime() {
        return transactionTime;
    }

    @Nullable
    public String getReserveReference() {
        return reserveReference;
    }

    @Nullable
    public String getAcquirerId() {
        return acquirerId;
    }

    @NonNull
    public List<Receipt> getReceipts() {
        return receipts;
    }

    public Date getParsedTransactionTime() throws ParseException {
        return Utils.parseTime(transactionTime, "transactionTime");
    }

    public String getCardNumber() { return cardNumber; }

    public String getCardIssuingCountry() { return cardIssuingCountry; }

    public String getCardAppLabel() {
        return cardAppLabel;
    }

    public String getCardAppId() {
        return cardAppId;
    }

    public String getAmountTip() {
        return amountTip;
    }

    public String getPanToken() { return panToken; }

    public static class Builder {
        private @NonNull Transaction transaction;
        private @NonNull State state;
        private @NonNull ResultCode resultCode;
        private String authorizationCode;
        private String terminalId;
        private Long sequenceCount;
        private String transactionTime;
        private String reserveReference;
        private String acquirerId;
        private String cardNumber;
        private @NonNull List<Receipt> receipts;
        private String cardIssuingCountry;
        private String cardAppLabel;
        private String cardAppId;
        private String amountTip;
        private String panToken;

        public Builder(TransactionResponse transactionResponse) {
            this.transaction = requireNonNull(transactionResponse.transaction, "transaction");
            this.state = requireNonNull(transactionResponse.state, "transaction");
            this.resultCode = requireNonNull(transactionResponse.resultCode, "transaction");
            this.authorizationCode = transactionResponse.authorizationCode;
            this.terminalId = transactionResponse.terminalId;
            this.sequenceCount = transactionResponse.sequenceCount;
            this.transactionTime = transactionResponse.transactionTime;
            this.reserveReference = transactionResponse.reserveReference;
            this.acquirerId = transactionResponse.acquirerId;
            this.receipts = requireNonNull(transactionResponse.receipts, "transaction");
            this.cardNumber = transactionResponse.cardNumber;
            this.cardIssuingCountry = transactionResponse.cardIssuingCountry;
            this.cardAppLabel = transactionResponse.cardAppLabel;
            this.cardAppId = transactionResponse.cardAppId;
            this.amountTip = transactionResponse.amountTip;
            this.panToken = transactionResponse.panToken;
        }

        // Every Transaction Response must have at least those 4 parameters!
        public Builder(@NonNull Transaction transaction, @NonNull State state, @NonNull ResultCode resultCode, @NonNull List<Receipt> receipts) {
            this.transaction = transaction;
            this.state = state;
            this.resultCode = resultCode;
            this.receipts = receipts;
        }

        public Builder setTransaction(@NonNull Transaction transaction) {
            this.transaction = transaction;
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

        public Builder setAuthorizationCode(String authorizationCode) {
            this.authorizationCode = authorizationCode;
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

        public Builder setReserveReference(String reserveReference) {
            this.reserveReference = reserveReference;
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

        public Builder setReceipts(@NonNull List<Receipt> receipts) {
            this.receipts = receipts;
            return this;
        }

        public Builder setCardIssuingCountry(String cardIssuingCountry) {
            this.cardIssuingCountry = cardIssuingCountry;
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

        public Builder setAmountTip(String amountTip) {
            this.amountTip = amountTip;
            return this;
        }

        public Builder setPanToken(String panToken) {
            this.panToken = panToken;
            return this;
        }

        public Transaction getTransaction() {
            return transaction;
        }

        public State getState() {
            return state;
        }

        public ResultCode getResultCode() {
            return resultCode;
        }

        public String getAuthorizationCode() {
            return authorizationCode;
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

        public String getReserveReference() {
            return reserveReference;
        }

        public String getAcquirerId() {
            return acquirerId;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public List<Receipt> getReceipts() {
            return receipts;
        }

        public String getCardIssuingCountry() {
            return cardIssuingCountry;
        }

        public String getCardAppLabel() {
            return cardAppLabel;
        }

        public String getCardAppId() {
            return cardAppId;
        }

        public String getAmountTip() {
            return amountTip;
        }

        public String getPanToken() { return panToken; }

        public TransactionResponse build() {
            return new TransactionResponse(this.transaction, this.state, this.resultCode, this.authorizationCode, this.terminalId, this.sequenceCount, this.transactionTime, this.reserveReference, this.acquirerId, this.receipts, this.cardNumber, this.cardIssuingCountry, this.cardAppLabel, this.cardAppId, this.amountTip, this.panToken);
        }
    }
}
