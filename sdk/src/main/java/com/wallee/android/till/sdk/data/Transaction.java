package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wallee.android.till.sdk.data.Utils.checkAscii;
import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 *
 */
public final class Transaction {
    private final List<LineItem> lineItems;

    private final String merchantReference;
    private final String invoiceReference;

    private final String customerId;
    private final Currency currency;

    private final String customerEmailAddress;
    private final Address billingAddress;
    private final Address shippingAddress;

    private final Long tokenId;
    private final State state;
    private final TransactionCompletionBehavior transactionCompletionBehavior;

    private final String resultCode;
    private final String authorizationCode;
    private final String terminalId;
    private final Long sequenceCount;
    private final String transactionTime;
    private final Long deferredReference;

    private final Map<String, String> metaData;

    /**
     * Ctor for Builder
     */
    private Transaction(@NonNull List<LineItem> lineItems, String merchantReference, String invoiceReference, String customerId, Currency currency, String customerEmailAddress, Address billingAddress, Address shippingAddress, Long tokenId, TransactionCompletionBehavior transactionCompletionBehavior, State state, String resultCode, String authorizationCode, String terminalId, Long sequenceCount, String transactionTime, Long deferredReference, Map<String, String> metaData) {
        this.lineItems = Collections.unmodifiableList(new ArrayList<>(requireNonNull(lineItems, "lineItems")));
        this.merchantReference = checkAscii(merchantReference, "merchantReference", 100);
        this.invoiceReference = checkAscii(invoiceReference, "invoiceReference", 100);
        this.customerId = customerId;
        this.currency = currency;
        this.customerEmailAddress = customerEmailAddress;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.tokenId = tokenId;
        this.transactionCompletionBehavior = requireNonNull(transactionCompletionBehavior, "transactionCompletionBehavior");
        this.metaData = Collections.unmodifiableMap(new HashMap<>(requireNonNull(metaData,"metaData")));

        // FIXME: For read only properties we need a solution to prevent public modification
        this.state = requireNonNull(state, "state");
        this.resultCode = resultCode;
        this.authorizationCode = authorizationCode;
        this.terminalId = terminalId;
        this.sequenceCount = sequenceCount;
        this.transactionTime = transactionTime;
        this.deferredReference = deferredReference;

        if (lineItems.isEmpty()) throw new IllegalArgumentException("At least one lineItem is required!");
        // When we have the currency object we can validate here if the line item amounts are fitting the currency's number of decimal places.
        for (LineItem lineItem : lineItems) {
            if (lineItem.getTotalAmountIncludingTax().scale() > currency.getDefaultFractionDigits()) {
                throw new IllegalArgumentException("The lineItem with id '" + lineItem.getId() + "' has a totalAmountIncludingTax that has more fractional decimals '" + lineItem.getTotalAmountIncludingTax() + "'than the currency " + currency + " default: '" + currency.getDefaultFractionDigits() + "'");
            }
        }
    }

    public Currency getCurrency() {
        return currency;
    }
    public Long getTokenId() {
        return tokenId;
    }

    public String getMerchantReference() {
        return merchantReference;
    }

    public String getInvoiceReference() {
        return invoiceReference;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public State getState() {
        return state;
    }

    public TransactionCompletionBehavior getTransactionCompletionBehavior() {
        return transactionCompletionBehavior;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
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

    public Long getDeferredReference() {
        return deferredReference;
    }

    public Map<String, String> getMetaData() {
        return metaData;
    }

    public BigDecimal getTotalAmountIncludingTax() {
        BigDecimal result = BigDecimal.ZERO;
        for (LineItem item : this.lineItems) {
            result = result.add(item.getTotalAmountIncludingTax());
        }
        return result;
    }

    public Date getParsedTransactionTime() throws ParseException {
        return Utils.parseTime(transactionTime, "transactionTime");
    }

    @NonNull
    @Override
    public String toString() {
        return getTotalAmountIncludingTax() + " " + getCurrency() +
                "\nmerchantRef=" + merchantReference +
                "\ninvoiceRef=" + invoiceReference;
    }

    public static class Builder {
        private List<LineItem> lineItems;

        private String merchantReference = "";
        private String invoiceReference;

        private String customerId;
        private Currency currency = Currency.getInstance("CHF");

        private String customerEmailAddress;
        private Address billingAddress;
        private Address shippingAddress;

        private Long tokenId;
        private State state = State.PENDING;
        private TransactionCompletionBehavior transactionCompletionBehavior = TransactionCompletionBehavior.COMPLETE_IMMEDIATELY;

        private String resultCode;
        private String authorizationCode;
        private String terminalId;
        private Long sequenceCount;
        private String transactionTime;
        private Long deferredReference;

        private Map<String, String> metaData = new HashMap<>();

        public Builder(List<LineItem> lineItems) {
            this.lineItems = lineItems;
        }

        /**
         * Copy ctor
         * @param transaction
         */
        public Builder(Transaction transaction) {
            this.lineItems = new ArrayList<>(transaction.lineItems);
            this.merchantReference = transaction.merchantReference;
            this.invoiceReference = transaction.invoiceReference;
            this.customerId = transaction.customerId;
            this.currency = transaction.currency;
            this.customerEmailAddress = transaction.customerEmailAddress;
            this.billingAddress = transaction.billingAddress;
            this.shippingAddress = transaction.shippingAddress;
            this.tokenId = transaction.tokenId;
            this.state = transaction.state;
            this.transactionCompletionBehavior = transaction.transactionCompletionBehavior;
            this.resultCode = transaction.resultCode;
            this.authorizationCode = transaction.authorizationCode;
            this.terminalId = transaction.terminalId;
            this.sequenceCount = transaction.sequenceCount;
            this.transactionTime = transaction.transactionTime;
            this.deferredReference = transaction.deferredReference;
            this.metaData = new HashMap<>(transaction.metaData);
        }

        public List<LineItem> getLineItems() {
            return lineItems;
        }

        public Address getBillingAddress() {
            return billingAddress;
        }

        public Address getShippingAddress() {
            return shippingAddress;
        }

        public Map<String, String> getMetaData() {
            return metaData;
        }

        public Builder setLineItems(List<LineItem> lineItems) {
            this.lineItems = lineItems;
            return this;
        }

        public Builder setMerchantReference(String merchantReference) {
            this.merchantReference = merchantReference;
            return this;
        }

        public Builder setInvoiceReference(String invoiceReference) {
            this.invoiceReference = invoiceReference;
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

        public Builder setShippingAddress(Address shippingAddress) {
            this.shippingAddress = shippingAddress;
            return this;
        }

        public Builder setTokenId(Long tokenId) {
            this.tokenId = tokenId;
            return this;
        }

        protected Builder setState(State state) {
            this.state = state;
            return this;
        }

        public Builder setTransactionCompletionBehavior(TransactionCompletionBehavior transactionCompletionBehavior) {
            this.transactionCompletionBehavior = transactionCompletionBehavior;
            return this;
        }

        public Builder setResultCode(String resultCode) {
            this.resultCode = resultCode;
            return this;
        }

        public Builder setAuthorizationCode(String authorizationCode) {
            this.authorizationCode = authorizationCode;
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

        public Builder setDeferredReference(Long deferredReference) {
            this.deferredReference = deferredReference;
            return this;
        }

        public Builder putMetaData(String key, String value) {
            this.metaData.put(key, value);
            return this;
        }

        public Transaction build() {
            Transaction transaction = new Transaction(this.lineItems, this.merchantReference, this.invoiceReference, this.customerId, this.currency, this.customerEmailAddress, this.billingAddress, this.shippingAddress, this.tokenId, this.transactionCompletionBehavior, this.state, this.resultCode, this.authorizationCode, this.terminalId, this.sequenceCount, this.transactionTime, this.deferredReference, this.metaData);
            return transaction;
        }
    }
}
