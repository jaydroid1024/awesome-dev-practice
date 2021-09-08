package com.jay.lib_kotlin

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/3
 */

fun main() {
    val a = FindTarget().getArray()
    val b = FindTarget().findNumberIn2DArray(a, 2)
    println("b:$b")

}

class FindTarget {

    //1,4
    //2,5
    //二分法只使用于下一行的第一个元素的值大于第一行的最后一个元素的情况
    fun findNumberIn2DArray(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isNullOrEmpty()) {
            return false
        }
        var left = 0
        var right = matrix[0].size * matrix.size - 1
        val col = matrix[0].size
        while (left <= right) {
            val mid = (left + right) / 2
            val tar = matrix[mid / col][mid % col]
            when {
                tar == target -> {
                    println("target: $target")
                    return true
                }
                tar < target -> {
                    left = mid + 1
                }
                else -> {
                    right = mid - 1
                }
            }
        }
        return false
    }


    fun getArray(): Array<IntArray> {
        val length = 2
        val ans = Array(size = length, init = { IntArray(length) })
        if (length == 2) {
            ans[0][0] = 1
            ans[0][1] = 4
            ans[1][0] = 2
            ans[1][1] = 5
            return ans
        }
        var num = 1
        for (i in ans.indices) {
            for (j in ans[i].indices) {
                ans[i][j] = num
                println("ans[i-$i][j-$j]:${ans[i][j]}")
                num++
            }
        }
        return ans
    }

}