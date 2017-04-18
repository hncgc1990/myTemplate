package com.hncgc1990.dagger2demo.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hncgc1990.dagger2demo.DemoApplication;
import com.hncgc1990.dagger2demo.data.model.Result;
import com.hncgc1990.dagger2demo.injection.component.DaggerMainActivityComponent;
import com.hncgc1990.dagger2demo.injection.module.MainActivityModule;
import com.hncgc1990.dagger2demo.ui.main.MainPresenter;
import com.hncgc1990.dagger2demo.ui.main.fragment.MainFragment;

import javax.inject.Inject;

/**
 * 一个简单的加载列表数据(使用Frgment的mvp结构)
 */
public class MainActivity extends AppCompatActivity implements MainFragment.OnListFragmentInteractionListener {

    @Inject
    public MainPresenter mMainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainFragment mainFragment=MainFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(android.R.id.content,mainFragment).commit();

        //注入
        DaggerMainActivityComponent.builder()
                .applicationComponent(((DemoApplication)getApplication()).getApplicationComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);

        mMainPresenter.attachView(mainFragment);

    }

    @Override
    public void onListFragmentInteraction(Result item) {
        //点击列表项目
    }



}
