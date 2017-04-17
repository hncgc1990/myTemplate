package com.hncgc1990.dagger2demo.injection.component;

import com.hncgc1990.dagger2demo.injection.module.MainActivityModule;
import com.hncgc1990.dagger2demo.injection.PerActivity;
import com.hncgc1990.dagger2demo.ui.main.activity.MainActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/4/13.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = MainActivityModule.class)
public interface MainActivityComponent {


    void inject(MainActivity mainActivity);


}
