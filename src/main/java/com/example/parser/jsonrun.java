package com.example.parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.json.JacksonJsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class jsonrun {

    public static void main(String[] args) throws IOException {

        String keyword = "";
        int payment_from = 45000;
        int payment_to = 100000;
        int age_from = 23;
        int age_to = 30;
        int t = 4;
        short no_agreement = 1;
        int page = 0;
        boolean more = true;

        String app_key="v3.h.3642781.04a4314fbdc985b37ac3d2734b0b273adba34243.add92a6ef672d3b0dfd2bfa42962538c9f83d462";
        while (more) {
            String url = "https://api.superjob.ru/2.0/resumes/?app_key=" + app_key +
                    "&keyword=" + keyword +
                    "&payment_from=" + payment_from +
                    "&payment_to=" + payment_to +
                    "&age_from=" + age_from +
                    "&age_to=" + age_to +
                    "&t=" + t +
                    "&no_agreement=" + no_agreement +
                    "&page=" + page +
                    "&count=100"+
                    "&published=1";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + "\""+url+"\"");
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "UTF-8"));
            StringBuffer response = new StringBuffer();
            JSONObject objectJson = new JSONObject(in.readLine());
            JSONObject objJS;
            int total = Integer.parseInt(objectJson.get("total").toString());
            System.out.println("Всего найдено вакансий по запросу: " + total);

            for (int i = 0; i < objectJson.getJSONArray("objects").length(); i++) {
                objJS = objectJson.getJSONArray("objects").getJSONObject(i);
                System.out.println("UserID: " + objJS.get("id_user")
                        + ", должность: " + objJS.get("profession")
                        + ", желаемая зп: " + objJS.get("payment")
                        + ", стаж: " + objJS.get("experience_month_count") + " мес."
                        + ", возраст: " + objJS.get("age")
                        + ", url: " + objJS.get("link")
                );
            }

            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (page >= total / 100)
                more = false;
            in.close();
            System.out.println("Страница: "+page+", total:"+total);
            page++;
        }

    }

}
