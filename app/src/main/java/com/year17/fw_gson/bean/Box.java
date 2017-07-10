package com.year17.fw_gson.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：张玉辉 on 2017/7/10 21:36.
 * @SerializedName注解能指定该字段在JSON中对应的字段名称
 */

public class Box {

    @SerializedName("w")
    private int width;

    @SerializedName("h")
    private int height;

    @SerializedName("d")
    private int depth;
}
