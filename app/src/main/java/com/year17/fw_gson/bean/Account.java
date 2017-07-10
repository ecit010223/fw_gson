package com.year17.fw_gson.bean;

import com.google.gson.annotations.Expose;

/**
 * 作者：张玉辉 on 2017/7/10 21:39.
 * @Expose注解能够指定该字段是否能够序列化或者反序列化，默认的是都支持(true)
 * 需要注意的通过 builder.excludeFieldsWithoutExposeAnnotation()方法是该注解生效。
 final GsonBuilder builder = new GsonBuilder();
 builder.excludeFieldsWithoutExposeAnnotation();
 final Gson gson = builder.create();
 */

public class Account {

    @Expose(deserialize = false)
    private String accountNumber;

    @Expose
    private String iban;

    @Expose(serialize = false)
    private String owner;

    @Expose(serialize = false, deserialize = false)
    private String address;

    private String pin;
}
