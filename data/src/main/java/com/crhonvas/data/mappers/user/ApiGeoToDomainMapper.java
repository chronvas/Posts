package com.crhonvas.data.mappers.user;

import android.support.annotation.NonNull;

import com.crhonvas.data.remote.model.user.GeoApiResponse;
import com.crhonvas.domain.model.user.Geo;

import javax.inject.Inject;

//TODO: Inject scheduler provider inorder to do mappings in a specified background thread

/**
 * Mapper from {@link GeoApiResponse} to {@link Geo}
 */
public class ApiGeoToDomainMapper {

    @Inject
    public ApiGeoToDomainMapper() {}

    @NonNull
    public Geo map(@NonNull GeoApiResponse geoApiResponse) {
        if (geoApiResponse == null) {
            return new Geo.Builder().build();
        }
        return new Geo.Builder()
                .lat(geoApiResponse.getLat())
                .lng(geoApiResponse.getLng())
                .build();
    }
}
