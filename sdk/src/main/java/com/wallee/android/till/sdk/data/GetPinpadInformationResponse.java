package com.wallee.android.till.sdk.data;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

import androidx.annotation.NonNull;

public class GetPinpadInformationResponse {
    private final State state;
    private final ResultCode resultCode;
    private final String terminalId;
    private final String serialNumber;

    public GetPinpadInformationResponse(@NonNull State state, @NonNull ResultCode resultCode, String terminalId, String serialNumber) {
        this.state = requireNonNull(state, "state");
        this.resultCode = requireNonNull(resultCode, "resultCode");
        this.terminalId=terminalId;
        this.serialNumber=serialNumber;

    }
    @NonNull
    public State getState() {
        return state;
    }

    @NonNull
    public ResultCode getResultCode() {
        return resultCode;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
