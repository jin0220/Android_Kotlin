package com.example.myapplication.roomDB

import androidx.room.*

@Dao
interface RoomMemoDAO {
    @Query("select * from room_memo")
    fun getAll() : List<RoomMemo>

    @Insert(onConflict = OnConflictStrategy.REPLACE) //insert로 데이터가 들어왔을 때 이미 있는 값(키)이 insert가 실행되면 충돌이 일어나면서 업데이트 해줌
    fun insert(memo:RoomMemo)

    @Delete
    fun delete(memo:RoomMemo)
}