package com.crhonvas.data.remote.repository

import com.crhonvas.data.mappers.user.UserApiToDomainMapper
import com.crhonvas.data.remote.api.users.IUserApi
import com.crhonvas.data.remote.model.user.UserApiResponse
import com.crhonvas.domain.IDomainSchedulerProvider
import com.crhonvas.domain.model.user.User
import com.crhonvas.domain.repo.IUserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: IUserApi, private val userMapper: UserApiToDomainMapper,
    private val schedulerProvider: IDomainSchedulerProvider
) : IUserRepository {
    override fun getUser(userId: Int): Single<User> {
        return userApi.getUser(userId)
            .subscribeOn(schedulerProvider.io())
            .map { userApiResponse: UserApiResponse -> userMapper.map(userApiResponse) }
            .subscribeOn(schedulerProvider.computation())
    }
}