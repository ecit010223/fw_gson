package com.year17.fw_gson.serial_and_deserial;

/**
 * 作者：张玉辉 on 2017/7/10 22:28.
 */

public class Book {
    private String[] authors;
    private String isbn;
    private String title;

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
