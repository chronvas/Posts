package com.chronvas.btest.activity.post;

import com.chronvas.btest.injection.ActivityScoped;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScoped
@Subcomponent
public interface PostActivityPresenterComponent extends AndroidInjector<PostActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PostActivity> {}
}