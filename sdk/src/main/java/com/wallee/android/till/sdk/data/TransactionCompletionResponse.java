package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 * The transaction completion response data from {@link com.wallee.android.till.sdk.ApiClient#completeTransaction(TransactionCompletion)} API call.
 */
public final class TransactionCompletionResponse {
    private final TransactionCompletion transactionCompletion;

    private final State state;
    private final ResultCode resultCode;
    private final String authorizationCode;
    private final String terminalId;
    private final Long sequenceCount;
    private final String transactionTime;
    private final List<Receipt> receipts;

    public TransactionCompletionResponse(@NonNull TransactionCompletion transactionCompletion, @NonNull State state, @NonNull ResultCode resultCode, @Nullable String authorizationCode, @Nullable String terminalId, @Nullable Long sequenceCount, @Nullable String transactionTime, @NonNull List<Receipt> receipts) {
        this.transactionCompletion = requireNonNull(transactionCompletion, "transactionCompletion");
        this.state = requireNonNull(state, "state");
        this.resultCode = requireNonNull(resultCode, "resultCode");
        this.authorizationCode = authorizationCode;
        this.terminalId = terminalId;
        this.sequenceCount = sequenceCount;
        this.transactionTime = transactionTime;
        this.receipts = requireNonNull(receipts, "receipts");
    }

    @NonNull
    public TransactionCompletion getTransactionCompletion() {
        return transactionCompletion;
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

    @NonNull
    public List<Receipt> getReceipts() {
        return receipts;
    }

    public Date getParsedTransactionTime() throws ParseException {
        return Utils.parseTime(transactionTime, "transactionTime");
    }
}
