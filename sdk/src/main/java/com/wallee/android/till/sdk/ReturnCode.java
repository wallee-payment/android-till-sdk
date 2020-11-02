package com.wallee.android.till.sdk;

public enum ReturnCode {
    /**
     * Indicates successful result.
     */
    OK,

    /**
     * Indicates not successful result.
     */
    NOT_PROCESSED,

    /**
     * Indicates when operation was aborted.
     */
    ABORTED,

    /**
     * Error happened during API operation.
     */
    ERROR
}
