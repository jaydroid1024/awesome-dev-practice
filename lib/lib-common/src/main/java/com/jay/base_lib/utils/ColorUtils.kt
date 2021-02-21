package com.jay.base_lib.utils

/**
 * @author wangxuejie
 * @version 1.0
 * @date 2020/10/6
 */
object ColorUtils {
    /**
     * 获取十六进制的颜色代码.例如  "#5A6677"
     * 分别取R、G、B的随机值，然后加起来即可
     *
     * @return String
     */
    fun getRandColor(): String? {
        var R: String = Integer.toHexString((0..256).random()).toUpperCase()
        var G: String = Integer.toHexString((0..256).random()).toUpperCase()
        var B: String = Integer.toHexString((0..256).random()).toUpperCase()
        R = if (R.length == 1) "0$R" else R
        G = if (G.length == 1) "0$G" else G
        B = if (B.length == 1) "0$B" else B
        return "#$R$G$B"
    }
}