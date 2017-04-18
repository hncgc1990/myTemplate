package com.hncgc1990.dagger2demo.ui.login.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.hncgc1990.dagger2demo.exception.InvalidateException;
import com.hncgc1990.dagger2demo.ui.login.contract.LoginContract;
import com.hncgc1990.dagger2demo.util.DialogHelper;
import com.hncgc1990.dagger2demo.util.SchedulerHelper;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2017-4-17.
 */
public class LoginPresent implements LoginContract.Presenter{

    LoginContract.View view;

    @Inject
    public LoginPresent(){
    }

    @Override
    public void  attachView(LoginContract.View view){
        this.view=view;
        view.setPresenter(this);
    }



    @Override
    public void detachView() {

    }

    @Override
    public void login() {
        final String password = view.getPass();
       final  String email = view.getUserName();


        Observable.just(1)
                .map(new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                Log.d("chen","当前的线程:"+Thread.currentThread().getName());
                Thread.sleep(2000);
                if(!email.equals("user@xx.com")){
                    throw new InvalidateException("用户名或密码错误",InvalidateException.PASS);
                }

                if(!password.equals("123456")){
                    throw new InvalidateException("用户名或密码错误",InvalidateException.USERNAME);
                }

                return true;
            }
        }).compose(SchedulerHelper.applySchedulers())
               .compose(new DialogHelper(new DialogHelper.ProgressListener() {
                   @Override
                   public void hide() {
                    view.showLoading(false);
                   }

                   @Override
                   public void show() {
                    view.showLoading(true);
                   }
               }).applyDialog())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                        view.resetError();
                    }

                    @Override
                    public void onNext(Object value) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getMessage();
                        if(e instanceof InvalidateException){
                            int errorCode = ((InvalidateException) e).getErrorCode();
                            if(errorCode ==InvalidateException.PASS){
                                view.showPassError(message);
                            }else if(errorCode==InvalidateException.USERNAME){
                                view.showUserNameError(message);
                            }
                        }else{
                            view.showLoginFail();
                        }


                    }

                    @Override
                    public void onComplete() {
                            view.showLoginSuccess();
                    }
                });




    }

    @Override
    public void invalidateForm(Observable<CharSequence> observable1, Observable<CharSequence> observable2) {

       Observable.combineLatest(observable1, observable2, new BiFunction<CharSequence, CharSequence, Boolean>() {
           @Override
           public Boolean apply(CharSequence email, CharSequence password) throws Exception {
               if (!TextUtils.isEmpty(password) && !isPasswordValid(password+"")) {
//                   throw new InvalidateException("密码太简单",InvalidateException.PASS);
                   return false;
               }

               if (TextUtils.isEmpty(email)) {
                   return false;
//                   throw new InvalidateException("邮箱为必选项",InvalidateException.USERNAME);
               } else if (!isEmailValid(email+"")) {
//                   throw new InvalidateException("无效的邮箱地址",InvalidateException.USERNAME);
                   return false;
               }

               return true;
           }
       }).subscribe(new Observer<Boolean>() {
           @Override
           public void onSubscribe(Disposable d) {

           }

           @Override
           public void onNext(Boolean value) {
               Log.d("chen",value+"____");
                view.enableButton(value);
           }

           @Override
           public void onError(Throwable e) {

           }

           @Override
           public void onComplete() {

           }
       });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
