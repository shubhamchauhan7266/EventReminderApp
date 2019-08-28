package com.android.mvvmandroidlib.utills

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.requestPermissions
import android.support.v4.app.Fragment


object AndroidPermissionUtils {

    /**
     * Method is used to check all given runtime permission.
     *
     * @param context        context
     * @param permissionList A List of Android runtime permission
     * @return true if all permission is granted , otherwise false
     */
    fun checkPermission(context: Context, vararg permissionList: String): Boolean {

        for (permission in permissionList) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    /**
     * Method is used to request for all given runtime permission.
     *
     * @param context        context
     * @param requestCode    requestCode
     * @param permissionList A list of all given runtime permission
     */
    fun requestForPermission(context: Activity, requestCode: Int, vararg permissionList: String) {

        requestPermissions(context, permissionList, requestCode)
    }

    /**
     * Method is used to request for all given runtime permission.
     *
     * @param context        context
     * @param requestCode    requestCode
     * @param permissionList A list of all given runtime permission
     */
    fun requestForPermission(context: Fragment, requestCode: Int, vararg permissionList: String) {

        context.requestPermissions(permissionList, requestCode)
    }
}