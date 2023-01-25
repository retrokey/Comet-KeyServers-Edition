package com.cometproject.server.game.landing.calendar;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CalendarDay {
    private int day;
    private String gift;
    private String product;
    private String image;
    private String item;

    public CalendarDay(ResultSet data) throws SQLException {
        this.day = data.getInt("day");
        this.gift = data.getString("gift");
        this.product = data.getString("product");
        this.image = data.getString("image");
        this.item = data.getString("item");
    }

    public int getDay() {
        return day;
    }

    public String getGift() { return gift; }

    public String getProduct() {
        return product;
    }

    public String getImage() {
        return image;
    }

    public String getItem() {
        return item;
    }
}
