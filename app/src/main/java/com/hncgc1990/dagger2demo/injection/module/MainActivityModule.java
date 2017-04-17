package com.hncgc1990.dagger2demo.injection.module;

import com.hncgc1990.dagger2demo.injection.PerActivity;
import com.hncgc1990.dagger2demo.ui.main.MainContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/13.
 */
@Module
@PerActivity
public class MainActivityModule {
    private MainContract.View mView;

    public MainActivityModule(MainContract.View view){
        mView=view;
    }
    @Provides
    public MainContract.View provideMainContractView(){
        return mView;
    }
}
