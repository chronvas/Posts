package com.crhonvas.data.mappers.user

import com.crhonvas.data.remote.model.user.UserApiResponse
import com.crhonvas.domain.model.user.Address
import com.crhonvas.domain.model.user.Company
import com.crhonvas.domain.model.user.User
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Tests for [UserApiToDomainMapper]
 */
class UserApiToDomainMapperTest {
    @Mock
    var apiAddressToDomainMapper: ApiAddressToDomainMapper? = null

    @Mock
    var apiCompanyToDomainMapper: ApiCompanyToDomainMapper? = null
    private var userApiToDomainMapper: UserApiToDomainMapper? = null
    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userApiToDomainMapper = UserApiToDomainMapper(apiAddressToDomainMapper!!, apiCompanyToDomainMapper!!)
    }

    @Test
    @Throws(Exception::class)
    fun map() {
        Mockito.`when`(apiAddressToDomainMapper!!.map(Matchers.any())).thenReturn(Address.Builder().build())
        Mockito.`when`(apiCompanyToDomainMapper!!.map(Matchers.any())).thenReturn(Company.Builder().build())
        val actual = userApiToDomainMapper!!.map(userApiResponse)
        Mockito.verify(apiAddressToDomainMapper, Mockito.times(1)).map(Matchers.any())
        Mockito.verify(apiCompanyToDomainMapper, Mockito.times(1)).map(Matchers.any())
        compare(actual, userApiResponse)
    }

    @Test
    @Throws(Exception::class)
    fun map_null_returnEmpty() {
        val actual = userApiToDomainMapper!!.map(null)
        compare(actual, UserApiResponse.Builder().build())
    }

    companion object {
        private val userApiResponse = UserApiResponse.Builder()
            .id(123414)
            .name("name")
            .username("username")
            .email("email")
            .phone("phone")
            .website("website")
            .build()

        private fun compare(actual: User, expected: UserApiResponse) {
            Assert.assertEquals(expected.getEmail(), actual.email)
            Assert.assertEquals(expected.getName(), actual.name)
            Assert.assertEquals(expected.getUsername(), actual.userName)
            Assert.assertEquals(expected.getId(), actual.id)
            Assert.assertEquals(expected.getWebsite(), actual.website)
            Assert.assertEquals(expected.getPhone(), actual.phone)
        }
    }
}