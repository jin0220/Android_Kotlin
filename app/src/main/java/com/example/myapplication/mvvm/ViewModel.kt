package com.example.myapplication.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// 데이터의 변경 - 뷰모델과 라이브 데이터를 사용하지 않으면 화면 회전, 언어 변경 등의 변화가 있으면 데이터가 사라지게 됨.
// 뷰모델은 데이터의 변경 사항을 알려주는 라이브 데이터를 가지고 있다
class ViewModel : ViewModel() {
    // 라이브 데이터의 종류는 두가지 뮤터블 라이브 데이터(변경 가능한 데이터), 라이브 데이터(데이터 변경 불가능)

    // 변경가능한 Mutable 타입의 LiveData
    private val _currentValue = MutableLiveData<Int>()

    // 무결성을 위한 Getter
    val currentValue : LiveData<Int>
        get() = _currentValue

    //초기값 설정
    init {
        _currentValue.value = 0
    }

    fun updateValue(actionType: Int){
        when(actionType){
            1 ->_currentValue.value = _currentValue.value?.plus(1)
            2 -> _currentValue.value = _currentValue.value?.minus(1)
        }
    }
}