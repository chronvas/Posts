package com.crhonvas.domain.usecase;

import com.crhonvas.domain.MockHelpers;
import com.crhonvas.domain.model.user.User;

import junit.framework.Assert;

import org.junit.Test;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

public class UserUseCaseTest {

    @Test
    public void execute_noError() throws Exception {
        UserUseCase userUseCase = new UserUseCase(MockHelpers.mockUserRepository);
        Single<User> actual = userUseCase.execute(MockHelpers.USER_Id);

        TestObserver<User> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);

        testObserver.assertSubscribed();
        testObserver.assertValue(MockHelpers.USER);
        testObserver.assertNoErrors();
        Assert.assertEquals(testObserver.valueCount(), 1);
    }

    @Test
    public void execute_error() throws Exception {
        UserUseCase userUseCase = new UserUseCase(MockHelpers.mockErrorUserRepository);
        Single<User> actual = userUseCase.execute(null);

        TestObserver<User> testObserver = new TestObserver<>();
        actual.subscribe(testObserver);

        testObserver.assertSubscribed();
        Assert.assertEquals(testObserver.errorCount(), 1);
        testObserver.assertErrorMessage("mockErrorUserRepository");
        testObserver.assertNoValues();
    }
}