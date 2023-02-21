package com.crhonvas.domain.usecase

import com.crhonvas.domain.model.user.User
import com.crhonvas.domain.repo.IUserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: IUserRepository) : IUseCase<Single<User>, Int> {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun execute(userId: Int): Single<User> {
        return userRepository.getUser(userId)
    }
}