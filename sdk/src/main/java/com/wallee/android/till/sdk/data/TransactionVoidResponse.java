package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

public final class TransactionVoidResponse {
    private final ResultCode resultCode;
    private final String terminalId;
    private final Long sequenceCount;
    private final String transactionTime;
    private final List<Receipt> receipts;

    public TransactionVoidResponse(@NonNull ResultCode resultCode, @NonNull String terminalId, @Nullable Long sequenceCount, @NonNull String transactionTime, @Nullable List<Receipt> receipts) {
        this.resultCode = requireNonNull(resultCode, "resultCode");
        this.terminalId = requireNonNull(terminalId, "terminalId");
        this.sequenceCount = sequenceCount;
        this.transactionTime = requireNonNull(transactionTime, "transactionTime");
        this.receipts = receipts;
    }

    @NonNull
    public ResultCode getResultCode() {
        return resultCode;
    }

    @NonNull
    public String getTerminalId() {
        return terminalId;
    }

    @Nullable
    public Long getSequenceCount() {
        return sequenceCount;
    }

    @NonNull
    public String getTransactionTime() {
        return transactionTime;
    }

    @Nullable
    public List<Receipt> getReceipts() {
        return receipts;
    }

    @NonNull
    public Date getParsedTransactionTime() throws ParseException {
        return Utils.parseTime(transactionTime, "transactionTime");
    }
}
