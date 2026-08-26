package com.wallee.android.till.sdk.data;

import static com.wallee.android.till.sdk.data.Utils.check;

/**
 * Describes the physical service location of a transaction when it differs from the merchant's
 * configured address.
 *
 * <p>City, country code, country subdivision code, and postal code are required.</p>
 */
public final class MerchantServiceLocation {
    private final String city;
    private final String countryCode;
    private final String countrySubdivisionCode;
    private final String postalCode;

    private MerchantServiceLocation(Builder builder) {
        this.city = check(builder.city, "city", 48);
        this.countryCode = validateCountryCode(builder.countryCode);
        this.countrySubdivisionCode = check(
                builder.countrySubdivisionCode,
                "countrySubdivisionCode",
                3
        );
        this.postalCode = check(builder.postalCode, "postalCode", 12);
    }

    private static String validateCountryCode(String value) {
        String countryCode = check(value, "countryCode", 3);
        if (!countryCode.matches("[0-9]{1,3}")) {
            throw new IllegalArgumentException("countryCode must contain 1 to 3 digits");
        }
        return countryCode;
    }

    /**
     * Returns the city in which the transaction takes place.
     *
     * @return the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the numeric ISO 3166 country code of the service location.
     *
     * @return the numeric country code.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Returns the ISO 3166-2 country subdivision code of the service location.
     *
     * @return the country subdivision code.
     */
    public String getCountrySubdivisionCode() {
        return countrySubdivisionCode;
    }

    /**
     * Returns the postal code of the service location.
     *
     * @return the postal code.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Builds a merchant service location with its four required address values.
     */
    public static final class Builder {
        private String city;
        private String countryCode;
        private String countrySubdivisionCode;
        private String postalCode;

        /**
         * Creates an empty builder. All four address values must be configured before
         * {@link #build()}.
         */
        public Builder() {
        }

        /**
         * Sets the service-location city.
         *
         * @param city the required city of at most 48 characters.
         * @return this builder.
         */
        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        /**
         * Sets the numeric ISO 3166 country code.
         *
         * @param countryCode the required numeric country code of at most 3 digits, such as
         *                    {@code "756"} for Switzerland.
         * @return this builder.
         */
        public Builder setCountryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        /**
         * Sets the ISO 3166-2 country subdivision code.
         *
         * @param countrySubdivisionCode the required subdivision code of at most 3 characters.
         * @return this builder.
         */
        public Builder setCountrySubdivisionCode(String countrySubdivisionCode) {
            this.countrySubdivisionCode = countrySubdivisionCode;
            return this;
        }

        /**
         * Sets the service-location postal code.
         *
         * @param postalCode the required postal code of at most 12 characters.
         * @return this builder.
         */
        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        /**
         * Creates an immutable merchant service location.
         *
         * @return the configured merchant service location.
         * @throws NullPointerException when a required value is {@code null}.
         * @throws IllegalArgumentException when a required value is empty or violates its format.
         */
        public MerchantServiceLocation build() {
            return new MerchantServiceLocation(this);
        }
    }
}
