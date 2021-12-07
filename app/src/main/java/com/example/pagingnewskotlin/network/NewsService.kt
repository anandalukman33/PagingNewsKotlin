package com.example.pagingnewskotlin.network

import com.example.pagingnewskotlin.model.NewsBean
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines?country=us&category=business&apiKey=d365302acf244531b773ab7075bf271d&pageSize=20&page=2")
    fun getNews(
        @Query("q") keyword: String,
        @Query("apiKey") api: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int

    ): Flowable<NewsBean>
}