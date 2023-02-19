package com.crhonvas.domain.model.user;

import androidx.annotation.NonNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Geo {
    private String lat;
    private String lng;

    private Geo(@NonNull Builder builder) {
        lat = builder.lat;
        lng = builder.lng;
    }

    @NonNull
    public String getLat() {
        return lat;
    }

    @NonNull
    public String getLng() {
        return lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Geo geo = (Geo) o;

        return new EqualsBuilder()
                .append(lat, geo.lat)
                .append(lng, geo.lng)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(lat)
                .append(lng)
                .toHashCode();
    }

    public static final class Builder {
        private String lat = "";
        private String lng = "";

        public Builder() {}

        @NonNull
        public Builder lat(@NonNull String lat) {
            this.lat = lat;
            return this;
        }

        @NonNull
        public Builder lng(@NonNull String lng) {
            this.lng = lng;
            return this;
        }

        @NonNull
        public Geo build() {
            return new Geo(this);
        }
    }
}
