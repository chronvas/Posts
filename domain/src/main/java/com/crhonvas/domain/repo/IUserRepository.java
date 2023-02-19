package com.crhonvas.domain.repo;

import com.crhonvas.domain.model.user.User;

import io.reactivex.Single;

public interface IUserRepository {
    Single<User> getUser(Integer userId);
}
