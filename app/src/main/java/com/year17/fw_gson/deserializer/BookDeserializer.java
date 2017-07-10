package com.year17.fw_gson.deserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.year17.fw_gson.deserializer.Book;

import java.lang.reflect.Type;

/**
 * 作者：张玉辉 on 2017/7/10 22:11.
 * 1.因为我们可以发现上面的JSON数据是一个{}大括号包围的，也就意味着这是一个Json对象。所以首先我们通过
     final JsonObject jsonObject = json.getAsJsonObject();将我们的JsonElement转化为JsonObject.
 * 2.通过jsonObject.get("xxx").getAsString()的形式获取相应String的值
 * 3.通过jsonObject.get("xx").getAsJsonArray();获取相应的json数组，并遍历出其中的相应字段值
 * 4.关于从本地流中读取Json数据可以使用  InputStreamReader完成
 // Configure Gson
 GsonBuilder gsonBuilder = new GsonBuilder();
 gsonBuilder.registerTypeAdapter(Book.class, new BookDeserializer());
 Gson gson = gsonBuilder.create();

 // The JSON data
 try(Reader reader = new InputStreamReader(Main.class.getResourceAsStream("/part1/sample.json"), "UTF-8")){
    // Parse JSON to Java
    Book book = gson.fromJson(reader, Book.class);
    System.out.println(book);
 }
 */

public class BookDeserializer implements JsonDeserializer<Book>{
    @Override
    public Book deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        final JsonElement jsonTitle = jsonObject.get("title");
        final String title = jsonTitle.getAsString();

        final String isbn10 = jsonObject.get("isbn-10").getAsString();
        final String isbn13 = jsonObject.get("isbn-13").getAsString();

        final JsonArray jsonAuthorsArray = jsonObject.get("authors").getAsJsonArray();
        final String[] authors = new String[jsonAuthorsArray.size()];
        for (int i = 0; i < authors.length; i++) {
            final JsonElement jsonAuthor = jsonAuthorsArray.get(i);
            authors[i] = jsonAuthor.getAsString();
        }

        final Book book = new Book();
        book.setTitle(title);
        book.setIsbn10(isbn10);
        book.setIsbn13(isbn13);
        book.setAuthors(authors);
        return book;
    }
}
