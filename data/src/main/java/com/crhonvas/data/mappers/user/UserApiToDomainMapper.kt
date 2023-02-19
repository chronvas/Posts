package com.crhonvas.data.mappers.user

import com.crhonvas.data.remote.model.user.UserApiResponse
import com.crhonvas.domain.model.user.User
import javax.inject.Inject

//TODO: Inject scheduler provider inorder to do mappings in a specified background thread
/**
 * Mapper from [UserApiResponse] to [User]
 */
class UserApiToDomainMapper @Inject constructor(
    private val apiAddressToDomainMapper: ApiAddressToDomainMapper,
    private val apiCompanyToDomainMapper: ApiCompanyToDomainMapper
) {
    fun map(userApiResponse: UserApiResponse): User {
        return if (userApiResponse == null) {
            User.Builder().build()
        } else User.Builder()
            .id(userApiResponse.id)
            .name(userApiResponse.name)
            .userName(userApiResponse.username)
            .email(userApiResponse.email)
            .address(apiAddressToDomainMapper.map(userApiResponse.address))
            .phone(userApiResponse.phone)
            .website(userApiResponse.website)
            .company(apiCompanyToDomainMapper.map(userApiResponse.userCompanyApiResponse))
            .build()
    }
}