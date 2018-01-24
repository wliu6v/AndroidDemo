package com.liuwei.knoweasy.video

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.widget.MediaController
import com.jakewharton.rxbinding.view.RxView
import com.liuwei.knoweasy.R
import com.liuwei.knoweasy.base.BaseActivity
import kotlinx.android.synthetic.main.activity_videoplayer.*

/**
 * Created by liuwei on 2017/10/9.
 */
class VideoPlayerActivity : BaseActivity() {
    var isPlaying = false
    var mediaController: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videoplayer)
        setBackable()
        title = "Video Player"

        RxView.clicks(videoPlayBtn).subscribe {
            if (isPlaying) {
                videoPlayBtn.text = "Play"
                videoView.pause()
            } else {
                videoPlayBtn.text = "Pause"
                playVideo()
            }

            isPlaying = !isPlaying
        }
    }

    fun playVideo() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
                return
            }
        }

        if (mediaController == null) {
            val uri = Uri.parse("${Environment.getExternalStorageDirectory().path}/6v/video/song1.mp4")
            mediaController = MediaController(this)
            videoView.setMediaController(mediaController)
            mediaController!!.setAnchorView(videoView)
            videoView.setVideoURI(uri)
        }

        videoView.start()
        videoView.requestFocus()
    }
}