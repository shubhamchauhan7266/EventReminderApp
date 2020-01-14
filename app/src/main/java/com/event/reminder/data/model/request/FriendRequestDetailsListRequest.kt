package com.event.reminder.data.model.request

import com.android.mvvmandroidlib.data.BaseRequestModel
import com.event.reminder.constant.RequestType

class FriendRequestDetailsListRequest (
    var userId: String?,
    var requestType: Int = RequestType.REQUEST_TYPE_SENT
) : BaseRequestModel()