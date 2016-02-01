package com.horoscope.yenox.smarthoroscope.models;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yenox on 1/30/16.
 */
public class Category {

    private String name;

    private int score;

    private final Map<String, Integer> attributes = new LinkedHashMap<>();

    public Category(String name) {
        this.name = name;
    }

    public void addAttr(String aName, int aScore) {
        attributes.put(aName, aScore);
        score = 0;
        for (int s : attributes.values()) {
            score += s;
        }
        score /= attributes.values().size();
    }

    public String getName() {
        return name;
    }
}
