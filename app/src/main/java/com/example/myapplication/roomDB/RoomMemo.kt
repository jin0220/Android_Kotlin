package com.example.myapplication.roomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_memo")
class RoomMemo {
    @PrimaryKey(autoGenerate = true) //테이블에서 autoIncrement 역할
    @ColumnInfo //컬럼으로 사용
    var no: Long? = null

    @ColumnInfo
    var content: String = ""

    @ColumnInfo(name = "date")
    var datetime: Long = 0

    constructor(content:String, datetime:Long){ //생성자
        this.content = content
        this.datetime = datetime
    }
}