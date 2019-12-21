package com.event.reminder.data.model.request

import com.event.reminder.constant.RequestType
import java.io.Serializable

class FriendRequestDetailsListRequest (
    var userId: String?,
    var requestType: Int = RequestType.REQUEST_TYPE_SENT
) : Serializable