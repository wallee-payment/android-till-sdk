package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
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
    private final String failureReason;
    private final TransactionCompletionBehavior transactionCompletionBehavior;

    private final Map<String, String> metaData;

    /**
     * Ctor for Builder
     */
    private Transaction(@NonNull List<LineItem> lineItems, String merchantReference, String invoiceReference, String customerId, Currency currency, String customerEmailAddress, Address billingAddress, Address shippingAddress, Long tokenId, TransactionCompletionBehavior transactionCompletionBehavior, State state, String failureReason, Map<String, String> metaData) {
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

        // FIXME: For read only properties we need a solution to prevent public modification (state and failureReason).
        this.state = requireNonNull(state, "state");
        this.failureReason = failureReason;

        if (lineItems.isEmpty()) throw new IllegalArgumentException("At least one lineItem is required!");
        // When we have the currency object we can validate here if the line item amounts are fitting the currency's number of decimal places.
        for (LineItem lineItem : lineItems) {
            if (lineItem.getTotalAmountIncludingTax().scale() > currency.getDefaultFractionDigits()) {
                throw new IllegalArgumentException("The lineItem with id '" + lineItem.getId() + "' has a totalAmountIncludingTax that has more fractional decimals '" + lineItem.getTotalAmountIncludingTax() + "'than the currency " + currency + " default: '" + currency.getDefaultFractionDigits() + "'");
            }
        }
    }

    public Currency getCurrency() {
        return this.currency;
    }
    public Long getTokenId() {
        return this.tokenId;
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

    public String getFailureReason() {
        return failureReason;
    }

    public TransactionCompletionBehavior getTransactionCompletionBehavior() {
        return transactionCompletionBehavior;
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
        private String failureReason;
        private TransactionCompletionBehavior transactionCompletionBehavior = TransactionCompletionBehavior.COMPLETE_IMMEDIATELY;

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
            this.customerEmailAddress = transaction.customerEmailAddress;
            this.billingAddress = transaction.billingAddress;
            this.shippingAddress = transaction.shippingAddress;
            this.tokenId = transaction.tokenId;
            this.state = transaction.state;
            this.failureReason = transaction.failureReason;
            this.transactionCompletionBehavior = transaction.transactionCompletionBehavior;
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

        protected Builder setFailureReason(String failureReason) {
            this.failureReason = failureReason;
            return this;
        }

        public Builder setTransactionCompletionBehavior(TransactionCompletionBehavior transactionCompletionBehavior) {
            this.transactionCompletionBehavior = transactionCompletionBehavior;
            return this;
        }

        public Builder putMetaData(String key, String value) {
            this.metaData.put(key, value);
            return this;
        }

        public Transaction build() {
            Transaction transaction = new Transaction(this.lineItems, this.merchantReference, this.invoiceReference, this.customerId, currency, this.customerEmailAddress, this.billingAddress, this.shippingAddress, this.tokenId, this.transactionCompletionBehavior, this.state, this.failureReason, this.metaData);
            return transaction;
        }
    }
}
