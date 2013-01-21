package com.lissenberg.tinker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinkerforge.BrickletLCD20x4;
import com.tinkerforge.IPConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Searches for tweets and displays result on LCD
 */
public class Tweet {
    public static final String KEYWORD = "#tinkerforge";
    public static final int NR = 5;
    public static final int SECS = 5;
    public static BrickletLCD20x4 lcd;


    public static void main(String[] args) throws Exception {
        IPConnection ip = new IPConnection("localhost", 4223);
        lcd = new BrickletLCD20x4("bfL");
        ip.addDevice(lcd);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(new Poller(), 0, NR * SECS, TimeUnit.SECONDS);

        lcd.backlightOn();
        lcd.clearDisplay();

        System.in.read();

        executor.shutdown();
        executor.awaitTermination(NR * SECS, TimeUnit.SECONDS);

        lcd.backlightOff();
        ip.destroy();
    }

    private static void updateLCD(TweetData tweet) {
        if (tweet == null) {
            lcd.writeLine((short) 0, (short) 0, "No tweets for:");
            lcd.writeLine((short) 1, (short) 0, KEYWORD);
        } else {
            lcd.writeLine((short) 0, (short) 0, tweet.from);
            String[] strings = threeLines(tweet.text);
            lcd.writeLine((short) 1, (short) 0, strings[0]);
            lcd.writeLine((short) 2, (short) 0, strings[1]);
            lcd.writeLine((short) 3, (short) 0, strings[2]);
        }
    }

    public static class Poller implements Runnable {
        @Override
        public void run() {
            try {
                List<TweetData> tweets = findTweets(KEYWORD);
                if (tweets.isEmpty()) {
                    updateLCD(null);
                } else {
                    for (TweetData tweet : tweets) {
                        updateLCD(tweet);
                        Thread.sleep(SECS * 1000);
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    private static List<TweetData> findTweets(String query) throws Exception {
        List<TweetData> tweets = new ArrayList<>();
        String q = String.format("q=%s&rpp=" + NR + "&include_entities=false&result=recent",
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
            for (Map<String, Object> result : results) {
                r = new TweetData();
                r.from = "@" + result.get("from_user").toString();
                r.text = result.get("text").toString();
                tweets.add(r);
            }
        }
        return tweets;
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
