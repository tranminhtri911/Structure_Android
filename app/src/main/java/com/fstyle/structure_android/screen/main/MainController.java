package com.fstyle.structure_android.screen.main;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import com.fstyle.structure_android.data.source.UserRepository;
import com.fstyle.structure_android.data.source.UserRepositoryImpl;
import com.fstyle.structure_android.data.source.remote.UserRemoteDataSource;
import com.fstyle.structure_android.data.source.remote.api.service.NameServiceClient;
import com.fstyle.structure_android.utils.rx.BaseSchedulerProvider;
import com.fstyle.structure_android.utils.validator.Validator;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

class MainController implements MainContract.Controller {
    private static final String TAG = MainController.class.getName();

    private UserRepository mUserRepository;
    private MainValidator mMainValidator;
    private CompositeSubscription mCompositeSubscription;

    MainController() {
        mUserRepository = new UserRepositoryImpl(null,
                new UserRemoteDataSource(NameServiceClient.getInstance()));
        mCompositeSubscription = new CompositeSubscription();
    }

    @VisibleForTesting
    MainController(UserRepository userRepository, MainValidator validator) {
        mUserRepository = userRepository;
        mMainValidator = validator;
    }

    @Override
    public void setSchedulerProvider(BaseSchedulerProvider schedulerProvider) {
        mUserRepository.setSchedulerProvider(schedulerProvider);
    }

    @Override
    public void setView(MainContract.View view) {
        if (mMainValidator == null) {
            Validator validator =
                    new Validator(((Context) view).getApplicationContext(), MainActivity.class);
            mMainValidator = new MainValidatorImpl(validator);
            mMainValidator.setView(view);
        }
        mUserRepository.setView(view);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    @Override
    public void validateKeywordInput(String keyword) {
        mMainValidator.validateKeywordInput(keyword);
    }

    @Override
    public void validateLimitNumberInput(String limit) {
        mMainValidator.validateLimitNumberInput(limit);
    }

    @Override
    public boolean validateDataInput(String keyword, String limit) {
        return mMainValidator.validateDataInput(keyword, limit);
    }

    @Override
    public void searchUsers(int limit, String keyWord) {
        mCompositeSubscription.add(mUserRepository.searchUsers(limit, keyWord));
    }
}
