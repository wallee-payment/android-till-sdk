package com.wallee.android.till.sdk.data;

import java.text.ParseException;
import java.util.Date;

public final class TransactionResponse {
    private final ResultCode resultCode;
    private final String authorizationCode;
    private final String terminalId;
    private final Long sequenceCount;
    private final String transactionTime;
    private final Long reserveReference;

    public TransactionResponse(ResultCode resultCode, String authorizationCode, String terminalId, Long sequenceCount, String transactionTime, Long reserveReference) {
        this.resultCode = resultCode;
        this.authorizationCode = authorizationCode;
        this.terminalId = terminalId;
        this.sequenceCount = sequenceCount;
        this.transactionTime = transactionTime;
        this.reserveReference = reserveReference;
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

    public Long getReserveReference() {
        return reserveReference;
    }

    public Date getParsedTransactionTime() throws ParseException {
        return Utils.parseTime(transactionTime, "transactionTime");
    }
}
