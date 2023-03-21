package com.example.enhanceittechincaltest.utils.api;


import com.example.enhanceittechincaltest.data.remote.tvshow.ModelResTvShows
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body

import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query

/**
 * Created by amansatija on 5/4/16.
 */
interface  ApiService {

    @GET("singlesearch/shows")
    suspend fun searchTvShows(@Query("q") query:String="",): Response<ModelResTvShows>
}