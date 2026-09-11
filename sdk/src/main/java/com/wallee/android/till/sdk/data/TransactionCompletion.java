package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;
/**
 * The transaction completion data for {@link com.wallee.android.till.sdk.ApiClient#completeTransaction(TransactionCompletion)} API call.
 */
public final class TransactionCompletion {
    private final List<LineItem> lineItems;
    private final String reserveReference;
    private final Currency currency;
    private final Integer transactionSyncNumber;
    private final AccessibilityMode accessMode;

    /**
     * Ctor for Builder
     */
    private TransactionCompletion(@NonNull List<LineItem> lineItems, @NonNull String reserveReference, @NonNull Currency currency, @Nullable Integer transactionSyncNumber, AccessibilityMode accessMode) {
        this.lineItems = Collections.unmodifiableList(new ArrayList<>(requireNonNull(lineItems, "lineItems")));
        this.reserveReference = requireNonNull(reserveReference, "reserveReference");
        this.currency = requireNonNull(currency, "currency");
        this.transactionSyncNumber = transactionSyncNumber;
        this.accessMode = accessMode == null ? AccessibilityMode.DISABLED : accessMode;
        if (lineItems.isEmpty()) throw new IllegalArgumentException("At least one lineItem is required!");
        // When we have the currency object we can validate here if the line item amounts are fitting the currency's number of decimal places.
        for (LineItem lineItem : lineItems) {
            if (lineItem.getTotalAmountIncludingTax().scale() > currency.getDefaultFractionDigits()) {
                throw new IllegalArgumentException("The lineItem with id '" + lineItem.getId() + "' has a totalAmountIncludingTax that has more fractional decimals '" + lineItem.getTotalAmountIncludingTax() + "' than the currency " + currency + " default: '" + currency.getDefaultFractionDigits() + "'");
            }
        }
    }

    @NonNull
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    @NonNull
    public String getReserveReference() {
        return reserveReference;
    }

    @NonNull
    public Currency getCurrency() {
        return currency;
    }

    public Integer getTransactionSyncNumber() {
        return transactionSyncNumber;
    }

    /**
     * Returns the accessibility interface requested for this completion transaction.
     *
     * @return the requested mode, or {@link AccessibilityMode#DISABLED} when absent or invalid.
     */
    @NonNull
    public AccessibilityMode getAccessMode() {
        return accessMode == null ? AccessibilityMode.DISABLED : accessMode;
    }


    @NonNull
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
                "\nreserveReference=" + reserveReference +
                "\ntransactionSyncNumber=" + transactionSyncNumber;
    }

    public static class Builder {
        private List<LineItem> lineItems;
        private String reserveReference;
        private Currency currency = Currency.getInstance("CHF");
        private Integer transactionSyncNumber;
        private AccessibilityMode accessMode = AccessibilityMode.DISABLED;

        public Builder(@NonNull List<LineItem> lineItems) {
            this.lineItems = lineItems;
        }
        /**
         * Copy ctor
         * @param transaction
         */
        public Builder(@NonNull TransactionCompletion transaction) {
            this.lineItems = new ArrayList<>(transaction.lineItems);
            this.reserveReference = transaction.reserveReference;
            this.currency = transaction.currency;
            this.transactionSyncNumber = transaction.transactionSyncNumber;
            this.accessMode = transaction.getAccessMode();
        }

        @NonNull
        public Builder setLineItems(@NonNull List<LineItem> lineItems) {
            this.lineItems = lineItems;
            return this;
        }

        @NonNull
        public Builder setReserveReference(@NonNull String reserveReference) {
            this.reserveReference = reserveReference;
            return this;
        }

        @NonNull
        public Builder setCurrency(@NonNull Currency currency) {
            this.currency = currency;
            return this;
        }

        @NonNull
        public Builder setTransactionSyncNumber(Integer transactionSyncNumber) {
            this.transactionSyncNumber = transactionSyncNumber;
            return this;
        }

        /**
         * Requests an accessibility interface for this completion transaction. The mode is not
         * inherited from the original reservation and requires terminal support.
         *
         * @param accessMode the requested mode, or {@code null} for the standard interface.
         * @return this builder.
         */
        @NonNull
        public Builder setAccessMode(@Nullable AccessibilityMode accessMode) {
            this.accessMode = accessMode == null ? AccessibilityMode.DISABLED : accessMode;
            return this;
        }

        @NonNull
        public TransactionCompletion build() {
            return new TransactionCompletion(this.lineItems, this.reserveReference, this.currency, this.transactionSyncNumber, this.accessMode);
        }
    }
}
