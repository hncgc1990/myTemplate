package com.hncgc1990.dagger2demo.data.remote;

import com.hncgc1990.dagger2demo.data.model.PostData;
import com.hncgc1990.dagger2demo.data.model.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by cgc on 17-4-15.
 * 提供某一业务模块的Api
 */

public interface DemoMainService {
    @GET("data/Android/13/1")
    public Observable<PostData<List<Result>>> getPostList();
}
