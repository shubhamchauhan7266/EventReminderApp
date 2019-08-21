package com.android.mvvmandroidlib.utills

object FileUtils {

    /*fun WriteToFile(context: Context, strFileName: String, strJSONData: String): Boolean {
        var context = context
        var bWriteSuccess = true
        try {
            val fileout = context.openFileOutput(strFileName, context.MODE_PRIVATE)
            //            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            //            outputWriter.write(strJSONData);
            //            outputWriter.flush();
            //            outputWriter.close();
            fileout.write(strJSONData.toByteArray())
            val fileDescriptor = fileout.fd
            fileDescriptor.sync()
            fileout.flush()
            fileout.close()
        } catch (e: Exception) {
//            CLogger.TraceLog(CLogger.TRACE_TYPE.TRACE_ERROR, "Exception Occurred : " + Log.getStackTraceString(e))
            bWriteSuccess = false
        }

        return bWriteSuccess
    }*/

    /*fun ReadFromFile(context: Context, strFileName: String): String {
        //reading text from file
        var strRet = ""
        if (!CFileSystem.IsFileExist(context, strFileName)) {
            return strRet
        }

        try {
            val fileIn = context.openFileInput(strFileName)
            val InputRead = InputStreamReader(fileIn)
            val bufferedReader = BufferedReader(InputRead)

            var receiveString: String? = ""
            val stringBuilder = StringBuilder()

            while ((receiveString = bufferedReader.readLine()) != null) {
                stringBuilder.append(receiveString)
            }
            strRet = stringBuilder.toString()
            receiveString = null
            bufferedReader.close()
            InputRead.close()
            fileIn.close()
        } catch (e: Exception) {
//            CLogger.TraceLog(CLogger.TRACE_TYPE.TRACE_ERROR, "Exception Occurred : " + Log.getStackTraceString(e))
        }

        return strRet
    }*/
}