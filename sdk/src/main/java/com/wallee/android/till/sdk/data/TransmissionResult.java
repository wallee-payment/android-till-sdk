package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

import com.wallee.android.till.sdk.ApiClient;

import java.util.List;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 * The transmission result data for {@link ApiClient#executeTransmission()} API call.
 */
public final class TransmissionResult {
    private final ResultCode resultCode;
    private final List<Receipt> receipts;

    public TransmissionResult(@NonNull ResultCode resultCode, @NonNull List<Receipt> receipts) {
        this.resultCode = requireNonNull(resultCode, "resultCode");
        this.receipts = requireNonNull(receipts, "receipts");
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
