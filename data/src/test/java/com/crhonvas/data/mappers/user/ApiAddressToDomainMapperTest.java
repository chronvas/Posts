package com.crhonvas.data.mappers.user;

import com.crhonvas.data.remote.model.user.AddressApiResponse;
import com.crhonvas.data.remote.model.user.GeoApiResponse;
import com.crhonvas.domain.model.user.Address;
import com.crhonvas.domain.model.user.Geo;

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
 * Tests for {@link ApiAddressToDomainMapper}
 */
public class ApiAddressToDomainMapperTest {
    private static final AddressApiResponse addressApiResponse = new AddressApiResponse.Builder()
            .city("city")
            .geo(new GeoApiResponse.Builder().lng("Asda").lat("232").build())
            .street("str")
            .suite("suite")
            .zipCode("zipc")
            .build();
    @Mock
    ApiGeoToDomainMapper apiGeoToDomainMapper;

    private ApiAddressToDomainMapper apiAddressToDomainMapper;

    private static void compare(Address actual, AddressApiResponse expected) {
        Assert.assertEquals(expected.getStreet(), actual.getStreet());
        Assert.assertEquals(expected.getSuite(), actual.getSuite());
        Assert.assertEquals(expected.getCity(), actual.getCity());
        Assert.assertEquals(expected.getZipCode(), actual.getZipCode());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        apiAddressToDomainMapper = new ApiAddressToDomainMapper(apiGeoToDomainMapper);
    }

    @Test
    public void map() throws Exception {
        when(apiGeoToDomainMapper.map(any())).thenReturn(new Geo.Builder().build());
        Address actual = apiAddressToDomainMapper.map(addressApiResponse);

        verify(apiGeoToDomainMapper, times(1)).map(any());
        compare(actual, addressApiResponse);
    }

    @Test
    public void map_null_returnEmpty() throws Exception {
        Address actual = apiAddressToDomainMapper.map(null);
        AddressApiResponse addressApiResponse = new AddressApiResponse.Builder().build();

        verify(apiGeoToDomainMapper, times(0)).map(any());
        compare(actual, addressApiResponse);
    }
}