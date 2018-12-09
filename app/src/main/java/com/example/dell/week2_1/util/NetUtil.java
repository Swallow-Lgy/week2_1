package com.example.dell.week2_1.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtil {
    public static String getData(String urlStr){
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode==200){
                String result = stream2String(urlConnection.getInputStream());
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String stream2String(InputStream is) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        for (String tmp = br.readLine(); tmp!=null; tmp=br.readLine()){
            stringBuilder.append(tmp);
        }
        return stringBuilder.toString();
    }
}
