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
import com.sunxiaoyu.photocore.core.PhotoConfig
import com.sunxiaoyu.photocore.SxyUtilsManager
import java.security.Permission
import java.security.Permissions

class MainActivity : AppCompatActivity() {


    val imageView: ImageView by lazy { findViewById(R.id.imageView) as ImageView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun takePhoto(view: View) {
        val savePath = "${Environment.getExternalStorageDirectory().absolutePath}/sunxyPhoto/${System.currentTimeMillis()}.jpg"
        SxyUtilsManager.getManager()
                .takePicture(this, 10088, true, savePath)
                .subscribe {
                    Log.v("sunxy", it.toString())
                    if (it.success()) {
                        val path = it.data.getStringExtra(PhotoConfig.RESULT_PHOTO_PATH)
                        setImage(path)
                    }
                }
    }

    fun selectPhoto(view: View) {
        val savePath = "${Environment.getExternalStorageDirectory().absolutePath}/sunxyPhoto/${System.currentTimeMillis()}.jpg"
        SxyUtilsManager.getManager()
                .selectPicture(this, 10086, true, savePath)
                .subscribe {
                    Log.v("sunxy", it.toString())
                    if (it.success()) {
                        val path = it.data.getStringExtra(PhotoConfig.RESULT_PHOTO_PATH)
                        setImage(path)
                    }
                }
    }

    fun setImage(path: String) {
        imageView.setImageBitmap(BitmapFactory.decodeFile(path))
        imageView.setOnClickListener {
            SxyUtilsManager.getManager().seePhoto(this, path)
        }
    }

    fun startA(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("n", "------------------------------------------9999990909090")
        SxyUtilsManager.getManager().startForResult(this, 10022, intent)
                .subscribe {
                    Log.v("sunxy", it.toString())
                    if (it.success()) {
                        val path = it.data.getStringExtra("n")
                        Log.v("sunxy", "result : $path")
                    }
                }
    }

    fun apply(view: View){
        SxyUtilsManager.getManager().requestPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe {
                    Log.v("sunxy", it.toString())
                }
    }
}
