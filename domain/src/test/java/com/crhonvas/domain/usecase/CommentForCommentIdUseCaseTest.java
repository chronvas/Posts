package com.crhonvas.domain.usecase;

import com.crhonvas.domain.MockHelpers;
import com.crhonvas.domain.model.comments.Comment;

import junit.framework.Assert;

import org.junit.Test;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

public class CommentForCommentIdUseCaseTest {

    @Test
    public void execute_noError() throws Exception {
        CommentForCommentIdUseCase commentForCommentIdUseCase = new CommentForCommentIdUseCase(MockHelpers.mockCommentsRepository);
        Single<Comment> actual = commentForCommentIdUseCase.execute(MockHelpers.COMMENT_1_Id);

        TestObserver<Comment> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);

        testObserver.assertSubscribed();
        testObserver.assertValue(MockHelpers.COMMENT_1);
        testObserver.assertNoErrors();
        Assert.assertEquals(testObserver.valueCount(), 1);
    }

    @Test
    public void execute_error() throws Exception {
        CommentForCommentIdUseCase commentForCommentIdUseCase = new CommentForCommentIdUseCase(MockHelpers.mockErrorCommentsRepository);
        Single<Comment> actual = commentForCommentIdUseCase.execute(null);

        TestObserver<Comment> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);

        testObserver.assertSubscribed();
        Assert.assertEquals(testObserver.errorCount(), 1);
        testObserver.assertErrorMessage("mockErrorCommentsRepository");
        testObserver.assertNoValues();
    }
}