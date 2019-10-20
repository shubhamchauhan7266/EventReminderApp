package com.event.reminder.api

import com.android.mvvmandroidlib.data.BaseResponseModel
import com.event.reminder.data.model.request.*
import com.event.reminder.data.model.response.*
import io.reactivex.Observable
import retrofit2.http.*

interface ApiClient {

    @POST(ApiConstant.LOGIN_API)
    fun login(@Body loginRequest: LoginRequest): Observable<LoggedInUserModel>

    @POST(ApiConstant.SIGN_UP_API)
    fun signUp(@Body signUpRequest: SignUpRequest): Observable<BaseResponseModel>

    @GET(ApiConstant.USER_DETAILS_API)
    fun getUserDetails(@Query(ApiConstant.USER_ID) userId: String): Observable<UserDetailsModel>

    @POST(ApiConstant.UPDATE_USER_DETAILS_API)
    fun updateUserDetails(@Body updateUserDetailsRequest: UpdateUserDetailsRequest): Observable<BaseResponseModel>

    @GET(ApiConstant.NOTIFICATION_DETAILS_LIST_API)
    fun getNotificationDetailsList(@Query(ApiConstant.USER_ID) userId: String): Observable<NotificationDetailsModel>

    @POST(ApiConstant.FRIEND_REQUEST_DETAILS_LIST_API)
    fun getFriendRequestDetailsList(@Body loginRequest: FriendRequestDetailsListRequest): Observable<FriendRequestDetailsModel>

    @POST(ApiConstant.FRIEND_DETAILS_LIST_API)
    fun getFriendDetailsList(@Body friendDetailsrequest: FriendDetailsListRequest): Observable<FriendDetailsModel>
}