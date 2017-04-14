package com.hncgc1990.dagger2demo.ui;

import com.hncgc1990.dagger2demo.ui.base.BasePresenter;
import com.hncgc1990.dagger2demo.ui.base.BaseView;

/**
 * Created by Administrator on 2017/4/14.
 */
public interface MainContract {


    interface View extends BaseView<MainPresenter> {

        void showProgress();

        void showList();


        void hideProgress();

    }


    interface Presenter extends BasePresenter {


        void loadData();

        void showList();
    }

}
