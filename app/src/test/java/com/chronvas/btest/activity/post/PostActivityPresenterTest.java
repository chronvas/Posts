package com.chronvas.btest.activity.post;

import com.chronvas.btest.activity.post.viewmodel.PostViewModel;
import com.chronvas.btest.activity.posts.viewmodel.PostItem;
import com.chronvas.btest.utils.scheduler.ISchedulerProvider;
import com.crhonvas.domain.model.comments.Comment;
import com.crhonvas.domain.model.user.User;
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

import static org.mockito.Mockito.when;

public class PostActivityPresenterTest {

    private static final int ITITIAL_POST_ITEM_UserId = 222;
    private static final int ITITIAL_POST_ITEM_PostId = 333;
    private static final String ITITIAL_POST_ITEM_PostTitle = "initial post title";
    private static final String ITITIAL_POST_ITEM_PostBody = "initial post body";
    private static final PostItem ITITIAL_POST_ITEM = new PostItem(ITITIAL_POST_ITEM_UserId, ITITIAL_POST_ITEM_PostId, ITITIAL_POST_ITEM_PostTitle, ITITIAL_POST_ITEM_PostBody);

    private static final int COMMENT_1_Id = 11;
    private static final int COMMENT_2_Id = 22;
    private static final Comment COMMENT_1 = new Comment.Builder().id(COMMENT_1_Id).build();
    private static final Comment COMMENT_2 = new Comment.Builder().id(COMMENT_2_Id).build();
    private static final List<Comment> COMMENTS = Arrays.asList(COMMENT_1, COMMENT_2);
    private static final Single<List<Comment>> COMMENTS_SINGLE = Single.just(COMMENTS);

    private static final int USER_Id = 5544;
    private static final String USER_Name = "User name";
    private static final String USER_UserName = "User UserName";
    private static final User USER = new User.Builder().id(USER_Id).name(USER_Name).userName(USER_UserName).build();
    private static final Single<User> USER_SINGLE = Single.just(USER);

    //These informations come from the previous activity. Some of the fields are null as expected.
    private static final PostViewModel INITIAL_POSTVIEWMODEL = new PostViewModel(ITITIAL_POST_ITEM_PostTitle, ITITIAL_POST_ITEM_PostBody, null, null, false);

    @Mock
    private IUseCase<Single<User>, Integer> userUseCase;
    @Mock
    private ISchedulerProvider schedulerProvider;
    @Mock
    private IPostActivityContract.View view;
    @Mock
    private IUseCase<Single<List<Comment>>, Integer> commentsForPostIdUseCase;
    private PostActivityPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(schedulerProvider.computation()).thenReturn(Schedulers.trampoline());
        when(schedulerProvider.io()).thenReturn(Schedulers.trampoline());
        when(schedulerProvider.ui()).thenReturn(Schedulers.trampoline());

        presenter = new PostActivityPresenter(view, userUseCase, commentsForPostIdUseCase, schedulerProvider);
    }

    //region onStart
    @Test
    public void onStart_noError() throws Exception {
        when(userUseCase.execute(ITITIAL_POST_ITEM_UserId)).thenReturn(USER_SINGLE);
        when(commentsForPostIdUseCase.execute(ITITIAL_POST_ITEM_PostId)).thenReturn(COMMENTS_SINGLE);

        presenter.onStart(ITITIAL_POST_ITEM);

        PostViewModel postViewModel_UserData = new PostViewModel(ITITIAL_POST_ITEM_PostTitle, ITITIAL_POST_ITEM_PostBody, USER_Name, null, false);
        PostViewModel postViewModel_CommentsData = new PostViewModel(ITITIAL_POST_ITEM_PostTitle, ITITIAL_POST_ITEM_PostBody, null, 2, false);

        InOrder inOrder = new InOrderImpl(Collections.singletonList(view));
        inOrder.verify(view).setItem(INITIAL_POSTVIEWMODEL);
        inOrder.verify(view).setItem(postViewModel_UserData);
        inOrder.verify(view).setItem(postViewModel_CommentsData);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void onStart_userUseCaseError_showError() throws Exception {
        when(userUseCase.execute(ITITIAL_POST_ITEM_UserId)).thenReturn(Single.error(new Throwable()));
        when(commentsForPostIdUseCase.execute(ITITIAL_POST_ITEM_PostId)).thenReturn(COMMENTS_SINGLE);

        presenter.onStart(ITITIAL_POST_ITEM);

        PostViewModel postViewModel_CommentsData = new PostViewModel(ITITIAL_POST_ITEM_PostTitle, ITITIAL_POST_ITEM_PostBody, null, 2, false);

        InOrder inOrder = new InOrderImpl(Collections.singletonList(view));
        inOrder.verify(view).setItem(INITIAL_POSTVIEWMODEL);
        inOrder.verify(view).userNameErrorMessage();
        inOrder.verify(view).setItem(postViewModel_CommentsData);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void onStart_commentsForPostIdUseCaseError_showError() throws Exception {
        when(userUseCase.execute(ITITIAL_POST_ITEM_UserId)).thenReturn(USER_SINGLE);
        when(commentsForPostIdUseCase.execute(ITITIAL_POST_ITEM_PostId)).thenReturn(Single.error(new Throwable()));

        presenter.onStart(ITITIAL_POST_ITEM);

        PostViewModel postViewModel_UserData = new PostViewModel(ITITIAL_POST_ITEM_PostTitle, ITITIAL_POST_ITEM_PostBody, USER_Name, null, false);

        InOrder inOrder = new InOrderImpl(Collections.singletonList(view));
        inOrder.verify(view).setItem(INITIAL_POSTVIEWMODEL);
        inOrder.verify(view).setItem(postViewModel_UserData);
        inOrder.verify(view).commentsSizeError();
        inOrder.verifyNoMoreInteractions();
    }
    //endregion

    //region onRefresh
    @Test
    public void onRefresh_noError() throws Exception {
        when(userUseCase.execute(ITITIAL_POST_ITEM_UserId)).thenReturn(USER_SINGLE);
        when(commentsForPostIdUseCase.execute(ITITIAL_POST_ITEM_PostId)).thenReturn(COMMENTS_SINGLE);

        presenter.onStart(ITITIAL_POST_ITEM);

        PostViewModel postViewModel_UserData = new PostViewModel(ITITIAL_POST_ITEM_PostTitle, ITITIAL_POST_ITEM_PostBody, USER_Name, null, false);
        PostViewModel postViewModel_CommentsData = new PostViewModel(ITITIAL_POST_ITEM_PostTitle, ITITIAL_POST_ITEM_PostBody, null, 2, false);

        InOrder inOrder = new InOrderImpl(Collections.singletonList(view));
        inOrder.verify(view).setItem(INITIAL_POSTVIEWMODEL);
        inOrder.verify(view).setItem(postViewModel_UserData);
        inOrder.verify(view).setItem(postViewModel_CommentsData);
        inOrder.verifyNoMoreInteractions();

        presenter.onRefresh(INITIAL_POSTVIEWMODEL);

        inOrder.verify(view).setItem(postViewModel_UserData);
        inOrder.verify(view).setItem(postViewModel_CommentsData);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void onRefresh_error_showError() throws Exception {
        when(userUseCase.execute(ITITIAL_POST_ITEM_UserId)).thenReturn(USER_SINGLE);
        when(commentsForPostIdUseCase.execute(ITITIAL_POST_ITEM_PostId)).thenReturn(COMMENTS_SINGLE);

        presenter.onStart(ITITIAL_POST_ITEM);

        PostViewModel postViewModel_UserData = new PostViewModel(ITITIAL_POST_ITEM_PostTitle, ITITIAL_POST_ITEM_PostBody, USER_Name, null, false);
        PostViewModel postViewModel_CommentsData = new PostViewModel(ITITIAL_POST_ITEM_PostTitle, ITITIAL_POST_ITEM_PostBody, null, 2, false);

        InOrder inOrder = new InOrderImpl(Collections.singletonList(view));
        inOrder.verify(view).setItem(INITIAL_POSTVIEWMODEL);
        inOrder.verify(view).setItem(postViewModel_UserData);
        inOrder.verify(view).setItem(postViewModel_CommentsData);
        inOrder.verifyNoMoreInteractions();

        when(userUseCase.execute(ITITIAL_POST_ITEM_UserId)).thenReturn(Single.error(new Throwable()));

        presenter.onRefresh(INITIAL_POSTVIEWMODEL);

        inOrder.verify(view).userNameErrorMessage();
        inOrder.verify(view).setItem(postViewModel_CommentsData);
        inOrder.verifyNoMoreInteractions();
    }
    //endregion
}