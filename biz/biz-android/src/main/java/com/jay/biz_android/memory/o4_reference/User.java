package com.jay.biz_android.memory.o4_reference;

import android.util.Log;

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/10/4
 */
class User {

  // 增加所占内存，方便触发GC
  private byte[] bs = new byte[190 * 1024 * 1024];

  private String id;

  public User(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "User{" + "id='" + id + '\'' + '}';
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    System.out.println("执行了 finalize 回收了User对象：" + getId());
    Log.d("Jay", "执行了 finalize 回收了User对象：" + getId());
  }
}
