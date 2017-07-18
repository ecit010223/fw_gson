package com.year17.fw_gson.analyze_dynamic_binding;

/**
 * Created by zyh on 2017/7/18.
 * Java的动态绑定又称为运行时绑定。意思就是说，程序会在运行的时候自动选择调用哪儿个方法。
 Son son = new Son();
 son.method;
 (1)首先，编译器根据对象的声明类型和方法名，搜索相应类(Son)及其父类(Father)的“方法表”，找出所有访问属性为public的method方法。
    可能存在多个方法名为method的方法，只是参数类型或数量不同。
 (2)然后，根据方法的“签名”找出完全匹配的方法。
    方法的名称和参数列表称为方法的签名。
    在Java SE 5.0 以前的版本中，覆盖父类的方法时，要求返回类型必须是一样的。现在子类覆盖父类的方法时，允许其返回类型定义为原始类型的子类型。
    public Father getFather(){...}  //父类中的方法
    public Son getFather(){...}     //子类覆盖父类中的getFather()方法
 (3)如果是private、static、final 方法或者是构造器，则编译器明确地知道要调用哪儿个方法，不能覆盖，这种调用方式成为“静态调用”。
 (4)调用方法：如果子类Son中定义了 method() 的方法，则直接调用子类中的相应方法；如果子类Son中没有定义相应的方法，则到其父类中寻找method()方法。
 (5)动态绑定只是针对对象的方法，对于属性无效。因为属性不能被重写。
 */
public class Son extends Father {

    @Override
    public void method() {
        System.out.println("子类方法："+this.getClass());
    }

    public static void main(String[] args){
        Father instance = new Son();
        instance.method();
    }
}
