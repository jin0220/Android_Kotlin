package com.example.myapplication.retrofit

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// object는 싱글턴임. 실글턴은 메모리를 하나만 쓴다.
object RetrofitClient {
    //레트로핏 클라이언트 선언
    private var retrofitClient: Retrofit? = null
//    private lateinit var retrofitClient: Retrofit

    //레트로핏 클라이언트 가져오기
    fun getClient(baseUrl: String): Retrofit? {
        Log.d("확인", "getClient() 호출")


        //okhttp 인스턴스 생성
        val client = OkHttpClient.Builder()

        //로그를 찍기 위해 로깅 인터셉터 설정 -> 전반적인 통신 내용을 볼 수 있음
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                Log.d("확인", "RetrofitClient - 로깅 / message $message")
            }

        })

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)

        //위에서 설정한 로깅 인터셉터를 okhttp 클라이언트에 추가한다.
        client.addInterceptor(loggingInterceptor)


        //기본 파라미터 인터셉터 설정 (좀 더 알아보기)
//        val baseParameterInterceptor : Interceptor = (object : Interceptor{
//            override fun intercept(chain: Interceptor.Chain): Response {
//                Log.d("확인", "RetrofitClient - intercept()")
//                //오리지날 리퀘스트 -> 기본 파라미터가 들어가기 전에 기존의 리퀘스트를 가져옴
//                val originalRequest = chain.request()
//
//                //쿼리 파라미터 추가
//                return chain.proceed()
//            }
//
//        })
//
//        //위에서 설정한 기본 파라미터 인터셉터를 okhttp 클라이언트에 추가한다.
//        client.addInterceptor(baseParameterInterceptor)


        // 레트로핏 클라이언트가 없으면 빌드함.
        if(retrofitClient == null){
            //레트로핏 빌더를 통해 인스턴스를 생성
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                //위에서 설정한 클라이언트로 레트로핏 클라이언트를 설정한다.
                .client(client.build())
                .build()
        }
        return retrofitClient
    }
}