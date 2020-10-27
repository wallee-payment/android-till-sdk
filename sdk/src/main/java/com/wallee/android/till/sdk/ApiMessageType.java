package com.wallee.android.till.sdk;

/**
 * The type of message being sent.
 */
public enum ApiMessageType {
    AUTHORIZE_TRANSACTION,
    COMPLETE_TRANSACTION,
    CANCEL_LAST_TRANSACTION,
    VOID_RESERVATION
}
