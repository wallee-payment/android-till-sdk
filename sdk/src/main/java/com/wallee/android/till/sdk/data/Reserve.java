package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Currency;
import java.util.Date;

import static com.wallee.android.till.sdk.data.Utils.checkAscii;
import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 *
 */
public final class Reserve {

    private final BigDecimal amount;

    private final String merchantReference;

    private final String customerId;
    private final Currency currency;

    private final String customerEmailAddress;
    private final Address billingAddress;

    private final Long tokenId;
    private final State state;

    private final String resultCode;
    private final String terminalId;
    private final Long sequenceCount;
    private final String transactionTime;
    private final Long reserveReference;

    /**
     * Ctor for Builder
     */
    private Reserve(@NonNull BigDecimal amount, String merchantReference, String customerId, Currency currency, String customerEmailAddress, Address billingAddress, Long tokenId, State state, String resultCode, String terminalId, Long sequenceCount, String transactionTime, Long reserveReference) {
        this.amount = requireNonNull(amount, "amount");
        this.merchantReference = checkAscii(merchantReference, "merchantReference", 100);
        this.customerId = customerId;
        this.currency = currency;
        this.customerEmailAddress = customerEmailAddress;
        this.billingAddress = billingAddress;
        this.tokenId = tokenId;

        // FIXME: For read only properties we need a solution to prevent public modification
        this.state = requireNonNull(state, "state");
        this.resultCode = resultCode;
        this.terminalId = terminalId;
        this.sequenceCount = sequenceCount;
        this.transactionTime = transactionTime;
        this.reserveReference = reserveReference;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMerchantReference() {
        return merchantReference;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public State getState() {
        return state;
    }

    public String getResultCode() {
        return resultCode;
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

    public Long getReserveReference() {
        return reserveReference;
    }

    public Date getParsedTransactionTime() throws ParseException {
        return Utils.parseTime(transactionTime, "transactionTime");
    }

    @NonNull
    @Override
    public String toString() {
        return amount + " " + getCurrency() +
                "\nmerchantRef=" + merchantReference +
                "\nreserveRef=" + reserveReference;
    }

    public static class Builder {
        private BigDecimal amount;

        private String merchantReference = "";

        private String customerId;
        private Currency currency = Currency.getInstance("CHF");

        private String customerEmailAddress;
        private Address billingAddress;

        private Long tokenId;
        private State state = State.PENDING;

        private String resultCode;
        private String terminalId;
        private Long sequenceCount;
        private String transactionTime;
        private Long reserveReference;

        public Builder(BigDecimal amount) {
            this.amount = amount;
        }

        /**
         * Copy ctor
         * @param reserve
         */
        public Builder(Reserve reserve) {
            this.amount = reserve.amount;
            this.merchantReference = reserve.merchantReference;
            this.customerId = reserve.customerId;
            this.currency = reserve.currency;
            this.customerEmailAddress = reserve.customerEmailAddress;
            this.billingAddress = reserve.billingAddress;
            this.tokenId = reserve.tokenId;
            this.state = reserve.state;
            this.resultCode = reserve.resultCode;
            this.terminalId = reserve.terminalId;
            this.sequenceCount = reserve.sequenceCount;
            this.transactionTime = reserve.transactionTime;
            this.reserveReference = reserve.reserveReference;
        }

        public Builder setAmount(BigDecimal amount) {
            this.amount = amount;
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

        public Builder setCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Builder setCustomerEmailAddress(String customerEmailAddress) {
            this.customerEmailAddress = customerEmailAddress;
            return this;
        }

        public Builder setBillingAddress(Address billingAddress) {
            this.billingAddress = billingAddress;
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

        public Builder setResultCode(String resultCode) {
            this.resultCode = resultCode;
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

        public Builder setReserveReference(Long reserveReference) {
            this.reserveReference = reserveReference;
            return this;
        }

        public Reserve build() {
            Reserve reserve = new Reserve(this.amount, this.merchantReference, this.customerId, this.currency, this.customerEmailAddress, this.billingAddress, this.tokenId, this.state, this.resultCode, this.terminalId, this.sequenceCount, this.transactionTime, this.reserveReference);
            return reserve;
        }
    }
}
