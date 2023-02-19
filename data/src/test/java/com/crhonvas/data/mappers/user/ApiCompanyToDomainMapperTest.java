package com.crhonvas.data.mappers.user;

import com.crhonvas.data.remote.model.user.CompanyApiResponse;
import com.crhonvas.domain.model.user.Company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * Test for {@link ApiCompanyToDomainMapper}
 */
public class ApiCompanyToDomainMapperTest {

    private final static CompanyApiResponse companyApiResponse = new CompanyApiResponse.Builder().bs("bs").catchPhrase("catchphrase").name("cname").build();

    private ApiCompanyToDomainMapper apiCompanyToDomainMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        apiCompanyToDomainMapper = new ApiCompanyToDomainMapper();
    }

    @Test
    public void map() throws Exception {
        Company actual = apiCompanyToDomainMapper.map(companyApiResponse);
        compare(actual, companyApiResponse);
    }

    @Test
    public void map_null_returnEmpty() throws Exception {
        Company actual = apiCompanyToDomainMapper.map(null);
        compare(actual, new CompanyApiResponse.Builder().build());
    }

    private static void compare(Company actual, CompanyApiResponse expected) {
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getBs(), actual.getBs());
        Assert.assertEquals(expected.getCatchPhrase(), actual.getCatchPhrase());
    }
}