package com.fstyle.structure_android.viewmodel.impl;

import android.util.Log;
import com.fstyle.structure_android.data.model.User;
import com.fstyle.structure_android.view.searchresult.SearchResultAdapter;
import com.fstyle.structure_android.view.searchresult.SearchResultRecyclerViewListener;
import com.fstyle.structure_android.utils.rx.BaseSchedulerProvider;
import com.fstyle.structure_android.viewmodel.SearchResultViewModel;

/**
 * Created by le.quang.dao on 20/03/2017.
 */

public class SearchResultViewModelImpl
        implements SearchResultViewModel, SearchResultRecyclerViewListener {

    private static final String TAG = "SearchResultViewModelIm";

    private SearchResultAdapter mAdapter;

    public SearchResultViewModelImpl(SearchResultAdapter adapter) {
        mAdapter = adapter;
        mAdapter.setItemClickListener(this);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onItemRecyclerViewClick(User user) {
        Log.d(TAG, "onItemRecyclerViewClick: " + user.getLogin());
    }

    public SearchResultAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void setSchedulerProvider(BaseSchedulerProvider schedulerProvider) {

    }
}
