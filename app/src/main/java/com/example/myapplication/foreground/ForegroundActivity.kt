package com.example.myapplication.foreground

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ActivityForegroundBinding

class ForegroundActivity : AppCompatActivity() {

    val binding by lazy { ActivityForegroundBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun serviceStart(View:View){
        val intent = Intent(this, Foreground::class.java)
        ContextCompat.startForegroundService(this, intent)
    }

    fun serviceStop(View: View){
        val intent = Intent(this, Foreground::class.java)
        stopService(intent)
    }
}