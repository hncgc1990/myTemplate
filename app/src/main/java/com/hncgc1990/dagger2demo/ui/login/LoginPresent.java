package com.hncgc1990.dagger2demo.ui.login;

import android.text.TextUtils;

import com.hncgc1990.dagger2demo.exception.InvalidateException;
import com.hncgc1990.dagger2demo.util.SchedulerHelper;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2017-4-17.
 */
public class LoginPresent implements LoginContract.Presenter{

    LoginContract.View view;

    @Inject
    public LoginPresent(LoginContract.View view){
        this.view=view;
    }


    @Inject
    public void  setup(){
    view.setPresenter(this);
    }



    @Override
    public void start() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void login() {
        final String password = view.getPass();
       final  String email = view.getUserName();


        Observable.just(1)
                .map(new Function() {
                    @Override
                    public Boolean apply(Object o) throws Exception {

                        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
                         throw new InvalidateException("密码太简单",InvalidateException.PASS);
                        }

                        if (TextUtils.isEmpty(email)) {

                            throw new InvalidateException("邮箱为必选项",InvalidateException.USERNAME);
                        } else if (!isEmailValid(email)) {
                            throw new InvalidateException("无效的邮箱地址",InvalidateException.USERNAME);
                        }

                        return true;
                    }
                })
                .delay(3, TimeUnit.SECONDS)

                .map(new Function() {
            @Override
            public Object apply(Object o) throws Exception {

                if(!email.equals("123456")){
                    throw new InvalidateException("用户名或密码错误",InvalidateException.PASS);
                }

                if(!password.equals("user@xx.com")){
                    throw new InvalidateException("用户名或密码错误",InvalidateException.USERNAME);
                }

                return true;
            }
        }).compose(SchedulerHelper.applySchedulers())

                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        view.resetError();
                    }
                })
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {

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



    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
