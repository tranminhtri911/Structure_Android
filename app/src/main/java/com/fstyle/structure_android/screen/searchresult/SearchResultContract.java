package com.fstyle.structure_android.screen.searchresult;

import com.fstyle.structure_android.screen.BaseController;
import com.fstyle.structure_android.screen.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
interface SearchResultContract {
    /**
     * View.
     */
    interface View extends BaseView {
    }

    /**
     * Controller.
     */
    interface Controller extends BaseController<View> {
    }
}
