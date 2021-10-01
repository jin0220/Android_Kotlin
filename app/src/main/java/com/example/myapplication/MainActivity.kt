package com.example.myapplication

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //토스트 메시지
        btn1.setOnClickListener {
            Toast.makeText(this, "click!",Toast.LENGTH_SHORT).show()
        }

        //스낵바
        btn2.setOnClickListener {
            Snackbar.make(it, "스낵바",Snackbar.LENGTH_LONG).show()
        }

        //화면전환
        btn3.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }

        //에디트 텍스트
        btn4.setOnClickListener {
            val intent = Intent(this, EditTextActivity::class.java)
            startActivity(intent)
        }

        //대화상자
        btn5.setOnClickListener {
            val builder = AlertDialog.Builder(this)

            builder.setIcon(R.drawable.ic_launcher_foreground) //제목 아이콘
            builder.setTitle("동의") //제목
            builder.setMessage("동의하십니까?") //내용

            //긍정 버튼
            builder.setPositiveButton("동의"){ _, _ ->
                Toast.makeText(this, "동의하셨습니다.",Toast.LENGTH_SHORT).show()
            }

            //부정 버튼
            builder.setNegativeButton("동의하지 않음"){ _, _ ->
                Toast.makeText(this, "동의하지 않습니다.",Toast.LENGTH_SHORT).show()
            }

            //중립 버튼
            builder.setNeutralButton("중립"){ _, _ ->
                Toast.makeText(this, "중립",Toast.LENGTH_SHORT).show()
            }

            //뒤로가기 or 바깥 부분 터치
            builder.setOnCancelListener {
                Toast.makeText(this, "대화상자 닫음",Toast.LENGTH_SHORT).show()
            }

            builder.show()
        }

        //프로그래스바
        btn6.setOnClickListener {

        }

        //스피너
        btn7.setOnClickListener {
            val intent = Intent(this, SpinnerActivity::class.java)
            startActivity(intent)
        }

    }
}