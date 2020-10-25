package com.wallee.android.till.sdk.data;

import java.text.ParseException;
import java.util.Date;

import static com.wallee.android.till.sdk.data.Utils.checkAscii;
import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 *
 */
public final class Cancellation {

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
    private Cancellation(String merchantReference, String customerId, String customerEmailAddress, Long tokenId, State state, String failureReason, String authorizationResponseCode, String terminalId, Long sequenceCount, String transactionTime) {
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

    public static class Builder {
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

        public Builder() {
        }

        /**
         * Copy ctor
         * @param cancellation
         */
        public Builder(Cancellation cancellation) {
            this.merchantReference = cancellation.merchantReference;
            this.customerId = cancellation.customerId;
            this.customerEmailAddress = cancellation.customerEmailAddress;
            this.tokenId = cancellation.tokenId;
            this.state = cancellation.state;
            this.failureReason = cancellation.failureReason;
            this.authorizationResponseCode = cancellation.authorizationResponseCode;
            this.terminalId = cancellation.terminalId;
            this.sequenceCount = cancellation.sequenceCount;
            this.transactionTime = cancellation.transactionTime;
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

        public Cancellation build() {
            Cancellation cancellation = new Cancellation(this.merchantReference, this.customerId, this.customerEmailAddress, this.tokenId, this.state, this.failureReason, this.authorizationResponseCode, this.terminalId, this.sequenceCount, this.transactionTime);
            return cancellation;
        }
    }
}
