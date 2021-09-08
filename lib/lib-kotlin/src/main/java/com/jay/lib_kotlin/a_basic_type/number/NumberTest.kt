package com.jay.lib_kotlin.a_basic_type.number

/**
 * @author jaydroid
 * @version 1.0
 * @date 3/14/21
 */

fun main() {
    testNumber()
    testBox()
    println(decimalDigitValue('a'))
    testArray()

    testString()
}

fun testString() {
    val text = """
    for (c in "foo")
        print(c)
    """
    println(text)

    val text2 = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin("|")

    println(text2)

    val price = """
    ${'$'}9.99
    """

    println(price)


}

fun testArray() {
    val array1 = arrayOf(1, 2, 3)
    val array2 = arrayOfNulls<Int>(4)
    // public inline constructor(size: Int, init: (Int) -> T)
    // 创建一个具有指定 [size] 的新数组，其中每个元素都通过调用指定的 [init] 函数来计算。
    // 函数 [init] 为每个数组元素从第一个开始依次调用。它应该返回给定索引的数组元素的值。
    val asc = Array(5) { i -> (i * i).toString() }
    val s1 = asc[0]

    // 大小为 5、值为 [0, 0, 0, 0, 0] 的整型数组
    val arr1 = IntArray(5)
    // 例如：用常量初始化数组中的值
    // 大小为 5、值为 [42, 42, 42, 42, 42] 的整型数组
    val arr2 = IntArray(5) { 42 }
    // 例如：使用 lambda 表达式初始化数组中的值
    // 大小为 5、值为 [0, 1, 2, 3, 4] 的整型数组（值初始化为其索引值）
    var arr3 = IntArray(5) { it * 1 }


}

/**
 * [官方文档](https://www.kotlincn.net/docs/reference/basic-types.html)
 * Kotlin 中使用的基本类型：数字、字符、布尔值、数组与字符串。
 *
 */
private fun testNumber() {
    //整数
    val one = 1 // Int
    val threeBillion = 3000000000 // Long
    val oneLong = 1L // Long
    val oneByte: Byte = 1
    /*
      对应的Java代码：
      int one = 1;
      long threeBillion = 3000000000L;
      long oneLong = 1L;
      byte oneByte = true;
     */

    //浮点数
    val pi = 3.14 // Double
    val e = 2.7182818284 // Double
    val eFloat = 2.7182818284f // Float，实际值为 2.7182817
    /*
      对应的Java代码：
      double pi = 3.14D;
      double e = 2.7182818284D;
      float eFloat = 2.7182817F;
     */


}

private fun testBox() {
    val a: Int = 100 //int
    val boxedA: Int? = a //Integer
    println(boxedA)
    val anotherBoxedA: Int? = a

    val b: Int = 10000 //Integer只缓存了【-128，127】个数字，其它范围都会新建实例
    val boxedB: Int? = b
    val anotherBoxedB: Int? = b

    println(boxedA === anotherBoxedA) // true
    println(boxedB === anotherBoxedB) // false


    val c: Int = 10000
    println(b == c) // 输出“true”
    val boxedC: Int? = c
    val anotherBoxedC: Int? = c
    println(boxedC == anotherBoxedC) // 输出“true”

    val d: Int? = 10000
    val e: Int = 10000

    /*
      Integer.valueOf(100)
      static final Integer cache[];
      public static Integer valueOf(int i) {
        if (i >= IntegerCache.low && i <= IntegerCache.high)
            return IntegerCache.cache[i + (-IntegerCache.low)];
        return new Integer(i);
      }

      int a = 100;
      Integer boxedA = Integer.valueOf(a);
      Integer anotherBoxedA = Integer.valueOf(a);
      int b = 10000;
      Integer boxedB = Integer.valueOf(b);
      Integer anotherBoxedB = Integer.valueOf(b);
      boolean var17 = boxedA == anotherBoxedA;
      boolean var18 = false;
      System.out.println(var17);
      var17 = boxedB == anotherBoxedB;
      var18 = false;
      System.out.println(var17);
     */


    val x = (1 shl 2) and 0x000FF000
    val y = 11 shl 2
}


fun decimalDigitValue(c: Char): Int {
    return c.toInt()  // 显式转换为数字
}

