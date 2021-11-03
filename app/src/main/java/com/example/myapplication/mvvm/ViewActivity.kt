package com.example.myapplication.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityViewBinding

class ViewActivity : AppCompatActivity() {
//    private val binding by lazy{ActivityViewBinding.inflate(layoutInflater)}

    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_view)

        // 뷰모델을 LifeCycle 에 종속시킴, LifeCycle 동안 옵저버 역할을 함
        binding.lifecycleOwner = this

        // 뷰모델 가져오기
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        binding.viewModel = viewModel



// 데이터 바인딩의 사용으로 필요 없음
//
//        // 뷰모델 라이브 데이터 접근하여 값이 변경되면 호출
//        viewModel.currentValue.observe(this, Observer {
//            binding.num.text = it.toString()
//        })

//        binding.up.setOnClickListener {
//            viewModel.updateValue(ActionType.UP)
//        }
//
//        binding.down.setOnClickListener {
//            viewModel.updateValue(ActionType.DOWN)
//        }
    }
}