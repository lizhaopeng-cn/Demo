package com.lzp.retrofitrxjava;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lzp on 2017/8/1.
 */

public interface MovieModel {
    @GET("top250")
//    Call<MovieBeen> getTopVovie(@Query("start") int start, @Query("count") int count);
    Observable<MovieBeen> getTopMovie(@Query("start") int start, @Query("count") int count);
}
