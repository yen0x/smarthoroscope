package com.horoscope.yenox.smarthoroscope.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yenox on 1/30/16.
 */
public class Horoscope {

    String sign;

    List<Category> categories = new ArrayList<>();

    public Horoscope() {
    }

    public Horoscope(String sign) {
        this.sign = sign;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
