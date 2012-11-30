package com.lissenberg.tinker;

import com.tinkerforge.BrickletLCD20x4;
import com.tinkerforge.IPConnection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Searches for tweets with #tinkerforge hashtag.
 * Extremely brittle and ugly code. Need to fix asap!
 */
public class Tweet {

    public static void main(String[] args) throws Exception {

        // http://search.twitter.com/search.json?q=blue%20angels
        // &rpp=5
        // &include_entities=true
        // &result_type=mixed
        String query = String.format("q=%s&rpp=1&include_entities=false&result=recent",
                URLEncoder.encode("#tinkerforge", "UTF-8"));
        URL search = new URL("http://search.twitter.com/search.json?" + query);

        HttpURLConnection con = (HttpURLConnection) search.openConnection();
        InputStream stream = con.getInputStream();
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            for (String line; (line = br.readLine()) != null; ) {
                response.append(line);
            }
        }
        String tweet = "Nothing!";
        System.out.println(response.toString());
        int start = response.indexOf(",\"text\":");
        int end = response.indexOf("\",\"", start + 9);
        if (start > 0 && end > 0) {
            tweet = response.substring(start + 9, end);
            tweet = URLDecoder.decode(tweet, "UTF-8");
            System.out.println(tweet);
        }

        IPConnection ip = new IPConnection("localhost", 4223);
        BrickletLCD20x4 lcd = new BrickletLCD20x4("bfL");
        ip.addDevice(lcd);

        lcd.backlightOn();
        lcd.clearDisplay();
        lcd.writeLine((short) 0, (short) 0, tweet);

        System.in.read();

        lcd.backlightOff();
        ip.destroy();
    }
}
