package com.example.androidservice

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log
import android.widget.CalendarView
import android.widget.Toast
import java.util.*
import kotlin.concurrent.thread

class MyService : Service() {

    lateinit var receiver : MyReceiver

    companion object{

        val ACTION_DOWNLOAD_COMPLETE = "com.example.service.action.download_complete"
    }
    var i = 0

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("SErvice" , "Oncreate yahallo")
        Toast.makeText(this, "IamStupid" , Toast.LENGTH_LONG).show()

        receiver = MyReceiver()  // creating a receiver
        val fliter = IntentFilter(MyService.ACTION_DOWNLOAD_COMPLETE)
        registerReceiver(receiver , fliter)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        i += 1
        Log.d("SErvice" , "onStartCommand yahallo")
        Toast.makeText(this, "IamStupid -> onStartCommand $i" , Toast.LENGTH_LONG).show()

        val downloadUrl = intent?.getStringExtra("url") ?: "http://google.com"


        // create a thread here to perform a task .
        thread {
            // executed on the new thread
            // perform long running task

            // code to for http download
            //TODO - code for http download
            // we wamt to notify the user  -> Toast , status baar notification , broadcast

            val bIntent = Intent(MyService.ACTION_DOWNLOAD_COMPLETE)
            bIntent.putExtra("name" , Calendar.getInstance().time.toString())
            bIntent.putExtra("downloadUrl" , downloadUrl)

            sendBroadcast(bIntent)
            Log.d("SErvice" , "Thread yahallo")
            //Toast.makeText(this,"completed Yahallo" , Toast.LENGTH_LONG).show()// cant create a toast as it require an activity


        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {

        super.onDestroy()

        Log.d("SErvice" , "onDestroy yahallo")
        Toast.makeText(this, "IamStupid -> onDestroy" , Toast.LENGTH_LONG).show()

        unregisterReceiver(receiver)
    }
}