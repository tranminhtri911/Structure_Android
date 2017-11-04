package com.fstyle.structure_android.viewmodel;

import android.databinding.ObservableField;
import com.fstyle.structure_android.data.model.User;

/**
 * Created by le.quang.dao on 20/03/2017.
 */

public interface UserViewModel {

    ObservableField<User> getUser();
}
