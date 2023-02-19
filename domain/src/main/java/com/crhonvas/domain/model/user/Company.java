package com.crhonvas.domain.model.user;

import android.support.annotation.NonNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    private Company(@NonNull Builder builder) {
        this.name = builder.name;
        this.catchPhrase = builder.catchPhrase;
        this.bs = builder.bs;
    }

    @NonNull
    public String getName() {
        return name == null ? "" : name;
    }

    @NonNull
    public String getCatchPhrase() {
        return catchPhrase == null ? "" : catchPhrase;
    }

    @NonNull
    public String getBs() {
        return bs == null ? "" : bs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        return new EqualsBuilder()
                .append(name, company.name)
                .append(catchPhrase, company.catchPhrase)
                .append(bs, company.bs)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(catchPhrase)
                .append(bs)
                .toHashCode();
    }

    public static final class Builder {
        private String name;
        private String catchPhrase;
        private String bs;

        public Builder() {}

        @NonNull
        public Builder name(@NonNull String name) {
            this.name = name;
            return this;
        }

        @NonNull
        public Builder catchPhrase(@NonNull String catchPhrase) {
            this.catchPhrase = catchPhrase;
            return this;
        }

        @NonNull
        public Builder bs(@NonNull String bs) {
            this.bs = bs;
            return this;
        }

        @NonNull
        public Company build() {
            return new Company(this);
        }
    }
}
