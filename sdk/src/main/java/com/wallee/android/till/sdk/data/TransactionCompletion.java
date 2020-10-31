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

    private final Long reserveReference;

    private final Currency currency;

    private final State state;

    private final TransactionCompletionResponse response;

    /**
     * Ctor for Builder
     */
    private TransactionCompletion(@NonNull List<LineItem> lineItems, @NonNull Long reserveReference, @NonNull Currency currency, @NonNull State state, @Nullable TransactionCompletionResponse response) {
        this.lineItems = Collections.unmodifiableList(new ArrayList<>(requireNonNull(lineItems, "lineItems")));
        this.reserveReference = requireNonNull(reserveReference, "reserveReference");
        this.currency = requireNonNull(currency, "currency");

        // FIXME: For read only properties we need a solution to prevent public modification
        this.state = requireNonNull(state, "state");
        this.response = response;

        if (lineItems.isEmpty()) throw new IllegalArgumentException("At least one lineItem is required!");
        // When we have the currency object we can validate here if the line item amounts are fitting the currency's number of decimal places.
        for (LineItem lineItem : lineItems) {
            if (lineItem.getTotalAmountIncludingTax().scale() > currency.getDefaultFractionDigits()) {
                throw new IllegalArgumentException("The lineItem with id '" + lineItem.getId() + "' has a totalAmountIncludingTax that has more fractional decimals '" + lineItem.getTotalAmountIncludingTax() + "'than the currency " + currency + " default: '" + currency.getDefaultFractionDigits() + "'");
            }
        }
    }

    @NonNull
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    @NonNull
    public Long getReserveReference() {
        return reserveReference;
    }

    @NonNull
    public Currency getCurrency() {
        return currency;
    }

    @NonNull
    public State getState() {
        return state;
    }

    @Nullable
    public TransactionCompletionResponse getResponse() {
        return response;
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
                "\nreserveReference=" + reserveReference;
    }

    public static class Builder {
        private List<LineItem> lineItems;

        private Long reserveReference;

        private Currency currency = Currency.getInstance("CHF");

        private State state = State.PENDING;

        private TransactionCompletionResponse response;

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
            this.state = transaction.state;
            this.response = transaction.response;
        }

        @NonNull
        public List<LineItem> getLineItems() {
            return lineItems;
        }

        @NonNull
        public Builder setLineItems(@NonNull List<LineItem> lineItems) {
            this.lineItems = lineItems;
            return this;
        }

        @NonNull
        public Builder setReserveReference(@NonNull Long reserveReference) {
            this.reserveReference = reserveReference;
            return this;
        }

        @NonNull
        public Builder setCurrency(@NonNull Currency currency) {
            this.currency = currency;
            return this;
        }

        @NonNull
        public Builder setState(@NonNull State state) {
            this.state = state;
            return this;
        }

        @NonNull
        public Builder setResponse(@Nullable TransactionCompletionResponse response) {
            this.response = response;
            return this;
        }

        @NonNull
        public TransactionCompletion build() {
            return new TransactionCompletion(this.lineItems, this.reserveReference, this.currency, this.state, this.response);
        }
    }
}
