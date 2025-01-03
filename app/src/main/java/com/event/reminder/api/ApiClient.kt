package com.event.reminder.api

import com.android.mvvmandroidlib.data.BaseResponseModel
import com.event.reminder.data.model.request.*
import com.event.reminder.data.model.response.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * This interface to provide all API method which is used by application to sync data from server.
 *
 * @author Shubham Chauhan
 */
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

    @POST(ApiConstant.FRIEND_REQUEST_LIST_API)
    fun getFriendRequestDetailsList(@Body loginRequest: FriendRequestDetailsListRequest): Observable<FriendRequestDetailsModel>

    @POST(ApiConstant.FRIEND_LIST_API)
    fun getFriendDetailsList(@Body friendListRequest: FriendListRequest): Observable<FriendDetailsModel>

    @POST(ApiConstant.UPDATE_FRIEND_STATUS_API)
    fun updateFriendStatus(@Body updateFriendStatusRequest: UpdateFriendStatusRequest): Observable<FriendStatusModel>

    @POST(ApiConstant.GET_FRIEND_STATUS_API)
    fun getFriendStatus(@Body getFriendStatusRequest: GetFriendStatusRequest): Observable<FriendStatusModel>

    @POST(ApiConstant.GENERATE_OTP_API)
    fun generateOTP(@Body generateOTPRequest: GenerateOTPRequest): Observable<BaseResponseModel>

    @POST(ApiConstant.VALIDATE_OTP_API)
    fun validateOTP(@Body validateOTPRequest: ValidateOTPRequest): Observable<BaseResponseModel>

    @POST(ApiConstant.CREATE_EVENT_API)
    fun createEvent(@Body createEventRequest: CreateEventRequest): Observable<BaseResponseModel>

    @POST(ApiConstant.UPDATE_EVENT_API)
    fun updateEvent(@Body updateEventRequest: UpdateEventRequest): Observable<BaseResponseModel>

    // TODO need to change request and response model.
    @POST(ApiConstant.SELF_EVENTS_LIST_API)
    fun getSelfEventList(@Body createEventRequest: CreateEventRequest): Observable<BaseResponseModel>

    @POST(ApiConstant.FRIEND_EVENTS_LIST_API)
    fun getFriendEventList(@Body createEventRequest: CreateEventRequest): Observable<BaseResponseModel>

    @POST(ApiConstant.GROUP_EVENTS_LIST_API)
    fun getGroupEventList(@Body createEventRequest: CreateEventRequest): Observable<BaseResponseModel>

    @GET(ApiConstant.ALL_EVENTS_LIST_API)
    fun getAllEventsList(@Query(ApiConstant.USER_ID) userId: String): Observable<BaseResponseModel>
}