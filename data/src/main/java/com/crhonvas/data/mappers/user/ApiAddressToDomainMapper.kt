package com.crhonvas.data.mappers.user

import com.crhonvas.data.remote.model.user.AddressApiResponse
import com.crhonvas.domain.model.user.Address
import javax.inject.Inject

//TODO: Inject scheduler provider inorder to do mappings in a specified background thread
/**
 * Mapper from [AddressApiResponse] to [Address]
 */
class ApiAddressToDomainMapper @Inject constructor(private val apiGeoToDomainMapper: ApiGeoToDomainMapper) {
    fun map(addressApiResponse: AddressApiResponse): Address {
        return if (addressApiResponse == null) {
            Address.Builder().build()
        } else Address.Builder()
            .street(addressApiResponse.street)
            .suite(addressApiResponse.suite)
            .city(addressApiResponse.city)
            .zipCode(addressApiResponse.zipCode)
            .geo(apiGeoToDomainMapper.map(addressApiResponse.geo))
            .build()
    }
}