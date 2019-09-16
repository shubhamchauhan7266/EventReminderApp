package com.android.mvvmandroidlib.utills

import android.content.Context
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

/**
 * This util class is used to provide some methods related to file.
 * Ex - write to file or read from file etc..
 * This class is declared as object('Singleton').
 *
 * @author Shubham Chauhan
 */
object FileUtils {

    private val TAG: String = FileUtils::class.java.simpleName

    /**
     * Method is used to write given data in file.
     *
     * @param context context
     * @param strFileName file name
     * @param strJSONData json data
     *
     * @return true if write data in file successfully otherwise false
     */
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

    /**
     * Method is used to read data from file.
     *
     * @param context context
     * @param strFileName file name
     *
     * @return data which is read from file.
     */
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

    /**
     * Method is used to know that given file is exist or not.
     *
     * @param context context
     * @param strFileName file name
     *
     * @return true if file exist otherwise false.
     */
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