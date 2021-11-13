package com.jay.biz_android.handler.jni;

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/27
 */
public class NDKTools {

  static {

    System.loadLibrary("NDKTools-jni");
  }

  public static native String getStringFromNDK();

}
