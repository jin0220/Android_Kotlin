package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityRecyclerViewBinding
import com.example.myapplication.databinding.ActivitySubBinding
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {

    val binding by lazy { ActivityRecyclerViewBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //1. 데이터를 불러온다.
        val data = loadData()

        //2. 어댑터를 생성
        val adapter = Adapter(data)

        //3. 화면의 리사이클러뷰와 연결
        binding.recycler.adapter = adapter

        //4. 레이아웃 매니저 설정(레이아웃 매니저로 목록처럼 보여줄 수도 있고, 그리드 형태로 보여줄 수도 있음)
        binding.recycler.layoutManager = LinearLayoutManager(this)
    }

    fun loadData(): MutableList<Memo>{
        val memoList = mutableListOf<Memo>()

        for (no in 1..10){
            val title = "제목 $no"
            val date = System.currentTimeMillis()
            val memo = Memo(no,title,date)
            memoList.add(memo)
        }
        return memoList
    }
}