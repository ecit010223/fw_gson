package com.year17.fw_gson.analyze_synthetic;

/**
 * Created by zyh on 2017/7/18.
 * Java编译器引入的字段（内部非静态类就具有一个指向父类的synthetic字段）、方法（泛型的桥方法以及拥有内部类并且
 * 内部类引用了外部类字段或方法都会产生相应的synthetic方法）或类。某些类由编译器引入，这种类就是合成类型。
 * 由编译器产生的任何构建，如果在源码中没有对应的构建存在，那么这个构建就必须被标记为synthetic(除了默认构造器和类初始化方法)。
 * “synthetic”是由编辑器产生的一个java构建。
 * 当存在内嵌class定义，而且需要在外包class和内嵌class之间访问对方的private修饰的属性的时候，java编译器必须创建synthetic method。
 * 对于java编译器而言，内部类也会被单独编译成一个class文件。那么原有代码中的相关属性可见性就难以维持，
 * syntheticmethod也就是为了这个目的而生成的。生成的synthetic方法是包访问性的static方法。
 */
public class Parent {
    private int num;
    public void foo() {
    }

    class inner {
        inner() {
            foo();
        }
    }
}
/**
 非static的inner class里面都会有一个this$0的字段保存它的父对象。编译后的inner class 就像下面这样：
 class parent$inner{
    synthetic Parent this$0;
    parent$inner(Parent this$0){
        this.this$0 = this$0;
        //所有父对象的非私有成员都通过 this$0来访问。
        this$0.foo();
    }
 }
 */
/**
 还有许多用到synthetic的地方。比如使用了assert关键字的class会有一个
 synthetic static boolean $assertionsDisabled 字段
 使用了assert的地方:
 assert condition;
 在class里被编译成:
 if(!$assertionsDisabled && !condition){
    throw new AssertionError();
 }
 */
/**
 在jvm里，所有class的私有成员都不允许在其他类里访问，包括它的inner class。在java语言里inner class是可以访问
 父类的私有成员的。在class里是用如下的方法实现的：
 class parent{
    private int value = 0;
    //在inner class里通过access$000来访问value字段
    synthetic static int access$000(parent obj){
        return value;
    }
 }
 */

