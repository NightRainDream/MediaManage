package com.nightrain.mediamanage

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.nightrain.mediaselect.MediaSelectHelp
import com.nightrain.mediaselect.entity.MediaEntity
import com.nightrain.mediaselect.listener.MediaSelectCallback

class MainActivity : AppCompatActivity() {
    private lateinit var iv_picture: ImageView
    private lateinit var iv_video: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iv_picture = findViewById(R.id.iv_picture)
        iv_video = findViewById(R.id.iv_video)
    }

    fun selectPicture(view: View) {
        MediaSelectHelp.BuildSelectPicture()
            .launch(this, object : MediaSelectCallback {
                override fun onSelectSuccess(entity: MediaEntity) {
                    Glide.with(this@MainActivity)
                        .load(entity.mediaUri)
                        .into(iv_picture)
                }

                override fun onSelectError(error: String) {
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_LONG).show()
                }
            })
    }

    fun selectGif(view: View) {
        MediaSelectHelp.BuildSelectGIF()
            .launch(this, object : MediaSelectCallback {
                override fun onSelectSuccess(entity: MediaEntity) {
                    Glide.with(this@MainActivity)
                        .load(entity.mediaUri)
                        .into(iv_picture)
                }

                override fun onSelectError(error: String) {
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_LONG).show()
                }
            })
    }

    fun selectVideo(view: View) {
        MediaSelectHelp.BuildSelectVideo()
            .launch(this, object : MediaSelectCallback {
                override fun onSelectSuccess(entity: MediaEntity) {
                    iv_video.setVideoURI(entity.mediaUri)
                    iv_video.requestFocus()
                    iv_video.start()
                }

                override fun onSelectError(error: String) {
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_LONG).show()
                }
            })
    }

    fun selectAudio(view: View) {
        MediaSelectHelp.BuildSelectAudio()
            .launch(this, object : MediaSelectCallback {
                override fun onSelectSuccess(entity: MediaEntity) {
                    val play = MediaPlayer()
                    play.setDataSource(this@MainActivity,entity.mediaUri)
                    play.prepare()
                    play.start()
                }

                override fun onSelectError(error: String) {
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_LONG).show()
                }
            })
    }


}