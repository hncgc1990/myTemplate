package com.hncgc1990.dagger2demo;

import android.app.Application;
import android.location.LocationManager;

import com.hncgc1990.dagger2demo.injection.component.ApplicationComponent;
import com.hncgc1990.dagger2demo.injection.component.DaggerApplicationComponent;
import com.hncgc1990.dagger2demo.injection.module.ApplicationModule;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/4/13.
 */
public class DemoApplication extends Application{

    private ApplicationComponent applicationComponent;

    @Inject
    public  LocationManager mLocationManager;


    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        applicationComponent.inject(this);
    }




    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public LocationManager getLocationManager() {
        return mLocationManager;
    }
}
