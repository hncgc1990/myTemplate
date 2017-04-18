package com.hncgc1990.dagger2demo.ui.base;

/**
 * Created by Administrator on 2017/4/14.
 */
public interface BasePresenter<T extends BaseView> {

    void attachView(T view);


    void detachView();
}
