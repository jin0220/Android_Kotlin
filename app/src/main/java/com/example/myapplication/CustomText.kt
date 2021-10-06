package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class CustomText: AppCompatTextView {
    constructor(context: Context):super(context){ //생성자

    }
    constructor(context: Context, attrs:AttributeSet):super(context,attrs){ //생성자 attrs -> 속성
        val attrList = context.obtainStyledAttributes(attrs, R.styleable.CustomText) //생성한 속성들이 들어감
        val size = attrList.indexCount

        for(i in 0 until size){
            when(attrList.getIndex(i)){
                R.styleable.CustomText_delimeter -> {
                    attrList.getString(attrList.getIndex(i))?.let {
                        process(it)
                    }
                }
            }
        }
    }
    constructor(context: Context, attrs:AttributeSet, defStyleAttr:Int):super(context,attrs,defStyleAttr){ //생성자 defStyleAttr -> 테마

    }

    fun process(delimeter:String){
        if(text.length == 8){
            val first4 = text.substring(0,4)
            val mid2 = text.substring(4,6)
            val last2 = text.substring(6)

            text = first4 + delimeter + mid2 + delimeter + last2
        }
    }
}