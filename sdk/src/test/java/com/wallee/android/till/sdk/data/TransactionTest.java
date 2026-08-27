package com.wallee.android.till.sdk.data;

import com.google.gson.Gson;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
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

    /**
     * Verifies that an explicit merchant service location is retained and serialized for the API service.
     */
    @Test
    public void ifMerchantServiceLocationIsConfiguredThenTransactionSerializesIt() {
        MerchantServiceLocation location = new MerchantServiceLocation.Builder()
                .setCity("Zurich")
                .setCountryCode("756")
                .setCountrySubdivisionCode("ZH")
                .setPostalCode("8001")
                .build();
        Transaction transaction = createTransactionBuilder()
                .setMerchantServiceLocation(location)
                .build();

        Gson gson = new Gson();
        String json = gson.toJson(transaction);
        Transaction deserialized = gson.fromJson(json, Transaction.class);

        assertSame(location, transaction.getMerchantServiceLocation());
        assertTrue(json.contains("\"merchantServiceLocation\""));
        assertTrue(json.contains("\"city\":\"Zurich\""));
        assertTrue(json.contains("\"countryCode\":\"756\""));
        assertEquals("Zurich", deserialized.getMerchantServiceLocation().getCity());
        assertEquals("756", deserialized.getMerchantServiceLocation().getCountryCode());
    }

    /**
     * Verifies that copying a transaction preserves its merchant service location.
     */
    @Test
    public void ifTransactionIsCopiedThenMerchantServiceLocationIsPreserved() {
        MerchantServiceLocation location = new MerchantServiceLocation.Builder()
                .setCity("Zurich")
                .setCountryCode("756")
                .setCountrySubdivisionCode("ZH")
                .setPostalCode("8001")
                .build();
        Transaction original = createTransactionBuilder()
                .setMerchantServiceLocation(location)
                .build();

        Transaction copy = new Transaction.Builder(original).build();

        assertSame(location, copy.getMerchantServiceLocation());
    }

    /**
     * Verifies that existing callers retain the previous transaction JSON shape.
     */
    @Test
    public void ifMerchantServiceLocationIsNotConfiguredThenItRemainsAbsent() {
        Transaction transaction = createTransactionBuilder().build();

        assertNull(transaction.getMerchantServiceLocation());
        assertFalse(new Gson().toJson(transaction).contains("merchantServiceLocation"));
    }

    private static List<LineItem> createLineItems() {
        return new LineItem.ListBuilder("item", new BigDecimal("1.00")).build();
    }

    private static Transaction.Builder createTransactionBuilder() {
        return new Transaction.Builder(createLineItems())
                .setMerchantReference("merchant-reference")
                .setInvoiceReference("invoice-reference");
    }

    @Test
    public void ifOrderIdIsOmittedThenOrderIdRemainsNull() {
        Transaction transaction = createTransactionBuilder().build();
        assertNull(transaction.getOrderId());
    }

    @Test
    public void ifOrderIdIsNullThenOrderIdRemainsNull() {
        Transaction transaction = createTransactionBuilder()
                .setOrderId(null)
                .build();
        assertNull(transaction.getOrderId());
    }

    @Test
    public void ifOrderIdHas48CharactersThenTransactionIsCreated() {
        String orderId = "123456789012345678901234567890123456789012345678";
        Transaction transaction = createTransactionBuilder()
                .setOrderId(orderId)
                .build();
        assertEquals(orderId, transaction.getOrderId());
    }

    @Test
    public void ifOrderIdHas49CharactersThenTransactionCreationFails() {
        String orderId = "1234567890123456789012345678901234567890123456789";
        assertThrows(IllegalArgumentException.class, () ->
                createTransactionBuilder().setOrderId(orderId).build()
        );
    }

    @Test
    public void ifOrderIdContainsUnsupportedCharactersThenTransactionCreationFails() {
        String orderId = "orderId-€";
        assertThrows(IllegalArgumentException.class, () ->
                createTransactionBuilder().setOrderId(orderId).build()
        );
    }

    @Test
    public void ifOrderIdContainsLatin1CharactersThenTransactionIsCreated() {
        String orderId = "Zürich-123";

        Transaction transaction = createTransactionBuilder()
                .setOrderId(orderId)
                .build();

        assertEquals(orderId, transaction.getOrderId());
    }
}
