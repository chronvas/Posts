package com.crhonvas.data.remote.repository;

import com.crhonvas.data.mappers.user.ApiAddressToDomainMapper;
import com.crhonvas.data.mappers.user.ApiCompanyToDomainMapper;
import com.crhonvas.data.mappers.user.ApiGeoToDomainMapper;
import com.crhonvas.data.mappers.user.UserApiToDomainMapper;
import com.crhonvas.data.remote.api.users.IUserApi;
import com.crhonvas.data.remote.model.user.UserApiResponse;
import com.crhonvas.domain.IDomainSchedulerProvider;
import com.crhonvas.domain.model.user.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.when;

public class UserRepositoryTest {

    @Mock
    private IUserApi userApi;
    @Mock
    private IDomainSchedulerProvider domainSchedulerProvider;

    private UserRepository userRepository;

    private ApiGeoToDomainMapper apiGeoToDomainMapper = new ApiGeoToDomainMapper();
    private ApiAddressToDomainMapper apiAddressToDomainMapper = new ApiAddressToDomainMapper(apiGeoToDomainMapper);
    private ApiCompanyToDomainMapper apiCompanyToDomainMapper = new ApiCompanyToDomainMapper();
    private UserApiToDomainMapper userApiToDomainMapper = new UserApiToDomainMapper(apiAddressToDomainMapper, apiCompanyToDomainMapper);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(domainSchedulerProvider.computation()).thenReturn(Schedulers.trampoline());
        when(domainSchedulerProvider.io()).thenReturn(Schedulers.trampoline());
        when(domainSchedulerProvider.ui()).thenReturn(Schedulers.trampoline());

        userRepository = new UserRepository(userApi, userApiToDomainMapper, domainSchedulerProvider);
    }

    @Test
    public void getUser() throws Exception {
        UserApiResponse input = new UserApiResponse.Builder().id(4845).build();
        when(userApi.getUser(4845)).thenReturn(Single.just(input));

        Single<User> actual = userRepository.getUser(4845);
        User expected = userApiToDomainMapper.map(input);

        TestObserver<User> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);

        testObserver.assertValue(expected);
    }

    @Test
    public void getUser_Error() throws Exception {
        when(userApi.getUser(4845)).thenReturn(Single.error(new Throwable("error")));

        Single<User> actual = userRepository.getUser(4845);

        TestObserver<User> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);
        testObserver.assertSubscribed();
        testObserver.assertNoValues();
        Assert.assertEquals(testObserver.errorCount(), 1);
    }
}