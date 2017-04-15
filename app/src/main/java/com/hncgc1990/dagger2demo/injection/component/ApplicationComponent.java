package com.hncgc1990.dagger2demo.injection.component;

import com.hncgc1990.dagger2demo.injection.module.ApplicationModule;
import com.hncgc1990.dagger2demo.DemoApplication;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/4/13.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    public void inject(DemoApplication application);


    Retrofit getRetrofit();
}
