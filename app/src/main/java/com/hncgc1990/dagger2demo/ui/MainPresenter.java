package com.hncgc1990.dagger2demo.ui;

import com.hncgc1990.dagger2demo.data.DataManager;
import com.hncgc1990.dagger2demo.data.model.PostData;
import com.hncgc1990.dagger2demo.data.model.Result;
import com.hncgc1990.dagger2demo.util.DialogHelper;
import com.hncgc1990.dagger2demo.util.Logger;
import com.hncgc1990.dagger2demo.util.ProtocolHelper;
import com.hncgc1990.dagger2demo.util.SchedulerHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/4/14.
 */
public class MainPresenter implements MainContract.Presenter{


    MainContract.View mView;


    @Inject
    public DataManager dataManager;




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

    }

    @Override
    public void loadData() {



        dataManager.getPostList()
                .compose(SchedulerHelper.<PostData<List<Result>>>applySchedulers())
                .compose(ProtocolHelper.applyProtocolHandler())
                .compose(new DialogHelper(new DialogHelper.ProgressListener() {
                    @Override
                    public void hide() {
                        mView.hideProgress();
                    }
                    @Override
                    public void show() {
                        mView.showProgress();
                    }
                }).<PostData<List<Result>>>applyDialog())
                .subscribe(new Observer<PostData<List<Result>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PostData<List<Result>> value) {

                        Logger.d("列表的大小"+value.getResults().size());
                        mView.showList(value.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete");
                    }
                });
    }

}
