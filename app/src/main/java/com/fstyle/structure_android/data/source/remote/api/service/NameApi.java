package com.fstyle.structure_android.data.source.remote.api.service;

import com.fstyle.structure_android.data.model.User;
import com.fstyle.structure_android.data.source.remote.api.response.SearchUserResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public interface NameApi {
    @GET("/search/users")
    Single<SearchUserResponse> searchGithubUsers(@Query("per_page") int limit,
                                                 @Query("q") String searchTerm);

    @GET("/users/{username}")
    Single<User> getUser(@Path("username") String username);
}
