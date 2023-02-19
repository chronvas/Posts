package com.crhonvas.data.remote.model.user;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AddressApiResponse {

    @SerializedName("street")
    private String street;
    @SerializedName("suite")
    private String suite;
    @SerializedName("city")
    private String city;
    @SerializedName("zipCode")
    private String zipCode;
    @SerializedName("geo")
    private GeoApiResponse geo;

    public AddressApiResponse() {
    }

    private AddressApiResponse(@NonNull Builder builder) {
        street = builder.street;
        suite = builder.suite;
        city = builder.city;
        zipCode = builder.zipCode;
        geo = builder.geo;
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
    public GeoApiResponse getGeo() {
        return geo == null ? new GeoApiResponse.Builder().build() : geo;
    }

    public static final class Builder {
        private String street;
        private String suite;
        private String city;
        private String zipCode;
        private GeoApiResponse geo;

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
        public Builder geo(@NonNull GeoApiResponse geo) {
            this.geo = geo;
            return this;
        }

        @NonNull
        public AddressApiResponse build() {
            return new AddressApiResponse(this);
        }
    }
}
