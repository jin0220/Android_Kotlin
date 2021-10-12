package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ActivityPermissionBinding
import kotlinx.android.synthetic.main.activity_permission.*

class PermissionActivity : BaseActivity() {

    val binding by lazy { ActivityPermissionBinding.inflate(layoutInflater) }

    val PERM_CAMERA = arrayOf(Manifest.permission.CAMERA)
    val PERM_STORAGE = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

    val REQ_STORAGE = 99 //요청 코드
    val REQ_CAMERA = 100 //요청 코드

    val TAKE_CAMERA = 101

    override fun permissionGranted(requestCode: Int) {
        when(requestCode){
            REQ_STORAGE -> {
                Toast.makeText(this, "스토리지 권한 승인", Toast.LENGTH_SHORT).show()
            }
            REQ_CAMERA -> {
                openCamera()
            }
        }
    }

    override fun permissionDenied(requestCode: Int) {
        when(requestCode){
            REQ_STORAGE -> {
                Toast.makeText(this, "스토리지 권한 없음", Toast.LENGTH_SHORT).show()
                finish()
            }
            REQ_CAMERA -> {
                Toast.makeText(this, "카메라 권한 없음", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //액티비티가 시작되면 스토리지 권한을 실행
        requirePermissions(PERM_STORAGE, REQ_STORAGE)

        //카메라 버튼이 클릭되면 권한처리 후 카메라 앱 실행
        binding.camera.setOnClickListener {
//            checkPermission()
            requirePermissions(PERM_CAMERA, REQ_CAMERA)
        }

    }

    fun openCamera(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) //카메라 앱이 실행
        startActivityForResult(intent, TAKE_CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            when(requestCode){
                TAKE_CAMERA -> {
                    //카메라 촬영 결과를 처리
                }
            }
        }
    }

//BaseActivity를 사용하지 않았을 경우
//    fun checkPermission(){
//        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//        if(cameraPermission == PackageManager.PERMISSION_GRANTED){ //권한이 주어졌을 경우
//            openCamera()
//        }
//        else{
//            requestPermission()
//        }
//    }
//
//    fun openCamera(){
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) //카메라 앱이 실행
//        startActivity(intent)
//    }
//
//    fun requestPermission(){ //실제 권한 요청
//        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),99)
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        when(requestCode){
//            99 -> {
//                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    openCamera()
//                }
//                else{
//                    Toast.makeText(this, "권한을 승인하지 않으면 종료됩니다.", Toast.LENGTH_SHORT).show()
//                    finish()
//                }
//            }
//        }
//    }
}