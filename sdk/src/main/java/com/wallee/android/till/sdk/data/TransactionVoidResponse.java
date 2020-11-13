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
    private final Long sequenceCount;
    private final String transactionTime;
    private final List<Receipt> receipts;

    public TransactionVoidResponse(@NonNull TransactionVoid transactionVoid, @NonNull State state, @NonNull ResultCode resultCode, @Nullable String terminalId, @Nullable Long sequenceCount, @Nullable String transactionTime, @NonNull List<Receipt> receipts) {
        this.transactionVoid = requireNonNull(transactionVoid, "transactionVoid");
        this.state = requireNonNull(state, "state");
        this.resultCode = requireNonNull(resultCode, "resultCode");
        this.terminalId = terminalId;
        this.sequenceCount = sequenceCount;
        this.transactionTime = transactionTime;
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
