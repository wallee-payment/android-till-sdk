package com.wallee.android.till.sdk.data;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public final class TransactionCompletionResponse {
    private final ResultCode resultCode;
    private final String authorizationCode;
    private final String terminalId;
    private final Long sequenceCount;
    private final String transactionTime;
    private final List<Receipt> receipts;

    public TransactionCompletionResponse(ResultCode resultCode, String authorizationCode, String terminalId, Long sequenceCount, String transactionTime, List<Receipt> receipts) {
        this.resultCode = resultCode;
        this.authorizationCode = authorizationCode;
        this.terminalId = terminalId;
        this.sequenceCount = sequenceCount;
        this.transactionTime = transactionTime;
        this.receipts = receipts;
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

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public Date getParsedTransactionTime() throws ParseException {
        return Utils.parseTime(transactionTime, "transactionTime");
    }
}
