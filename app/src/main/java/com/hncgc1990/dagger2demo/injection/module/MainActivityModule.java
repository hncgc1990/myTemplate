package com.hncgc1990.dagger2demo.injection.module;

import android.app.Activity;
import android.content.Context;

import com.hncgc1990.dagger2demo.injection.ActivityContext;
import com.hncgc1990.dagger2demo.injection.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/13.
 */
@Module
@PerActivity
public class MainActivityModule {


    Activity mContext;

    public MainActivityModule(Activity context){
    }


    @Provides
    public Activity provideActivity(){
        return mContext;
    }

    @Provides
    @ActivityContext
    public Context provideContext(){
        return mContext;
    }


}
