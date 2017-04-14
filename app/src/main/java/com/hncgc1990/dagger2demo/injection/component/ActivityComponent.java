package com.hncgc1990.dagger2demo.injection.component;

import com.hncgc1990.dagger2demo.injection.module.ActivityModule;
import com.hncgc1990.dagger2demo.injection.PerActivity;
import com.hncgc1990.dagger2demo.ui.yywu1.activity.MainActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/4/13.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(MainActivity mainActivity);


}
