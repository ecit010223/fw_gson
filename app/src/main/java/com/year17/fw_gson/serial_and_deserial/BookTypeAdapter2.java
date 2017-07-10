package com.year17.fw_gson.serial_and_deserial;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：张玉辉 on 2017/7/10 22:48.
 {
    "isbn": "978-0321336781",
    "title": "Java Puzzlers: Traps, Pitfalls, and Corner Cases",
    "authors":
    [
         {
            "id": 1,
            "name": "Joshua Bloch"
         },
        {
            "id": 2,
            "name": "Neal Gafter"
        }
    ]
 }
 */

public class BookTypeAdapter2 extends TypeAdapter<Book2> {
    @Override
    public void write(JsonWriter out, Book2 value) throws IOException {
        out.beginObject();
        out.name("isbn").value(value.getIsbn());
        out.name("title").value(value.getTitle());
        out.name("authors").beginArray();
        for (final Book2.Author author : value.getAuthors()) {
            out.beginObject();
            out.name("id").value(author.getId());
            out.name("name").value(author.getName());
            out.endObject();
        }
        out.endArray();
        out.endObject();
    }

    @Override
    public Book2 read(JsonReader in) throws IOException {
        final Book2 book = new Book2();

        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "isbn":
                    book.setIsbn(in.nextString());
                    break;
                case "title":
                    book.setTitle(in.nextString());
                    break;
                case "authors":
                    in.beginArray();
                    final List authors = new ArrayList<>();
                    while (in.hasNext()) {
                        in.beginObject();
                        final Book2.Author author = new Book2.Author();
                        while (in.hasNext()) {
                            switch (in.nextName()) {
                                case "id":
                                    author.setId(in.nextInt());
                                    break;
                                case "name":
                                    author.setName(in.nextString());
                                    break;
                            }
                        }
                        authors.add(author);
                        in.endObject();
                    }
                    book.setAuthors((Book2.Author[]) authors.toArray(new Book2.Author[authors.size()]));
                    in.endArray();
                    break;
            }
        }
        in.endObject();

        return book;
    }
}
