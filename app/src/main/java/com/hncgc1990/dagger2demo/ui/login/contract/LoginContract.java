package com.hncgc1990.dagger2demo.ui.login.contract;

import com.hncgc1990.dagger2demo.ui.base.BasePresenter;
import com.hncgc1990.dagger2demo.ui.base.BaseView;
import com.hncgc1990.dagger2demo.ui.login.presenter.LoginPresent;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017-4-17.
 */
public class LoginContract {

    public interface Presenter extends BasePresenter<View> {

        void login();
        void invalidateForm(Observable<CharSequence> observable1,Observable<CharSequence> observable2);
    }


    public interface View extends BaseView<LoginPresent>{

        String getUserName();
        String getPass();
        void resetError();
        void showLoginSuccess();
        void showLoginFail();
        void showLoading(boolean show);
        void showUserNameError(String error);
        void showPassError(String error);
        void enableButton(boolean isenable);


    }

}
