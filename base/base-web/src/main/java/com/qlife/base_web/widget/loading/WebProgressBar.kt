package com.qlife.base_web.widget.loading

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.qlife.base_web.R

/**
 * web进度条
 */
class WebProgressBar : View {

    private var progress: Int = 0
    private var barColor: Int = resources.getColor(R.color.aliceblue)
    private var mHeight: Int = 0
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    init {
        paint.style = Paint.Style.FILL
        paint.color = barColor
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mHeight = h
    }


    fun setProgress(newProgress: Int) {
        progress = newProgress
        invalidate()
    }

    fun setProgressBarColor(newProgressBarColor: Int) {
        paint.color = newProgressBarColor
        invalidate()
    }

    fun getProgress(): Int {
        return progress
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.strokeWidth = mHeight.toFloat()
        canvas?.drawLine(0f, 0f, width * progress / 100f, 0f, paint)
    }
}