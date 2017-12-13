package com.sunxiaoyu.sxyphoto

import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.ImageView
import com.sunxiaoyu.photocore.PhotoConfig
import com.sunxiaoyu.photocore.SxyUtilsManager

class MainActivity : AppCompatActivity() {


    val imageView : ImageView by lazy { findViewById(R.id.imageView) as ImageView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun takePhoto(view: View){
        val savePath = "${Environment.getExternalStorageDirectory().absolutePath}/sunxyPhoto/${System.currentTimeMillis()}.jpg"
        SxyUtilsManager.getManager().takePhoto(this, 100, true, savePath)
    }

    fun selectPhoto(view: View){
        val savePath = "${Environment.getExternalStorageDirectory().absolutePath}/sunxyPhoto/${System.currentTimeMillis()}.jpg"
        SxyUtilsManager.getManager().selectPhoto(this, 100, true, savePath)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val path = data?.getStringExtra(PhotoConfig.RESULT_PHOTO_PATH)
        imageView.setImageBitmap(BitmapFactory.decodeFile(path))

        imageView.setOnClickListener{
            SxyUtilsManager.getManager().seePhoto(this, path)
        }
    }

}
