package com.year17.fw_gson.analyze_generic;

/**
 * Created by zyh on 2017/7/18.
 * 虚拟机中并没有泛型类型对象，所有的对象都是一样的，都属于普通的类。由于JVM根本不支持泛型类型，是编译器“耍了个花招”，
 * 使得似乎存在对泛型类型的支持―它们用泛型类型信息检查所有的代码，但随即“擦除”所有的泛型类型并生成只包含普通类型的类文件。
 * 泛型类在Java源码上看起来与一般的类不同，在执行时被虚拟机翻译成对应的“原始类型”。泛型类的类型参数列表被去掉，
 * 虚拟机用类型参数的限定类型对使用类型参数的地方进行了替换，如果没有限定类型则使用Object类型进行替换。
 * 这个过程就是所谓的“类型擦除”。类型参数如果有多个限定，则使用第一个限定类型做替换。
 * 泛型方法也会做相同的替换。
 */
public class Pair<T> {
    private T first;
    private T second;

    public Pair(T first,T second){
        this.first = first;
        this.second = second;
    }

    public void setFirst(T first){
        this.first = first;
    }

    public T getFirst(){
        return first;
    }

    public void setSecond(T second){
        this.second = second;
    }

    public T getSecond(){
        return second;
    }
}
/****************
 使用类分析器对其进行分析，结果：
 public class Pair extends java.lang.Object{
    //域
    private java.lang.Object first;
    private java.lang.Object second;
    //构造器
    public Pair(java.lang.Object, java.lang.Object);
    //方法
    public void setFirst(java.lang.Object);
    public void setSecond(java.lang.Object);
    public java.lang.Object getSecond( );
    public java.lang.Object getFirst( );
 }
 ****************/
/****************
 如果将泛型类Pair的类型参数加上限定，比如Pair<T extends Comparable>，再使用类分析器对其进行分析，结果：
 public class Pair extends java.lang.Object{
    //域
    private java.lang.Comparable first;
    private java.lang.Comparable second;
    //构造器
    public Pair(java.lang.Comparable,java.lang.Comparable);
    //方法
    public void setFirst(java.lang.Comparable);
    public void setSecond(java.lang.Comparable);
    public java.lang.Comparable getSecond( );
    public java.lang.Comparable getFirst( );
 }
 ****************/
/****************
 翻译泛型表达式:在程序调用泛型方法的时候，如果返回值被擦除，编译器会插入强制的类型转换:
 如下两条语句
    Pair<GregorianCalendar> birthdays =...;
    GregorianCalendar first =birthdays.getFirst();
 原始类型中方法getFirst()的返回被替换成Object，但是编译器会自动插入GregorianCalendar的强制类型转换。
 编译器会将这条语句翻译成两条虚拟机指令，并插入字节码：
 (1)对原始方法getFirst()的调用；
 (2)将返回的Object对象强制转换成GregorianCalendar。
 当存取一个泛型域的时候也会在字节码中插入强制的类型转换。
 ***************/
/***************
 翻译泛型方法：类型擦除同样发生在泛型方法中
 虚拟机中同样也没有泛型方法，泛型方法也同样会经历“类型擦除”。例如，我们定义几个泛型方法：
 public class ArrayAlg{
    public static <T> T getMiddle(T[] t){
        System.out.println("泛型方法");
        return t[t.length/2];
    }
    public static <T extends Comparable> T min(T[] a){
        if(a == null || a.length == 0){
            return null;
        }
        T smallest = a[0];
        for(int i = 1;i <a.length;i++){
            if(smallest.compareTo(a[i]) >0){
                smallest = a[i];
            }
        }
        return smallest;
    }
    public static <T extends Comparable> Pair<T> minmax(T[] ts){
        if(ts == null || ts.length == 0){
            return null;
        }
        T min = ts[0];
        T max = ts[0];
        for(int i = 0;i <ts.length;i++){
            if(min.compareTo(ts[i]) >0){
                min = ts[i];
            }
            if(max.compareTo(ts[i]) <0){
                max = ts[i];
            }
        }
        return new Pair<T>(min,max);
    }
    public static void main(String[] args) {
        String[] s = {"AAA","BBB","CCC"};
        //在方法名前指定类型
        System.out.println(ArrayAlg.<String>getMiddle(s));
        //不能这样用，虽然调用的是处在同一个类中静态方法,语法问题，<>不能加在方法名前
        //System.out.println(<String>getMiddle(s));
        Date[] d = {new Date(),new Date(),new Date()};
        //其实可以不指定参数，编译器有足够的信息推断出要调用的方法
        System.out.println(getMiddle(d));
        int[] is = {100,200,300};
        System.out.println(getMiddle(is));
    }
 }
 使用类分析器对其进行分析，结果：
 public class ArrayAlg extends java.lang.Object{
    //方法
    public static int getMiddle(int[]);
    public static Java.lang.Object getMiddle(java.lang.Object[]);
    public static Pair minmax(java.lang.Comparable[]);
    public static void main(java.lang.String[]);
    public static java.lang.Comparable min(java.lang.Comparable[]);
 }
 *****************/
/****************
 泛型方法的类型擦除会带来两个问题：1.类型擦除与多态的冲突；2.方法签名冲突。
 public class DateInterval extends Pair<Date> {
    public DateInterval(Date first, Date second){
        super(first, second);
    }
    @Override
    public void setSecond(Date second) {
        super.setSecond(second);
    }
    @Override
    public Date getSecond(){
        return super.getSecond();
    }
    public static void main(String[] args) {
        DateInterval interval = new DateInterval(new Date(), newDate());
        Pair<Date> pair =interval;//超类，多态
        Date date = new Date(2000, 1, 1);
        System.out.println("原来的日期："+pair.getSecond());
        System.out.println("set进新日期："+date);
        pair.setSecond(date);
        System.out.println("执行pair.setSecond(date)后的日期："+pair.getSecond());
    }
 }
 我们知道Java中的方法调用采用的是动态绑定的方式，应该呈现出多态的特性。子类覆写超类中的方法，如果将子类向下转型成超类后，
 仍然可以调用覆写后的方法。但是泛型类的类型擦除造成了一个问题。
 Pair的原始类型中存在方法：
    public void setSecond(Objectsecond);
 DateInterval中的方法：
    public void setSecond(Datesecond);
 我们的本意是想覆写Pair中的setSecond方法，但是从方法签名上看，这完全是两个不同的方法，类型擦除与多态产生了冲突。
 而实际情况那？运行DateInterval的main方法，我们看到：
    public void setSecond(Datesecond)的确覆写了public void setSecond(Object second)方法。这是如何做到的那？
 使用Java类分析器对其进行分析，结果：
    public class DateInterval extends Pair{
        //构造器
        public DateInterval(java.util.Date,java.util.Date);
        //方法
        public void setSecond(java.util.Date);
        public volatile void setSecond(java.lang.Object);//方法1
        public java.util.Date getSecond( );//方法2
        public volatile java.lang.Object getSecond();//方法3，它难道不会和方法1冲突？
        public static void main(java.lang.String[]);
    }
 方法1和方法3是我们在源码中不曾定义的，它肯定是由编译器生成的。这个方法称为桥方法(bridgemethod)，真正覆写超类方法的是它。
 语句pair.setSecond(date)实际上调用的是方法1，publicvolatile void setSecond(Object)，通过这个方法再去调
 用public voidsetSecond(Date)。这个桥方法的实际内容是：
    public void setSecond(Objectsecond){
        this.setSecond((java.util.Date) second );
    }
 这样的结果就符合面向对象中多态的特性了，实现了方法的动态绑定。但是，这样的做法给我们带来了一种错觉，
 就认为public void setSecond(Date)覆写了泛型类的public void setSecond(Object)，如果我们在DateInterval中增加一个方法：
    public void setSecond(Object obj){
        System.out.println("覆写超类方法！");
    }
 补充说明：从JDK1.5开始，在一个方法覆盖另一个方法时可以指定一个更严格的返回类型，它的机制也是同样使用的桥方法。例如：
    public class A{
        public List getList(){
            return null;
        }
    }
    public class ASub extends A{
        @Override
        public ArrayList getList(){
            return null;
        }
    }
    分析ASub类，结果：
    public class ASub extends A{
        //构造器
        public ASub();
        //方法
        public java.util.ArrayList getList( );
        public volatile java.util.List getList( );
    }
 ****************/