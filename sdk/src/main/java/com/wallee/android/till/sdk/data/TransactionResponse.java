package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

public final class TransactionResponse {
    private final ResultCode resultCode;
    private final String authorizationCode;
    private final String terminalId;
    private final Long sequenceCount;
    private final String transactionTime;
    private final Long reserveReference;
    private final List<Receipt> receipts;

    public TransactionResponse(@NonNull ResultCode resultCode, @Nullable String authorizationCode, @NonNull String terminalId, @Nullable Long sequenceCount, @NonNull String transactionTime, @Nullable Long reserveReference, @Nullable List<Receipt> receipts) {
        this.resultCode = requireNonNull(resultCode, "resultCode");
        this.authorizationCode = authorizationCode;
        this.terminalId = requireNonNull(terminalId, "terminalId");
        this.sequenceCount = sequenceCount;
        this.transactionTime = requireNonNull(transactionTime, "transactionTime");
        this.reserveReference = reserveReference;
        this.receipts = receipts;
    }

    @NonNull
    public ResultCode getResultCode() {
        return resultCode;
    }

    @Nullable
    public String getAuthorizationCode() {
        return authorizationCode;
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
    public Long getReserveReference() {
        return reserveReference;
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
