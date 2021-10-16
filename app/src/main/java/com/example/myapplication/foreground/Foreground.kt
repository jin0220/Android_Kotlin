package com.example.myapplication.foreground

import android.app.*
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.myapplication.R
import kotlin.concurrent.thread

/*
* 포어그라운드 서비스 - 서비스와 함께 notification울 험께 보여주는 서비스
* 이 서비스를 사용하는 이유는 어떠한 앱이 백그라운드에서 실행되고 있으면 사용자는 알지 못함
* --> 서비스가 돌아가고 있다는 것을 사용자에게 알려서 주의를 주기 위해서 사용.
* 그래서 장시간 동작하는 서비스들은 포어그라운드 서비스로 만들게끔 함.
* 매니패스트에 퍼미션을 줘야함.
* */

class Foreground : Service() {
    //안드로이드 오레오이상부터는 채널을 사용함. 포어그라운드 서비스가 띄우는 notification이 채널을 사용함.

    val CHANNEL_ID = "FGS153"
    val NOTI_ID = 153

    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //마지막인자는 notification의 중요도 -> 중요도가 낮으면 알림이 있을때 소리가 없다거나 높으면 소리와 진동 등 알림의 강도가 세진다.
            val serviceChannel = NotificationChannel(CHANNEL_ID, "FOREGROUND", NotificationManager.IMPORTANCE_DEFAULT)
            //채널을 사용하겠다고 알려줌
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        //띄울 notification을 만듦.

        //notification을 만들어져서 변수에 저장
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .build()

        startForeground(NOTI_ID, notification) //현재 사용하는 서비스가 포어그라운드로 동작한다고 알려줌.

//        runBackground()

        return super.onStartCommand(intent, flags, startId)
    }

    fun runBackground(){
        thread(start = true){
            for(i in 0..100){
                Thread.sleep(1000)
                Log.d("서비스", "COUNT ==>$i")
            }
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return Binder()
    }
}