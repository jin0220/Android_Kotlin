package com.example.myapplication

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.fragment.FragmentActivity
import com.example.myapplication.fragment.FragmentDataActivity
import com.example.myapplication.recycler.RecyclerViewActivity
import com.example.myapplication.roomDB.RoomActivity
import com.example.myapplication.viewPager.ViewPagerActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {

            //토스트 메시지
            btn1.setOnClickListener {
                Toast.makeText(applicationContext, "click!", Toast.LENGTH_SHORT).show()
            }

            //스낵바
            btn2.setOnClickListener {
                Snackbar.make(it, "스낵바", Snackbar.LENGTH_LONG).show()
            }

            //화면전환
            btn3.setOnClickListener {
                val intent = Intent(applicationContext, SubActivity::class.java)
                intent.putExtra("from", 100)
                startActivityForResult(intent, 99)
            }

            //에디트 텍스트
            btn4.setOnClickListener {
                val intent = Intent(applicationContext, EditTextActivity::class.java)
                startActivity(intent)
            }

            //대화상자
            btn5.setOnClickListener {
                val builder = AlertDialog.Builder(applicationContext)

                builder.setIcon(R.drawable.ic_launcher_foreground) //제목 아이콘
                builder.setTitle("동의") //제목
                builder.setMessage("동의하십니까?") //내용

                //긍정 버튼
                builder.setPositiveButton("동의") { _, _ ->
                    Toast.makeText(applicationContext, "동의하셨습니다.", Toast.LENGTH_SHORT).show()
                }

                //부정 버튼
                builder.setNegativeButton("동의하지 않음") { _, _ ->
                    Toast.makeText(applicationContext, "동의하지 않습니다.", Toast.LENGTH_SHORT).show()
                }

                //중립 버튼
                builder.setNeutralButton("중립") { _, _ ->
                    Toast.makeText(applicationContext, "중립", Toast.LENGTH_SHORT).show()
                }

                //뒤로가기 or 바깥 부분 터치
                builder.setOnCancelListener {
                    Toast.makeText(applicationContext, "대화상자 닫음", Toast.LENGTH_SHORT).show()
                }

                builder.show()
            }

            //프로그래스바
            btn6.setOnClickListener {
                val intent = Intent(applicationContext, ProgressBarActivity::class.java)
                startActivity(intent)
            }

            //스피너
            btn7.setOnClickListener {
                val intent = Intent(applicationContext, SpinnerActivity::class.java)
                startActivity(intent)
            }

            btn8.setOnClickListener {
                val intent = Intent(applicationContext, SeekBarActivity::class.java)
                startActivity(intent)
            }

            btn9.setOnClickListener {
                val intent = Intent(applicationContext, RecyclerViewActivity::class.java)
                startActivity(intent)
            }

            btn10.setOnClickListener {
                val intent = Intent(applicationContext, FragmentActivity::class.java)
                startActivity(intent)
            }

            btn11.setOnClickListener {
                val intent = Intent(applicationContext, FragmentDataActivity::class.java)
                startActivity(intent)
            }

            btn12.setOnClickListener {
                val intent = Intent(applicationContext, CustomActivity::class.java)
                startActivity(intent)
            }

            btn13.setOnClickListener {
                val intent = Intent(applicationContext, ViewPagerActivity::class.java)
                startActivity(intent)
            }

            btn14.setOnClickListener {
                val intent = Intent(applicationContext, PermissionActivity::class.java)
                startActivity(intent)
            }

            btn15.setOnClickListener {
                val intent = Intent(applicationContext, RoomActivity::class.java)
                startActivity(intent)
            }

            btn16.setOnClickListener {
                val intent = Intent(applicationContext, ThreadActivity::class.java)
                startActivity(intent)
            }

            btn17.setOnClickListener {
                val intent = Intent(applicationContext, CoroutineActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                99 -> {
                    data?.getStringExtra("값")?.let { message ->
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun shardPreference(){
        val shard = getSharedPreferences("파일명", Context.MODE_PRIVATE)

        val firstOpen = shard.getBoolean("key", false) //키에 대한 정보가 저장되어 있는지 확인
        if(firstOpen){

        }

        val editor = shard.edit() //수정을 위한 에디터를 꺼냄
        editor.putBoolean("key",true)
        editor.commit()
    }

}