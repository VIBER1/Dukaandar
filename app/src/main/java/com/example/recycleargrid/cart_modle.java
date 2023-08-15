package com.example.recycleargrid;

import java.util.ArrayList;

public class cart_modle {
    private String item_name,item_price;

    public cart_modle(String item_name, String item_price) {
        this.item_name = item_name;
        this.item_price = item_price;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_price() {
        return item_price;
    }
}
