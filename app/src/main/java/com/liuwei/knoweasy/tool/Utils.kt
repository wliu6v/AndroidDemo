package com.liuwei.knoweasy.tool

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.*

/**
 * Created by liuwei on 2017/7/25.
 */
fun dp2px(context: Context, dp: Float): Float {
    val metrics = context.resources.displayMetrics;
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics)
}

fun isLighterColor(color: Int): Boolean {
    val y = Color.red(color) * 0.299 + Color.green(color) * 0.587 + Color.blue(color) * 0.114
    return y > 157
}

fun hideKeyboard(v: View) {
    val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(v.windowToken, 0)
}

//
fun View.getMeasureType(measureSpec: Int): String {
    val mode = (measureSpec and (0x3 shl 30)) ushr 30
    when (mode) {
        0 -> return "UNSPECIFIED"
        1 -> return "EXACTLY"
        2 -> return "AT_MOST"
    }
    return mode.toString()
}

fun View.getMeasureSize(measureSpec: Int): Int {
    val size = (measureSpec and (0x3 shl 30).inv())
    return size
}

var StringDataArrayList = ArrayList<String>(Arrays.asList("Licensed", "under", "the", "Apache", "Version",
        "2.0", "you", "may", "not", "use", "this", "file", "except", "in", "compliance", "with",
        "the", "You", "may", "obtain", "a", "copy", "of", "the", "License", "at", "Unless",
        "required", "by", "applicable", "law", "or", "agreed", "to", "in", "distributed", "under",
        "the", "License", "is", "distributed", "on", "an", "IS", "WITHOUT", "WARRANTIES", "OR",
        "CONDITIONS", "OF", "ANY", "either", "express", "or", "See", "the", "License", "for", "the",
        "specific", "language", "governing", "permissions", "limitations", "under", "the"))