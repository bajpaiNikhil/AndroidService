package com.example.androidservice

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    fun buttonClick(view: View) {
        val serviceIntent = Intent(this,MyService::class.java)
        serviceIntent.putExtra("url" , "http://fgijnsdhusdhvj.com")

        when (view.id){
            R.id.startB ->{
                //launch service
                startService(serviceIntent)

            }

            R.id.stopB ->{
                //stop service
                stopService(serviceIntent)
            }

            R.id.scheduledB ->{
                startServiceLater()
            }
        }
    }

    private fun startServiceLater() {
        //scheduled to run my serice after 10 seconds

        val aManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val i = Intent(this, MyService::class.java)
        i.putExtra("url" , "https//testing.com")

        val pi = PendingIntent.getService(this,1,i,0)

        aManager.set(AlarmManager.RTC , System.currentTimeMillis()+10000 , pi)
    }
}