package com.sunxiaoyu.sxyphoto

import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.sunxiaoyu.utils.UtilsCore
import com.sunxiaoyu.utils.core.PhotoConfig
import com.sunxiaoyu.utils.core.utils.DialogUtils

class MainActivity : AppCompatActivity() {


    val imageView: ImageView by lazy { findViewById(R.id.imageView) as ImageView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // 拍照
    fun takePhoto(view: View) {
        DialogUtils.showEditDialog(this, "请输入标签名"){_,_ ->

        }
//        val savePath = "${Environment.getExternalStorageDirectory().absolutePath}/sunxyPhoto/${System.currentTimeMillis()}.jpg"
//        UtilsCore.manager()
//                .takePicture(this, 10088, true, savePath)
//                .subscribe {
//                    Log.v("sunxy", it.toString())
//                    if (it.success()) {
//                        val path = it.data.getStringExtra(PhotoConfig.RESULT_PHOTO_PATH)
//                        setImage(path)
//                    }
//                }
    }

    // 图库选照
    fun selectPhoto(view: View) {
        val savePath = "${Environment.getExternalStorageDirectory().absolutePath}/sunxyPhoto/${System.currentTimeMillis()}.jpg"
        UtilsCore.manager()
                .selectPicture(this, 10086, true, savePath)
                .subscribe {
                    Log.v("sunxy", it.toString())
                    if (it.success()) {
                        val path = it.data.getStringExtra(PhotoConfig.RESULT_PHOTO_PATH)
                        setImage(path)
                    }
                }
    }

    // 跳转界面
    fun startA(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("n", "------------------------------------------9999990909090")
        UtilsCore.manager().startForResult(this, 10022, intent)
                .subscribe {
                    Log.v("sunxy", it.toString())
                    if (it.success()) {
                        val path = it.data.getStringExtra("n")
                        Log.v("sunxy", "result : $path")
                    }
                }
    }

    // 申请权限
    fun apply(view: View){
        UtilsCore.manager().requestPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe {
                    Log.v("sunxy", it.toString())
                }
    }

    // 查看图片
    fun setImage(path: String) {
        imageView.setImageBitmap(BitmapFactory.decodeFile(path))
        imageView.setOnClickListener {
            UtilsCore.manager().seePhoto(this, path)
        }
    }
}
