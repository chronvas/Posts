package com.crhonvas.domain.model.user;

import android.support.annotation.NonNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class User {
    private Integer id;
    private String name;
    private String userName;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    private User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.userName = builder.userName;
        this.email = builder.email;
        this.address = builder.address;
        this.phone = builder.phone;
        this.website = builder.website;
        this.company = builder.company;
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
    public String getUserName() {
        return userName == null ? "" : userName;
    }

    @NonNull
    public String getEmail() {
        return email == null ? "" : email;
    }

    @NonNull
    public Address getAddress() {
        return address == null ? new Address.Builder().build() : address;
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
    public Company getCompany() {
        return company == null ? new Company.Builder().build() : company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(id, user.id)
                .append(name, user.name)
                .append(userName, user.userName)
                .append(email, user.email)
                .append(address, user.address)
                .append(phone, user.phone)
                .append(website, user.website)
                .append(company, user.company)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(userName)
                .append(email)
                .append(address)
                .append(phone)
                .append(website)
                .append(company)
                .toHashCode();
    }

    public static final class Builder {
        private Integer id;
        private String name;
        private String userName;
        private String email;
        private Address address;
        private String phone;
        private String website;
        private Company company;

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
        public Builder userName(@NonNull String userName) {
            this.userName = userName;
            return this;
        }

        @NonNull
        public Builder email(@NonNull String email) {
            this.email = email;
            return this;
        }

        @NonNull
        public Builder address(@NonNull Address address) {
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
        public Builder company(@NonNull Company company) {
            this.company = company;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
