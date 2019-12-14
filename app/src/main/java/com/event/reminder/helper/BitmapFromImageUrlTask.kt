package com.event.reminder.helper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.event.reminder.callback.IResultCallBack
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

/**
 * This helper class is used to get bitmap from URL. It is using [HttpURLConnection] to download image
 * and send result back through [IResultCallBack].
 *
 * @author Shubham Chauhan
 */
class BitmapFromImageUrlTask(private val iResultCallBack: IResultCallBack<Bitmap>) :
    AsyncTask<String?, Void?, Bitmap?>() {

    private val TAG: String = BitmapFromImageUrlTask::class.java.simpleName

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        LoggerUtils.info(TAG, "Inside onPostExecute")
        try {
            if (result != null) {
                iResultCallBack.onSuccess(result)
            } else {
                iResultCallBack.onFailure("Bitmap is null")
            }
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
        }
    }

    override fun doInBackground(vararg params: String?): Bitmap? {
        LoggerUtils.debug(TAG, "Inside doInBackground")
        try {
            val url = URL(params[0])
            LoggerUtils.debug(TAG, "[Url : $url]")
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val inputStream = connection.inputStream
            return BitmapFactory.decodeStream(inputStream)
        } catch (e: MalformedURLException) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
        } catch (e: IOException) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
        }
        return null
    }
}