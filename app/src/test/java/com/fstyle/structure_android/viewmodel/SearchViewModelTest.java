package com.fstyle.structure_android.viewmodel;

import com.fstyle.structure_android.data.model.User;
import com.fstyle.structure_android.data.source.remote.api.error.BaseException;
import com.fstyle.structure_android.data.source.remote.api.response.ErrorResponse;
import com.fstyle.structure_android.repository.UserRepository;
import com.fstyle.structure_android.utils.common.StringUtils;
import com.fstyle.structure_android.utils.navigator.Navigator;
import com.fstyle.structure_android.utils.rx.ImmediateSchedulerProvider;
import com.fstyle.structure_android.utils.validator.Validator;
import com.fstyle.structure_android.viewmodel.impl.SearchViewModelImpl;
import com.fstyle.structure_android.widget.dialog.DialogManager;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by daolq on 11/3/17.
 * Unit test for @{@link SearchViewModel}
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchViewModelTest {

    private SearchViewModel mSearchViewModel;
    @Mock
    private UserRepository mUserRepository;
    @Mock
    private DialogManager mDialogManager;
    @Mock
    private Navigator mNavigator;
    @Mock
    private Validator mValidator;
    @InjectMocks
    private ImmediateSchedulerProvider mSchedulerProvider;

    @Before
    public void setUp() throws Exception {
        mSearchViewModel = new SearchViewModelImpl(mUserRepository);
        mSearchViewModel.setDialogManager(mDialogManager);
        mSearchViewModel.setNavigator(mNavigator);
        mSearchViewModel.setValidator(mValidator);
        mSearchViewModel.setSchedulerProvider(mSchedulerProvider);
    }



    @Test
    public void onSearchButtonClicked_requestServerSuccess_InvokesResultActivity() throws Exception {
        String keyword = "abc";
        String limit = "12";
        // Give
        mSearchViewModel.getKeyWord().set(keyword);
        mSearchViewModel.getLimitNumber().set(limit);

        // When
        Mockito.when(mValidator.validateAll()).thenReturn(true);
        Mockito.when(mUserRepository.searchUsers(keyword, StringUtils.convertStringToNumber(limit)))
                .thenReturn(Single.just(fakeUsers()));

        // Then
        mSearchViewModel.onSearchButtonClicked(null);

        // Verify
        Mockito.verify(mDialogManager).showIndeterminateProgressDialog();
        Mockito.verify(mDialogManager).dismissProgressDialog();

//        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList(Constant.ARGUMENT_LIST_USER,
//                (ArrayList<? extends Parcelable>) fakeUsers());
//        Mockito.verify(mNavigator).startActivity(SearchResultActivity.class,bundle);
    }

    @Test
    public void onSearchButtonClicked_requestServerFail_InvokesShowDialogError() throws Exception {
        String keyword = "abc";
        String limit = "12";
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("ServerError");
        BaseException expertError = BaseException.toServerError(errorResponse);
        // Give
        mSearchViewModel.getKeyWord().set(keyword);
        mSearchViewModel.getLimitNumber().set(limit);

        // When
        Mockito.when(mValidator.validateAll()).thenReturn(true);
        Mockito.when(mUserRepository.searchUsers(keyword, StringUtils.convertStringToNumber(limit)))
                .thenReturn(Single.<List<User>>error(expertError));

        // Then
        mSearchViewModel.onSearchButtonClicked(null);

        // Verify
        Mockito.verify(mDialogManager).dismissProgressDialog();
        Mockito.verify(mDialogManager).dialogError(expertError.getMessage());
    }

    @Test
    public void getKeyWork_userNoInputKeyWord_invokesMessageErrorEmpty() {
        String keywork = "";
        // Given
        String expertError = "Empty Error";

        // When
        Mockito.when(mValidator.validateValueNonEmpty(keywork)).thenReturn(expertError);

        // Then
        mSearchViewModel.getKeyWord().set(keywork);

        // Verify
        Assert.assertEquals(expertError,mSearchViewModel.getKeywordErrorMsg().get());
    }

    @Test
    public void getKeyWork_userInputInvalidKeyWord_invokesMessageErrorInvalid() {
        String keywork = "shit";
        // Given
        String expertError = "Invalid Error";

        // When
        Mockito.when(mValidator.validateValueNonEmpty(keywork)).thenReturn("");
        Mockito.when(mValidator.validateNGWord(keywork)).thenReturn(expertError);

        // Then
        mSearchViewModel.getKeyWord().set(keywork);

        // Verify
        Assert.assertEquals(expertError,mSearchViewModel.getKeywordErrorMsg().get());
    }

    @Test
    public void getKeyWork_userNoInputLimitNumber_invokesMessageNoEmpty() {
        String limitNumber = "";
        // Given
        String expertError = "Empty Error";

        // When
        Mockito.when(mValidator.validateValueNonEmpty(limitNumber)).thenReturn(expertError);

        // Then
        mSearchViewModel.getLimitNumber().set(limitNumber);

        // Verify
        Assert.assertEquals(expertError,mSearchViewModel.getLimitErrorMsg().get());
    }

    @Test
    public void getKeyWork_userInputLimitNumberMoreThan100_invokesMessageNoEmpty() {
        String limitNumber = "120";
        // Given
        String expertError = "Invalid Error";

        // When
        Mockito.when(mValidator.validateValueNonEmpty(limitNumber)).thenReturn("");
        Mockito.when(mValidator.validateValueRangeFrom0to100(limitNumber)).thenReturn(expertError);

        // Then
        mSearchViewModel.getLimitNumber().set(limitNumber);

        // Verify
        Assert.assertEquals(expertError,mSearchViewModel.getLimitErrorMsg().get());
    }

    private List<User> fakeUsers() {
        List<User> users = new ArrayList<>();
        User expertUser1 = new User();
        expertUser1.setLogin("user 1");
        users.add(expertUser1);
        User expertUser2 = new User();
        expertUser2.setLogin("user 2");
        users.add(expertUser2);
        return users;
    }
}