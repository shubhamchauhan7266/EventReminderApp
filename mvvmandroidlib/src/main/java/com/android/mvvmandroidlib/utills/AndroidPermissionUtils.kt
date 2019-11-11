package com.android.mvvmandroidlib.utills

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions

/**
 * This util class is used to provide some common methods related to Android Permission.
 * Ex - Camera, internal and external storage permission etc..
 * This class is declared as object('Singleton').
 *
 * @author Shubham Chauhan
 */
object AndroidPermissionUtils {

    /**
     * Method is used to check all given runtime permission.
     *
     * @param context        context
     * @param permissionList A List of Android runtime permission
     * @return true if all permission is granted , otherwise false
     */
    fun checkPermission(context: Context, vararg permissionList: String): Boolean {

        if (ContextUtils.isContextNull(context)) {
            return false
        }
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

        if (ContextUtils.isContextNull(context)) {
            return
        }
        requestPermissions(context, permissionList, requestCode)
    }

    /**
     * Method is used to request for all given runtime permission.
     *
     * @param context        context
     * @param requestCode    requestCode
     * @param permissionList A list of all given runtime permission
     */
    fun requestForPermission(
        context: androidx.fragment.app.Fragment,
        requestCode: Int,
        vararg permissionList: String
    ) {

        if (ContextUtils.isObjectNull(context)) {
            return
        }
        context.requestPermissions(permissionList, requestCode)
    }
}