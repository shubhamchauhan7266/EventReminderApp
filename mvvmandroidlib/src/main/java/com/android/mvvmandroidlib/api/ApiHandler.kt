package com.android.mvvmandroidlib.api

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


abstract class ApiHandler {

    abstract fun getBaseUrl(): String

    abstract fun getReadTimeout(): Long

    abstract fun getConnectTimeout(): Long

    abstract fun getInterceptor(): Interceptor

    fun getRetrofitBuilder(): Retrofit.Builder {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(getInterceptor())
            .readTimeout(getReadTimeout(), TimeUnit.SECONDS)
            .connectTimeout(getConnectTimeout(), TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(client)
    }
}