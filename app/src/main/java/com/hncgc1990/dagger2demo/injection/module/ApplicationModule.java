package com.hncgc1990.dagger2demo.injection.module;

import android.content.Context;
import android.location.LocationManager;

import com.hncgc1990.dagger2demo.DemoApplication;
import com.hncgc1990.dagger2demo.data.remote.DemoRetrofitFactory;
import com.hncgc1990.dagger2demo.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

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
    @ApplicationContext
    public Context provideApplicationContext(){

        return mContext;
    }

    @Singleton
    @Provides
    public LocationManager provideLocationManager(){
        return (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(){
        return DemoRetrofitFactory.getInstance();
    }



}
