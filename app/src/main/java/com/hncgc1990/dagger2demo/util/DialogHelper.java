package com.hncgc1990.dagger2demo.util;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 *
 */
public class DialogHelper {

    public ProgressListener mListener;

    public DialogHelper(ProgressListener listener){
        this.mListener=listener;
    }


    public <T> ObservableTransformer<T,T> applyDialog(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {

               return  upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        //在这里进行弹窗
                        Logger.d("doOnSubscribe");
                        mListener.show();

                    }
                }).doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        //在这里进行取消弹窗
                        Logger.d("doOnComplete");
                        mListener.hide();

                    }
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //在这里进行取消弹窗
                        Logger.d("doOnError");
                       mListener.hide();
                    }
                });

            }
        };


    }


    public interface ProgressListener{
        void hide();
        void show();
    }


}
