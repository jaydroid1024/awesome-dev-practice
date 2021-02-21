# Java-注解-使用与实战

- [Oracle Java 泛型官方教程-英文版](https://docs.oracle.com/javase/tutorial/java/generics/index.html)
- [Oracle Java 泛型官方教程-中文版](https://pingfangx.github.io/java-tutorials/java/generics/index.html)
- [GitHub-本文完整测试代码](https://github.com/Jay-Droid/awesome-dev-practice/tree/master/lib/lib-java/src/main/java/com/jay/lib_java/o2_annotation)


> Java 注解（Annotation）又称 Java 标注，是 JDK1.5 引入的一种注释机制。注解本身没有任何意义，它需要结合其他技术如反射、插桩等才有意义。源于其灵活而又强大标注功能，目前很多框架都会采用注解技术来让自己的框架变得更优雅。
既然 Java 中这个“标兵” 如此优秀，作为一个同样优秀 Android 程序猿是必要和他刚一下了，为了能够全面了解并掌握Java注解的相关知识，本文将从 `围绕注解提出一系列问题`->`结合问题与源码阐述其实现原理`->``在实际生产中灵活运用`三个纬度来全面锤打这个“标兵”。



## 一. 打破砂锅问到底

1. #### 注解的作用和意义是什么？

   注解本身没有任何意义，单独的注解就是一种注释，他需要结合其他如反射、插桩等技术才有意义。Java 注解（Annotation）又称 Java 标注，是 JDK1.5 引入的一种注释机制。是元数据的一种形式，提供有关于程序但不属于程序本身的数据。注解对它们注解的代码的操作没有直接影响。 

1. #### 何为元注解以及有哪些元注解？

   在定义注解时，注解类也需要使用其他的注解声明。对注解类型进行注解的注解类，我们称之为 meta-annotation（元注解）。

   @Target：声明的注解允许作用于哪些节点

   @Retention：声明保留级别，保留级别如下：

   - RetentionPolicy.SOURCE ：标记的注解仅保留在源级别中，并被编译器忽略。

   - RetentionPolicy.CLASS ：标记的注解在编译时由编译器保留，但 Java 虚拟机(JVM)会忽略。

   - RetentionPolicy.RUNTIME ：标记的注解由 JVM 保留，因此运行时环境可以使用它。

   - SOURCE < CLASS < RUNTIME，即CLASS包含了SOURCE，RUNTIME包含SOURCE、CLASS。

1. #### 注解的应用场景有哪些？

   根据注解可以作用于源码、字节码与运行时三个级别，对注解的使用自然存在不同场景。如下表：

   | 级别   | 技术  | 说明 |
   | :-: | :-: | :-------- |
   | 源码 |    APT     | 在编译期能够获取注解与注解声明的类包括类中所有成员信息，一般用于生成额外的辅助类。 |
   | 字节码 | 字节码增强 | 在编译出Class后，通过修改Class数据 以实现修改代码逻辑目的。对于是否需要修改的区分或者修改为不同逻辑的判断可以使用注解。 |
   | 运行时 | 反射 | 在程序运行期间，通过反射技术动态获取注解与其元素，从而完成不同的逻辑判定。 |

   
   
1. #### 什么是泛型？Java 泛型的来源？



## 二. 晓之以理动之以码

#### Q：什么是泛型？Java 泛型的来源？
#### Q：简单说一下 Java Type 体系？


## 三. 实践出真知

 <img src="res/java_type_relationship.png"/>



测试代码：

```

```

