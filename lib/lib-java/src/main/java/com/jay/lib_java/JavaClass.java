package com.jay.lib_java;

public class JavaClass {

  public static void main(String[] args) {

    System.out.println("Java");
    String a = null;
    System.out.println();

    throw new NullPointerException();
    //    throw new OutOfMemoryError();
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }
}
