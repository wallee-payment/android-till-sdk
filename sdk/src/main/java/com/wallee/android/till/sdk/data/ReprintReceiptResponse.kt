package com.wallee.android.till.sdk.data

class ReprintReceiptResponse (
    val state: State,
    val resultCode: ResultCode,
    val receipts: List<Receipt>,
    val lastTrx: TransactionResponse?,
    val lastRev: CancelationResult?,
    val lastCancelRes: TransactionVoidResponse?
) {
    class Builder(
        private var state: State,
        private var resultCode: ResultCode,
        private var receipts: List<Receipt>
    ) {
        private var lastTrx: TransactionResponse? = null
        private var lastRev: CancelationResult? = null
        private var lastCancelRes: TransactionVoidResponse? = null

        constructor(response: ReprintReceiptResponse): this(
            response.state,
            response.resultCode,
            response.receipts
        ) {
            lastTrx = response.lastTrx
            lastRev = response.lastRev
            lastCancelRes = response.lastCancelRes
        }

        fun setLastTrx(lastTrx: TransactionResponse?) = apply { this.lastTrx = lastTrx }
        fun setLastRev(lastRev: CancelationResult?) = apply { this.lastRev = lastRev }
        fun setLastCancelRes(lastCancelRes: TransactionVoidResponse?) = apply { this.lastCancelRes = lastCancelRes }

        fun build() = ReprintReceiptResponse(
            state,
            resultCode,
            receipts,
            lastTrx,
            lastRev,
            lastCancelRes
        )
    }
}