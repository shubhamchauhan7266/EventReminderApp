package com.android.mvvmandroidlib.utills

import android.content.Context
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader


object FileUtils {

    private val TAG: String = FileUtils::class.java.simpleName

    fun writeToFile(context: Context, strFileName: String, strJSONData: String): Boolean {
        var writeSuccess = true
        try {
            val fileOut = context.openFileOutput(strFileName, Context.MODE_PRIVATE)
            fileOut.write(strJSONData.toByteArray())
            val fileDescriptor = fileOut.fd
            fileDescriptor.sync()
            fileOut.flush()
            fileOut.close()
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
            writeSuccess = false
        }

        return writeSuccess
    }

    fun readFromFile(context: Context, strFileName: String): String {
        var strRet = ""
        if (!isFileExist(context, strFileName)) {
            return strRet
        }

        try {
            val fileIn = context.openFileInput(strFileName)
            val inputRead = InputStreamReader(fileIn)
            val bufferedReader = BufferedReader(inputRead)

            var receiveString: String?
            val stringBuilder = StringBuilder()

            receiveString = bufferedReader.readLine()
            while (receiveString != null) {
                stringBuilder.append(receiveString)
                receiveString = bufferedReader.readLine()
            }
            strRet = stringBuilder.toString()
            bufferedReader.close()
            inputRead.close()
            fileIn.close()
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
        }
        return strRet
    }

    fun isFileExist(context: Context, strFileName: String): Boolean {
        var isFileExists = false
        try {
            val file = File(context.filesDir.toString() + "/" + strFileName)
            if (file.exists()) {
                isFileExists = true
            }
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
        }
        return isFileExists
    }
}