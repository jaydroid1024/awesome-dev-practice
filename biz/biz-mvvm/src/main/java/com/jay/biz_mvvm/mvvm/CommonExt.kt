package com.jay.biz_mvvm.mvvm

import java.lang.reflect.ParameterizedType

/**
 *
 * @Description 通用扩展类
 * @date 12/21/20 11:31 AM
 * @author BryceCui
 * @Version 1.0
 */

/**
 * 获取当前类绑定的泛型ViewModel-clazz
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}