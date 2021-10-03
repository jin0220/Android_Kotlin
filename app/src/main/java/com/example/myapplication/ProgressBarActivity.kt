package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_progress_bar.*
import kotlin.concurrent.thread

class ProgressBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar)

        //ui는 메인스레드에서 동작

        showProgress(true)

        //아래 코드처럼 사용했을 경우 ui를 그리는 코드까지 같이 멈춰버림
//        Thread.sleep(3000)
//        progressBar.visibility = View.GONE

        //thread() 함수를 사용하여 따로 스레드를 만듦, 메인스레드와 상관없이 동작함 -> 서브 스레드
        thread(start=true){
            Thread.sleep(3000)
            //그림을 그려주는 스레드가 아니기 때문에 동작을 하지 않음. 화면에 영향을 미치는 코드는 메인스레드로 다시 보내야한다.
            runOnUiThread {
//                progressLayout.visibility = View.GONE
                showProgress(false)
            }
        }
    }

    fun showProgress(show: Boolean){
        progressLayout.visibility = if(show) View.VISIBLE else View.GONE
    }
}