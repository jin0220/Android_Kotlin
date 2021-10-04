package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_spinner.*

class SpinnerActivity : AppCompatActivity() {

    var items = listOf("가","나","다","라")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        spinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,items)

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
//                when(position){
//                    0-> textView.text = items[0]
//                    1-> textView.text = items[1]
//                    2-> textView.text = items[2]
//                    3-> textView.text = items[3]
//                }
                val selected = items.get(position)
                textView.text = selected
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }
}