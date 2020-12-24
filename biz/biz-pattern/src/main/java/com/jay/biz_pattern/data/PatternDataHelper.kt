package com.jay.biz_pattern.data

import com.jay.base_component.widget.demolist.DemoListEntity


/**
 * 参考：
https://refactoringguru.cn/
https://blog.csdn.net/rocketeerli/article/details/81585705
https://www.runoob.com/design-pattern/design-pattern-tutorial.html
 * @author wangxuejie
 * @version 1.0
 * @date 2020/8/13
 */
object PatternDataHelper {

    /**
     * 设计模式可以根据其意图或目的来分成三类。
    创建型模式 提供创建对象的机制， 增加已有代码的灵活性和可复用性。
    结构型模式 介绍如何将对象和类组装成较大的结构， 并同时保持结构的灵活和高效。
    行为型模式 负责对象间的高效沟通和职责委派。
     */
    fun demoItemData(): ArrayList<DemoListEntity> {
        val list = ArrayList<DemoListEntity>()
        list.add(DemoListEntity(headerTitle = "创建型模式"))
        list.add(DemoListEntity(headerTitle = "结构型模式"))
        list.add(DemoListEntity(headerTitle = "行为型模式"))

        return list

    }


}