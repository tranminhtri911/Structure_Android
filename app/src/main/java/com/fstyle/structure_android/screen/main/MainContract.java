package com.fstyle.structure_android.screen.main;

import com.fstyle.structure_android.data.model.User;
import com.fstyle.structure_android.data.source.remote.api.error.BaseException;
import com.fstyle.structure_android.screen.BaseController;
import com.fstyle.structure_android.screen.BaseView;
import com.fstyle.structure_android.utils.rx.BaseSchedulerProvider;
import java.util.List;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public interface MainContract {

    /**
     * View
     */
    interface View extends BaseView {
        void onSearchError(BaseException throwable);

        void onSearchUsersSuccess(List<User> users);

        void onInvalidKeyWord(String errorMsg);

        void onInvalidLimitNumber(String errorMsg);
    }

    /**
     * Controller
     */
    interface Controller extends BaseController<View> {

        void setSchedulerProvider(BaseSchedulerProvider schedulerProvider);

        void validateKeywordInput(String keyword);

        void validateLimitNumberInput(String limit);

        boolean validateDataInput(String keyword, String limit);

        void searchUsers(int limit, String keyWord);
    }
}
