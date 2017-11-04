package com.fstyle.structure_android.viewmodel;

import com.fstyle.structure_android.utils.rx.BaseSchedulerProvider;

/**
 * Created by le.quang.dao on 10/03/2017.
 * BaseViewModel
 */
public interface BaseViewModel {

    void onStart();

    void onStop();

    void setSchedulerProvider(BaseSchedulerProvider schedulerProvider);
}
