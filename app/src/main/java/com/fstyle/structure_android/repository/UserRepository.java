package com.fstyle.structure_android.repository;

import com.fstyle.structure_android.data.source.datasource.UserDataSource;

/**
 * Created by daolq on 11/2/17.
 */

public interface UserRepository extends UserDataSource.LocalDataSource, UserDataSource.RemoteDataSource{
}
