package com.samples.bootcamp.flickrApp

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL

enum class DownloadStatus {
    OK, IDLE, NOT_INITIALISED, FAILED_OR_EMPTY, PERMISSIONS_ERROR, ERROR
}

class GetRawData(private val listener: OnDownloadComplete) : AsyncTask<String, Void, String>() {
    private val TAG = "GetRawData"
    private var downloadStatus =
        DownloadStatus.IDLE

    interface OnDownloadComplete {
        fun onDownloadComplete(data: String, status: DownloadStatus)
    }

//    fun setDownloadCompleteListener(callbackObject: MainActivity) {
//        listener = callbackObject
//    }

//    override fun onProgressUpdate(vararg values: Void?) {
//        super.onProgressUpdate(*values)
//        Log.d(TAG, "onProgressUpdate called")
//    }

    override fun onPostExecute(result: String) {
        //super.onPostExecute(result)
        Log.d(TAG, "onPostExecute called")
        listener.onDownloadComplete(result, downloadStatus)
    }

    override fun doInBackground(vararg params: String?): String {
        Log.d(TAG, "doInBackground, params ${params[0]}")
        if (params[0] == null) {
            downloadStatus =
                DownloadStatus.NOT_INITIALISED
            return "No URL specified"
        }
        try {
            Log.d(TAG, "doInBackground: in Try{} ")
            downloadStatus = DownloadStatus.OK
            return URL(params[0]).readText()
        } catch (e: Exception) {
            return when (e) {
                is MalformedURLException -> {
                    downloadStatus =
                        DownloadStatus.NOT_INITIALISED
                    "doInBackGround: Invalid URL ${e.message}"
                }
                is IOException -> {
                    downloadStatus =
                        DownloadStatus.FAILED_OR_EMPTY
                    "doInBackGround: IO Exception reading data ${e.message}"
                }
                is SecurityException -> {
                    downloadStatus =
                        DownloadStatus.PERMISSIONS_ERROR
                    "doInBackGround: Secutity exception: Needs permission? ${e.message}"
                }
                else -> {
                    downloadStatus =
                        DownloadStatus.ERROR
                    "Unknown Error ${e.message}"
                }
            }
        }

    }


}