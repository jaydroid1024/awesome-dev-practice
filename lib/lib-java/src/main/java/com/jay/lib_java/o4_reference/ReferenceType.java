package com.jay.lib_java.o4_reference;

import static com.jay.lib_java.o4_reference.ReferenceTest.printVMSize;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/10/4
 */
class ReferenceType {

  private static ReferenceQueue<User> RQ = new ReferenceQueue<>();

  private static void printQueue(String str) {

    Thread thread =
        new Thread(
            () -> {
              try {
                int cnt = 0;
                Reference<User> k;
                while ((k = (Reference) RQ.poll()) != null) {
                  System.out.println((cnt++) +  "回收了:" + k);
                  System.out.println("printQueue-poll，引用类型:" + str + ",  回收对象：" + k.get());
                }
              } catch (Exception e) {
                // 结束循环
              }
            });
    thread.setDaemon(true);
    thread.start();
  }

  public static void testSoftReference() {
    List<SoftReference<User>> softReferenceList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      // VM 内存使用情况
      printVMSize("分配前：");
      // gc 后会把回收的对象放到 ReferenceQueue
      SoftReference<User> sr = new SoftReference<User>(new User("Soft-" + i), RQ);
      System.out.println("软引用对象：" + sr.get());
      softReferenceList.add(sr);
    }
    System.gc();
    // VM 内存使用情况
    printVMSize("分配后：");
    printQueue("软引用");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void testWakeReference() {
    List<WeakReference<User>> softReferenceList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      // VM 内存使用情况
      printVMSize("分配前：");
      // gc 后会把回收的对象放到 ReferenceQueue
      WeakReference<User> sr = new WeakReference<User>(new User("Weak-" + i), RQ);
      System.out.println("弱引用对象：" + sr.get());
      softReferenceList.add(sr);
    }
    System.gc();

    try {
      Thread.sleep(6000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // VM 内存使用情况
    printVMSize("分配后：");
    printQueue("弱引用");
  }

  public static void testPhantomReference() {
    List<PhantomReference<User>> softReferenceList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      // VM 内存使用情况
      printVMSize("分配前：");
      // gc 后会把回收的对象放到 ReferenceQueue
      PhantomReference<User> sr = new PhantomReference<User>(new User("Phantom-" + i), RQ);
      System.out.println("虚引用对象：" + sr.get());
      softReferenceList.add(sr);
    }
    printQueue("虚引用");

    //    System.gc();
    // VM 内存使用情况
    printVMSize("分配后：");
    try {
      Thread.sleep(6000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
