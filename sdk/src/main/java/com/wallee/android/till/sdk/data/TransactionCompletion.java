package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

/**
 *
 */
public final class TransactionCompletion {
    private final List<LineItem> lineItems;

    private final Long deferredReference;

    private final Currency currency;

    private final State state;

    private final String resultCode;
    private final String authorizationCode;
    private final String terminalId;
    private final Long sequenceCount;
    private final String transactionTime;

    /**
     * Ctor for Builder
     */
    private TransactionCompletion(@NonNull List<LineItem> lineItems, Long deferredReference, Currency currency, State state, String resultCode, String authorizationCode, String terminalId, Long sequenceCount, String transactionTime) {
        this.lineItems = Collections.unmodifiableList(new ArrayList<>(requireNonNull(lineItems, "lineItems")));
        this.deferredReference = deferredReference;
        this.currency = currency;

        // FIXME: For read only properties we need a solution to prevent public modification
        this.state = requireNonNull(state, "state");
        this.resultCode = resultCode;
        this.authorizationCode = authorizationCode;
        this.terminalId = terminalId;
        this.sequenceCount = sequenceCount;
        this.transactionTime = transactionTime;

        if (lineItems.isEmpty()) throw new IllegalArgumentException("At least one lineItem is required!");
        // When we have the currency object we can validate here if the line item amounts are fitting the currency's number of decimal places.
        for (LineItem lineItem : lineItems) {
            if (lineItem.getTotalAmountIncludingTax().scale() > currency.getDefaultFractionDigits()) {
                throw new IllegalArgumentException("The lineItem with id '" + lineItem.getId() + "' has a totalAmountIncludingTax that has more fractional decimals '" + lineItem.getTotalAmountIncludingTax() + "'than the currency " + currency + " default: '" + currency.getDefaultFractionDigits() + "'");
            }
        }
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public Long getDeferredReference() {
        return deferredReference;
    }

    public Currency getCurrency() {
        return currency;
    }

    public State getState() {
        return state;
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
                "\ndeferredReference=" + deferredReference;
    }

    public static class Builder {
        private List<LineItem> lineItems;

        private Long deferredReference;

        private Currency currency = Currency.getInstance("CHF");

        private State state = State.PENDING;

        private String resultCode;
        private String authorizationCode;
        private String terminalId;
        private Long sequenceCount;
        private String transactionTime;

        public Builder(List<LineItem> lineItems) {
            this.lineItems = lineItems;
        }

        /**
         * Copy ctor
         * @param transaction
         */
        public Builder(TransactionCompletion transaction) {
            this.lineItems = new ArrayList<>(transaction.lineItems);
            this.deferredReference = transaction.deferredReference;
            this.currency = transaction.currency;
            this.state = transaction.state;
            this.resultCode = transaction.resultCode;
            this.authorizationCode = transaction.authorizationCode;
            this.terminalId = transaction.terminalId;
            this.sequenceCount = transaction.sequenceCount;
            this.transactionTime = transaction.transactionTime;
        }

        public List<LineItem> getLineItems() {
            return lineItems;
        }

        public Builder setLineItems(List<LineItem> lineItems) {
            this.lineItems = lineItems;
            return this;
        }

        public Builder setDeferredReference(Long deferredReference) {
            this.deferredReference = deferredReference;
            return this;
        }

        public Builder setCurrency(Currency currency) {
            this.currency = currency;
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

        public TransactionCompletion build() {
            TransactionCompletion transaction = new TransactionCompletion(this.lineItems, this.deferredReference, this.currency, this.state, this.resultCode, this.authorizationCode, this.terminalId, this.sequenceCount, this.transactionTime);
            return transaction;
        }
    }
}
