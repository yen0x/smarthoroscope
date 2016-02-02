package com.horoscope.yenox.smarthoroscope.models.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 */
public class SignContent {

    public static final List<SignItem> SIGN_ITEMS = new ArrayList<SignItem>();

    static {
    }

    public static void addSignItems(List<String> list) {
        for (String sign : list) {
            addItem(new SignItem(sign));
        }
    }

    private static void addItem(SignItem item) {
        SIGN_ITEMS.add(item);
    }

    public static class SignItem {
        public final String name;

        public SignItem(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
