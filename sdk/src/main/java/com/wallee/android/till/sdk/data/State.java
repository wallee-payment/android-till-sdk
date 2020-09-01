package com.wallee.android.till.sdk.data;

public enum State {
    /**
     * The transaction has been created but can still be modified.
     */
    PENDING,

    /**
     * The transaction is being processed and can no longer be modified.
     */
    PROCESSING,

    /**
     * The transaction was completed successfully.
     */
    SUCCESSFUL,

    /**
     * The transaction failed. See also Transaction.failureReason.
     */
    FAILED
}
