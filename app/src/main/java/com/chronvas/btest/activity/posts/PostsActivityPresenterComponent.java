package com.chronvas.btest.activity.posts;

import com.chronvas.btest.injection.ActivityScoped;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScoped
@Subcomponent
public interface PostsActivityPresenterComponent extends AndroidInjector<PostsActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PostsActivity> {}
}