package com.hncgc1990.dagger2demo.injection.module;

import com.hncgc1990.dagger2demo.injection.PerActivity;
import com.hncgc1990.dagger2demo.ui.MainContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/13.
 */
@Module
@PerActivity
public class ActivityModule {
    private MainContract.View mView;

    public ActivityModule(MainContract.View view){
        mView=view;
    }
    @Provides
    public MainContract.View provideMainContractView(){
        return mView;
    }
}
