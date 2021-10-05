package com.example.myapplication.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityFragmentBinding

class FragmentActivity : AppCompatActivity() {

    val binding by lazy { ActivityFragmentBinding.inflate(layoutInflater) }
    val firstFragment by lazy { FirstFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment()

        binding.send.setOnClickListener {
            firstFragment.setValue("값 전달하는 두 번째 방법")
        }

    }

    fun goSecondFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, SecondFragment()).addToBackStack("second").commit()
        //addToBackStack()는 뒤로가기를 하기 위해 필요
    }

    fun goBack(){
        onBackPressed()
    }

    fun setFragment(){
        //1. 사용할 프래그먼트 생성
//        val firstFragment = FirstFragment()

        //액티비티에서 프래그먼트로 데이터 전달하기
        val bundle = Bundle()
        bundle.putString("key1", "firstFragment")

        firstFragment.arguments = bundle

        //2. 트랜잭션 생성
        val transaction = supportFragmentManager.beginTransaction()
        //3.트랜잭션을 통해 프래그먼트 삽입
        transaction.add(R.id.frameLayout, firstFragment).commit()
    }
}