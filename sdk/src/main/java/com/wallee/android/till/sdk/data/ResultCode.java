package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

public class ResultCode {
    private final Long code;
    private final String description;

    public ResultCode(@NonNull Long code, @NonNull String description) {
        this.code = requireNonNull(code, "code");
        this.description = requireNonNull(description, "description");
    }

    @NonNull
    public Long getCode() {
        return code;
    }

    @NonNull
    public String getDescription() {
        return description;
    }
}
