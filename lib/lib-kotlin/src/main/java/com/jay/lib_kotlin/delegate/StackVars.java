package com.jay.lib_kotlin.delegate;

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/15
 */
public class StackVars {

  private static int staticX; // static 变量
  private int x; // instance变量

  public static void main(String[] args) {
    int time = 100_0000;
    StackVars stackVars = new StackVars();

    long t1 = System.currentTimeMillis();
    stackVars.stackAccess(time);
    long t2 = System.currentTimeMillis();
    System.out.println("time1:" + (t2 - t1));

    long t3 = System.currentTimeMillis();
    stackVars.instanceAccess(time);
    long t4 = System.currentTimeMillis();
    System.out.println("time2:" + (t4 - t3));

    long t5 = System.currentTimeMillis();
    stackVars.staticAccess(time);
    long t6 = System.currentTimeMillis();
    System.out.println("time3:" + (t6 - t5));

    long t7 = System.currentTimeMillis();
    stackVars.instanceAccessPro(time);
    long t8 = System.currentTimeMillis();
    System.out.println("time4:" + (t8 - t7));

    long t9 = System.currentTimeMillis();
    stackVars.staticAccessPro(time);
    long t10 = System.currentTimeMillis();
    System.out.println("time5:" + (t10 - t9));


    //todo 局部变量将性能提高了25%以上

    // i += 1

    // val time = 1_0000_0000
    // 在 PC 设备上测试的结果
    // time1:3
    // time2:20
    // time3:27
    // time4:3
    // time5:14

    // 在 Android 设备上测试的结果
    // time1:551
    // time2:1309
    // time3:3528
    // time4:586
    // time5:550
    // 导致了APP ANR

    // int time = 100_0000;
    // 在 PC 设备上测试的结果
    // time1:1
    // time2:4
    // time3:3
    // time4:2
    // time5:1

    // 在 Android 设备上测试的结果
    // time1:6
    // time2:15
    // time3:42
    // time4:6
    // time5:6

  }

  public void stackAccess(int val) { // 访问和操作stack变量j

    int j = 0;

    for (int i = 0; i < val; i++) {

      j += 1;
    }
  }

  public void instanceAccess(int val) { // 访问和操作instance变量x

    for (int i = 0; i < val; i++) {

      x += 1;
    }
  }

  public void staticAccess(int val) { // 访问和操作static变量staticX

    for (int i = 0; i < val; i++) {

      staticX += 1;
    }
  }

  public void instanceAccessPro(int val) { // 访问和操作instance变量x

    int tempX = x;
    for (int i = 0; i < val; i++) {
      tempX += 1;
    }
    x = tempX;
  }

  public void staticAccessPro(int val) { // 访问和操作static变量staticX

    int tempStaticX = staticX;
    for (int i = 0; i < val; i++) {
      tempStaticX += 1;
    }
    staticX = tempStaticX;
  }
}
