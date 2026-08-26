package com.wallee.android.till.sdk.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Verifies the public Merchant Service Location value constraints.
 */
public class MerchantServiceLocationTest {

    /**
     * Verifies that every EP2 8.2 location value is retained.
     */
    @Test
    public void ifMerchantServiceLocationIsValidThenAllValuesAreRetained() {
        MerchantServiceLocation location = new MerchantServiceLocation.Builder()
                .setCity("Zurich")
                .setCountryCode("756")
                .setCountrySubdivisionCode("ZH")
                .setPostalCode("8001")
                .build();

        assertEquals("Zurich", location.getCity());
        assertEquals("756", location.getCountryCode());
        assertEquals("ZH", location.getCountrySubdivisionCode());
        assertEquals("8001", location.getPostalCode());
    }

    /**
     * Verifies that every EP2 8.2 location value is mandatory.
     */
    @Test
    public void ifAnyMandatoryMerchantServiceLocationValueIsMissingThenBuildFails() {
        assertThrows(
                NullPointerException.class,
                () -> createBuilder(null, "756", "ZH", "8001").build()
        );
        assertThrows(
                NullPointerException.class,
                () -> createBuilder("Zurich", null, "ZH", "8001").build()
        );
        assertThrows(
                NullPointerException.class,
                () -> createBuilder("Zurich", "756", null, "8001").build()
        );
        assertThrows(
                NullPointerException.class,
                () -> createBuilder("Zurich", "756", "ZH", null).build()
        );
    }

    /**
     * Verifies that the country code uses the required numeric ISO 3166 representation and EP2
     * length limit.
     */
    @Test
    public void ifCountryCodeIsNotNumericOrExceedsMaximumLengthThenBuildFails() {
        assertThrows(
                IllegalArgumentException.class,
                () -> createBuilder("Zurich", "CH", "ZH", "8001").build()
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> createBuilder("Zurich", "1234", "ZH", "8001").build()
        );
    }

    /**
     * Verifies that EP2 numeric values do not require leading-zero padding to their maximum length.
     */
    @Test
    public void ifCountryCodeHasFewerThanThreeDigitsThenBuildSucceeds() {
        MerchantServiceLocation location = createBuilder("Brussels", "56", "BRU", "1000")
                .build();

        assertEquals("56", location.getCountryCode());
    }

    /**
     * Verifies that text values longer than the EP2 limits are rejected.
     */
    @Test
    public void ifAnyTextValueExceedsMaximumLengthThenBuildFails() {
        assertThrows(
                IllegalArgumentException.class,
                () -> createBuilder(
                        "1234567890123456789012345678901234567890123456789",
                        "756",
                        "ZH",
                        "8001"
                ).build()
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> createBuilder("Zurich", "756", "ABCD", "8001").build()
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> createBuilder("Zurich", "756", "ZH", "1234567890123").build()
        );
    }

    private static MerchantServiceLocation.Builder createBuilder(
            String city,
            String countryCode,
            String countrySubdivisionCode,
            String postalCode
    ) {
        return new MerchantServiceLocation.Builder()
                .setCity(city)
                .setCountryCode(countryCode)
                .setCountrySubdivisionCode(countrySubdivisionCode)
                .setPostalCode(postalCode);
    }
}
