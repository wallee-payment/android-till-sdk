package com.wallee.android.till.sdk.data;

public enum TransactionCompletionBehavior {
    /**
     * Complete the transaction immediately.
     */
    COMPLETE_IMMEDIATELY,

    /**
     * Reserve the transaction.
     */
    COMPLETE_DEFERRED
}
