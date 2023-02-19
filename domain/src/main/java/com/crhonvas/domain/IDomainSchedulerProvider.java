package com.crhonvas.domain;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

public interface IDomainSchedulerProvider {
    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
