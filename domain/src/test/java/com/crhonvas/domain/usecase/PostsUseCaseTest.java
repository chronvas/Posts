package com.crhonvas.domain.usecase;

import com.crhonvas.domain.MockHelpers;
import com.crhonvas.domain.model.posts.Post;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

public class PostsUseCaseTest {

    @Test
    public void execute_noError() throws Exception {
        PostsUseCase postsUseCase = new PostsUseCase(MockHelpers.mockPostsRepository);
        Single<List<Post>> actual = postsUseCase.execute(null);

        TestObserver<List<Post>> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);

        testObserver.assertSubscribed();
        testObserver.assertValue(MockHelpers.POST_LIST);
        testObserver.assertNoErrors();
        Assert.assertEquals(testObserver.valueCount(), 1);
    }

    @Test
    public void execute_error() throws Exception {
        PostsUseCase postsUseCase = new PostsUseCase(MockHelpers.mockErrorPostsRepository);
        Single<List<Post>> actual = postsUseCase.execute(null);

        TestObserver<List<Post>> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);

        testObserver.assertSubscribed();
        Assert.assertEquals(testObserver.errorCount(), 1);
        testObserver.assertErrorMessage("mockErrorPostsRepository");
        testObserver.assertNoValues();
    }
}