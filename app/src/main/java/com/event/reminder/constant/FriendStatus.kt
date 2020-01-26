package com.event.reminder.constant

/**
 * This constant class is used to provide Friend Status.
 *
 * @author Shubham Chauhan
 */
object FriendStatus {
    const val NOT_A_FRIEND = 0
    const val PENDING = 1
    const val ACCEPTED = 2
    const val REJECTED = 3
    const val BLOCKED = 4
    const val UN_FRIEND = 5
    const val UN_BLOCKED = 6
    const val CANCEL = 7

    /*fun getUpdatedFriendStatus(currentFriendStatus: Int, isFriendStatus: Boolean) : Int{
        return when(currentFriendStatus){
            NOT_A_FRIEND -> {
                if(isFriendStatus){
                    PENDING
                }else{
                    BLOCKED
                }
            }
            PENDING -> {
                if(isFriendStatus){
                    ACCEPTED
                }else{
                    BLOCKED
                }
            }
            ACCEPTED -> {
                if(isFriendStatus){
                    REJECTED
                }else{
                    BLOCKED
                }
            }
            REJECTED -> {
                if(isFriendStatus){
                    PENDING
                }else{
                    BLOCKED
                }
            }
            BLOCKED -> {

            }
            UN_FRIEND -> {

            }
            UN_BLOCKED -> {

            }
        }
    }*/
}