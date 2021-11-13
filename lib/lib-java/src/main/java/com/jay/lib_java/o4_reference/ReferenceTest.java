package com.jay.lib_java.o4_reference;

/** @author xuejiewang */
public class ReferenceTest {

  public static void main(String[] args) {
//    ReferenceType.testSoftReference();
    ReferenceType.testWakeReference();
//    ReferenceType.testPhantomReference();
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

    System.out.println(
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
            + ableMemory / MB);
  }
}
