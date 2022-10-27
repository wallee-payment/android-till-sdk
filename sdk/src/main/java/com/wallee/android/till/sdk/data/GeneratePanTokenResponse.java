package com.wallee.android.till.sdk.data;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

import androidx.annotation.NonNull;

public class GeneratePanTokenResponse {
    private final State state;
    private final ResultCode resultCode;
    private final String panToken;

    public GeneratePanTokenResponse(@NonNull State state, @NonNull ResultCode resultCode, String panToken) {
        this.state = requireNonNull(state, "state");
        this.resultCode = requireNonNull(resultCode, "resultCode");
        this.panToken = panToken;
    }

    @NonNull
    public State getState() {
        return state;
    }

    @NonNull
    public ResultCode getResultCode() {
        return resultCode;
    }

    public String getPanToken() {
        return panToken;
    }
}
