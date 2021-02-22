package com.qlife.base_web.widget.loading

import android.content.Context
import android.util.TypedValue

fun dp2px(context: Context, dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        context.resources.displayMetrics
    )
}