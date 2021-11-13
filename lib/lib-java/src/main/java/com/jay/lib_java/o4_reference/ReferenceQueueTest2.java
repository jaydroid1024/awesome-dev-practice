package com.jay.lib_java.o4_reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/10/4
 */
class ReferenceQueueTest2 {

  ReferenceQueue<Foo> fooQueue = new ReferenceQueue<Foo>();

  public Thread cleanupThread =
      new Thread() {
        @Override
        public void run() {
          while (true) {
            ReferenceWithCleanup ref = null;
            try {
              ref = (ReferenceWithCleanup) fooQueue.remove();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            if (ref != null) {
              ref.cleanUp();
            }
          }
        }
      };

  public void doStuff() {
    cleanupThread.start();
    Foo foo = new Foo();
    Bar bar = new Bar();
    ReferenceWithCleanup ref = new ReferenceWithCleanup(foo, bar);
    // From now on, once you release all non-weak references to foo,
    // then at some indeterminate point in the future, bar.cleanUp() will
    // be run. You can force it by calling ref.enqueue().
  }

  class ReferenceWithCleanup extends WeakReference<Foo> {
    Bar bar;

    ReferenceWithCleanup(Foo foo, Bar bar) {
      super(foo, fooQueue);
      this.bar = bar;
    }

    public void cleanUp() {
      bar.cleanUp();
    }
  }

  class Foo {}

  class Bar {
    public void cleanUp() {}
  }
}
