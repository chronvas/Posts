package com.crhonvas.domain.usecase;

import com.crhonvas.domain.MockHelpers;
import com.crhonvas.domain.model.comments.Comment;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

public class CommentsUseCaseTest {

    @Test
    public void execute_noError() throws Exception {
        CommentsUseCase commentsUseCase = new CommentsUseCase(MockHelpers.mockCommentsRepository);
        Single<List<Comment>> actual = commentsUseCase.execute(MockHelpers.COMMENT_1_Id);

        TestObserver<List<Comment>> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);

        testObserver.assertSubscribed();
        testObserver.assertValue(MockHelpers.COMMENT_LIST);
        testObserver.assertNoErrors();
        Assert.assertEquals(testObserver.valueCount(), 1);
    }

    @Test
    public void execute_error() throws Exception {
        CommentsUseCase commentsUseCase = new CommentsUseCase(MockHelpers.mockErrorCommentsRepository);
        Single<List<Comment>> actual = commentsUseCase.execute(null);

        TestObserver<List<Comment>> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);

        testObserver.assertSubscribed();
        Assert.assertEquals(testObserver.errorCount(), 1);
        testObserver.assertErrorMessage("mockErrorCommentsRepository");
        testObserver.assertNoValues();
    }
}