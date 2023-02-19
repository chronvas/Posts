package com.crhonvas.data.mappers.user

import com.crhonvas.data.remote.model.user.GeoApiResponse
import com.crhonvas.domain.model.user.Geo
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Tests for [ApiGeoToDomainMapper]
 */
class ApiGeoToDomainMapperTest {
    private var apiGeoToDomainMapper: ApiGeoToDomainMapper? = null
    @Before
    @Throws(Exception::class)
    fun setUp() {
        apiGeoToDomainMapper = ApiGeoToDomainMapper()
    }

    @Test
    @Throws(Exception::class)
    fun map() {
        val actual = apiGeoToDomainMapper!!.map(geoApiResponse)
        compare(actual, geoApiResponse)
    }

    @Test
    @Throws(Exception::class)
    fun map_null_returnEmpty() {
        val actual = apiGeoToDomainMapper!!.map(null)
        compare(actual, GeoApiResponse.Builder().build())
    }

    companion object {
        private val geoApiResponse = GeoApiResponse.Builder()
            .lat("lat")
            .lng("lng").build()

        private fun compare(actual: Geo, expected: GeoApiResponse) {
            Assert.assertEquals(expected.getLat(), actual.lat)
            Assert.assertEquals(expected.getLng(), actual.lng)
        }
    }
}