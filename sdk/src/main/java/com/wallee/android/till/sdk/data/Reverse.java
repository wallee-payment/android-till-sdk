package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.util.Date;

import static com.wallee.android.till.sdk.data.Utils.checkAscii;
import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 *
 */
public final class Reverse {

    private final Long sequenceCountToReverse;
    private final Long reserveReferenceToReverse;

    private final String merchantReference;

    private final String customerId;
    private final String customerEmailAddress;

    private final Long tokenId;
    private final State state;
    private final String failureReason;

    private final String authorizationResponseCode;
    private final String terminalId;
    private final Long sequenceCount;
    private final String transactionTime;

    /**
     * Ctor for Builder
     */
    private Reverse(Long sequenceCountToReverse, Long reserveReferenceToReverse, String merchantReference, String customerId, String customerEmailAddress, Long tokenId, State state, String failureReason, String authorizationResponseCode, String terminalId, Long sequenceCount, String transactionTime) {
        this.sequenceCountToReverse = sequenceCountToReverse;
        this.reserveReferenceToReverse = reserveReferenceToReverse;
        this.merchantReference = checkAscii(merchantReference, "merchantReference", 100);
        this.customerId = customerId;
        this.customerEmailAddress = customerEmailAddress;
        this.tokenId = tokenId;

        // FIXME: For read only properties we need a solution to prevent public modification
        this.state = requireNonNull(state, "state");
        this.failureReason = failureReason;
        this.authorizationResponseCode = authorizationResponseCode;
        this.terminalId = terminalId;
        this.sequenceCount = sequenceCount;
        this.transactionTime = transactionTime;
    }

    public Long getSequenceCountToReverse() {
        return sequenceCountToReverse;
    }

    public Long getReserveReferenceToReverse() {
        return reserveReferenceToReverse;
    }

    public String getMerchantReference() {
        return merchantReference;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public State getState() {
        return state;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public String getAuthorizationResponseCode() {
        return authorizationResponseCode;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public Long getSequenceCount() {
        return sequenceCount;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public Date getParsedTransactionTime() throws ParseException {
        return Utils.parseTime(transactionTime, "transactionTime");
    }

    @NonNull
    @Override
    public String toString() {
        return "sequenceCountToReverse=" + sequenceCountToReverse +
                "\nreserveReferenceToReverse=" + reserveReferenceToReverse +
                "\nmerchantRef=" + merchantReference;
    }

    public static class Builder {
        private Long sequenceCountToReverse;
        private Long reserveReferenceToReverse;

        private String merchantReference = "";

        private String customerId;
        private String customerEmailAddress;

        private Long tokenId;
        private State state = State.PENDING;
        private String failureReason;

        private String authorizationResponseCode;
        private String terminalId;
        private Long sequenceCount;
        private String transactionTime;

        public Builder(Long sequenceCountToReverse, Long reserveReferenceToReverse) {
            this.sequenceCountToReverse = sequenceCountToReverse;
            this.reserveReferenceToReverse = reserveReferenceToReverse;
        }

        /**
         * Copy ctor
         * @param reverse
         */
        public Builder(Reverse reverse) {
            this.sequenceCountToReverse = reverse.sequenceCountToReverse;
            this.reserveReferenceToReverse = reverse.reserveReferenceToReverse;
            this.merchantReference = reverse.merchantReference;
            this.customerId = reverse.customerId;
            this.customerEmailAddress = reverse.customerEmailAddress;
            this.tokenId = reverse.tokenId;
            this.state = reverse.state;
            this.failureReason = reverse.failureReason;
            this.authorizationResponseCode = reverse.authorizationResponseCode;
            this.terminalId = reverse.terminalId;
            this.sequenceCount = reverse.sequenceCount;
            this.transactionTime = reverse.transactionTime;
        }

        public Builder setSequenceCountToReverse(Long sequenceCountToReverse) {
            this.sequenceCountToReverse = sequenceCountToReverse;
            return this;
        }

        public Builder setReserveReferenceToReverse(Long reserveReferenceToReverse) {
            this.reserveReferenceToReverse = reserveReferenceToReverse;
            return this;
        }

        public Builder setMerchantReference(String merchantReference) {
            this.merchantReference = merchantReference;
            return this;
        }

        public Builder setCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setCustomerEmailAddress(String customerEmailAddress) {
            this.customerEmailAddress = customerEmailAddress;
            return this;
        }

        public Builder setTokenId(Long tokenId) {
            this.tokenId = tokenId;
            return this;
        }

        public Builder setState(State state) {
            this.state = state;
            return this;
        }

        public Builder setFailureReason(String failureReason) {
            this.failureReason = failureReason;
            return this;
        }

        public Builder setAuthorizationResponseCode(String authorizationResponseCode) {
            this.authorizationResponseCode = authorizationResponseCode;
            return this;
        }

        public Builder setTerminalId(String terminalId) {
            this.terminalId = terminalId;
            return this;
        }

        public Builder setSequenceCount(Long sequenceCount) {
            this.sequenceCount = sequenceCount;
            return this;
        }

        public Builder setTransactionTime(String transactionTime) {
            this.transactionTime = transactionTime;
            return this;
        }

        public Reverse build() {
            Reverse reverse = new Reverse(this.sequenceCountToReverse, this.reserveReferenceToReverse, this.merchantReference, this.customerId, this.customerEmailAddress, this.tokenId, this.state, this.failureReason, this.authorizationResponseCode, this.terminalId, this.sequenceCount, this.transactionTime);
            return reverse;
        }
    }
}
