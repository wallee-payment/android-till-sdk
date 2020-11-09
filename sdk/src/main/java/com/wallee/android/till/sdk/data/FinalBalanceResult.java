package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wallee.android.till.sdk.ApiClient;

import java.util.List;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 * The final balance result data for {@link ApiClient#executeFinalBalance()} API call.
 */
public final class FinalBalanceResult {
    private final State state;
    private final ResultCode resultCode;
    private final List<Receipt> receipts;

    public FinalBalanceResult(@NonNull State state, @NonNull ResultCode resultCode, @Nullable List<Receipt> receipts) {
        this.state = requireNonNull(state, "state");
        this.resultCode = requireNonNull(resultCode, "resultCode");
        this.receipts = receipts;
    }

    @NonNull
    public State getState() {
        return state;
    }

    @NonNull
    public ResultCode getResultCode() {
        return resultCode;
    }

    @Nullable
    public List<Receipt> getReceipts() {
        return receipts;
    }
}
