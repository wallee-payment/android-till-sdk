package com.wallee.android.till.sdk.data;

import com.google.gson.Gson;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Verifies optional transaction request properties exposed by {@link Transaction}.
 */
public class TransactionTest {

    /**
     * Verifies that an explicit suppression request is retained and serialized for the API service.
     */
    @Test
    public void ifDisplayMessageSuppressionIsEnabledThenTransactionSerializesTheFlag() {
        Transaction transaction = createTransactionBuilder()
                .setDisplayMessageSuppressionFlag(true)
                .build();

        assertEquals(Boolean.TRUE, transaction.getDisplayMessageSuppressionFlag());
        assertTrue(new Gson().toJson(transaction).contains("\"displayMessageSuppressionFlag\":true"));
    }

    /**
     * Verifies that copying a transaction preserves its display-message preference.
     */
    @Test
    public void ifTransactionIsCopiedThenDisplayMessageSuppressionFlagIsPreserved() {
        Transaction original = createTransactionBuilder()
                .setDisplayMessageSuppressionFlag(false)
                .build();

        Transaction copy = new Transaction.Builder(original).build();

        assertEquals(Boolean.FALSE, copy.getDisplayMessageSuppressionFlag());
    }

    /**
     * Verifies that callers which do not use the new option retain the existing default behaviour.
     */
    @Test
    public void ifDisplayMessageSuppressionIsNotConfiguredThenFlagRemainsAbsent() {
        Transaction transaction = createTransactionBuilder().build();

        assertNull(transaction.getDisplayMessageSuppressionFlag());
        assertFalse(new Gson().toJson(transaction).contains("displayMessageSuppressionFlag"));
    }

    private static List<LineItem> createLineItems() {
        return new LineItem.ListBuilder("item", new BigDecimal("1.00")).build();
    }

    private static Transaction.Builder createTransactionBuilder() {
        return new Transaction.Builder(createLineItems())
                .setMerchantReference("merchant-reference")
                .setInvoiceReference("invoice-reference");
    }
}
