package com.example.androidservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        val dowmloadTime = intent.getStringExtra("name")
        val downloadUrl = intent.getStringExtra("downloadUrl")
        Toast.makeText(context , "Yahallo at $dowmloadTime , $downloadUrl" , Toast.LENGTH_LONG).show()


    }
}