package com.hncgc1990.dagger2demo.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hncgc1990.dagger2demo.DemoApplication;
import com.hncgc1990.dagger2demo.data.model.Result;
import com.hncgc1990.dagger2demo.injection.component.DaggerActivityComponent;
import com.hncgc1990.dagger2demo.injection.module.ActivityModule;
import com.hncgc1990.dagger2demo.ui.MainPresenter;
import com.hncgc1990.dagger2demo.ui.main.fragment.MainFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainFragment.OnListFragmentInteractionListener {

    @Inject
    public MainPresenter mMainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainFragment mainFragment=MainFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(android.R.id.content,mainFragment).commit();

        //注入
        DaggerActivityComponent.builder()
                .applicationComponent(((DemoApplication)getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(mainFragment))
                .build()
                .inject(this);

    }

    @Override
    public void onListFragmentInteraction(Result item) {
        //点击列表项目
    }
}
