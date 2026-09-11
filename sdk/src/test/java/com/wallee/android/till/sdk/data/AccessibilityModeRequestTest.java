package com.wallee.android.till.sdk.data;

import com.google.gson.Gson;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/** Verifies the accessibility-mode contract across transaction construction and JSON transport. */
public class AccessibilityModeRequestTest {
    private final Gson gson = new Gson();

    /** Verifies that existing callers request the standard interface without supplying a new field. */
    @Test
    public void ifModeIsNotConfiguredThenBothRequestsSerializeZero() {
        assertEquals(AccessibilityMode.DISABLED, transactionBuilder().build().getAccessMode());
        assertEquals(AccessibilityMode.DISABLED, completionBuilder().build().getAccessMode());
        assertTrue(gson.toJson(transactionBuilder().build()).contains("\"accessMode\":0"));
        assertTrue(gson.toJson(completionBuilder().build()).contains("\"accessMode\":0"));
    }

    /** Verifies that mode codes survive transport for both financial request types. */
    @Test
    public void ifModeIsConfiguredThenBothRequestsPreserveItAcrossJsonTransport() {
        for (AccessibilityMode mode : AccessibilityMode.values()) {
            Transaction transaction = transactionBuilder().setAccessMode(mode).build();
            TransactionCompletion completion = completionBuilder().setAccessMode(mode).build();

            assertEquals(mode, gson.fromJson(gson.toJson(transaction), Transaction.class).getAccessMode());
            assertEquals(mode, gson.fromJson(gson.toJson(completion), TransactionCompletion.class).getAccessMode());
        }
        assertTrue(gson.toJson(transactionBuilder().setAccessMode(AccessibilityMode.BLIND_MODE).build())
                .contains("\"accessMode\":1"));
        assertTrue(gson.toJson(completionBuilder().setAccessMode(AccessibilityMode.COLOUR_BLIND_MODE).build())
                .contains("\"accessMode\":2"));
    }

    /** Verifies compatibility with omitted mode fields from older SDK clients. */
    @Test
    public void ifJsonOmitsModeThenBothRequestsDefaultToDisabled() {
        assertEquals(AccessibilityMode.DISABLED, gson.fromJson("{}", Transaction.class).getAccessMode());
        assertEquals(AccessibilityMode.DISABLED, gson.fromJson("{}", TransactionCompletion.class).getAccessMode());
    }

    /** Verifies acceptance of the numeric codes and their string representations. */
    @Test
    public void ifJsonContainsSupportedCodeThenBothRequestsDecodeIt() {
        AccessibilityMode[] expected = {
                AccessibilityMode.DISABLED, AccessibilityMode.BLIND_MODE, AccessibilityMode.COLOUR_BLIND_MODE
        };
        for (int code = 0; code <= 2; code++) {
            for (String value : new String[]{String.valueOf(code), "\"" + code + "\""}) {
                String json = "{\"accessMode\":" + value + "}";
                assertEquals(expected[code], gson.fromJson(json, Transaction.class).getAccessMode());
                assertEquals(expected[code], gson.fromJson(json, TransactionCompletion.class).getAccessMode());
            }
        }
    }

    /** Verifies that invalid values cannot abort JSON decoding or consume neighbouring fields. */
    @Test
    public void ifJsonContainsInvalidModeThenBothRequestsDefaultToDisabledAndRetainOtherFields() {
        for (String value : new String[]{"-1", "3", "99", "1.5", "\"99\"", "\"\"", "\"blind\"",
                "null", "true", "false", "{}", "[1,{\"nested\":[2]}]"}) {
            String json = "{\"accessMode\":" + value + ",\"transactionSyncNumber\":42}";
            Transaction transaction = gson.fromJson(json, Transaction.class);
            TransactionCompletion completion = gson.fromJson(json, TransactionCompletion.class);

            assertEquals(value, AccessibilityMode.DISABLED, transaction.getAccessMode());
            assertEquals(value, AccessibilityMode.DISABLED, completion.getAccessMode());
            assertEquals(Integer.valueOf(42), transaction.getTransactionSyncNumber());
            assertEquals(Integer.valueOf(42), completion.getTransactionSyncNumber());
        }
    }

    /** Verifies copy behaviour and independence from separately constructed transactions. */
    @Test
    public void ifRequestsAreCopiedThenModeIsPreservedWithoutChangingNewRequests() {
        Transaction transaction = transactionBuilder().setAccessMode(AccessibilityMode.BLIND_MODE).build();
        TransactionCompletion completion = completionBuilder().setAccessMode(AccessibilityMode.BLIND_MODE).build();

        assertEquals(AccessibilityMode.BLIND_MODE, new Transaction.Builder(transaction).build().getAccessMode());
        assertEquals(AccessibilityMode.BLIND_MODE, new TransactionCompletion.Builder(completion).build().getAccessMode());
        assertEquals(AccessibilityMode.DISABLED, transactionBuilder().build().getAccessMode());
        assertEquals(AccessibilityMode.DISABLED, completionBuilder().build().getAccessMode());
    }

    /** Verifies that callers can clear a requested mode before submitting a transaction. */
    @Test
    public void ifModeIsResetToNullThenBothRequestsUseDisabled() {
        assertEquals(AccessibilityMode.DISABLED, transactionBuilder()
                .setAccessMode(AccessibilityMode.BLIND_MODE).setAccessMode(null).build().getAccessMode());
        assertEquals(AccessibilityMode.DISABLED, completionBuilder()
                .setAccessMode(AccessibilityMode.BLIND_MODE).setAccessMode(null).build().getAccessMode());
        assertEquals(AccessibilityMode.DISABLED, AccessibilityMode.fromCode(-1));
        assertEquals(AccessibilityMode.DISABLED, AccessibilityMode.fromCode(99));
    }

    private static List<LineItem> lineItems() {
        return new LineItem.ListBuilder("item", new BigDecimal("1.00")).build();
    }

    private static Transaction.Builder transactionBuilder() {
        return new Transaction.Builder(lineItems())
                .setMerchantReference("merchant").setInvoiceReference("invoice");
    }

    private static TransactionCompletion.Builder completionBuilder() {
        return new TransactionCompletion.Builder(lineItems()).setReserveReference("reservation");
    }
}
