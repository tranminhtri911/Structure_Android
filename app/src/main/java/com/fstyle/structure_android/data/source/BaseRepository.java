package com.fstyle.structure_android.data.source;

import com.fstyle.structure_android.screen.BaseView;
import com.fstyle.structure_android.utils.rx.BaseSchedulerProvider;

/**
 * Created by framgia on 11/07/2017.
 */

public interface BaseRepository {
    void setView(BaseView view);

    void setSchedulerProvider(BaseSchedulerProvider schedulerProvider);
}
