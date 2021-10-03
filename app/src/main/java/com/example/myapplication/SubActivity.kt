package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    val binding by lazy { ActivitySubBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            textView4.text = intent.getIntExtra("from",0).toString()

            button.setOnClickListener {
                val intent = Intent()
                val message = editText.text.toString()
                intent.putExtra("값", message)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}