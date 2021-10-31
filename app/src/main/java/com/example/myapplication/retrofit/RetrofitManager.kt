package com.example.myapplication.retrofit

import android.util.Log
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {

    //싱글턴이 적용되도록 companion object 사용
    companion object{
        val instance = RetrofitManager()
    }

    // http는 보안상 사용하면 응답을 받을 수 없음. http 통신 허용을 해야함.
    val baseUrl: String = "http://easyfarm.dothome.co.kr/"
    //레트로핏 인터페이스 가져오기
    private val iRetrofit : IRetrofit? = RetrofitClient.getClient(baseUrl)?.create(IRetrofit::class.java)

    // api, 두 번째 인자 completion은 끝난 결과를 알려줌
    fun searchUsers(id: String?, pw: String?, completion: (String) -> Unit){
        val id = id?: "" //값이 비어있으면 ""을 넣어줌
        val pw = pw?: ""

        val call = iRetrofit?.searchUsers(id = id, pw = pw).let{
            it
        }?: return

        call.enqueue(object : retrofit2.Callback<JsonElement>{
            //응답 성공
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d("확인", "응답 성공 / response : ${response.body()}")

                completion(response.body().toString())
            }

            //응답 실패
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d("확인", "응답 실패 / t : $t")
            }

        })
    }
}