package com.crhonvas.domain.usecase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crhonvas.domain.model.user.User;
import com.crhonvas.domain.repo.IUserRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class UserUseCase implements IUseCase<Single<User>, Integer> {

    @NonNull
    private final IUserRepository userRepository;

    @Inject
    public UserUseCase(@NonNull IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Single<User> execute(@Nullable Integer userId) {
        return userRepository.getUser(userId);
    }
}
