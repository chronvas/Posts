package com.chronvas.btest.utils.scheduler;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SchedulerProviderModule {

    @Binds
    @Singleton
    abstract ISchedulerProvider provideSchedulerProvider(SchedulerProvider schedulerProvider);
}
