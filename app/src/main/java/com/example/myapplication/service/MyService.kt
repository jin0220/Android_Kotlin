package com.example.myapplication.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

/*
* 서비스는 화면이 없는 액티비티임
* 백그라운드로 실행하려면 서비스에서 스레드를 실행시켜 처리함
*/

class MyService : Service() {
 //서비스의 종류는 bound 서비스와 started 서비스가 있음

    companion object { //서비스의 명령어를 액티비티에서도 접근할 수 있음.
        val ACTION_CREATE = "create"
        val ACTION_DELETE = "delete"
    }

    inner class MyBinder: Binder(){
        fun getService():MyService { //서비스를 가져다 쓸 수 있게 해줌
            return this@MyService
        }
    }

    override fun onBind(intent: Intent): IBinder { //bound 서비스를 하기 위한 메서드, 바인드를 할때는 바인드를 해주는 바인더를 만들어서 보내줘야함.
        return MyBinder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int { //started 서비스를 하기 위한 메서드
        //서비스를 생성하면서 서비스쪽으로 명령어를 날려서 서비스에 지시함
        val action = intent?.action
        when(action){
            ACTION_CREATE -> create()
            ACTION_DELETE -> delete()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    fun create(){
        Log.d("확인", "create()가 호출됨.")
    }

    fun delete(){
        Log.d("확인", "delete()가 호출됨.")
    }
}