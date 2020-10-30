package com.wallee.android.till.sdk.data;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public final class CancelationResponse {
    private final ResultCode resultCode;
    private final String terminalId;
    private final Long sequenceCount;
    private final Long cancelledSequenceCount;
    private final String transactionTime;
    private final List<Receipt> receipts;

    public CancelationResponse(ResultCode resultCode, String terminalId, Long sequenceCount, Long cancelledSequenceCount, String transactionTime, List<Receipt> receipts) {
        this.resultCode = resultCode;
        this.terminalId = terminalId;
        this.sequenceCount = sequenceCount;
        this.cancelledSequenceCount = cancelledSequenceCount;
        this.transactionTime = transactionTime;
        this.receipts = receipts;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public Long getSequenceCount() {
        return sequenceCount;
    }

    public Long getCancelledSequenceCount() {
        return cancelledSequenceCount;
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
