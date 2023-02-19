package com.crhonvas.domain.usecase;

import android.support.annotation.Nullable;

public interface IUseCase<T, P> {
    //TODO check this annotation
    T execute(@Nullable P params);
}
