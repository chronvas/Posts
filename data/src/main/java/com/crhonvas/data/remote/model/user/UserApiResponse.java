package com.crhonvas.data.remote.model.user;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class UserApiResponse {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("address")
    private AddressApiResponse address;
    @SerializedName("phone")
    private String phone;
    @SerializedName("website")
    private String website;
    @SerializedName("company")
    private CompanyApiResponse userCompanyApiResponse;

    private UserApiResponse(@NonNull Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.username = builder.username;
        this.email = builder.email;
        this.address = builder.address;
        this.phone = builder.phone;
        this.website = builder.website;
        this.userCompanyApiResponse = builder.userCompanyApiResponse;
    }

    @NonNull
    public Integer getId() {
        return id == null ? -1 : id;
    }

    @NonNull
    public String getName() {
        return name == null ? "" : name;
    }

    @NonNull
    public String getUsername() {
        return username == null ? "" : username;
    }

    @NonNull
    public String getEmail() {
        return email == null ? "" : email;
    }

    @NonNull
    public AddressApiResponse getAddress() {
        return address == null ? new AddressApiResponse.Builder().build() : address;
    }

    @NonNull
    public String getPhone() {
        return phone == null ? "" : phone;
    }

    @NonNull
    public String getWebsite() {
        return website == null ? "" : website;
    }

    @NonNull
    public CompanyApiResponse getCompany() {
        return userCompanyApiResponse == null ? new CompanyApiResponse.Builder().build() : userCompanyApiResponse;
    }

    public static final class Builder {
        private Integer id;
        private String name;
        private String username;
        private String email;
        private AddressApiResponse address;
        private String phone;
        private String website;
        private CompanyApiResponse userCompanyApiResponse;

        public Builder() {}

        @NonNull
        public Builder id(@NonNull Integer id) {
            this.id = id;
            return this;
        }

        @NonNull
        public Builder name(@NonNull String name) {
            this.name = name;
            return this;
        }

        @NonNull
        public Builder username(@NonNull String username) {
            this.username = username;
            return this;
        }

        @NonNull
        public Builder email(@NonNull String email) {
            this.email = email;
            return this;
        }

        @NonNull
        public Builder address(@NonNull AddressApiResponse address) {
            this.address = address;
            return this;
        }

        @NonNull
        public Builder phone(@NonNull String phone) {
            this.phone = phone;
            return this;
        }

        @NonNull
        public Builder website(@NonNull String website) {
            this.website = website;
            return this;
        }

        @NonNull
        public Builder userCompanyApiResponse(@NonNull CompanyApiResponse userCompanyApiResponse) {
            this.userCompanyApiResponse = userCompanyApiResponse;
            return this;
        }

        @NonNull
        public UserApiResponse build() {
            return new UserApiResponse(this);
        }
    }
}
