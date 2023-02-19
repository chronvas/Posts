package com.crhonvas.data.mappers.user

import com.crhonvas.data.remote.model.user.CompanyApiResponse
import com.crhonvas.domain.model.user.Company
import javax.inject.Inject

//TODO: Inject scheduler provider inorder to do mappings in a specified background thread
/**
 * Mapper from [CompanyApiResponse] to [Company]
 */
class ApiCompanyToDomainMapper @Inject constructor() {
    fun map(companyApiResponse: CompanyApiResponse): Company {
        return if (companyApiResponse == null) {
            Company.Builder().build()
        } else Company.Builder()
            .name(companyApiResponse.name)
            .catchPhrase(companyApiResponse.catchPhrase)
            .bs(companyApiResponse.bs)
            .build()
    }
}