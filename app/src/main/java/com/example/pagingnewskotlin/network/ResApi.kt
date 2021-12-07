package com.example.pagingnewskotlin.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ResApi {

    companion object {

        val baseUrl = "https://newsapi.org/v2/"

        fun restApi(): NewsService {
            // Step 1 configurasi interceptor
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttp = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

            // Step 2 build retrofit config
            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()

            // Step 3 include configurasi retrofit ke interface
            return retrofit.create(NewsService::class.java)
        }
    }
}