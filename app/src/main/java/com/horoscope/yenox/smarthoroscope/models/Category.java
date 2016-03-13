package com.horoscope.yenox.smarthoroscope.models;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yenox on 1/30/16.
 */
public class Category {

    private String id;

    private int score;

    private final Map<String, Integer> attributes = new LinkedHashMap<>();

    public Category() {

    }

    public Category(String id) {
        this.id = id;
    }

    public void addAttr(String aName, int aScore) {
        attributes.put(aName, aScore);
        score = 0;
        for (int s : attributes.values()) {
            score += s;
        }
        score /= attributes.values().size();
    }

    public Map<String, Integer> getAttributes() {
        return attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
