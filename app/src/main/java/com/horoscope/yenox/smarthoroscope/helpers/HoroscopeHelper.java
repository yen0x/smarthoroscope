package com.horoscope.yenox.smarthoroscope.helpers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import com.horoscope.yenox.smarthoroscope.R;
import com.horoscope.yenox.smarthoroscope.models.Category;
import com.horoscope.yenox.smarthoroscope.models.Horoscope;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by yenox on 1/31/16.
 */
public class HoroscopeHelper {

    public static Horoscope retrieveHoroscope(Context context, String name) {

        try {
            return getHoroscopeFromNetwork(context, name);
        } catch (Exception e) {
            Toast.makeText(context, "error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return mockedUpData(context, name);
    }

    private static Horoscope mockedUpData(Context context, String name) {
        Horoscope horoscope = new Horoscope(name);
        List<String> categories = Arrays.asList(context.getResources().getStringArray(R.array.categories));
        for (String cat : categories) {
            Category category = new Category(cat);
            int identifier = context.getResources().getIdentifier("subcategory" + categories.indexOf(cat), "array", context.getPackageName());
            List<String> attributes = Arrays.asList(context.getResources().getStringArray(identifier));
            for (String attr : attributes) {
                category.addAttr(attr, generateNumber());
            }
            horoscope.addCategory(category);
        }
        return horoscope;
    }

    private static Horoscope getHoroscopeFromNetwork(Context context, String signName) throws IOException, JSONException {
        try {
            URL url = new URL(String.format(context.getString(R.string.horoscope_url, null),//
                    signName, new SimpleDateFormat("yyyyMMdd").format(new Date())));
            AsyncTask<URL, Void, InputStream> httpget = new NetworkGetTask().execute(url);
            return buildFromJson(context, httpget.get());
        } catch (Exception e) {
            Log.e("HoroscopeGet", e.getMessage());
            return null;
        }
    }

    protected static Horoscope buildFromJson(Context context, InputStream inputStream) throws JSONException, IOException {
        JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream));

        Horoscope horoscope = new Horoscope();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            switch (name) {
                case "name":
                    horoscope.setSign(jsonReader.nextString());
                    break;
                case "categories":
                    addCategoriesFromJson(context, jsonReader, horoscope);
                    break;
                default:
                    jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return horoscope;
    }

    protected static void addCategoriesFromJson(Context context, JsonReader jsonReader, Horoscope horoscope) throws IOException {
        List<String> categories = Arrays.asList(context.getResources().getStringArray(R.array.categories));
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            jsonReader.beginObject();
            Category category = new Category();
            int id = 0;
            while (jsonReader.hasNext()) {
                switch (jsonReader.nextName()) {
                    case "id":
                        id = jsonReader.nextInt();
                        category.setId(categories.get(id));
                        break;
                    case "scores":
                        jsonReader.beginArray();
                        int i = 0;
                        int identifier = context.getResources().getIdentifier("subcategory" + id, "array", context.getPackageName());
                        List<String> attributes = Arrays.asList(context.getResources().getStringArray(identifier));
                        while (jsonReader.hasNext()) {
                            category.getAttributes().put(attributes.get(i++), jsonReader.nextInt());
                        }
                        jsonReader.endArray();
                        break;
                    default:
                        jsonReader.skipValue();
                }
            }
            horoscope.addCategory(category);
            jsonReader.endObject();
        }
        jsonReader.endArray();
    }

    public static String read(InputStream input) throws IOException {
        String result = "";
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                result += line;
            }
        }
        return result;
    }

    public static int generateNumber() {
        int HIGH = 10, LOW = 3;
        return (int) (Math.random() * (HIGH - LOW)) + LOW;
    }
}
