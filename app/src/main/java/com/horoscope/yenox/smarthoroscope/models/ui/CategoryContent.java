package com.horoscope.yenox.smarthoroscope.models.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 */
public class CategoryContent {


    static {
    }

    public static void addCategoryItem() {
    }

    public static class CategoryItem {
        public final String name;
        public final int score;

        public CategoryItem(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
