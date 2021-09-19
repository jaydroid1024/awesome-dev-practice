package com.jay.lib_kotlin.delegate;

import java.lang.reflect.Field;

/**
 * java 实现 kotlin 属性委托
 *
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/9
 */
class Person {
  static final Field[] delegatedProperties = Person.class.getFields();
  private final NameDelegate nameDelegate = new NameDelegate();
  public final String getName() {
    return this.nameDelegate.getValue(this, delegatedProperties[0]);
  }
}
class NameDelegate {
  String getValue(Person thisRef, Field property) {
    return "Jay";
  }
}
