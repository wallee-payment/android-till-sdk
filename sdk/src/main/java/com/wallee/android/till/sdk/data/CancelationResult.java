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
