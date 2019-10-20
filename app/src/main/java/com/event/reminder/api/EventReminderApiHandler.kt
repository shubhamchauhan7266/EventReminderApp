package com.event.reminder.api

import com.android.mvvmandroidlib.api.ApiHandler
import com.event.reminder.BuildConfig
import com.event.reminder.constant.AppConstant
import com.event.reminder.utills.EventReminderSharedPrefUtils
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
                .addHeader(ApiConstant.HEADER_ACCEPT, ApiConstant.HEADER_TYPE_APPLICATION_JSON)
                .addHeader(ApiConstant.HEADER_CONTENT_TYPE, ApiConstant.HEADER_TYPE_APPLICATION_JSON)
                .addHeader(ApiConstant.HEADER_APP_VERSION, BuildConfig.VERSION_NAME)
                .addHeader(ApiConstant.HEADER_API_VERSION, "")
                .addHeader(ApiConstant.HEADER_AUTHORIZATION, ApiConstant.BEARER + EventReminderSharedPrefUtils.getAccessToken())

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