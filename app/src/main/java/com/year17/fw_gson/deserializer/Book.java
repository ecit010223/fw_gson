package com.year17.fw_gson.deserializer;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：张玉辉 on 2017/7/10 22:08.
 {
    "title": "Java Puzzlers: Traps, Pitfalls, and Corner Cases",
    "isbn-10": "032133678X",
    "isbn-13": "978-0321336781",
    "authors": [
        "Joshua Bloch",
        "Neal Gafter"
    ]
 }
 * 反序列化方案1:利用@SerializedName注解
 * 反序列化方案2:使用JsonDeserializer,见utils包中BookDeserializer
 */

public class Book {
    private String[] authors;

    @SerializedName("isbn-10")
    private String isbn10;

    // value也就是默认的字段，对序列化和反序列化都有效，alternate只有反序列化才有效果。
    // 也就是说一般服务器返回给我们JSON数据的时候可能同样的一个图片，表示"image","img","icon"等，
    // 我们利用@SerializedName 中的alternate字段就能解决这个问题，全部转化为我们实体类中的图片字段。
    @SerializedName(value = "isbn-13", alternate = {"isbn13","isbn.13"})
    private String isbn13;
    private String title;

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
