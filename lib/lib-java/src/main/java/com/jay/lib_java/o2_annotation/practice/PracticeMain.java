package com.jay.lib_java.o2_annotation.practice;

/**
 * Practice Code For Annotation
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-11-28 16:20
 */
public class PracticeMain {

  public static void main(String[] args) {
    int demoIndex = 3;

    switch (demoIndex) {
      case 1: {
        // Demo1:
        Demo1();
        break;
      }
      case 2: {
        // Demo2:
        Demo2();
        break;
      }
      case 3: {
        // Demo3:
        Demo3();
        break;
      }
    }
  }

  /**
   * Demo1:
   */
  private static void Demo1() {
    System.out.println("-----Demo1-----\n\n");

  }

  /**
   * Demo2:泛型+反射实现集合排序工具类
   */
  private static void Demo2() {
    System.out.println("-----Demo2-----\n\n");

  }

  /**
   * Demo3:Gson库中的泛型使用-TypeToken获取泛型类型
   */
  private static void Demo3() {
    System.out.println("-----Demo3-----\n\n");

  }

}
