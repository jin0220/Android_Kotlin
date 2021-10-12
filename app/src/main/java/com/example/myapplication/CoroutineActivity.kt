package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class CoroutineActivity : AppCompatActivity() {
    //Coroutine - 백그라운드 처리 구조

    val binding by lazy { ActivityCoroutineBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.download.setOnClickListener {
            //어떤 영역에서 동작을 하는지 명시, 코드가 다른 영역에서 동작을 하게끔 만들어줌, 코루틴만의 새로운 영역
            CoroutineScope(Dispatchers.Main).launch {
                //메인 스레드에서는 화면을 다루는 코드를 작성
                binding.progress.visibility = View.VISIBLE
                val url = binding.editUrl.text.toString()

                val bitmap = withContext(Dispatchers.IO) {
                    //메인 scope는 통신이나 파일을 읽을 수 없음
                    loadImage(url)
                }

                binding.Preview.setImageBitmap(bitmap)
                binding.progress.visibility = View.INVISIBLE
            }
        }
    }
}

//클래스 안에 쓰면 메서드, 밖은 함수
suspend fun loadImage(imageUrl:String) : Bitmap {
    val url = URL(imageUrl)
    val stream = url.openStream() //주소에 있는 것들을 스트림으로 연다

    return BitmapFactory.decodeStream(stream) //비트맵으로 반환
}