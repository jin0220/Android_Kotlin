package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.myapplication.databinding.ActivityThreadBinding
import kotlin.concurrent.thread

class ThreadActivity : AppCompatActivity() {

    val binding by lazy { ActivityThreadBinding.inflate(layoutInflater) }

    var total = 0
    var started = false

    //핸들러, 화면에 시간 값을 출력해주는 역할
    val handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            val minute = String.format("%02d", total/60)
            val second = String.format("%02d", total%60)

            binding.time.text = "$minute:$second"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.start.setOnClickListener {
            started = true
            thread(start=true) {
                while (started){
                    Thread.sleep(1000)
                    if(started) { //sleep이었을 때 false로 바뀔 경우에 대해 조건문 필요
                        total += 1
//                        runOnUiThread { //핸들러를 사용하거나 runOnUiThread를 사용하거나 선택!
//                            val minute = String.format("%02d", total/60)
//                            val second = String.format("%02d", total%60)
//
//                            binding.time.text = "$minute:$second"
//                        }
                        handler?.sendEmptyMessage(0)
                    }
                }
            }
        }

        binding.stop.setOnClickListener {
            if(started) {
                started = false
                total = 0
                binding.time.text = "00:00"
            }
        }

    }
}