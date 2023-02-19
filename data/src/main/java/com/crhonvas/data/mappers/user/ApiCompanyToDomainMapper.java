package com.crhonvas.data.mappers.user;

import android.support.annotation.NonNull;

import com.crhonvas.data.remote.model.user.CompanyApiResponse;
import com.crhonvas.domain.model.user.Company;

import javax.inject.Inject;

//TODO: Inject scheduler provider inorder to do mappings in a specified background thread

/**
 * Mapper from {@link CompanyApiResponse} to {@link Company}
 */
public class ApiCompanyToDomainMapper {

    @Inject
    public ApiCompanyToDomainMapper() {}

    @NonNull
    public Company map(@NonNull CompanyApiResponse companyApiResponse) {
        if (companyApiResponse == null) {
            return new Company.Builder().build();
        }
        return new Company.Builder()
                .name(companyApiResponse.getName())
                .catchPhrase(companyApiResponse.getCatchPhrase())
                .bs(companyApiResponse.getBs())
                .build();
    }
}
