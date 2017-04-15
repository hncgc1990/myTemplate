package com.hncgc1990.dagger2demo.data.remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cgc on 17-4-15.
 * Retrofit
 *提供全局的Retrofit
 *
 */

public class DemoRetrofitFactory {


    public static  Retrofit getInstance(){
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);//设置查看日志的等级
                OkHttpClient client=new OkHttpClient.Builder().addInterceptor(logging ).build();

                Retrofit retrofit = new Retrofit.Builder()
                        .client(client)
                        .baseUrl("http://gank.io/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用rxjava返回
                        .build();
        return retrofit;
    }

}
