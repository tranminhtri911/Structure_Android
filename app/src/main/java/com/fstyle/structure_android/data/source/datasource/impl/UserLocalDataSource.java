package com.fstyle.structure_android.data.source.datasource.impl;

import android.support.annotation.NonNull;

import com.fstyle.structure_android.data.model.User;
import com.fstyle.structure_android.data.source.datasource.UserDataSource;
import com.fstyle.structure_android.data.source.local.BaseLocalDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.realm.Realm;

/**
 * Created by le.quang.dao on 14/03/2017.
 */

public class UserLocalDataSource extends BaseLocalDataSource implements UserDataSource.LocalDataSource {


    public UserLocalDataSource(Realm realm) {
        super(realm);
    }

    @Override
    public void openTransaction() {
        if (mRealm == null) {
            mRealm = Realm.getDefaultInstance();
        }
    }

    @Override
    public void closeTransaction() {
        mRealm.close();
    }


    @Override
    public Completable insertUser(@NonNull User user) {
        return null;
    }

    @Override
    public Completable updateUser(@NonNull User user) {
        return null;
    }

    @Override
    public Completable deleteUser(@NonNull User user) {
        return null;
    }

    @Override
    public Single<List<User>> getAllUser() {
        return null;
    }

    @Override
    public Maybe<User> getUserByUserLogin(String userLogin) {
        return null;
    }
}
