package com.fstyle.structure_android.viewmodel;

import com.fstyle.structure_android.view.searchresult.SearchResultAdapter;

/**
 * Created by le.quang.dao on 20/03/2017.
 */

public interface SearchResultViewModel extends BaseViewModel {

    SearchResultAdapter getAdapter();
}
