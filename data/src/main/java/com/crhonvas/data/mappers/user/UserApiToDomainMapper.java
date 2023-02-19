package com.crhonvas.data.mappers.user;

import androidx.annotation.NonNull;

import com.crhonvas.data.remote.model.user.UserApiResponse;
import com.crhonvas.domain.model.user.User;

import javax.inject.Inject;

//TODO: Inject scheduler provider inorder to do mappings in a specified background thread

/**
 * Mapper from {@link UserApiResponse} to {@link User}
 */
public class UserApiToDomainMapper {

    private final ApiAddressToDomainMapper apiAddressToDomainMapper;
    private final ApiCompanyToDomainMapper apiCompanyToDomainMapper;

    @Inject
    public UserApiToDomainMapper(ApiAddressToDomainMapper apiAddressToDomainMapper,
                                 ApiCompanyToDomainMapper apiCompanyToDomainMapper) {
        this.apiAddressToDomainMapper = apiAddressToDomainMapper;
        this.apiCompanyToDomainMapper = apiCompanyToDomainMapper;
    }

    @NonNull
    public User map(@NonNull UserApiResponse userApiResponse) {
        if (userApiResponse == null) {
            return new User.Builder().build();
        }
        return new User.Builder()
                .id(userApiResponse.getId())
                .name(userApiResponse.getName())
                .userName(userApiResponse.getUsername())
                .email(userApiResponse.getEmail())
                .address(apiAddressToDomainMapper.map(userApiResponse.getAddress()))
                .phone(userApiResponse.getPhone())
                .website(userApiResponse.getWebsite())
                .company(apiCompanyToDomainMapper.map(userApiResponse.getCompany()))
                .build();
    }
}
