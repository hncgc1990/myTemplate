package com.hncgc1990.dagger2demo.injection.component;

import com.hncgc1990.dagger2demo.injection.PerActivity;
import com.hncgc1990.dagger2demo.injection.module.LoginActivityModule;
import com.hncgc1990.dagger2demo.ui.login.activity.LoginActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/4/13.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = LoginActivityModule.class)
public interface LoginActivityComponent {


    void inject(LoginActivity mainActivity);


}
