package com.crhonvas.data.remote.api.users;

import com.crhonvas.data.remote.model.user.UserApiResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IUserApi {
    @GET("users")
    Single<List<UserApiResponse>> getUsers();

    @GET("users/{id}")
    Single<UserApiResponse> getUser(@Path("id") Integer id);
}
