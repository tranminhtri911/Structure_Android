package com.fstyle.structure_android.data.source.datasource.impl;

import com.fstyle.structure_android.data.model.User;
import com.fstyle.structure_android.data.source.datasource.UserDataSource;
import com.fstyle.structure_android.data.source.remote.BaseRemoteDataSource;
import com.fstyle.structure_android.data.source.remote.api.response.SearchUserResponse;
import com.fstyle.structure_android.data.source.remote.api.service.NameApi;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public class UserRemoteDataSource extends BaseRemoteDataSource
        implements UserDataSource.RemoteDataSource {
    public UserRemoteDataSource(NameApi nameApi) {
        super(nameApi);
    }

    @Override
    public Single<List<User>> searchUsers(String keyWord, int limit) {
        return mNameApi.searchGithubUsers(limit, keyWord).map(new Function<SearchUserResponse, List<User>>() {
            @Override
            public List<User> apply(SearchUserResponse searchUserResponse) throws Exception {
                return searchUserResponse.getItems();
            }
        });
    }
}
