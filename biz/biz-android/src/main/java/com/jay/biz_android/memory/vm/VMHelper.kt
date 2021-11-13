package com.jay.biz_android.memory.vm

import java.lang.reflect.InvocationTargetException

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/10/4
 */
object VMHelper {

    private const val SELECT_RUNTIME_PROPERTY = "persist.sys.dalvik.vm.lib"
    private const val LIB_DALVIK = "libdvm.so"
    private const val LIB_ART = "libart.so"
    private const val LIB_ART_D = "libartd.so"

    fun getCurrentRuntimeValue(): CharSequence? {
        return try {
            val systemProperties = Class.forName("android.os.SystemProperties")
            try {
                val get = systemProperties.getMethod(
                    "get",
                    String::class.java, String::class.java
                ) ?: return "WTF?!"

                try {
                    val value = get.invoke(
                        systemProperties, SELECT_RUNTIME_PROPERTY,  /* Assuming default is */
                        "Dalvik"
                    ) as String
                    if (LIB_DALVIK.equals(value)) {
                        return "Dalvik"
                    } else if (LIB_ART.equals(value)) {
                        return "ART"
                    } else if (LIB_ART_D.equals(value)) {
                        return "ART debug build"
                    }
                    value
                } catch (e: IllegalAccessException) {
                    "IllegalAccessException"
                } catch (e: IllegalArgumentException) {
                    "IllegalArgumentException"
                } catch (e: InvocationTargetException) {
                    "InvocationTargetException"
                }
            } catch (e: NoSuchMethodException) {
                "SystemProperties.get(String key, String def) method is not found"
            }
        } catch (e: ClassNotFoundException) {
            "SystemProperties class is not found"
        }
    }
}