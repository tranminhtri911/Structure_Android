package com.fstyle.structure_android.screen.main;

import com.fstyle.structure_android.screen.BaseView;

/**
 * Created by framgia on 11/07/2017.
 */

public interface MainValidator {
    void setView(BaseView view);

    void validateKeywordInput(String keyword);

    void validateLimitNumberInput(String limit);

    boolean validateDataInput(String keyword, String limit);
}
