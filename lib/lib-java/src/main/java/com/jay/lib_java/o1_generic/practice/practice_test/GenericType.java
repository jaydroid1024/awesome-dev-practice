package com.jay.lib_java.o1_generic.practice.practice_test;

/**
 * @author wangxuejie
 * @version 1.0
 * @date 2020/6/18
 */
public class GenericType<T> {

  private T data;

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
