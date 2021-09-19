package com.jay.lib_kotlin.delegate.cas;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/16
 */
public class AtomicReferenceFieldUpdaterTest {
  public static void main(String[] args) throws Exception {
    // T:持有可更新字段的对象的类型
    // V:字段的类型
    AtomicReferenceFieldUpdater<Dog, String> updater =
        // 包含该字段所在的类、将被更新的对象的类、将被更新的字段的名称
        AtomicReferenceFieldUpdater.newUpdater(Dog.class, String.class, "name");
    Dog dog = new Dog();

    // 如果期望值和字段当前值相等，说明目前是最新的值可以进行更新，则原子地将字段设置为给定的更新值。
    // 参数：
    // obj: 字段所在对象
    // expect - 期望值
    // update - 新值
    // 返回：如果成功则为true
    System.out.println(dog.name); // dog1 默认值
    boolean result = updater.compareAndSet(dog, "dog1", "dog2");
    System.out.println(result); // true 修改成功
    System.out.println(dog.name); // dog2 修改后的的值
    boolean result2 = updater.compareAndSet(dog, "dog1", "dog3");
    System.out.println(result2); // false 修改失败
    System.out.println(dog.name); // dog2 还是原来的值

    // 原子地将此更新程序管理的给定对象的字段设置为给定值并返回旧值。
    // 参数：
    // obj – 更新字段的对象
    // newValue – 新值
    // 返回：之前的的值
    String result3 = updater.getAndSet(dog, "dog4");
    System.out.println(result3); // dog2  原来的值
    System.out.println(dog.name); // dog4 修改后的值
  }
}

class Dog {
  volatile String name = "dog1";
}
