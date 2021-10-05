package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentFirstBinding
import com.example.myapplication.fragment.FragmentActivity

class FirstFragment : Fragment() {

    lateinit var fragmentActivity: FragmentActivity
    lateinit var binding: FragmentFirstBinding

    override fun onAttach(context: Context) { //context -> 현재 프래그먼트를 삽입한 액티비티가 담겨있음
        super.onAttach(context)

        if(context is FragmentActivity) fragmentActivity = context
    }

    override fun onCreateView( //여기서는 바인딩만하고 onViewCreated()에서 로직을 처리하도록 구현할 수도 있음
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //액티비티에서 전달 잗은 데이터 꺼내기
        binding.textView9.text = arguments?.getString("key1")

        binding.button2.setOnClickListener {
            fragmentActivity.goSecondFragment()
        }
    }

    fun setValue(value:String){
        binding.textView10.text = value
    }
}