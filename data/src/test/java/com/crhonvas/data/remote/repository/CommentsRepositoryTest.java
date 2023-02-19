package com.crhonvas.data.remote.repository;

import android.os.Build;

import com.crhonvas.data.mappers.comments.CommentsApiToDomainMapper;
import com.crhonvas.data.remote.api.comments.ICommentsApi;
import com.crhonvas.data.remote.model.comments.CommentApiResponse;
import com.crhonvas.data.utils.ListUtils;
import com.crhonvas.domain.IDomainSchedulerProvider;
import com.crhonvas.domain.model.comments.Comment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.when;


public class CommentsRepositoryTest {

    private static final CommentsApiToDomainMapper commentsApiToDomainMapper = new CommentsApiToDomainMapper();

    @Mock
    ICommentsApi commentsApi;
    @Mock
    private IDomainSchedulerProvider domainSchedulerProvider;

    private CommentsRepository commentsRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ListUtils.setBuildVersionProvider(() -> Build.VERSION_CODES.N);

        when(domainSchedulerProvider.computation()).thenReturn(Schedulers.trampoline());
        when(domainSchedulerProvider.io()).thenReturn(Schedulers.trampoline());
        when(domainSchedulerProvider.ui()).thenReturn(Schedulers.trampoline());

        commentsRepository = new CommentsRepository(commentsApi, commentsApiToDomainMapper, domainSchedulerProvider);
    }

    @Test
    public void getCommentById() throws Exception {
        CommentApiResponse input = new CommentApiResponse.Builder().id(242).build();
        when(commentsApi.getCommentForCommentId(242)).thenReturn(Single.just(input));
        Single<Comment> actual = commentsRepository.getCommentById(242);

        Comment expected = commentsApiToDomainMapper.map(input);

        TestObserver<Comment> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);
        testObserver.assertSubscribed();
        testObserver.assertNoErrors();
        testObserver.assertValue(expected);
    }

    @Test
    public void getCommentById_error() throws Exception {
        when(commentsApi.getCommentForCommentId(222)).thenReturn(Single.error(new Throwable()));
        Single<Comment> actual = commentsRepository.getCommentById(222);

        TestObserver<Comment> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);
        testObserver.assertSubscribed();
        Assert.assertEquals(testObserver.valueCount(), 0);
        testObserver.assertNoValues();
    }

    @Test
    public void getComments() throws Exception {
        List<CommentApiResponse> input = Collections.singletonList(new CommentApiResponse.Builder().id(242).build());
        when(commentsApi.getComments()).thenReturn(Single.just(input));
        Single<List<Comment>> actual = commentsRepository.getComments();

        List<Comment> expected = commentsApiToDomainMapper.map(input);

        TestObserver<List<Comment>> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);
        testObserver.assertSubscribed();
        testObserver.assertNoErrors();
        testObserver.assertValue(expected);
    }

    @Test
    public void getComments_error() throws Exception {
        when(commentsApi.getComments()).thenReturn(Single.error(new Throwable()));
        Single<List<Comment>> actual = commentsRepository.getComments();

        TestObserver<List<Comment>> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);
        testObserver.assertSubscribed();
        Assert.assertEquals(testObserver.valueCount(), 0);
        testObserver.assertNoValues();
    }

    @Test
    public void getCommentsForPostId() throws Exception {
        List<CommentApiResponse> input = Collections.singletonList(new CommentApiResponse.Builder().id(242).build());
        when(commentsApi.getCommentsForPostId(242)).thenReturn(Single.just(input));
        Single<List<Comment>> actual = commentsRepository.getCommentsForPostId(242);

        List<Comment> expected = commentsApiToDomainMapper.map(input);

        TestObserver<List<Comment>> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);
        testObserver.assertSubscribed();
        testObserver.assertNoErrors();
        testObserver.assertValue(expected);
    }

    @Test
    public void getCommentsForPostId_error() throws Exception {
        when(commentsApi.getCommentsForPostId(242)).thenReturn(Single.error(new Throwable()));
        Single<List<Comment>> actual = commentsRepository.getCommentsForPostId(242);

        TestObserver<List<Comment>> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);
        testObserver.assertSubscribed();
        Assert.assertEquals(testObserver.valueCount(), 0);
        testObserver.assertNoValues();
    }
}