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
 * The transaction data for {@link com.wallee.android.till.sdk.ApiClient#authorizeTransaction(Transaction)} API call.
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

    private final TransactionProcessingBehavior transactionProcessingBehavior;

    private final Map<String, String> metaData;

    private final String customText;

    private final String language;

    /**
     * Ctor for Builder
     */
    private Transaction(@NonNull List<LineItem> lineItems, String merchantReference, String invoiceReference, String customerId, Currency currency, String customerEmailAddress, Address billingAddress, Address shippingAddress, TransactionProcessingBehavior transactionProcessingBehavior, Map<String, String> metaData, String customText, String language) {
        this.lineItems = Collections.unmodifiableList(new ArrayList<>(requireNonNull(lineItems, "lineItems")));
        this.merchantReference = checkAscii(merchantReference, "merchantReference", 100);
        this.invoiceReference = checkAscii(invoiceReference, "invoiceReference", 100);
        this.customerId = customerId;
        this.currency = currency;
        this.customerEmailAddress = customerEmailAddress;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.transactionProcessingBehavior = requireNonNull(transactionProcessingBehavior, "transactionCompletionBehavior");
        this.metaData = Collections.unmodifiableMap(new HashMap<>(requireNonNull(metaData,"metaData")));
        this.customText = customText;
        if (lineItems.isEmpty()) throw new IllegalArgumentException("At least one lineItem is required!");
        // When we have the currency object we can validate here if the line item amounts are fitting the currency's number of decimal places.
        for (LineItem lineItem : lineItems) {
            if (lineItem.getTotalAmountIncludingTax().scale() > currency.getDefaultFractionDigits()) {
                throw new IllegalArgumentException("The lineItem with id '" + lineItem.getId() + "' has a totalAmountIncludingTax that has more fractional decimals '" + lineItem.getTotalAmountIncludingTax() + "'than the currency " + currency + " default: '" + currency.getDefaultFractionDigits() + "'");
            }
        }
        this.language = language;
    }

    public Currency getCurrency() {
        return currency;
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

    public TransactionProcessingBehavior getTransactionProcessingBehavior() {
        return transactionProcessingBehavior;
    }

    public Map<String, String> getMetaData() {
        return metaData;
    }

    public String getCustomText() { return customText; }

    public String getLanguage() {
        return language;
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

        private TransactionProcessingBehavior transactionProcessingBehavior = TransactionProcessingBehavior.COMPLETE_IMMEDIATELY;

        private Map<String, String> metaData = new HashMap<>();

        private String customText;

        private String language;

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
            this.transactionProcessingBehavior = transaction.transactionProcessingBehavior;
            this.customText = transaction.customText;
            this.language = transaction.language;
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

        public String getCustomText() { return customText; }

        public String getLanguage() {
            return language;
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

        public Builder setTransactionProcessingBehavior(TransactionProcessingBehavior transactionProcessingBehavior) {
            this.transactionProcessingBehavior = transactionProcessingBehavior;
            return this;
        }

        public Builder putMetaData(String key, String value) {
            this.metaData.put(key, value);
            return this;
        }

        public Builder setCustomText(String customText) {
            this.customText = customText;
            return this;
        }

        public Builder setLanguage(String language) {
            this.language = language;
            return this;
        }

        public Transaction build() {
            return new Transaction(this.lineItems, this.merchantReference, this.invoiceReference, this.customerId, this.currency, this.customerEmailAddress, this.billingAddress, this.shippingAddress, this.transactionProcessingBehavior, this.metaData, this.customText, this.language);
        }
    }
}
