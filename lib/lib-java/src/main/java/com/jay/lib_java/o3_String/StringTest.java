package com.jay.lib_java.o3_String;

import java.util.HashSet;

public class StringTest {

  public static void main(String[] args) {
    //    testEquals();
    testHashCode();
  }

  private static void testHashCode() {
    Object o1 = new Object();
    Object o2 = new Object();
    System.out.println(o1.hashCode()); // 366712642
    System.out.println(o2.hashCode()); // 1829164700
    System.out.println(o1.hashCode() == o2.hashCode()); // false

    HashSet<String> hashSet = new HashSet<String>();
    hashSet.add("a");


  }

  /** ==对比的是栈中的值，基本数据类型是变量值，引用类型是堆中内存**对象的地址 equals：object中默认也是采用==比较，通常会重写 */
  private static void testEquals() {
    String str1 = "Hello";
    String str2 = new String("Hello");
    String str3 = str2;
    System.out.println(str1 == str2); // false
    System.out.println(str1 == str3); // false
    System.out.println(str2 == str3); // true
    System.out.println(str1.equals(str2)); // true
    System.out.println(str1.equals(str3)); // true
    System.out.println(str2.equals(str3)); // true
  }
}
