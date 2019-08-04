package com.event.reminder.api

import com.android.mvvmandroidlib.api.ApiHandler
import com.event.reminder.constant.AppConstant
import okhttp3.Interceptor
import retrofit2.Retrofit

class EventReminderApiHandler : ApiHandler() {

    companion object {

        @Volatile
        private var retrofitBuilder: Retrofit.Builder? = null

        @Volatile
        private var apiHandler: EventReminderApiHandler? = null

        // singleton
        fun getAPIHandler(): EventReminderApiHandler? {
            if (apiHandler == null) {
                synchronized(this) {
                    if (apiHandler == null) {
                        apiHandler = EventReminderApiHandler()
                    }
                }
            }
            return apiHandler
        }
    }

    override fun getInterceptor(): Interceptor {

        return Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")

            // Adding Authorization token (API Key)
            // Requests will be denied without API key
//            if (!TextUtils.isEmpty("sf")) {
//                requestBuilder.addHeader("Authorization", "sf")
//            }

            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    override fun getBaseUrl(): String {
        return ApiConstant.API_BASE_URL
    }

    override fun getReadTimeout(): Long {
        return AppConstant.NETWORK_TIMEOUT
    }

    override fun getConnectTimeout(): Long {
        return AppConstant.NETWORK_TIMEOUT
    }

    //get API Client instance with retrofit module(singleton)
    fun getAPIClient(): ApiClient {

        if (retrofitBuilder == null) {
            retrofitBuilder = getRetrofitBuilder()
        }
        return retrofitBuilder!!.build().create(ApiClient::class.java)
    }
}