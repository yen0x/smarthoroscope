package com.horoscope.yenox.smarthoroscope.helpers;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by yenox on 2/23/16.
 */
public class NetworkHelper {

    public static void checkResponse(HttpURLConnection connection) throws IOException {
        if(connection.getResponseCode() != 200) {
            throw new IOException(String.format("Error calling %s {%d}", connection.getURL().toString(), connection.getResponseCode()));
        }
    }
}
