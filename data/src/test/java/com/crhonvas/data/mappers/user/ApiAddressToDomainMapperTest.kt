package com.crhonvas.data.mappers.user

import com.crhonvas.data.remote.model.user.AddressApiResponse
import com.crhonvas.data.remote.model.user.GeoApiResponse
import com.crhonvas.domain.model.user.Address
import com.crhonvas.domain.model.user.Geo
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Tests for [ApiAddressToDomainMapper]
 */
class ApiAddressToDomainMapperTest {
    @Mock
    var apiGeoToDomainMapper: ApiGeoToDomainMapper? = null
    private var apiAddressToDomainMapper: ApiAddressToDomainMapper? = null
    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        apiAddressToDomainMapper = ApiAddressToDomainMapper(apiGeoToDomainMapper!!)
    }

    @Test
    @Throws(Exception::class)
    fun map() {
        Mockito.`when`(apiGeoToDomainMapper!!.map(Matchers.any())).thenReturn(Geo.Builder().build())
        val actual = apiAddressToDomainMapper!!.map(addressApiResponse)
        Mockito.verify(apiGeoToDomainMapper, Mockito.times(1)).map(Matchers.any())
        compare(actual, addressApiResponse)
    }

    @Test
    @Throws(Exception::class)
    fun map_null_returnEmpty() {
        val actual = apiAddressToDomainMapper!!.map(null)
        val addressApiResponse = AddressApiResponse.Builder().build()
        Mockito.verify(apiGeoToDomainMapper, Mockito.times(0)).map(Matchers.any())
        compare(actual, addressApiResponse)
    }

    companion object {
        private val addressApiResponse = AddressApiResponse.Builder()
            .city("city")
            .geo(GeoApiResponse.Builder().lng("Asda").lat("232").build())
            .street("str")
            .suite("suite")
            .zipCode("zipc")
            .build()

        private fun compare(actual: Address, expected: AddressApiResponse) {
            Assert.assertEquals(expected.getStreet(), actual.street)
            Assert.assertEquals(expected.getSuite(), actual.suite)
            Assert.assertEquals(expected.getCity(), actual.city)
            Assert.assertEquals(expected.getZipCode(), actual.zipCode)
        }
    }
}