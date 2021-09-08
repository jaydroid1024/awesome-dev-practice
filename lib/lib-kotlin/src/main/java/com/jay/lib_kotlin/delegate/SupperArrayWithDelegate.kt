package com.jay.lib_kotlin.delegate

/**
 * 接口代理，默认还回去，可以重写需要改动的方法
 * 对象 list 和 map 代理 SupperArrayWithDelegate 实现 MutableList，MutableMap
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/2
 */
class SupperArrayWithDelegate<E>(
    private val list: MutableList<E?> = mutableListOf(),
    private val map: MutableMap<String, E> = mutableMapOf()
) : MutableList<E?> by list, MutableMap<String, E> by map {

    override fun clear() {
        list.clear()
        map.clear()
    }

    override fun isEmpty(): Boolean {
        return list.isEmpty() && map.isEmpty()
    }

    override val size: Int
        get() = list.size + map.size

    override fun set(index: Int, element: E?): E? {
        if (index <= list.size) {
            repeat(index - list.size - 1) {
                list.add(null)
            }
        }
        return list.set(index, element)
    }

    override fun toString(): String {
        return "list:$list,map:$map"
    }
}