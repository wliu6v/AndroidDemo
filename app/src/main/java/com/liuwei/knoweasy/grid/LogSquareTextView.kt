package com.liuwei.knoweasy.grid

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.util.Log
import com.liuwei.knoweasy.tool.getMeasureSize
import com.liuwei.knoweasy.tool.getMeasureType

/**
 * Created by liuwei on 2017/12/1.
 */
class LogSquareTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.d("SquareTextView", "id=${hashCode()} | onMeasure | widthMeasureSpec=(${getMeasureType(widthMeasureSpec)}, ${getMeasureSize(widthMeasureSpec)}) , heightMeasureSpec=(${getMeasureType(heightMeasureSpec)}, ${getMeasureSize(heightMeasureSpec)})")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, widthMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        Log.d("SquareTextView", "id=${hashCode()} | onLayout | Changed=$changed , left=$left , top=$top , right=$right , bottom=$bottom")
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        Log.d("SquareTextView", "id=${hashCode()} | onSizeChanged | w=$w , h=$h , oldw=$oldw , oldh=$oldh")
        super.onSizeChanged(w, h, oldw, oldh)
    }
}
