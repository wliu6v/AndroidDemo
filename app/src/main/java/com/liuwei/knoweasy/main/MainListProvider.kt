package com.liuwei.knoweasy.main

import android.content.Context
import android.content.Intent

/**
 * Created by liuwei on 2017/7/16.
 */

class MainListProvider {
    val data = arrayListOf<DemoBean>()

    fun addData(bean: DemoBean) {
        data.add(bean)
    }

    fun getTitle(pos: Int) = data[pos].title

    fun getCount() = data.size

    fun getIntent(context: Context, pos: Int): Intent {
        val intent = Intent()
        intent.setClass(context, data[pos].targetClass)
        return intent
    }

}

data class DemoBean(val title: String, val targetClass: Class<out Any>)