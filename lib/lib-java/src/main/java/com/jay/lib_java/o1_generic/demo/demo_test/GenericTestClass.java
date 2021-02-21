package com.jay.lib_java.o1_generic.demo.demo_test;

/**
 * 泛型类
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2020/5/18
 */
public class GenericTestClass<T extends Number> {

  private T data;

  public T getData() {
    return data;
  }

  public void setData(T setData) {
    this.data = setData;
  }

  public void show() {
    System.out.println(data.toString());
  }
}
