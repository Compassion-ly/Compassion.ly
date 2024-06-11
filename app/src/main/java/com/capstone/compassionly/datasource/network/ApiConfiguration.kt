package com.capstone.compassionly.datasource.network

import com.capstone.compassionly.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfiguration {

    companion object {
        private val interceptor = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
        private val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .build()
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://compassionly-api-test-zue4vcwkfa-et.a.run.app")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        val hitPointService : HitPointService by lazy {
            retrofit.create(HitPointService::class.java)
        }
    }

}