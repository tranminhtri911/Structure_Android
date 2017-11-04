package com.fstyle.structure_android.viewmodel.impl;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.fstyle.structure_android.data.model.User;
import com.fstyle.structure_android.viewmodel.UserViewModel;

/**
 * Created by le.quang.dao on 20/03/2017.
 */

public class UserViewModelImpl implements UserViewModel {

    private ObservableField<User> mUserObservableField = new ObservableField<>();

    public UserViewModelImpl(@NonNull  User user) {
        mUserObservableField.set(user);
    }

    @Override
    public ObservableField<User> getUser() {
        return mUserObservableField;
    }
}
