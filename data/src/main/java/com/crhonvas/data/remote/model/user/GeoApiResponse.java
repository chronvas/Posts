package com.crhonvas.data.remote.model.user;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class GeoApiResponse {

    @SerializedName("lat")
    private String lat;
    @SerializedName("lng")
    private String lng;

    private GeoApiResponse(@NonNull Builder builder) {
        lat = builder.lat;
        lng = builder.lng;
    }

    @NonNull
    public String getLat() {
        return lat == null ? "" : lat;
    }

    @NonNull
    public String getLng() {
        return lng == null ? "" : lng;
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
        public GeoApiResponse build() {
            return new GeoApiResponse(this);
        }
    }
}
