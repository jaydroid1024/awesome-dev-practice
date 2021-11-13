package com.jay.lib_java.o4_reference;

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/10/4
 */
class User {
  // 增加所占内存，方便触发GC
  private byte[] bs = new byte[272*1024*1024];

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
  }
}
