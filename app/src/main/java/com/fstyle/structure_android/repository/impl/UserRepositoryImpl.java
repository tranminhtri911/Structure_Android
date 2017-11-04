package com.fstyle.structure_android.repository.impl;

import android.support.annotation.NonNull;

import com.fstyle.structure_android.data.model.User;
import com.fstyle.structure_android.data.source.datasource.UserDataSource;
import com.fstyle.structure_android.repository.UserRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public class UserRepositoryImpl implements UserRepository {

    private UserDataSource.LocalDataSource mLocalDataSource;
    private UserDataSource.RemoteDataSource mRemoteDataSource;

    public UserRepositoryImpl(UserDataSource.LocalDataSource localDataSource,
                              UserDataSource.RemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public Single<List<User>> searchUsers(String keyWord, int limit) {
        return mRemoteDataSource.searchUsers(keyWord, limit);
    }

    @Override
    public Completable insertUser(@NonNull User user) {
        return mLocalDataSource.insertUser(user);
    }

    @Override
    public Completable updateUser(@NonNull User user) {
        return mLocalDataSource.updateUser(user);
    }

    @Override
    public Completable deleteUser(@NonNull User user) {
        return mLocalDataSource.deleteUser(user);
    }

    @Override
    public Single<List<User>> getAllUser() {
        return mLocalDataSource.getAllUser();
    }

    @Override
    public Maybe<User> getUserByUserLogin(String userLogin) {
        return mLocalDataSource.getUserByUserLogin(userLogin);
    }
}
