package com.example.myapplication.roomDB

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myapplication.databinding.ActivityRoomBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomActivity : AppCompatActivity() {

    val binding by lazy { ActivityRoomBinding.inflate(layoutInflater) }
    lateinit var helper: RoomHelper
    lateinit var memoAdapter: RecyclerAdapter
    val memoList = mutableListOf<RoomMemo>()
    lateinit var memoDao: RoomMemoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        helper = Room.databaseBuilder(this, RoomHelper::class.java, "room_db") //마지막인자 -> db 명
//            .allowMainThreadQueries() //공부할 때만 사용, 메인 스레드에서 쿼리를 사용, 원래는 메인스레드에서 쿼리를 할 수 없음
            .build()

        memoDao = helper.roomMemoDao()

//        memoList.addAll(helper.roomMemoDao().getAll())

        memoAdapter = RecyclerAdapter(memoList)

        refreshAdapter()

        with(binding){
            recyclerMemo.adapter = memoAdapter
            recyclerMemo.layoutManager = LinearLayoutManager(this@RoomActivity)

            save.setOnClickListener {
                val content = editMemo.text.toString()
                if(content.isNotEmpty()){
                    val datetime = System.currentTimeMillis()
                    val memo = RoomMemo(content,datetime)
                    editMemo.setText("")
                    insertMemo(memo)
//                    memoList.clear()
//                    memoList.addAll(helper.roomMemoDao().getAll())
//                    memoAdapter.notifyDataSetChanged()

                }
            }
        }
    }

    fun insertMemo(memo:RoomMemo){
        CoroutineScope(Dispatchers.IO).launch {
            memoDao.insert(memo)
            refreshAdapter()
        }
    }

    fun refreshAdapter(){
        //room과 관련된 코드는 서브 스레드에서 실행시킴
        CoroutineScope(Dispatchers.IO).launch {
            memoList.clear()
            memoList.addAll(memoDao.getAll()) //memoDao.getAll() 같은 것은 서브 스레드에서만 실행해야됨.
            //화면 구성하는 코드는 메인 스레드에서 실행시킴
            withContext(Dispatchers.Main){
                memoAdapter.notifyDataSetChanged()
            }
        }
    }

}