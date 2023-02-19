package com.chronvas.btest.activity.posts;

import com.chronvas.btest.activity.posts.viewmodel.PostItem;
import com.chronvas.btest.activity.posts.viewmodel.PostsVieModel;
import com.chronvas.btest.injection.ActivityScoped;
import com.chronvas.btest.utils.scheduler.ISchedulerProvider;
import com.chronvas.btest.utils.ListUtils;
import com.crhonvas.domain.model.posts.Post;
import com.crhonvas.domain.usecase.IUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

@ActivityScoped
public class PostsActivityPresenter implements IPostsActivityContract.Presenter {

    private final IUseCase<Single<List<Post>>, Void> postsUseCase;
    private final ISchedulerProvider schedulerProvider;
    private IPostsActivityContract.View view;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public PostsActivityPresenter(IPostsActivityContract.View view,
                                  IUseCase<Single<List<Post>>, Void> postsUseCase,
                                  ISchedulerProvider schedulerProvider) {
        this.view = view;
        this.postsUseCase = postsUseCase;
        this.schedulerProvider = schedulerProvider;
        this.disposables = new CompositeDisposable();
    }

    private void disposeAll() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    private void refreshDisposables() {
        disposeAll();
        disposables = new CompositeDisposable();
    }

    @Override
    public void onDestroy() {
        disposeAll();
    }

    @Override
    public void onStart() {
        getPosts();
    }

    private void getPosts() {
        disposables.add(postsUseCase.execute(null)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe(disposable -> view.setItems(new PostsVieModel(null, true)))
                .map(posts -> {
                    List<PostItem> postItems = ListUtils.convert(posts,
                            post -> new PostItem(post.getUserId(), post.getId(), post.getTitle(), post.getBody()));
                    return new PostsVieModel(postItems, false);
                })
                .subscribe(postsVieModel -> {
                    view.setItems(postsVieModel);
                }, error -> view.errorMessage())
        );
    }

    @Override
    public void postItemClicked(PostItem post) {
        view.startPostActivityForItem(post);
    }

    @Override
    public void refresh() {
        refreshDisposables();
        onStart();
    }
}
