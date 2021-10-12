package com.example.myapplication.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RoomMemo::class), version = 1, exportSchema = false) //사용할 테이블이 몇 개가 있는지 알려줌
abstract class RoomHelper: RoomDatabase() { //실제로 room을 사용, DAO 클래스들을 꺼내서 쓸 수 있게 해줌
    abstract fun roomMemoDao(): RoomMemoDAO
}