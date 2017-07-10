package com.year17.fw_gson.serializer.bean;

import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;

/**
 * 作者：张玉辉 on 2017/7/10 21:42.
 * @Since(1.2)代表从版本1.2之后才生效
 * @Until(0.9)代表着在0.9版本之前都是生效的
 * 也就是说我们利用方法builder.setVersion(1.0)定义版本1.0，如下：
 final GsonBuilder builder = new GsonBuilder();
 builder.setVersion(1.0);

 final Gson gson = builder.create();

 final SoccerPlayer account = new SoccerPlayer();
 account.setName("Albert Attard");
 account.setShirtNumber(10); // Since version 1.2
 account.setTeamName("Zejtun Corinthians");
 account.setCountry("Malta"); // Until version 0.9

 final String json = gson.toJson(account);
 System.out.printf("Serialised (version 1.0)%n  %s%n", json);
 */

public class SoccerPlayer {
    private String name;

    @Since(1.2)
    private int shirtNumber;

    @Until(0.9)
    private String country;

    private String teamName;

}
