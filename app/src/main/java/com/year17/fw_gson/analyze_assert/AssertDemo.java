package com.year17.fw_gson.analyze_assert;

/**
 * Created by zyh on 2017/7/18.
 * 通过assertion方式可以证明程序的正确性。
 * assertion就是在程序中的一条语句，它对一个boolean表达式进行检查，一个正确程序必须保证这个boolean表达式的值为true；
 * 如果该值为false，说明程序已经处于不正确的状态下，系统将给出警告并且退出。一般来说，assertion用于保证程序最基本、
 * 关键的正确性。assertion检查通常在开发和测试时开启。为了提高性能，在软件发布后，assertion检查通常是关闭的。
 * 在运行时，如果关闭了assertion功能，这些语句将不起任何作用。如果打开了assertion功能，
 * 那么expression1的值将被计算，如果它的值为false，该语句强抛出一个AssertionError对象。
 * 开启assertion：开发工具 --> JREs --> 选择版本 --> edit --> 在Default VM arguments里面输入-ea
 */
public class AssertDemo {
    public static void main(String[] args) {
        test1(-5);
        test2(-3);
    }

    private static void test1(int a){
        assert a > 0;
        System.out.println(a);
    }
    private static void test2(int a){
        //用于在失败时输出错误信息
        assert a > 0 : "something goes wrong here, a cannot be less than 0";
        System.out.println(a);
    }
}
