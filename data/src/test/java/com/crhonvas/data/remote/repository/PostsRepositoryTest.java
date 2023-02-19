package com.crhonvas.data.remote.repository;

import android.os.Build;

import com.crhonvas.data.mappers.posts.PostsApiToDomainMapper;
import com.crhonvas.data.remote.api.posts.IPostsApi;
import com.crhonvas.data.remote.model.posts.PostsApiResponse;
import com.crhonvas.data.utils.ListUtils;
import com.crhonvas.domain.IDomainSchedulerProvider;
import com.crhonvas.domain.model.posts.Post;

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

public class PostsRepositoryTest {
    @Mock
    private IPostsApi postsApi;
    @Mock
    private IDomainSchedulerProvider domainSchedulerProvider;

    private PostsApiToDomainMapper postsApiToDomainMapper = new PostsApiToDomainMapper();

    private PostsRepository postsRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ListUtils.setBuildVersionProvider(() -> Build.VERSION_CODES.N);

        when(domainSchedulerProvider.computation()).thenReturn(Schedulers.trampoline());
        when(domainSchedulerProvider.io()).thenReturn(Schedulers.trampoline());
        when(domainSchedulerProvider.ui()).thenReturn(Schedulers.trampoline());

        postsRepository = new PostsRepository(postsApi, postsApiToDomainMapper, domainSchedulerProvider);
    }

    @Test
    public void getPost() throws Exception {
        when(postsApi.getPost(2324)).thenReturn(Single.just(
                new PostsApiResponse.Builder().id(2324).build())
        );
        Single<Post> actual = postsRepository.getPost(2324);

        Post expected = postsApiToDomainMapper.map(new PostsApiResponse.Builder().id(2324).build());

        TestObserver<Post> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);
        testObserver.assertSubscribed();
        testObserver.assertNoErrors();
        testObserver.assertValue(expected);
    }

    @Test
    public void getPost_error() throws Exception {
        when(postsApi.getPost(2324)).thenReturn(Single.error(new Throwable()));
        Single<Post> actual = postsRepository.getPost(2324);

        TestObserver<Post> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);
        testObserver.assertSubscribed();
        testObserver.assertNoValues();
        Assert.assertEquals(testObserver.errorCount(), 1);
    }

    @Test
    public void getPosts() throws Exception {
        when(postsApi.getPosts()).thenReturn(Single.just(
                Collections.singletonList(new PostsApiResponse.Builder().id(23445).build())
        ));
        Single<List<Post>> actual = postsRepository.getPosts();

        List<Post> expected = postsApiToDomainMapper.map(Collections.singletonList(new PostsApiResponse.Builder().id(23445).build()));

        TestObserver<List<Post>> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);
        testObserver.assertSubscribed();
        testObserver.assertNoErrors();
        testObserver.assertValue(expected);
    }

    @Test
    public void getPosts_error() throws Exception {
        when(postsApi.getPosts()).thenReturn(Single.error(new Throwable()));
        Single<List<Post>> actual = postsRepository.getPosts();

        TestObserver<List<Post>> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);
        testObserver.assertSubscribed();
        testObserver.assertNoValues();
        Assert.assertEquals(testObserver.errorCount(), 1);
    }
}