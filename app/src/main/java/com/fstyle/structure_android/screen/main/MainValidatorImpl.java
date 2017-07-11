package com.fstyle.structure_android.screen.main;

import com.fstyle.structure_android.screen.BaseView;
import com.fstyle.structure_android.utils.common.StringUtils;
import com.fstyle.structure_android.utils.validator.Validator;

/**
 * Created by framgia on 11/07/2017.
 */

public class MainValidatorImpl implements MainValidator {

    private MainContract.View mMainView;
    private Validator mValidator;

    public MainValidatorImpl(Validator validator) {
        mValidator = validator;
        validator.initNGWordPattern();
    }

    @Override
    public void setView(BaseView view) {
        mMainView = (MainContract.View) view;
    }

    @Override
    public void validateKeywordInput(String keyword) {
        String message = mValidator.validateValueNonEmpty(keyword);
        if (StringUtils.isBlank(message)) {
            message = mValidator.validateNGWord(keyword);
        }
        mMainView.onInvalidKeyWord(message);
    }

    @Override
    public void validateLimitNumberInput(String limit) {
        String message = mValidator.validateValueNonEmpty(limit);
        if (StringUtils.isBlank(message)) {
            message = mValidator.validateValueRangeFrom0to100(limit);
        }
        mMainView.onInvalidLimitNumber(message);
    }

    @Override
    public boolean validateDataInput(String keyword, String limit) {
        validateKeywordInput(keyword);
        validateLimitNumberInput(limit);
        try {
            return mValidator.validateAll(mMainView);
        } catch (IllegalAccessException e) {
            return false;
        }
    }
}
