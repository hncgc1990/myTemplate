package com.hncgc1990.dagger2demo.data;

import com.hncgc1990.dagger2demo.data.model.PostData;
import com.hncgc1990.dagger2demo.data.model.Result;
import com.hncgc1990.dagger2demo.data.remote.DemoMainService;
import com.hncgc1990.dagger2demo.injection.PerActivity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by cgc on 17-4-15.
 * 所有的Api提供者
 *
 */
@PerActivity//仅仅是在某一activity中保持单例
public class DataManager {

    private Retrofit mRetrofit;

    @Inject
    public DataManager(Retrofit retrofit){
        mRetrofit=retrofit;
    }

    /**
     * 返回列表数据
     * @return
     */
    public Observable<PostData<List<Result>>> getPostList(){
        DemoMainService demoMainService = mRetrofit.create(DemoMainService.class);
        return demoMainService.getPostList();
    }



}
