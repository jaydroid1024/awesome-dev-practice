package com.jay.lib_kotlin.delegate

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater

/**
 * DCL单例(Double check locking)
 * 双重检查锁定被破坏声明
 * http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html
 * Broken under Java 1.4
 *
 * synchronized如何保证可见性的
 * 根据Java内存模型，synchronized的语义不仅仅是在同一个信号上的互乐(mutex),也包含线程和主存之间数据交互
 * 同步,它确保在多处理器、多线程下对内存能有可预见的一致性视图。
 * 获取或释放锁会触发一次内存屏障(memory barrier) 强迫线程本地存和主存同步。
 * 当一个线程退出一个synchronized block,触发一次写屏障(write barrier )
 * 在释放锁前必须把所有在这个同步块里修改过的变量值刷新到主存
 * 同样，进入一个synchronized block 触发一次读屏障
 *
 * 什么是指令重排序
 * 指令进行重排序的目的是为了提高程序的运行效率
 * 单线程内指令重排序为什么不会有问题
 * 根据java语言规范，所有线程在执行java程序时必须要遵守 intra-thread semantics（线程内语义）。
 * 线程内语义保证重排序不会改变单线程内的程序执行结果。
 *
 * volatile 有两个特性：可见性和有序性
 * 什么是可见性，即线程A修改变量x，那么对于线程B应该立即可以获取到信息x被修改，并重新读取
 * 什么是有序性，即禁止许指令重排序
 * 禁止重排序是怎么做到的？
 * 一是语义层次，二是内存屏障。内存屏障分为读屏障(loadload)，写屏障(storestore)，读写屏障(loadstore或storeload)；如load,loadload,load，
 * 在进行新的读操时，加入读屏障，保证上一次的读操作完成再执行新的读操作而不会越过屏障去执行新操作。
 *
 * DCL单例模式为什么需要通过 volatile 禁止重排序
 *
 * 对象的创建过程(Object o=new Object())，并不是一步完成的，而是分为多步：
 * 1、申请内存空间，并且默认初始化（赋0值，半初始化）
 * 2、调用构造方法，进行初始化，赋值等
 * 3、返回地址给，使 o 指向分配的地址空间（执行完 3 时 o 就不为null）
 * 对应的字节码为：
 * 0: new           #2                  // class java/lang/Object
 * 3: dup
 * 4: invokespecial #1                  // Method java/lang/Object."<init>":()V
 * 7: astore_1
 * 8: return
 *
 * DCL破坏的原因
 * 第一个线程执行 先执行new ，指令重排后再执行 astore_1，这个时候还没执行invokespecial的时候，
 * 第二个线程拿到了cpu执行权，调用 getInstance，判断这个对象是否为空，
 * 因为执行了 astore_1 指令，那当然就不为空了，，然后就拿去用了，
 * 但是，你这个对象里面的数据是还没有初始化的，这就有问题了。
 * 如果加了volatile禁止了重排序就不存在这个问题
 * 总结以下就是
 * 在高并发的情况下，DLC双重检验不能保证由于指令重排（1-3-2）导致的线程获取到未完全初始化对象的问题，
 * 所以需要利用volatile禁止重排的特性来保证单例的线程安全。
 *
</init> */
class LazyDCL private constructor() {


    companion object {

        // 该实例仍未初始化，因此我们可以安全地（没有其他线程可以进入该区域）创建一个实例并将其赋值给我们的单例引用。// 再次将实例分配给局部变量以检查它是否被其他线程初始化，而当前线程被阻止进入锁定区域。
        // 如果它被初始化，当前线程也能感知他的存在了。
        // 它没有初始化，但我们不能确定，因为其他线程可能同时初始化了它。
        // 所以为了以防万一，这里需要添加一把互斥锁来保证只有一个线程去实例化实例对象。
        // 局部变量将性能提高了 25% Joshua Bloch “Effective Java, Second Edition”，第 3 页。 283-284
        // 检查单例实例是否已初始化。如果它被初始化，那么我们可以返回实例。
        /**
         * 供全局访问的静态方法
         * 只要 instance 被创建之后，即便再调用 getInstance() 函数也不会再进入到加锁逻辑中了。
         * 所以，这种实现方式解决了懒汉式并发度低的问题。
         * @return 单例对象的实例
         * 提供一个静态实例的引用，延迟初始化
         * volatile:防止指令重排序
         */
        @Volatile
        var instance: LazyDCL? = null
            get() {
                // 局部变量将性能提高了 25% Joshua Bloch “Effective Java, Second Edition”，第 3 页。 283-284
                var result = field
                // 检查单例实例是否已初始化。如果它被初始化，那么我们可以返回实例。
                if (result == null) {
                    // 它没有初始化，但我们不能确定，因为其他线程可能同时初始化了它。
                    // 所以为了以防万一，这里需要添加一把互斥锁来保证只有一个线程去实例化实例对象。
                    synchronized(LazyDCL::class.java) {
                        // 再次将实例分配给局部变量以检查它是否被其他线程初始化，而当前线程被阻止进入锁定区域。
                        // 如果它被初始化，当前线程也能感知他的存在了。
                        result = field
                        if (result == null) {
                            // 该实例仍未初始化，因此我们可以安全地（没有其他线程可以进入该区域）创建一个实例并将其赋值给我们的单例引用。
                            result = LazyDCL()
                            field = result
                        }
                    }
                }
                return result
            }
            private set

        //防止反射的标志位
        private var flag = true
    }


    internal object UNINITIALIZED_VALUE


    private class SynchronizedLazyImpl<out T>(initializer: () -> T) {
        private var initializer: (() -> T)? = initializer

        @Volatile
        private var _value: Any? = UNINITIALIZED_VALUE

        private val lock = this

        val value: T
            get() {
                //局部变量将性能提高了 25% Joshua Bloch “Effective Java, Second Edition”，第 3 页。 283-284
                var _v1 = _value
                if (_v1 == UNINITIALIZED_VALUE) {
                    synchronized(lock) {
                        // 再次将实例分配给局部变量以检查它是否被其他线程初始化，而当前线程被阻止进入锁定区域。
                        // 如果它被初始化，当前线程也能感知他的存在了。
                        _v1 = _value
                        if (_v1 == UNINITIALIZED_VALUE) {
                            // 该实例仍未初始化，因此我们可以安全地（没有其他线程可以进入该区域）创建一个实例并将其赋值给我们的单例引用。
                            _v1 = initializer!!()
                            initializer = null
                        }
                    }
                }
                @Suppress("UNCHECKED_CAST")
                return _v1 as T
            }
    }


    private class SynchronizedLazyImpl2<out T>(initializer: () -> T) {

        private var initializer: (() -> T)? = initializer

        @Volatile // 用内存可见性来检查是否在其他线程初始化过，同时也会禁止指令重排序防止_value拿到不完整的实例
        private var _value: Any? = UNINITIALIZED_VALUE

        //实例使用自身进行同步
        private val lock = this

        //Lazy 接口的 value 属性用于获取当前 Lazy 实例的延迟初始化值。一旦初始化后，它不得在此 Lazy 实例的剩余生命周期内更改。
        val value: T
            // 重写 get 来保证懒加载，只在使用的时候才执行函数
            get() {
                //局部变量可以将性能提高25%以上
                val _v1 = _value
                //检查单例实例是否已初始化。如果它被初始化就返回实例。
                if (_v1 !== UNINITIALIZED_VALUE) {
                    @Suppress("UNCHECKED_CAST")
                    return _v1 as T
                }
                //到这里还没有初始化，但我们不能确定，因为可能有多个其他线程可能同时初始化了它。
                //所以为了以防万一，这里需要添加一把互斥锁来保证只有一个线程去实例化实例对象。
                return synchronized(lock) {
                    //再次将实例分配给局部变量以检查它是否被其他线程初始化，而当前线程被阻止进入锁定区域。
                    val _v2 = _value
                    //如果它已经被其它线程初始化了，当前线程也能感知他的存在了，直接返回实例
                    if (_v2 !== UNINITIALIZED_VALUE) {
                        @Suppress("UNCHECKED_CAST")
                        (_v2 as T)
                    } else {
                        //到这里该实例仍未初始化，因此我们可以安全地（没有其他线程可以进入该区域）创建一个实例了。
                        val typedValue = initializer!!() //执行 Function 对象的 invoke 并将函数的返回值缓存起来
                        _value = typedValue //_value赋值通知其它线程别进来了，拿走用吧
                        initializer = null //initializer在当前类实例已经没用了
                        typedValue // 返回最终的结果给 value
                    }
                }
            }
    }


    /**
     * 私有构造函数以防止客户端实例化。
     */
    init {
        // 防止通过反射实例化
        if (flag) {
            flag = false
        } else {
            throw IllegalStateException("Already initialized.")
        }
    }
}

class BaseAuthNetwork private constructor(private val currentBaseUrl: String) {

    fun printUrl() {
        println("currentBaseUrl: $currentBaseUrl")
    }

    companion object {
        @Volatile
        private var sInstance: BaseAuthNetwork? = null
        private val sLock = Any()
        fun getInstance(currentBaseUrl: String): BaseAuthNetwork {
            if (sInstance == null) {
                synchronized(sLock) {
                    if (sInstance == null) {
                        sInstance = BaseAuthNetwork(currentBaseUrl)
                    }
                }
            }
            return sInstance!!
        }
    }
}

internal object UNINITIALIZED_VALUE

private class SafePublicationLazyImpl<out T>(initializer: () -> T) {
    @Volatile
    private var initializer: (() -> T)? = initializer

    @Volatile
    private var _value: Any? = UNINITIALIZED_VALUE

    // this final field is required to enable safe initialization of the constructed instance
    private val final: Any = UNINITIALIZED_VALUE

    val value: T
        get() {
            val value = _value
            if (value !== UNINITIALIZED_VALUE) {
                @Suppress("UNCHECKED_CAST")
                return value as T
            }
            val initializerValue = initializer
            //如果我们在这里看到初始值设定项为 null，则表示该值已被另一个线程设置
            if (initializerValue != null) {
                //多个线程都可能执行这个函数
                val newValue = initializerValue()
                //多个线程拿到值后通过CAS比较，一样就返回，不一样就放弃
                if (valueUpdater.compareAndSet(this, UNINITIALIZED_VALUE, newValue)) {
                    initializer = null
                    return newValue
                }
            }
            @Suppress("UNCHECKED_CAST")
            return _value as T
        }


    companion object {
        private val valueUpdater =
            AtomicReferenceFieldUpdater.newUpdater(
                SafePublicationLazyImpl::class.java,
                Any::class.java,
                "_value"
            )
    }
}