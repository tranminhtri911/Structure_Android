package com.fstyle.structure_android.screen.searchresult;

/**
 * Listens to user actions from the UI ({@link SearchResultActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class SearchResultController implements SearchResultContract.Controller {
    private static final String TAG = SearchResultController.class.getName();

    private SearchResultContract.View mView;

    SearchResultController() {
    }

    @Override
    public void setView(SearchResultContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
