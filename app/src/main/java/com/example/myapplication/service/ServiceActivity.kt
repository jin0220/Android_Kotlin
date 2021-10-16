package com.example.myapplication.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.example.myapplication.databinding.ActivityServiceBinding

class ServiceActivity : AppCompatActivity() {

    val binding by lazy { ActivityServiceBinding.inflate(layoutInflater) }

    lateinit var serviceIntent:Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        serviceIntent = Intent(this, MyService::class.java)
    }

    fun serviceStart(View:View){ //View:View -> onCreate에서 따로 호출하지 않고 view에서 호출됨
        serviceIntent.action = MyService.ACTION_CREATE//명령어를 실어서 보내줌.
        startService(serviceIntent) //=> 서비스를 생성해주는 메서드, 서비스가 이미 생성되어 있으면 서비스의 onStartCommand가 호출됨
    }

    fun serviceStop(View:View){
        stopService(serviceIntent)
    }

    var myService: MyService? = null
    var isService = false
    val connetion = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) { //바인딩이 되면 호출
            isService = true
            val binder = service as MyService.MyBinder //서비스의 onbind 메서드를 실행한 결과가 넘어옴
            myService = binder.getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) { //예외가 발생하였을 때 실행
            isService = false
        }
    }

    fun serviceBind(View:View){
        bindService(intent, connetion, Context.BIND_AUTO_CREATE)
    }

    fun serviceCommand() {
        myService?.create()
        myService?.delete()
    }

    fun serviceUnbind(View:View){

    }


}