package com.lissenberg.tinker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinkerforge.BrickletLCD20x4;
import com.tinkerforge.IPConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Searches for tweets and displays result on LCD
 */
public class Tweet {
    public static final String KEYWORD = "#tinkerforge";

    public static void main(String[] args) throws Exception {
        IPConnection ip = new IPConnection("localhost", 4223);
        BrickletLCD20x4 lcd = new BrickletLCD20x4("bfL");
        ip.addDevice(lcd);

        TweetData tweet = findTweet(KEYWORD);

        lcd.backlightOn();
        lcd.clearDisplay();
        if (tweet != null) {
            lcd.writeLine((short) 0, (short) 0, tweet.from);
            String[] strings = threeLines(tweet.text);
            lcd.writeLine((short) 1, (short) 0, strings[0]);
            lcd.writeLine((short) 2, (short) 0, strings[1]);
            lcd.writeLine((short) 3, (short) 0, strings[2]);
        } else {
            lcd.writeLine((short) 0, (short) 0, "No tweets for:");
            lcd.writeLine((short) 1, (short) 0, KEYWORD);
        }

        System.in.read();

        lcd.backlightOff();
        ip.destroy();
    }


    private static TweetData findTweet(String query) throws Exception {
        String q = String.format("q=%s&rpp=1&include_entities=false&result=recent",
                URLEncoder.encode(query, "UTF-8"));
        URL search = new URL("http://search.twitter.com/search.json?" + q);
        HttpURLConnection con = (HttpURLConnection) search.openConnection();
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            for (String line; (line = br.readLine()) != null; ) {
                response.append(line);
            }
        }
        con.disconnect();
        Map map = new ObjectMapper().readValue(response.toString(), Map.class);
        List<Map<String, Object>> results = (List<Map<String, Object>>) map.get("results");
        TweetData r = null;
        if (results.size() > 0) {
            r = new TweetData();
            for (Map<String, Object> result : results) {
                r.from = "@" + result.get("from_user").toString();
                r.text = result.get("text").toString();
            }
        }
        return r;
    }

    static class TweetData {
        public String from;
        public String text;
    }

    private static String[] threeLines(String text) {
        String[] result = new String[3];
        if (text.length() > 40) {
            int to = Math.min(text.length(), 60);
            result[2] = text.substring(40, to);
        }
        if (text.length() > 20) {
            int to = Math.min(text.length(), 40);
            result[1] = text.substring(20, to);
        }
        result[0] = text.substring(0, 20);
        return result;
    }

}
