package com.chronvas.btest.activity.post;

import android.app.Activity;

import com.crhonvas.domain.model.comments.Comment;
import com.crhonvas.domain.model.user.User;
import com.crhonvas.domain.usecase.CommentsForPostIdUseCase;
import com.crhonvas.domain.usecase.CommentsUseCase;
import com.crhonvas.domain.usecase.IUseCase;
import com.crhonvas.domain.usecase.UserUseCase;

import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.multibindings.IntoMap;
import io.reactivex.Single;

@Module
@InstallIn(ViewModelComponent.class)
public abstract class PostActivityPresenterModule {

    @Binds
    @IntoMap
    @ActivityKey(PostActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bind(PostActivityPresenterComponent.Builder builder);

    @Binds
    abstract IPostActivityContract.Presenter postListPresenter(PostActivityPresenter presenter);

    @Binds
    abstract IPostActivityContract.View postListView(PostActivity view);

    @Binds
    abstract IUseCase<Single<User>, Integer> userUseCase(UserUseCase userUseCase);

    @Binds
    abstract IUseCase<Single<List<Comment>>, Integer> commentsFroPostIdUseCase(CommentsForPostIdUseCase commentsUseCase);
}
