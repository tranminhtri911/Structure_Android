package com.fstyle.structure_android.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import com.fstyle.structure_android.view.main.SearchActivity;
import com.fstyle.structure_android.utils.navigator.Navigator;
import com.fstyle.structure_android.utils.validator.Validator;
import com.fstyle.structure_android.widget.dialog.DialogManager;

/**
 * Created by Sun on 3/19/2017.
 * Relative @{@link SearchActivity}
 */

public interface SearchViewModel extends BaseViewModel {

    void setDialogManager(DialogManager dialogManager);

    void setNavigator(Navigator navigator);

    void setValidator(Validator validator);

    void onSearchButtonClicked(View view);

    ObservableField<String> getKeyWord();

    ObservableField<String> getLimitNumber();

    ObservableField<String> getKeywordErrorMsg();

    ObservableField<String> getLimitErrorMsg();
}
