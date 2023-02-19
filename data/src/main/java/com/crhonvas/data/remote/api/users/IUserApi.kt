package com.crhonvas.data.remote.api.users

import com.crhonvas.data.remote.model.user.UserApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IUserApi {
    @get:GET("users")
    val users: Single<List<UserApiResponse?>?>?

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int?): Single<UserApiResponse>
}