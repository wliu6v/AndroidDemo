package com.liuwei.knoweasy.tool

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

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