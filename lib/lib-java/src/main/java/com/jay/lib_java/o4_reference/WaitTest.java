package com.jay.lib_java.o4_reference;

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/10/5
 */
class WaitTest {
  private static final Object lock = new Object();

  public static void main(String[] args) throws InterruptedException {
    extracted();
  }

  private static void extracted() throws InterruptedException {
    int i = 1;
    for (; ; ) {
      lock.wait(2000);
      if (i > 1000) {
        return;
      }
      i++;
      System.out.println(i);
    }
  }
}
