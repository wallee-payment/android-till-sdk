package com.wallee.android.till.sdk.data;

public enum TransactionProcessingBehavior {
    /**
     * Complete the transaction immediately.
     */
    COMPLETE_IMMEDIATELY,

    /**
     * Reserve the transaction.
     */
    RESERVE,

    /**
     * Complete deferred.
     */
    COMPLETE_DEFERRED,


    /**
     * Adjust reservation.
     */
    ADJUST_RESERVATION
}
