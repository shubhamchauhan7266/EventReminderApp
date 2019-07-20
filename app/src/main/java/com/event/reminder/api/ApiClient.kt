package com.event.reminder.api

import com.event.reminder.data.model.response.LoggedInUser
import io.reactivex.Observable
import retrofit2.http.POST

interface ApiClient {

    @POST("http")
    fun logout(): Observable<LoggedInUser>
}