package com.hncgc1990.dagger2demo.injection.module;

import com.hncgc1990.dagger2demo.injection.PerActivity;
import com.hncgc1990.dagger2demo.ui.login.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/13.
 */
@Module
@PerActivity
public class LoginActivityModule {
    private LoginContract.View mView;

    public LoginActivityModule(LoginContract.View view){
        mView=view;
    }
    @Provides
    public LoginContract.View provideMainContractView(){
        return mView;
    }
}
