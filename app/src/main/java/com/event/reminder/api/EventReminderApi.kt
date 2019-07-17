package com.event.reminder.api

import com.event.reminder.constant.AppConstant
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object EventReminderApi {

    private var service: ApiService?

    init {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(AppConstant.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(AppConstant.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .build()
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(ApiConstant.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(client)

        service = retrofitBuilder.build().create(ApiService::class.java)
    }

    fun getService(): ApiService? {
        return service
    }
}