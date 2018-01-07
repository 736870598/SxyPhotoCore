package com.sunxiaoyu.sxyphoto

import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.sunxiaoyu.photocore.PhotoConfig
import com.sunxiaoyu.photocore.SxyUtilsManager
import com.sunxiaoyu.photocore.AvoidOnResult
import com.sunxiaoyu.photocore.core.ui.SxySelectPhotoActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
//        SxyUtilsManager.getManager().selectPhoto(this, 100, true, savePath)

        val intent = Intent(this, SxySelectPhotoActivity::class.java)
        intent.putExtra(PhotoConfig.NEED_COMPRESS, true)
        intent.putExtra(PhotoConfig.SAVE_PATH, savePath)

//        val intent = Intent(this, SecondActivity::class.java)
        AvoidOnResult(this).startForResult(intent, 1000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val intent = it.data
                    val name = intent.getStringExtra(PhotoConfig.RESULT_PHOTO_PATH)
                    Log.v("sunxy", "requsetCode: ${it.requestCode}, resultCode: ${it.resultCode}, name: $name")
                }
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
