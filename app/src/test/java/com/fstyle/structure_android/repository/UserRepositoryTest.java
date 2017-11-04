package com.fstyle.structure_android.repository;

import com.fstyle.structure_android.data.model.User;
import com.fstyle.structure_android.data.source.datasource.impl.UserRemoteDataSource;
import com.fstyle.structure_android.repository.impl.UserRepositoryImpl;
import io.reactivex.Single;
import io.reactivex.subscribers.TestSubscriber;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by daolq on 11/3/17.
 * Unit Test for @{@link UserRepository}
 */
@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

    private UserRepository mUserRepository;
    @Mock
    private UserRemoteDataSource mUserRemoteDataSource;

    @Before
    public void setUp() throws Exception {
        mUserRepository = new UserRepositoryImpl(null, mUserRemoteDataSource);
    }

    @Test
    public void searchUsers_200ResponseCode_InvokesCorrectApiCalls() {
        String keywork = "abc";
        int limit = 12;
        List<User> users = new ArrayList<>();
        User expertUser1 = new User();
        expertUser1.setLogin("user 1");
        users.add(expertUser1);
        User expertUser2 = new User();
        expertUser2.setLogin("user 2");
        users.add(expertUser2);

        // Given
        Mockito.when(mUserRemoteDataSource.searchUsers(keywork, limit))
                .thenReturn(Single.just(users));

        // When
        TestSubscriber<List<User>> subscriber = new TestSubscriber<>();
        mUserRepository.searchUsers(keywork, limit).toFlowable().subscribe(subscriber);

        // Then
        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();

        // Verify
        Mockito.verify(mUserRemoteDataSource).searchUsers(keywork, limit);
        List<User> userList = subscriber.values().get(0);
        Assert.assertEquals(expertUser1, userList.get(0));
        Assert.assertEquals(expertUser2, userList.get(1));
    }

    @Test
    public void searchUsers_OtherHttpError_InvokesTerminatedWithError() {
        String keywork = "abc";
        int limit = 12;

        HttpException expert = new HttpException(Response.error(403,
                ResponseBody.create(MediaType.parse("application/json"), "Forbidden")));

        // Given
        Mockito.when(mUserRemoteDataSource.searchUsers(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(Single.<List<User>>error(expert));

        // When
        TestSubscriber<List<User>> subscriber = new TestSubscriber<>();
        mUserRepository.searchUsers(keywork, limit).toFlowable().subscribe(subscriber);

        // Then
        subscriber.awaitTerminalEvent();
        subscriber.assertError(HttpException.class);

        // Verify
        Mockito.verify(mUserRemoteDataSource).searchUsers(keywork, limit);
        Throwable actual = subscriber.errors().get(0);
        Assert.assertEquals(actual, expert);
    }
}