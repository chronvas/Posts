package com.crhonvas.domain.model.user;

import androidx.annotation.NonNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipCode;
    private Geo geo;

    private Address(@NonNull Builder builder) {
        this.street = builder.street;
        this.suite = builder.suite;
        this.city = builder.city;
        this.zipCode = builder.zipCode;
        this.geo = builder.geo;
    }

    @NonNull
    public String getStreet() {
        return street == null ? "" : street;
    }

    @NonNull
    public String getSuite() {
        return suite == null ? "" : suite;
    }

    @NonNull
    public String getCity() {
        return city == null ? "" : city;
    }

    @NonNull
    public String getZipCode() {
        return zipCode == null ? "" : zipCode;
    }

    @NonNull
    public Geo getGeo() {
        return geo == null ? new Geo.Builder().build() : geo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return new EqualsBuilder()
                .append(street, address.street)
                .append(suite, address.suite)
                .append(city, address.city)
                .append(zipCode, address.zipCode)
                .append(geo, address.geo)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(street)
                .append(suite)
                .append(city)
                .append(zipCode)
                .append(geo)
                .toHashCode();
    }

    public static final class Builder {
        private String street;
        private String suite;
        private String city;
        private String zipCode;
        private Geo geo;

        public Builder() {}

        @NonNull
        public Builder street(@NonNull String street) {
            this.street = street;
            return this;
        }

        @NonNull
        public Builder suite(@NonNull String suite) {
            this.suite = suite;
            return this;
        }

        @NonNull
        public Builder city(@NonNull String city) {
            this.city = city;
            return this;
        }

        @NonNull
        public Builder zipCode(@NonNull String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        @NonNull
        public Builder geo(@NonNull Geo geo) {
            this.geo = geo;
            return this;
        }

        @NonNull
        public Address build() {
            return new Address(this);
        }
    }
}
