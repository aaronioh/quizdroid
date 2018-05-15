package edu.washington.aaronioh.quizdroid

import android.Manifest
import android.app.Activity
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log

class FileReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context, p1: Intent) {
        val url = p1!!.getStringExtra("url")
        val request = DownloadManager.Request(Uri.parse(url))
        val downloadManager = p0!!.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        request.setTitle("Download")
        request.setDescription("Downloading json file")
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "questions.json")
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
        downloadManager.enqueue(request)
        Log.i("FileReceiver", "Download requested")
    }
}
