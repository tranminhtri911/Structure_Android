package com.fstyle.structure_android.data.source;

import com.fstyle.structure_android.data.model.User;
import com.fstyle.structure_android.data.source.local.realm.UserLocalDataSource;
import com.fstyle.structure_android.data.source.remote.UserRemoteDataSource;
import com.fstyle.structure_android.data.source.remote.api.error.BaseException;
import com.fstyle.structure_android.data.source.remote.api.error.RequestError;
import com.fstyle.structure_android.screen.BaseView;
import com.fstyle.structure_android.screen.main.MainContract;
import com.fstyle.structure_android.utils.rx.BaseSchedulerProvider;
import java.util.List;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public class UserRepositoryImpl implements UserRepository {

    private UserDataSource.LocalDataSource mLocalDataSource;
    private UserDataSource.RemoteDataSource mRemoteDataSource;
    private MainContract.View mMainView;
    private BaseSchedulerProvider mSchedulerProvider;

    public UserRepositoryImpl(UserLocalDataSource localDataSource,
            UserRemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public void setView(BaseView view) {
        mMainView = (MainContract.View) view;
    }

    @Override
    public void setSchedulerProvider(BaseSchedulerProvider schedulerProvider) {
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public Subscription searchUsers(int limit, String keyWord) {
        return mRemoteDataSource.searchUsers(limit, keyWord)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new Action1<List<User>>() {
                    @Override
                    public void call(List<User> users) {
                        mMainView.onSearchUsersSuccess(users);
                    }
                }, new RequestError() {
                    @Override
                    public void onRequestError(BaseException error) {
                        mMainView.onSearchError(error);
                    }
                });
    }
}
