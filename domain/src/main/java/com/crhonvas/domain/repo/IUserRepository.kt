package com.crhonvas.domain.repo

import com.crhonvas.domain.model.user.User
import io.reactivex.Single

interface IUserRepository {
    fun getUser(userId: Int): Single<User>
}