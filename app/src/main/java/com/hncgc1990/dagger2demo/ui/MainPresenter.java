package com.hncgc1990.dagger2demo.ui;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/4/14.
 */
public class MainPresenter implements MainContract.Presenter{


    MainContract.View mView;


    @Inject
    public MainPresenter(MainContract.View view){
        mView=view;
    }



    @Inject
    public void setUp(){
        mView.setPresenter(this);
    }



    @Override
    public void start() {
        mView.showProgress();
    }

    @Override
    public void loadData() {



    }

    @Override
    public void showList() {
        mView.hideProgress();
        mView.showList();
    }
}
