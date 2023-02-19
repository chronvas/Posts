package com.chronvas.btest.activity.posts;

import com.chronvas.btest.activity.posts.viewmodel.PostItem;
import com.chronvas.btest.activity.posts.viewmodel.PostsVieModel;
import com.chronvas.btest.utils.scheduler.ISchedulerProvider;
import com.crhonvas.domain.model.posts.Post;
import com.crhonvas.domain.usecase.IUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.InOrderImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class PostsActivityPresenterTest {
    private static final String POST_1_Body = "post 1 body";
    private static final String POST_1_Title = "title post 1";
    private static final int POST_1_Id = 55;
    private static final int POST_1_UserId = 11;
    private static final Post POST_1 = new Post.Builder()
            .body(POST_1_Body)
            .id(POST_1_Id)
            .title(POST_1_Title)
            .userId(POST_1_UserId)
            .build();
    private static final PostItem POST_ITEM_1 =
            new PostItem(POST_1_UserId, POST_1_Id, POST_1_Title, POST_1_Body);

    private static final String POST_2_Body = "post 2 body";
    private static final int POST_2_Id = 66;
    private static final String POST_2_Title = "title post 2";
    private static final int POST_2_UserId = 22;
    private static final Post POST_2 = new Post.Builder()
            .body(POST_2_Body)
            .id(POST_2_Id)
            .title(POST_2_Title)
            .userId(POST_2_UserId)
            .build();
    private static final PostItem POST_ITEM_2 =
            new PostItem(POST_2_UserId, POST_2_Id, POST_2_Title, POST_2_Body);

    private static final PostsVieModel EMPTY_NOTLOADING_VIEWMODEL = new PostsVieModel(null, true);
    private final static List<PostItem> NORMAL_VIEWMODEL = Arrays.asList(POST_ITEM_1, POST_ITEM_2);
    private final static Single<List<Post>> NORMAL_POST_LIST_SINGLE = Single.just(Arrays.asList(POST_1, POST_2));

    @Mock
    private IUseCase<Single<List<Post>>, Void> postsUseCase;
    @Mock
    private ISchedulerProvider schedulerProvider;
    @Mock
    private IPostsActivityContract.View view;
    private PostsActivityPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(schedulerProvider.computation()).thenReturn(Schedulers.trampoline());
        when(schedulerProvider.io()).thenReturn(Schedulers.trampoline());
        when(schedulerProvider.ui()).thenReturn(Schedulers.trampoline());

        presenter = new PostsActivityPresenter(view, postsUseCase, schedulerProvider);
    }

    @Test
    public void onStart_noError_showPostList() throws Exception {
        when(postsUseCase.execute(any())).thenReturn(NORMAL_POST_LIST_SINGLE);

        presenter.onStart();

        PostsVieModel expected = new PostsVieModel(Arrays.asList(POST_ITEM_1, POST_ITEM_2), false);

        InOrder inOrder = new InOrderImpl(Collections.singletonList(view));
        inOrder.verify(view).setItems(EMPTY_NOTLOADING_VIEWMODEL);
        inOrder.verify(view).setItems(expected);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void onStart_error_showErrorMessage() throws Exception {
        when(postsUseCase.execute(any())).thenReturn(Single.error(new Throwable()));

        presenter.onStart();

        InOrder inOrder = new InOrderImpl(Collections.singletonList(view));
        inOrder.verify(view).setItems(EMPTY_NOTLOADING_VIEWMODEL);
        inOrder.verify(view).errorMessage();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void refresh_noError_showPostsList() throws Exception {
        when(postsUseCase.execute(any())).thenReturn(NORMAL_POST_LIST_SINGLE);

        presenter.onStart();

        PostsVieModel expected = new PostsVieModel(NORMAL_VIEWMODEL, false);

        InOrder inOrder = new InOrderImpl(Collections.singletonList(view));
        inOrder.verify(view).setItems(EMPTY_NOTLOADING_VIEWMODEL);
        inOrder.verify(view).setItems(expected);
        inOrder.verifyNoMoreInteractions();

        presenter.refresh();
        inOrder.verify(view).setItems(EMPTY_NOTLOADING_VIEWMODEL);
        inOrder.verify(view).setItems(expected);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void postItemClicked() throws Exception {
        when(postsUseCase.execute(any())).thenReturn(NORMAL_POST_LIST_SINGLE);

        presenter.onStart();
        presenter.postItemClicked(POST_ITEM_1);

        PostsVieModel expected = new PostsVieModel(NORMAL_VIEWMODEL, false);

        InOrder inOrder = new InOrderImpl(Collections.singletonList(view));
        inOrder.verify(view).setItems(EMPTY_NOTLOADING_VIEWMODEL);
        inOrder.verify(view).setItems(expected);
        inOrder.verify(view).startPostActivityForItem(POST_ITEM_1);
        inOrder.verifyNoMoreInteractions();
    }
}