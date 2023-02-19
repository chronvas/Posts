package com.crhonvas.data.mappers.user;

import com.crhonvas.data.remote.model.user.UserApiResponse;
import com.crhonvas.domain.model.user.Address;
import com.crhonvas.domain.model.user.Company;
import com.crhonvas.domain.model.user.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link UserApiToDomainMapper}
 */
public class UserApiToDomainMapperTest {

    private static final UserApiResponse userApiResponse = new UserApiResponse.Builder()
            .id(123414)
            .name("name")
            .username("username")
            .email("email")
            .phone("phone")
            .website("website")
            .build();

    @Mock
    ApiAddressToDomainMapper apiAddressToDomainMapper;
    @Mock
    ApiCompanyToDomainMapper apiCompanyToDomainMapper;

    private UserApiToDomainMapper userApiToDomainMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userApiToDomainMapper = new UserApiToDomainMapper(apiAddressToDomainMapper, apiCompanyToDomainMapper);
    }

    @Test
    public void map() throws Exception {
        when(apiAddressToDomainMapper.map(any())).thenReturn(new Address.Builder().build());
        when(apiCompanyToDomainMapper.map(any())).thenReturn(new Company.Builder().build());
        User actual = userApiToDomainMapper.map(userApiResponse);

        verify(apiAddressToDomainMapper,times(1)).map(any());
        verify(apiCompanyToDomainMapper,times(1)).map(any());
        compare(actual, userApiResponse);
    }

    @Test
    public void map_null_returnEmpty() throws Exception {
        User actual = userApiToDomainMapper.map(null);
        compare(actual, new UserApiResponse.Builder().build());
    }

    private static void compare(User actual, UserApiResponse expected) {
        Assert.assertEquals(expected.getEmail(), actual.getEmail());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getUsername(), actual.getUserName());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getWebsite(), actual.getWebsite());
        Assert.assertEquals(expected.getPhone(), actual.getPhone());
    }
}