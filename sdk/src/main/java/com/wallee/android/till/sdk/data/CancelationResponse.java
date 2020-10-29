package com.wallee.android.till.sdk.data;

import java.text.ParseException;
import java.util.Date;

public final class CancelationResponse {
    private final ResultCode resultCode;
    private final String terminalId;
    private final Long sequenceCount;
    private final Long cancelledSequenceCount;
    private final String transactionTime;

    public CancelationResponse(ResultCode resultCode, String terminalId, Long sequenceCount, Long cancelledSequenceCount, String transactionTime) {
        this.resultCode = resultCode;
        this.terminalId = terminalId;
        this.sequenceCount = sequenceCount;
        this.cancelledSequenceCount = cancelledSequenceCount;
        this.transactionTime = transactionTime;
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

    public Date getParsedTransactionTime() throws ParseException {
        return Utils.parseTime(transactionTime, "transactionTime");
    }
}
