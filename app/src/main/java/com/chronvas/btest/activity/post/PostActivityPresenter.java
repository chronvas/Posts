package com.chronvas.btest.activity.post;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chronvas.btest.activity.post.viewmodel.PostViewModel;
import com.chronvas.btest.activity.posts.viewmodel.PostItem;
import com.chronvas.btest.injection.ActivityScoped;
import com.chronvas.btest.utils.scheduler.ISchedulerProvider;
import com.crhonvas.domain.model.comments.Comment;
import com.crhonvas.domain.model.user.User;
import com.crhonvas.domain.usecase.IUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

@ActivityScoped
public class PostActivityPresenter implements IPostActivityContract.Presenter {

    private final IUseCase<Single<List<Comment>>, Integer> commentsForPostIdUseCase;
    private final ISchedulerProvider schedulerProvider;
    private final IUseCase<Single<User>, Integer> userUseCase;
    private IPostActivityContract.View view;
    private CompositeDisposable disposables = new CompositeDisposable();
    private PostItem postItem;

    @Inject
    public PostActivityPresenter(IPostActivityContract.View view,
                                 IUseCase<Single<User>, Integer> userUseCase,
                                 IUseCase<Single<List<Comment>>, Integer> commentsForPostIdUseCase,
                                 ISchedulerProvider schedulerProvider) {
        this.view = view;
        this.userUseCase = userUseCase;
        this.commentsForPostIdUseCase = commentsForPostIdUseCase;
        this.schedulerProvider = schedulerProvider;
        this.disposables = new CompositeDisposable();
    }

    private void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    private void refreshDisposables() {
        dispose();
        disposables = new CompositeDisposable();
    }

    @Override
    public void onDestroy() {
        dispose();
    }

    @Override
    public void onStart(@Nullable PostItem postItem) {
        this.postItem = postItem;
        if (postItem != null) {
            view.setItem(new PostViewModel(
                    postItem.getPostTitle(), postItem.getBody(), null, null, false)
            );
            getUserName(postItem);
            getCommentsSize(postItem);
        }
    }

    private void getCommentsSize(PostItem postItem) {
        disposables.add(commentsForPostIdUseCase.execute(postItem.getPostId())
                .observeOn(schedulerProvider.ui())
                .subscribe(comments -> view.setItem(new PostViewModel(postItem.getPostTitle(), postItem.getBody(),
                                null, comments.size(), false)),
                        throwable -> view.commentsSizeError()));
    }

    private void getUserName(PostItem postItem) {
        disposables.add(userUseCase.execute(postItem.getUserId())
                .observeOn(schedulerProvider.ui())
                .subscribe(user -> view.setItem(new PostViewModel(postItem.getPostTitle(), postItem.getBody(),
                        user.getName(), null, false)
                ), error -> view.userNameErrorMessage()));
    }

    @Override
    public void onRefresh(PostViewModel postViewModel) {
        view.setItem(new PostViewModel(
                postItem.getPostTitle(), postItem.getBody(), null, null, true)
        );
        refreshDisposables();
        getUserName(postItem);
        getCommentsSize(postItem);
    }
}
