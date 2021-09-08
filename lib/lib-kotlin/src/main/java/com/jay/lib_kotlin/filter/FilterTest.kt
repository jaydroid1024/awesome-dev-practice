package com.jay.lib_kotlin.filter

import com.jay.lib_kotlin.filter.FilterTest.Companion.LAYOUT_FOLDER_FILTER
import java.io.File
import java.io.FilenameFilter

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/7
 */
class FilterTest {
    companion object {
        //指定筛选条件
        val LAYOUT_FOLDER_FILTER = FilenameFilter { dir: File?, name: String ->
            name.startsWith("layout")
        }
    }


}

fun main() {
    val file = File("layout/", "layout")
    if (LAYOUT_FOLDER_FILTER.accept(file, file.name)) {
        println("get this file")
    }
}