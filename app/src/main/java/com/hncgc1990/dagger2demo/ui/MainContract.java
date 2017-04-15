package com.hncgc1990.dagger2demo.ui;

import com.hncgc1990.dagger2demo.data.model.Result;
import com.hncgc1990.dagger2demo.ui.base.BasePresenter;
import com.hncgc1990.dagger2demo.ui.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */
public interface MainContract {


    interface View extends BaseView<MainPresenter> {

        void showProgress();

        void showList(List<Result> list);


        void hideProgress();

    }


    interface Presenter extends BasePresenter {


        void loadData();
    }

}
