package com.fstyle.structure_android.viewmodel.impl;

import android.databinding.Observable;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import com.fstyle.structure_android.R;
import com.fstyle.structure_android.data.model.User;
import com.fstyle.structure_android.data.source.remote.api.error.BaseException;
import com.fstyle.structure_android.repository.UserRepository;
import com.fstyle.structure_android.utils.Constant;
import com.fstyle.structure_android.utils.common.StringUtils;
import com.fstyle.structure_android.utils.navigator.Navigator;
import com.fstyle.structure_android.utils.rx.BaseSchedulerProvider;
import com.fstyle.structure_android.utils.validator.Rule;
import com.fstyle.structure_android.utils.validator.ValidType;
import com.fstyle.structure_android.utils.validator.Validation;
import com.fstyle.structure_android.utils.validator.Validator;
import com.fstyle.structure_android.view.main.SearchActivity;
import com.fstyle.structure_android.view.searchresult.SearchResultActivity;
import com.fstyle.structure_android.viewmodel.SearchViewModel;
import com.fstyle.structure_android.widget.dialog.DialogManager;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sun on 3/19/2017.
 * Relative @{@link SearchActivity}
 */

public class SearchViewModelImpl implements SearchViewModel {

    private Validator mValidator;
    private UserRepository mUserRepository;
    private DialogManager mDialogManager;
    private Navigator mNavigator;
    private BaseSchedulerProvider mSchedulerProvider;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Validation({
            @Rule(types = {
                    ValidType.NG_WORD, ValidType.NON_EMPTY
            }, message = R.string.error_unusable_characters)
    })
    private ObservableField<String> mKeyWord = new ObservableField<>();
    @Validation({
            @Rule(types = ValidType.VALUE_RANGE_0_100, message = R.string
                    .error_lenght_from_0_to_100),
            @Rule(types = ValidType.NON_EMPTY, message = R.string.must_not_empty)
    })
    private ObservableField<String> mLimitNumber = new ObservableField<>();
    private ObservableField<String> mKeywordErrorMsg = new ObservableField<>();
    private ObservableField<String> mLimitErrorMsg = new ObservableField<>();

    public SearchViewModelImpl(UserRepository userRepository) {
        this.mUserRepository = userRepository;

        mKeyWord.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                validateKeywordInput();
            }
        });

        mLimitNumber.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                validateLimitNumberInput();
            }
        });
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void setDialogManager(DialogManager dialogManager) {
        this.mDialogManager = dialogManager;
    }

    @Override
    public void setNavigator(Navigator navigator) {
        this.mNavigator = navigator;
    }

    @Override
    public void setSchedulerProvider(BaseSchedulerProvider schedulerProvider) {
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void setValidator(Validator validator) {
        mValidator = validator;
    }

    @Override
    public ObservableField<String> getKeyWord() {
        return mKeyWord;
    }

    @Override
    public ObservableField<String> getLimitNumber() {
        return mLimitNumber;
    }

    @Override
    public ObservableField<String> getKeywordErrorMsg() {
        return mKeywordErrorMsg;
    }

    @Override
    public ObservableField<String> getLimitErrorMsg() {
        return mLimitErrorMsg;
    }

    @Override
    public void onSearchButtonClicked(View view) {
        if (!mValidator.validateAll()) {
            return;
        }
        callAPISearchUsers();
    }

    private void callAPISearchUsers() {
        mDialogManager.showIndeterminateProgressDialog();
        mUserRepository.searchUsers(mKeyWord.get(),
                StringUtils.convertStringToNumber(mLimitNumber.get()))
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SingleObserver<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<User> users) {
                        mDialogManager.dismissProgressDialog();
                        gotoSearchResultActivity(users);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mDialogManager.dismissProgressDialog();
                        showDialogError((BaseException) e);
                    }
                });
    }

    private void showDialogError(BaseException e) {
        if (mDialogManager == null) {
            return;
        }
        mDialogManager.dialogError(e.getMessage());
    }

    private void gotoSearchResultActivity(List<User> users) {
        Bundle bundle = new Bundle();
        if (!bundle.isEmpty()) {
            bundle.putParcelableArrayList(Constant.ARGUMENT_LIST_USER,
                    (ArrayList<? extends Parcelable>) users);
        }
        mNavigator.startActivity(SearchResultActivity.class, bundle);
    }

    private void validateKeywordInput() {
        mKeywordErrorMsg.set(mValidator.validateValueNonEmpty(mKeyWord.get()));
        if (StringUtils.isBlank(mKeywordErrorMsg.get())) {
            mKeywordErrorMsg.set(mValidator.validateNGWord(mKeyWord.get()));
        }
    }

    private void validateLimitNumberInput() {
        mLimitErrorMsg.set(mValidator.validateValueNonEmpty(mLimitNumber.get()));
        if (StringUtils.isBlank(mLimitErrorMsg.get())) {
            mLimitErrorMsg.set(mValidator.validateValueRangeFrom0to100(mLimitNumber.get()));
        }
    }
}
