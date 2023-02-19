package com.crhonvas.data.mappers.user;

import com.crhonvas.data.remote.model.user.GeoApiResponse;
import com.crhonvas.domain.model.user.Geo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link ApiGeoToDomainMapper}
 */
public class ApiGeoToDomainMapperTest {
    private ApiGeoToDomainMapper apiGeoToDomainMapper;
    @Before
    public void setUp() throws Exception {
        apiGeoToDomainMapper = new ApiGeoToDomainMapper();
    }

    private static final GeoApiResponse geoApiResponse = new GeoApiResponse.Builder()
            .lat("lat")
            .lng("lng").build();
    @Test
    public void map() throws Exception {
        Geo actual = apiGeoToDomainMapper.map(geoApiResponse);

        compare(actual, geoApiResponse);
    }
     @Test
    public void map_null_returnEmpty() throws Exception {
        Geo actual = apiGeoToDomainMapper.map(null);

        compare(actual, new GeoApiResponse.Builder().build());
    }

    private static void compare(Geo actual, GeoApiResponse expected){
        Assert.assertEquals(expected.getLat(),actual.getLat());
        Assert.assertEquals(expected.getLng(),actual.getLng());
    }
}