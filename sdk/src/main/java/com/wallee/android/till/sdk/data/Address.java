package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

import static com.wallee.android.till.sdk.data.Utils.checkNullable;
import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

public class Address  {
    private final String givenName;
    private final String familyName;

    private final String organization;

    private final String street;
    private final String postcode;
    private final String city;
    private final String countryCode;

    public Address(String givenName, String familyName, String organization, String street, String postcode, String city, @NonNull String countryCode) {
        this.givenName = checkNullable(givenName, "givenName", 100);
        this.familyName = checkNullable(familyName, "familyName", 100);
        this.organization = checkNullable(organization, "organization", 200);
        this.street = checkNullable(street, "street", 300);

        this.postcode = checkNullable(postcode, "postcode", 40);
        this.city = checkNullable(city, "city", 100);
        this.countryCode = requireNonNull(countryCode, "countryCode");
        if (this.countryCode.matches("[A-Z]{2}")) throw new IllegalArgumentException("countryCode must have 2 letters");
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getOrganization() {
        return organization;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }
}
