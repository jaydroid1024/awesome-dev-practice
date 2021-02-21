package com.qlife.lib_app.appdelegate

/**
 * Application 生命周期分发
 * @author wangxuejie
 * @version 1.0
 * @date 12/30/20
 */
object AppPriority {


    /**
     * 低优先级
     */
    const val LOW_DEFAULT = 1

    object LOW {
        const val LOW_1 = 1
        const val LOW_2 = 2
        const val LOW_3 = 3
    }

    /**
     * 中优先级
     */
    const val MEDIUM_DEFAULT = 4

    object MEDIUM {
        const val MEDIUM_1 = 4
        const val MEDIUM_2 = 5
        const val MEDIUM_3 = 6
    }

    /**
     * 高优先级
     */
    const val HIGH_DEFAULT = 7

    object HIGH {
        const val HIGH_1 = 7
        const val HIGH_2 = 8
        const val HIGH_3 = 9
    }
}