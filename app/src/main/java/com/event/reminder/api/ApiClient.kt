package com.event.reminder.api

import com.android.mvvmandroidlib.data.BaseResponseModel
import com.event.reminder.data.model.request.LoginRequest
import com.event.reminder.data.model.request.SignUpRequest
import com.event.reminder.data.model.request.UserDetailsRequest
import com.event.reminder.data.model.response.LoggedInUser
import com.event.reminder.data.model.response.UserDetailsModel
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiClient {

    @POST(ApiConstant.LOGIN_API)
    fun login(@Body loginRequest: LoginRequest): Observable<LoggedInUser>

    @POST(ApiConstant.SIGN_UP_API)
    fun signUp(@Body signUpRequest: SignUpRequest): Observable<BaseResponseModel>

    @POST(ApiConstant.USER_DETAILS_API)
    fun getUserDetails(@Body loginRequest: UserDetailsRequest): Observable<UserDetailsModel>
}