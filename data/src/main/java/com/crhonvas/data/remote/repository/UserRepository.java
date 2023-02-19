package com.crhonvas.data.remote.repository;

import com.crhonvas.data.mappers.user.UserApiToDomainMapper;
import com.crhonvas.data.remote.api.users.IUserApi;
import com.crhonvas.domain.IDomainSchedulerProvider;
import com.crhonvas.domain.model.user.User;
import com.crhonvas.domain.repo.IUserRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class UserRepository implements IUserRepository {

    private final IUserApi userApi;
    private final UserApiToDomainMapper userMapper;
    private final IDomainSchedulerProvider schedulerProvider;

    @Inject
    public UserRepository(IUserApi userApi, UserApiToDomainMapper userMapper,
                          IDomainSchedulerProvider schedulerProvider) {
        this.userApi = userApi;
        this.userMapper = userMapper;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public Single<User> getUser(Integer userId) {
        return userApi.getUser(userId)
                .subscribeOn(schedulerProvider.io())
                .map(userMapper::map)
                .subscribeOn(schedulerProvider.computation());
    }
}
