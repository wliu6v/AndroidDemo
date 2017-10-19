package com.liuwei.knoweasy

import android.os.Bundle
import android.support.text.emoji.EmojiCompat
import android.support.text.emoji.bundled.BundledEmojiCompatConfig
import android.util.Log
import com.liuwei.knoweasy.base.BaseActivity
import kotlinx.android.synthetic.main.activity_edittext.*

/**
 * Created by liuwei on 2017/10/18.
 */
class EditTextDemoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EmojiCompat.init(BundledEmojiCompatConfig(this))
        setContentView(R.layout.activity_edittext)

        setBackable()
        title = "Emoji Edit Text Demo"

        Log.d("6v", "${editText1.keyListener.inputType}")
        Log.d("6v", "${editText2.keyListener.inputType}")
    }
}
