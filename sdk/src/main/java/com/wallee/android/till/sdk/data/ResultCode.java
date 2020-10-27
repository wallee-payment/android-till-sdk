package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

public class ResultCode {
    private final Long code;
    private final String description;

    public ResultCode(@NonNull Long code, @NonNull String description) {
        this.code = code;
        this.description = description;
    }

    public Long getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
