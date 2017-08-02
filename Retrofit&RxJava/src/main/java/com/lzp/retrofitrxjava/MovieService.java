package com.lzp.retrofitrxjava;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lzp on 2017/8/1.
 */

public interface MovieService {
    @GET("top250")
    Call<MovieEntity> getTopView(@Query("start") int start, @Query("count") int count);
}
