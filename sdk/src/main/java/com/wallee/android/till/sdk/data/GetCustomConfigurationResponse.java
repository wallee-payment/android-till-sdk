package com.wallee.android.till.sdk.data;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

import androidx.annotation.NonNull;

public class GetCustomConfigurationResponse {
    private final State state;
    private final ResultCode resultCode;
    private final String customConfiguration;

    public GetCustomConfigurationResponse(@NonNull State state, @NonNull ResultCode resultCode, String customConfiguration) {
        this.state = requireNonNull(state, "state");
        this.resultCode = requireNonNull(resultCode, "resultCode");
        this.customConfiguration = customConfiguration;
    }

    @NonNull
    public State getState(){
        return state;
    }

    @NonNull
    public ResultCode getResultCode(){
        return resultCode;
    }

    public String getCustomConfiguration() {
        return customConfiguration;
    }
}