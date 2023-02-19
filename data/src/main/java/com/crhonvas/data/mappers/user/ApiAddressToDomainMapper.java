package com.crhonvas.data.mappers.user;

import android.support.annotation.NonNull;

import com.crhonvas.data.remote.model.user.AddressApiResponse;
import com.crhonvas.domain.model.user.Address;

import javax.inject.Inject;

//TODO: Inject scheduler provider inorder to do mappings in a specified background thread

/**
 * Mapper from {@link AddressApiResponse} to {@link Address}
 */
public class ApiAddressToDomainMapper {

    private final ApiGeoToDomainMapper apiGeoToDomainMapper;

    @Inject
    public ApiAddressToDomainMapper(ApiGeoToDomainMapper apiGeoToDomainMapper) {
        this.apiGeoToDomainMapper = apiGeoToDomainMapper;
    }

    @NonNull
    public Address map(@NonNull AddressApiResponse addressApiResponse) {
        if (addressApiResponse == null) {
            return new Address.Builder().build();
        }
        return new Address.Builder()
                .street(addressApiResponse.getStreet())
                .suite(addressApiResponse.getSuite())
                .city(addressApiResponse.getCity())
                .zipCode(addressApiResponse.getZipCode())
                .geo(apiGeoToDomainMapper.map(addressApiResponse.getGeo()))
                .build();
    }
}
