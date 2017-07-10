package com.year17.fw_gson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 作者：张玉辉 on 2017/7/10 21:02.
 * 1.Serialization:序列化，使Java对象到Json字符串的过程。
 * 2.Deserialization：反序列化，字符串转换成Java对象。
 * 3.JSON数据中的JsonElement有下面这四种类型：
 *   (1)JsonPrimitive:例如一个字符串或整型;
 *   (2)JsonObject:一个以JsonElement名字(类型为String)作为索引的集合,
 *      也就是说可以把 JsonObject 看作值为 JsonElement 的键值对集合;
 *   (3)JsonArray:JsonElement的集合,注意数组的元素可以是四种类型中的任意一种，或者混合类型都支持;
 *   (4)JsonNull:值为null.
 * Gson处理对象的几个重要点
 * 1.推荐把成员变量都声明称private的.
 * 2.没有必要用注解(@Expose注解)指明某个字段是否会被序列化或者反序列化，
 *   所有包含在当前类(包括父类)中的字段都应该默认被序列化或者反序列化.
 * 3.下面的实现方式能够正确的处理null
 *   (1)当序列化的时候，如果对象的某个字段为null，是不会输出到Json字符串中的;
 *   (2)当反序列化的时候，某个字段在Json字符串中找不到对应的值，就会被赋值为null
 * 4.如果某个字段被 transient 这个Java关键词修饰，就不会被序列化或者反序列化.
 * 5.如果一个字段是 synthetic的,他会被忽视，也即是不应该被序列化或者反序列化.
 * 6.内部类(或者anonymous class(匿名类)，或者local class(局部类，可以理解为在方法内部声明的类))
 *   的某个字段和外部类的某个字段一样的话，就会被忽视，不会被序列化或者反序列化.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
