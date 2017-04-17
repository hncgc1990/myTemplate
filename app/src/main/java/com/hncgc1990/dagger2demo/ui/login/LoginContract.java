package com.hncgc1990.dagger2demo.ui.login;

import com.hncgc1990.dagger2demo.ui.base.BasePresenter;
import com.hncgc1990.dagger2demo.ui.base.BaseView;

/**
 * Created by Administrator on 2017-4-17.
 */
public class LoginContract {

    public interface Presenter extends BasePresenter{

       void login();

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


    }

}
