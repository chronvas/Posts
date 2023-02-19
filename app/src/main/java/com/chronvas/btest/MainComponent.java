package com.chronvas.btest;

import com.chronvas.btest.activity.post.PostActivityPresenterModule;
import com.chronvas.btest.activity.posts.PostsActivityPresenterModule;
import com.chronvas.btest.repo.RepositoryModule;
import com.chronvas.btest.utils.network.NetworkModule;
import com.chronvas.btest.utils.scheduler.SchedulerProviderModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                MainModule.class,
                PostsActivityPresenterModule.class,
                PostActivityPresenterModule.class,
                RepositoryModule.class,
                SchedulerProviderModule.class,
                NetworkModule.class
        }
)
@Singleton
public interface MainComponent extends AndroidInjector<Main> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<Main> {
    }
}
