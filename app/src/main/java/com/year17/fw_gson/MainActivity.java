package com.year17.fw_gson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.year17.fw_gson.model.City;
import com.year17.fw_gson.serial_and_deserial.Book;
import com.year17.fw_gson.serial_and_deserial.BookTypeAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

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
 * 4.如果某个字段被transient这个Java关键词修饰，就不会被序列化或者反序列化.
 *   (1)transient关键字只能修饰变量，而不能修饰方法和类。注意，本地变量是不能被transient关键字修饰的。
 *   (2)被transient关键字修饰的变量不再能被序列化，一个静态变量不管是否被transient修饰，均不能被序列化。
 *   (3)一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问。
 *      也可以认为在将持久化的对象反序列化后，被transient修饰的变量将按照普通类成员变量一样被初始化。
 * 5.如果一个字段是synthetic的,他会被忽视，也即是不应该被序列化或者反序列化.
 * 6.内部类(或者anonymous class(匿名类)，或者local class(局部类，可以理解为在方法内部声明的类))
 *   的某个字段和外部类的某个字段一样的话，就会被忽视，不会被序列化或者反序列化.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnParseIO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        useTypeAdapter();
    }

    private void initView(){
        btnParseIO = (Button)findViewById(R.id.btn_parse_io);
        btnParseIO.setOnClickListener(this);
    }

    /** 使用TypeAdapter进行序列化与反序列化 **/
    private void useTypeAdapter(){
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Book.class, new BookTypeAdapter());
        gsonBuilder.setPrettyPrinting();

        final Gson gson = gsonBuilder.create();

        final Book book = new Book();
        book.setAuthors(new String[] { "Joshua Bloch", "Neal Gafter" });
        book.setTitle("Java Puzzlers: Traps, Pitfalls, and Corner Cases");
        book.setIsbn("978-0321336781");

        final String json = gson.toJson(book);
        System.out.println("Serialised");
        System.out.println(json);

        final Book parsedBook = gson.fromJson(json, Book.class);
        System.out.println("\nDeserialised");
        System.out.println(parsedBook);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_parse_io:
                parseIO();
                break;
        }
    }

    /** 解析IO数据流 **/
    private void parseIO(){
        InputStream stream = null;
        try{
            stream = getAssets().open("cities.json");
        }catch (IOException e){
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().create();
        JsonElement json = new JsonParser().parse(new InputStreamReader(stream));
        List<City> cities = gson.fromJson(json,new TypeToken<List<City>>(){}.getType());
        Log.d(Constants.TAG,cities.toString());
    }
}
