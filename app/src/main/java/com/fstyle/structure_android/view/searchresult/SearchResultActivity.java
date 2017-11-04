package com.fstyle.structure_android.view.searchresult;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.fstyle.structure_android.R;
import com.fstyle.structure_android.data.model.User;
import com.fstyle.structure_android.databinding.ActivitySearchResultBinding;
import com.fstyle.structure_android.view.BaseActivity;
import com.fstyle.structure_android.utils.Constant;
import com.fstyle.structure_android.viewmodel.SearchResultViewModel;
import com.fstyle.structure_android.viewmodel.impl.SearchResultViewModelImpl;

import java.util.ArrayList;

/**
 * SearchResult Screen.
 */
public class SearchResultActivity extends BaseActivity {

    private SearchResultViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<User> users =
                getIntent().getParcelableArrayListExtra(Constant.ARGUMENT_LIST_USER);
        SearchResultAdapter searchResultAdapter = new SearchResultAdapter(this, users);
        mViewModel = new SearchResultViewModelImpl(searchResultAdapter);

        ActivitySearchResultBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_search_result);
        binding.setViewModel(mViewModel);
    }

    @Override
    protected void onStarted() {
        mViewModel.onStart();
    }

    @Override
    protected void onStopped() {
        super.onStop();
    }
}
