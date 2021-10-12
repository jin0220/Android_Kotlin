package com.example.myapplication

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ActivityPermissionBinding
import kotlinx.android.synthetic.main.activity_permission.*
import java.lang.Exception
import java.text.SimpleDateFormat

class PermissionActivity : BaseActivity() {

    val binding by lazy { ActivityPermissionBinding.inflate(layoutInflater) }

    val PERM_CAMERA = arrayOf(Manifest.permission.CAMERA)
//    val PERM_STORAGE = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    val PERM_STORAGE = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    val REQ_STORAGE = 99 //요청 코드
    val REQ_CAMERA = 100 //요청 코드
    val REQ_GALLERY = 102

    val TAKE_CAMERA = 101


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //액티비티가 시작되면 스토리지 권한을 실행, 공용 저장소 권한이 있는지 확인
        requirePermissions(PERM_STORAGE, REQ_STORAGE)

    }

    fun initViews(){
        //카메라 버튼이 클릭되면 권한을 먼저 체크 후 카메라 앱 실행
        binding.camera.setOnClickListener {
//            checkPermission()
            requirePermissions(PERM_CAMERA, REQ_CAMERA)
        }
        binding.gallery.setOnClickListener {
            openGallery()
        }
    }

    //원본 이미지의 주소를 저장할 변수
    var realUri: Uri? = null //원본 이미지를 가져오기 위해 uri 생성

    //3. 카메라에 찍은 사진을 저장하기 위한 uri를 넘겨줌
    fun openCamera(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) //카메라 앱이 실행

        createImageUri(newFileName(),"image/jpg")?.let { uri ->
            realUri = uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, realUri)
            startActivityForResult(intent, TAKE_CAMERA)
        }
    }

    fun openGallery(){
        val intent  = Intent(Intent.ACTION_PICK) //선택창이 나옴
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, REQ_GALLERY)
    }

    //1. 원본 이미지를 저장할 uri를 mediaStore(데이터베이스)에 생성하는 메서드
    fun createImageUri(filename:String, mimeType:String) : Uri?{ //mimeType -> 이미지의 타입
        val values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME, filename) //MediaStore.Images.Media.DISPLAY_NAME -> filename 키
        values.put(MediaStore.Images.Media.MIME_TYPE, mimeType)

        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values) //파일을 저장할 수 있는 uri를 넘겨줌
        //MediaStore.Images.Media.EXTERNAL_CONTENT_URI -> 미디어가 저장되는 테이블 명이라고 생각하면됨.
    }

    //2. 파일 이름을 생성하는 메서드
    fun newFileName(): String{
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename = sdf.format(System.currentTimeMillis())
        return "${filename}.jpg"
    }

    //원본 이미지를 불러오는 메서드
    fun loadBitmap(photoUri: Uri) : Bitmap? {
        //이미지를 불러올 때 예외가 발생할 수 있음
        try {
            return if (Build.VERSION.SDK_INT > 27){
                val source = ImageDecoder.createSource(contentResolver,photoUri)
                ImageDecoder.decodeBitmap(source)
            } else{
                MediaStore.Images.Media.getBitmap(contentResolver,photoUri)
            }

        }catch (e:Exception){
            e.printStackTrace()
        }
        return null
    }

    //4. 카메라를 찍은 후 호출
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            when(requestCode){
                TAKE_CAMERA -> {
                    //카메라 촬영 결과를 처리
//                    val bitmap = data?.extras?.get("data") as Bitmap//촬영한 미리보기 이미지가 들어있음, 미리보기 이미지는 해상도가 깨짐
//                    binding.imagePreview.setImageBitmap(bitmap)
                    realUri?.let { uri ->
                        val bitmap = loadBitmap(uri)
                        binding.imagePreview.setImageBitmap(bitmap)

                        realUri = null //다 썼으니 null
                    }
                }
                REQ_GALLERY -> {
                    //uri 형태로 이미지 주소가 저장되어 있음
                    data?.data?.let { uri ->
                        binding.imagePreview.setImageURI(uri)
                    }
                }
            }
        }
    }

    override fun permissionGranted(requestCode: Int) {
        when(requestCode){
            REQ_STORAGE -> {
//                Toast.makeText(this, "스토리지 권한 승인", Toast.LENGTH_SHORT).show()
                initViews()
            }
            REQ_CAMERA -> openCamera()
        }
    }

    override fun permissionDenied(requestCode: Int) {
        when(requestCode){
            REQ_STORAGE -> {
                Toast.makeText(this, "공용 저장소 권한을 승인해야 앱을 사용할 수 있습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
            REQ_CAMERA -> {
                Toast.makeText(this, "카메라 권한 없음", Toast.LENGTH_SHORT).show()
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