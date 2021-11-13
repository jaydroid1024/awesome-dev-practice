package com.jay.biz_android.memory.o4_reference;

import android.util.Log;

/** @author xuejiewang */
public class ReferenceTest {

  public static void main(String[] args) {
    ReferenceType.testSoftReference();
  }

  public static void printVMSize(String label) {
    long MB = 1024 * 1024;
    // 总指定内存，这将等于配置的-Xmx值：
    long maxMemory = Runtime.getRuntime().maxMemory();
    // 当前分配的可用内存，
    long freeMemory = Runtime.getRuntime().freeMemory();
    // 总分配内存
    long totalMemory = Runtime.getRuntime().totalMemory();
    // 已用内存
    long usedMemory = totalMemory - freeMemory;
    // 可用内存
    long ableMemory = maxMemory - usedMemory;
    String info =
        label
            + ": 最大分配内存(MB): "
            + maxMemory / MB
            + ", 总分配内存(MB): "
            + totalMemory / MB
            + ", 使用的内存(MB): "
            + usedMemory / MB
            + ", 剩余可使用(MB): "
            + freeMemory / MB
            + ", 剩余可分配(MB): "
            + ableMemory / MB;
    System.out.println(info);

    Log.d("Jay", "vm info" + info);
  }
}
