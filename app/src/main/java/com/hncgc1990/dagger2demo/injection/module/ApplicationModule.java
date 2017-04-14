package com.hncgc1990.dagger2demo.injection.module;

import android.content.Context;
import android.location.LocationManager;

import com.hncgc1990.dagger2demo.DemoApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/13.
 */
@Module
public class ApplicationModule {


    DemoApplication mContext;

    public ApplicationModule(DemoApplication context){
        this.mContext=context;
    }


    @Provides
    @Singleton
    public Context provideApplicationContext(){

        return mContext;
    }

    @Singleton
    @Provides
    public LocationManager provideLocationManager(){
        return (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
    }

}
