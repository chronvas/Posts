package com.crhonvas.data.mappers.user

import com.crhonvas.data.remote.model.user.CompanyApiResponse
import com.crhonvas.domain.model.user.Company
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

/**
 * Test for [ApiCompanyToDomainMapper]
 */
class ApiCompanyToDomainMapperTest {
    private var apiCompanyToDomainMapper: ApiCompanyToDomainMapper? = null
    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        apiCompanyToDomainMapper = ApiCompanyToDomainMapper()
    }

    @Test
    @Throws(Exception::class)
    fun map() {
        val actual = apiCompanyToDomainMapper!!.map(companyApiResponse)
        compare(actual, companyApiResponse)
    }

    @Test
    @Throws(Exception::class)
    fun map_null_returnEmpty() {
        val actual = apiCompanyToDomainMapper!!.map(null)
        compare(actual, CompanyApiResponse.Builder().build())
    }

    companion object {
        private val companyApiResponse =
            CompanyApiResponse.Builder().bs("bs").catchPhrase("catchphrase").name("cname").build()

        private fun compare(actual: Company, expected: CompanyApiResponse) {
            Assert.assertEquals(expected.getName(), actual.name)
            Assert.assertEquals(expected.getBs(), actual.bs)
            Assert.assertEquals(expected.getCatchPhrase(), actual.catchPhrase)
        }
    }
}