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
    private final Long reserveReference;
    private final String acquirerId;
    private final String cardNumber;
    private final List<Receipt> receipts;
    private String cardIssuingCountry;
    private String cardAppLabel;
    private String cardAppId;
    private String amountTip;

    public TransactionResponse(@NonNull Transaction transaction, @NonNull State state, @NonNull ResultCode resultCode, @Nullable String authorizationCode, @Nullable String terminalId, @Nullable Long sequenceCount, @Nullable String transactionTime, @Nullable Long reserveReference, @Nullable String acquirerId, @NonNull List<Receipt> receipts) {
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
        this.cardNumber = "";
        this.cardIssuingCountry = "";
    }

    public TransactionResponse(@NonNull Transaction transaction, @NonNull State state, @NonNull ResultCode resultCode, @Nullable String authorizationCode, @Nullable String terminalId, @Nullable Long sequenceCount, @Nullable String transactionTime, @Nullable Long reserveReference, @Nullable String acquirerId, @NonNull List<Receipt> receipts, @Nullable String cardNumber) {
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
        this.cardIssuingCountry = "";
    }

    public TransactionResponse(@NonNull Transaction transaction, @NonNull State state, @NonNull ResultCode resultCode, @Nullable String authorizationCode, @Nullable String terminalId, @Nullable Long sequenceCount, @Nullable String transactionTime, @Nullable Long reserveReference, @Nullable String acquirerId, @NonNull List<Receipt> receipts, @Nullable String cardNumber, @Nullable String cardIssuingCountry) {
        this(transaction, state, resultCode, authorizationCode,terminalId, sequenceCount, transactionTime, reserveReference, acquirerId, receipts, cardNumber);
        this.cardIssuingCountry = cardIssuingCountry;
    }

    public TransactionResponse(@NonNull Transaction transaction, @NonNull State state, @NonNull ResultCode resultCode, @Nullable String authorizationCode, @Nullable String terminalId, @Nullable Long sequenceCount, @Nullable String transactionTime, @Nullable Long reserveReference, @Nullable String acquirerId, @NonNull List<Receipt> receipts,
                               @Nullable String cardNumber, @Nullable String cardIssuingCountry, @Nullable String cardAppLabel,@Nullable String  cardAppId, @Nullable String amountTip) {
        this(transaction, state, resultCode, authorizationCode,terminalId, sequenceCount, transactionTime, reserveReference, acquirerId, receipts, cardNumber, cardIssuingCountry);
        this.cardAppLabel = cardAppLabel;
        this.cardAppId = cardAppId;
        this.amountTip = amountTip;
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
    public Long getReserveReference() {
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
}
