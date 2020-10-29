package com.wallee.android.till.sdk;

/**
 * The type of message being sent.
 */
public enum ApiMessageType {
    AUTHORIZE_TRANSACTION,
    COMPLETE_TRANSACTION,
    CANCEL_LAST_TRANSACTION_OPERATION,
    VOID_RESERVATION,
    EXECUTE_SUBMISSION,
    EXECUTE_TRANSMISSION,
    EXECUTE_FINAL_BALANCE,
}
