package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wallee.android.till.sdk.ApiClient;

import java.util.List;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 * The submission result data for {@link ApiClient#executeSubmission()} API call.
 */
public final class SubmissionResult {
    private final State state;
    private final ResultCode resultCode;
    private final List<Receipt> receipts;

    public SubmissionResult(@NonNull State state, @NonNull ResultCode resultCode, @NonNull List<Receipt> receipts) {
        this.state = requireNonNull(state, "state");
        this.resultCode = requireNonNull(resultCode, "resultCode");
        this.receipts = requireNonNull(receipts, "receipts");
    }

    @NonNull
    public State getState() {
        return state;
    }

    @NonNull
    public ResultCode getResultCode() {
        return resultCode;
    }

    @NonNull
    public List<Receipt> getReceipts() {
        return receipts;
    }
}
