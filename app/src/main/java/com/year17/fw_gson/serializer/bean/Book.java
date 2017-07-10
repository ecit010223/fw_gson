package com.year17.fw_gson.serializer.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：张玉辉 on 2017/7/10 21:45.
 * Java对象到JSON字符串的过程:
 * 序列化方案1:采用@SerializedName注解
 {
    "title": "Java Puzzlers: Traps, Pitfalls, and Corner Cases",
    "isbn-10": "032133678X",
    "isbn-13": "978-0321336781",
    "authors": [
        "Joshua Bloch",
        "Neal Gafter"
    ]
 }
 * 序列化方案2:利用JsonSerializer类,见utils包下BookSerialiser类
 */

public class Book {
    private String[] authors;

    @SerializedName("isbn-10")
    private String isbn10;

    @SerializedName("isbn-13")
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
