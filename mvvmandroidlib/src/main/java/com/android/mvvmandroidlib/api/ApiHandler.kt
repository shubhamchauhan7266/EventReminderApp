package com.android.mvvmandroidlib.api

import com.android.mvvmandroidlib.BuildConfig
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


abstract class ApiHandler {

    abstract fun getBaseUrl(): String

    abstract fun getReadTimeout(): Long

    abstract fun getConnectTimeout(): Long

    abstract fun getInterceptor(): Interceptor

    fun getRetrofitBuilder(): Retrofit.Builder {

        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            interceptor.level = HttpLoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(getInterceptor())
            .readTimeout(getReadTimeout(), TimeUnit.SECONDS)
            .connectTimeout(getConnectTimeout(), TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
    }
}