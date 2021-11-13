package com.jay.biz_android.memory.o4_reference;

import static com.jay.biz_android.memory.o4_reference.ReferenceTest.printVMSize;

import android.util.Log;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/10/4
 */
public class ReferenceType {
  private static final ReferenceQueue<User> RQ = new ReferenceQueue<>();

  private static void printQueue(String str) {
    Reference<? extends User> obj = RQ.poll();
    if (obj != null) {
      System.out.println("引用类型:" + str + ",  回收对象：" + obj.get());
      Log.d("Jay", "printQueue: 引用类型:" + str + ",  回收对象：" + obj.get());
    }
  }

  public static void testSoftReference() {
    List<SoftReference<User>> softReferenceList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      // VM 内存使用情况
      printVMSize("分配前：");
      // gc 后会把回收的对象放到 ReferenceQueue
      SoftReference<User> sr = new SoftReference<User>(new User("soft-" + i), RQ);
      System.out.println("软引用对象：" + sr.get());
      softReferenceList.add(sr);
      printVMSize("分配后：");
    }
    try {
      Thread.sleep(1000);
      // VM 内存使用情况
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    printQueue("soft");
  }
}
