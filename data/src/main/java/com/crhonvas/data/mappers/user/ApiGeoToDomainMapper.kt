package com.crhonvas.data.mappers.user

import com.crhonvas.data.remote.model.user.GeoApiResponse
import com.crhonvas.domain.model.user.Geo
import javax.inject.Inject

//TODO: Inject scheduler provider inorder to do mappings in a specified background thread
/**
 * Mapper from [GeoApiResponse] to [Geo]
 */
class ApiGeoToDomainMapper @Inject constructor() {
    fun map(geoApiResponse: GeoApiResponse): Geo {
        return if (geoApiResponse == null) {
            Geo.Builder().build()
        } else Geo.Builder()
            .lat(geoApiResponse.lat)
            .lng(geoApiResponse.lng)
            .build()
    }
}