package com.chronvas.btest.activity.posts;

import android.app.Activity;

import com.crhonvas.domain.model.posts.Post;
import com.crhonvas.domain.model.user.User;
import com.crhonvas.domain.usecase.IUseCase;
import com.crhonvas.domain.usecase.PostsUseCase;
import com.crhonvas.domain.usecase.UserUseCase;

import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import io.reactivex.Single;

@Module(subcomponents = PostsActivityPresenterComponent.class)
public abstract class PostsActivityPresenterModule {

    @Binds
    @IntoMap
    @ActivityKey(PostsActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bind(PostsActivityPresenterComponent.Builder builder);

    @Binds
    abstract IPostsActivityContract.Presenter postsListPresenter(PostsActivityPresenter presenter);

    @Binds
    abstract IPostsActivityContract.View postsListView(PostsActivity view);

    @Binds
    abstract IUseCase<Single<List<Post>>, Void> postsUseCase(PostsUseCase postsUseCase);
}
