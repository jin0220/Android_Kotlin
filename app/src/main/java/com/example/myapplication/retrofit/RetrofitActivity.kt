package com.example.myapplication.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityRetrofitBinding

/*
* Retrofit는 서버와 클라이언트 간 http 통신을 위한 라이브러리이다. 즉, 안드로이드에서 http 통신을 할 수 있도록 도와주는 것이다.
*/

class RetrofitActivity : AppCompatActivity() {
    val binding by lazy { ActivityRetrofitBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // api 호출
        RetrofitManager.instance.searchUsers(id = "20172804", pw = "123", completion = {
            response -> binding.response.text = response
        })
    }
}