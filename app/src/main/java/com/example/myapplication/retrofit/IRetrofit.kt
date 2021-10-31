package com.example.myapplication.retrofit


import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface IRetrofit {
    //GET : 서버 내 데이터 조회 용도, URL에 데이터를 모두 담아서 전송
    //POST : Create, POST는 서버에 데이터를 생성하는 메서드(Method), 데이터를 @BODY에 담아서 요청하는 방식

    //http://easyfarm.dothome.co.kr/json/user_table.php?id=""&password=""
    @GET("json/user_table.php") // baseUrl 뒤에 붙는 추가된 url을 넣어준다.
    fun searchUsers(@Query("id") id: String, @Query("password") pw: String): Call<JsonElement>


}