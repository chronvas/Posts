package com.crhonvas.domain.usecase;

import com.crhonvas.domain.MockHelpers;
import com.crhonvas.domain.model.posts.Post;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;

public class PostUseCaseTest {

    @Test
    public void execute_noError() throws Exception {
        PostUseCase postUseCase = new PostUseCase(MockHelpers.mockPostsRepository);
        Single<Post> actual = postUseCase.execute(MockHelpers.POST_1_Id);

        TestObserver<Post> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);

        testObserver.assertSubscribed();
        testObserver.assertValue(MockHelpers.POST_1);
        testObserver.assertNoErrors();
        Assert.assertEquals(testObserver.valueCount(), 1);
    }

    @Test
    public void execute_error() throws Exception {
        PostUseCase postUseCase = new PostUseCase(MockHelpers.mockErrorPostsRepository);
        Single<Post> actual = postUseCase.execute(null);

        TestObserver<Post> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);

        testObserver.assertSubscribed();
        Assert.assertEquals(testObserver.errorCount(), 1);
        testObserver.assertErrorMessage("mockErrorPostsRepository");
        testObserver.assertNoValues();
    }
}