package com.jay.lib_kotlin.property;

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/8
 */
public class PersonJava {

  int age;

  String name;

  public PersonJava(int age, String name) {
    this.age = age;
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

