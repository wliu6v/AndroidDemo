package com.liuwei.knoweasy.grid

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.liuwei.knoweasy.tool.getMeasureSize
import com.liuwei.knoweasy.tool.getMeasureType

/**
 * Created by liuwei on 2017/12/1.
 */

class LogLinearLayout : LinearLayout {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.d("LogLinearLayout", "id=${hashCode()} | onMeasure | widthMeasureSpec=(${getMeasureType(widthMeasureSpec)}, ${getMeasureSize(widthMeasureSpec)}) , heightMeasureSpec=(${getMeasureType(heightMeasureSpec)}, ${getMeasureSize(heightMeasureSpec)})")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        Log.d("LogLinearLayout", "id=${hashCode()} | onLayout | Changed=$changed , left=$left , top=$top , right=$right , bottom=$bottom")
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun measureChildWithMargins(child: View, parentWidthMeasureSpec: Int, widthUsed: Int, parentHeightMeasureSpec: Int, heightUsed: Int) {
        Log.d("LogLinearLayout", "id=${hashCode()} | measureChildWithMargins | child=${child.javaClass.simpleName} , parentWidthMeasure=(${getMeasureType(parentWidthMeasureSpec)}, ${getMeasureSize(parentWidthMeasureSpec)}) , widthUsed=$widthUsed , parentHeightMeasureSpec=(${getMeasureType(parentHeightMeasureSpec)}, ${getMeasureSize(parentHeightMeasureSpec)}) , heightUsed=$heightUsed")
        super.measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        Log.d("LogLinearLayout", "id=${hashCode()} | onSizeChanged | w=$w , h=$h , oldw=$oldw , oldh=$oldh")
        super.onSizeChanged(w, h, oldw, oldh)
    }
}
