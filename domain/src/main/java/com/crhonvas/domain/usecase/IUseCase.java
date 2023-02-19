package com.crhonvas.domain.usecase;

import androidx.annotation.Nullable;

public interface IUseCase<T, P> {
    //TODO check this annotation
    T execute(@Nullable P params);
}
