package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentReceiverBinding
import com.example.myapplication.databinding.FragmentSenderBinding

class SenderFragment : Fragment() {

    lateinit var binding:FragmentSenderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSenderBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            yes.setOnClickListener {
                val bundle = bundleOf("senderKey" to "yes") //키 to 값
                setFragmentResult("request", bundle) //프래그먼트로 값을 전달해주는 함수
            }
            no.setOnClickListener {
                val bundle = bundleOf("senderKey" to "no") //키 to 값
                setFragmentResult("request", bundle) //프래그먼트로 값을 전달해주는 함수
            }
        }
    }
}