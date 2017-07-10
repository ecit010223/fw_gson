package com.year17.fw_gson.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.year17.fw_gson.bean.Book;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * 作者：张玉辉 on 2017/7/10 21:52.
 * JsonSerializer是一个接口，我们需要提供自己的实现，来满足自己的序列化要求。
 public interface JsonSerializer<T> {
    Gson 会在解析指定类型T数据的时候触发当前回调方法进行序列化
    @param T 需要转化为Json数据的类型，对应上面的Book
    @return 返回T指定的类对应JsonElement
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context);
    }
 * 解析过程同样需要配置：
    // Configure GSON
    final GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(Book.class, new BookSerialiser());
    //告诉Gson对生成的JSON对象进行格式化
    gsonBuilder.setPrettyPrinting();
    final Gson gson = gsonBuilder.create();

    final Book javaPuzzlers = new Book();
    javaPuzzlers.setTitle("Java Puzzlers: Traps, Pitfalls, and Corner Cases");
    javaPuzzlers.setIsbn10("032133678X");
    javaPuzzlers.setIsbn13("978-0321336781");
    javaPuzzlers.setAuthors(new String[] { "Joshua Bloch", "Neal Gafter" });

    // Format to JSON
    final String json = gson.toJson(javaPuzzlers);
 */

public class BookSerialiser implements JsonSerializer<Book> {
    @Override
    public JsonElement serialize(Book src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title", src.getTitle());
        jsonObject.addProperty("isbn-10", src.getIsbn10());
        jsonObject.addProperty("isbn-13", src.getIsbn13());
        final JsonArray jsonAuthorArray = new JsonArray();
        for(final String author:src.getAuthors()){
            final JsonPrimitive jsonAuthor = new JsonPrimitive(author);
            jsonAuthorArray.add(jsonAuthor);
        }
        jsonObject.add("authors",jsonAuthorArray);
        return jsonObject;
    }
}
