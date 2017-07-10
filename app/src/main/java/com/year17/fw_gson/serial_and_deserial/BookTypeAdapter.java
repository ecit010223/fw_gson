package com.year17.fw_gson.serial_and_deserial;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * 作者：张玉辉 on 2017/7/10 22:30.
 * 1.JsonSerializer和JsonDeserializer解析的时候都利用到了一个中间件-JsonElement
 * 2.TypeAdapter的使用去掉了这个中间层，直接用流来解析数据，极大程度上提高了解析效率。
 * 3.TypeAdapter作为一个抽象类提供两个抽象方法。分别是write()和read()方法,也对应着序列化和反序列化
 * 设置TypeAdapter之后还是需要配置（注册）,可以注意到的是gsonBuilder.registerTypeAdapter(xxx)方法
 * 进行注册在我们之前的JsonSerializer和JsonDeserializer中也有使用:
 final GsonBuilder gsonBuilder = new GsonBuilder();
 gsonBuilder.registerTypeAdapter(Book.class, new BookTypeAdapter());
 final Gson gson = gsonBuilder.create();
 */

public class BookTypeAdapter extends TypeAdapter<Book> {
    // write()方法中会传入JsonWriter，和需要被序列化的Book对象的实例，
    // 采用和PrintStream类似的方式 写入到JsonWriter中。
    @Override
    public void write(JsonWriter out, Book value) throws IOException {
        // 产生"{",如果我们希望产生的是一个数组对象，对应的使用beginArray()
        out.beginObject();
        out.name("isbn").value(value.getIsbn());
        out.name("title").value(value.getTitle());
//        out.name("authors").value(StringUtils.join(value.getAuthors(), ";"));
        // 产生"}"
        out.endObject();
    }

    //read()方法将会传入一个JsonReader对象实例并返回反序列化的对象。
    @Override
    public Book read(JsonReader in) throws IOException {
        final Book book = new Book();
        in.beginObject();
        while(in.hasNext()){
            switch (in.nextName()) {
                case "isbn":
                    book.setIsbn(in.nextString());
                    break;
                case "title":
                    book.setTitle(in.nextString());
                    break;
                case "authors":
                    book.setAuthors(in.nextString().split(";"));
                    break;
            }
        }
        in.endObject();
        return book;
    }
}
