package com.horoscope.yenox.smarthoroscope.helpers;

import android.content.Context;

import com.horoscope.yenox.smarthoroscope.R;
import com.horoscope.yenox.smarthoroscope.models.Category;
import com.horoscope.yenox.smarthoroscope.models.Horoscope;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yenox on 1/31/16.
 */
public class HoroscopeHelper {

    public static Horoscope retrieveHoroscope(Context context, String name) {
        return mockedUpData(context, name);
    }

    private static Horoscope mockedUpData(Context context, String name) {
        Horoscope horoscope = new Horoscope(name);
        List<String> categories = Arrays.asList(context.getResources().getStringArray(R.array.categories));
        for (String cat : categories) {
            Category category = new Category(cat);
            int identifier = context.getResources().getIdentifier(cat + "_category", "array", context.getPackageName());
            List<String> attributes = Arrays.asList(context.getResources().getStringArray(identifier));
            for (String attr : attributes) {
                category.addAttr(attr, generateNumber());
            }
            horoscope.addCategory(category);
        }
        return horoscope;
    }

    public static int generateNumber() {
        int HIGH = 10, LOW = 3;
        return (int) (Math.random() * (HIGH - LOW)) + LOW;
    }
}
