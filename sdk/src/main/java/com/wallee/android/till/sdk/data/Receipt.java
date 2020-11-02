package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

public class Receipt {
    private final String content;
    private final ReceiptType receiptType;
    private final ReceiptFormat receiptFormat;

    public Receipt(@NonNull String content, @NonNull ReceiptType receiptType, @NonNull ReceiptFormat receiptFormat) {
        this.content = requireNonNull(content, "content");
        this.receiptType = requireNonNull(receiptType, "receiptType");
        this.receiptFormat = requireNonNull(receiptFormat, "receiptFormat");
    }

    @NonNull
    public String getContent() {
        return content;
    }

    @NonNull
    public ReceiptType getReceiptType() {
        return receiptType;
    }

    @NonNull
    public ReceiptFormat getReceiptFormat() {
        return receiptFormat;
    }
}
