package com.horoscope.yenox.smarthoroscope.helpers;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yenox on 3/10/16.
 */
public class NetworkGetTask extends AsyncTask<URL, Void, InputStream> {
    @Override
    protected InputStream doInBackground(URL... params) {
        try {
            if (params.length != 1) {
                throw new Exception("NetworkGetTask supports only one URL atm.");
            }
            HttpURLConnection conn = (HttpURLConnection) params[0].openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            NetworkHelper.checkResponse(conn);
            return conn.getInputStream();
        } catch (Exception e) {
            Log.e("Async get", e.getMessage());
            return null;
        }
    }
}
