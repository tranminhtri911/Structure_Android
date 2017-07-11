package com.fstyle.structure_android.screen.searchresult;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.fstyle.structure_android.R;
import com.fstyle.structure_android.data.model.User;
import com.fstyle.structure_android.screen.BaseActivity;
import com.fstyle.structure_android.utils.Constant;
import java.util.ArrayList;

/**
 * SearchResult Screen.
 */
public class SearchResultActivity extends BaseActivity implements SearchResultContract.View {

    private SearchResultContract.Controller mController;

    private RecyclerView mRecyclerView;

    private SearchResultAdapter mSearchResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);

        mController = new SearchResultController();
        mController.setView(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.resultRecyclerView);

        mSearchResultAdapter = new SearchResultAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSearchResultAdapter);
        ArrayList<User> users = getIntent().getParcelableArrayListExtra(Constant.LIST_USER_ARGS);
        mSearchResultAdapter.updateData(users);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mController.onStart();
    }

    @Override
    protected void onStop() {
        mController.onStop();
        super.onStop();
    }
}
