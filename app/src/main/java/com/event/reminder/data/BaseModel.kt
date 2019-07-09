package com.event.reminder.data

import java.io.Serializable

open class BaseModel : Serializable {

    var responseMessage : String = ""
    var responseCode : Int = 0
    var status : Boolean = false
}