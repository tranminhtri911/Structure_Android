package com.fstyle.structure_android.view.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.fstyle.structure_android.R;
import com.fstyle.structure_android.data.source.datasource.impl.UserRemoteDataSource;
import com.fstyle.structure_android.data.source.remote.api.service.NameServiceClient;
import com.fstyle.structure_android.databinding.ActivityMainBinding;
import com.fstyle.structure_android.repository.UserRepository;
import com.fstyle.structure_android.repository.impl.UserRepositoryImpl;
import com.fstyle.structure_android.view.BaseActivity;
import com.fstyle.structure_android.utils.navigator.Navigator;
import com.fstyle.structure_android.utils.rx.SchedulerProvider;
import com.fstyle.structure_android.utils.validator.Validator;
import com.fstyle.structure_android.viewmodel.SearchViewModel;
import com.fstyle.structure_android.viewmodel.impl.SearchViewModelImpl;
import com.fstyle.structure_android.widget.dialog.DialogManagerImpl;

/**
 * Create by DaoLQ
 */
public class SearchActivity extends BaseActivity {

    private SearchViewModel mSearchViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserRepository userRepository = new UserRepositoryImpl(null,
                new UserRemoteDataSource(NameServiceClient.getInstance()));
        mSearchViewModel = new SearchViewModelImpl(userRepository);
        mSearchViewModel.setDialogManager(new DialogManagerImpl(this));
        mSearchViewModel.setNavigator(new Navigator(this));

        Validator validator = new Validator(getApplicationContext(), mSearchViewModel);
        validator.initNGWordPattern();
        mSearchViewModel.setValidator(validator);
        mSearchViewModel.setSchedulerProvider(SchedulerProvider.getInstance());

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(mSearchViewModel);
    }

    @Override
    protected void onStarted() {
        mSearchViewModel.onStart();
    }

    @Override
    protected void onStopped() {
        mSearchViewModel.onStop();
    }
}
