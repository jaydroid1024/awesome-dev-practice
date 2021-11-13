package com.jay.lib_java;

import java.util.ArrayList;

public class JavaClass {

  ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

  ArrayList<String> a =
      new ArrayList<String>() {
        @Override
        public boolean add(String o) {
          stringThreadLocal.set("");
          return super.add(o);
        }
      };

  public static void main(String[] args) {

    System.out.println("Java");
    String a = null;
    System.out.println();
  }
}
