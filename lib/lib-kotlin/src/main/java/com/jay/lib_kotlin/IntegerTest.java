package com.jay.lib_kotlin;

/**
 *
 * @author jaydroid
 * @version 1.0
 * @date 3/11/21
 */
class IntegerTest {

    static boolean boolValue;

    public static void main(String[] args) {
//        basicTypeCacheTest();
//        Unsafe.getUnsafe().putBoolean();

                boolValue = true; // 将这个 true 替换为 2 或者 3，再看看打印结果
        if (boolValue) System.out.println("Hello, Java!");
        if (boolValue == true) System.out.println("Hello, JVM!");

    }



    private static void basicTypeCacheTest() {
        //equals与==的区别：
        //==是判断两个变量或实例是不是指向同一个内存空间，
        //equals是判断两个变量或实例所指向的内存空间的值是不是相同
        //==是指对内存地址进行比较 ，
        //equals()是对字符串的内容进行比较
        //==指引用是否相同，
        //equals()指的是值是否相同

        //java内部为了节省内存，Integer中的私有静态内部类IntegerCache类中有一个数组缓存了值从-128到127 共256个Integer对象。
        // 当我们调用Integer.valueOf（int i）的时候，
        // 如果i的值时在-128到127之间的，会直接从这个缓存中返回一个对象，否则就new一个新的Integer对象。
        //
        //其它的包装器：
        //Boolean：(全部缓存: TRUE FALSE)
        //Byte：(全部缓存: -128 — 127)
        //Character(<= 127缓存)
        //Short(-128 — 127缓存)
        //Long(-128 — 127缓存)
        //Float(没有缓存,每次都new 一个)
        //Double(没有缓存)

        Boolean boo = Boolean.valueOf(true);

        Byte by = Byte.valueOf((byte) 200);

        Character c = Character.valueOf((char) 200);

        Short s = Short.valueOf((short) 200);

        Integer i = Integer.valueOf(200);

        Float f = Float.valueOf(200);

        Double d = Double.valueOf(200);


        Integer a = 100;
        Integer b = 100;

        System.out.println(" a == b is " + (a == b));
        System.out.println(" a.equals(b) is " + (a.equals(b)));

        Integer a1 = 200;
        Integer b1 = 200;


        System.out.println(" a1 == b1 is " + (a1 == b1));
        System.out.println(" a1.equals(b1) is " + (a1.equals(b1)));
    }

}
