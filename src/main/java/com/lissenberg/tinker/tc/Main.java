package com.lissenberg.tinker.tc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.tinkerforge.BrickletLCD20x4;
import com.tinkerforge.IPConnection;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static final String PROJECT_ID = "project2";
    public static final String BUILD_ID = "bt2";

    private static final URI BASE = UriBuilder.fromPath("http://localhost:8111/httpAuth").build();
    private static BrickletLCD20x4 lcd = null;
    private static WebResource resource = null;

    public static void main(String[] args) throws Exception {
        IPConnection ip = new IPConnection("localhost", 4223);
        lcd = new BrickletLCD20x4("bfL");
        ip.addDevice(lcd);
        lcd.addListener(new BrickletLCD20x4.ButtonPressedListener() {
            @Override
            public void buttonPressed(short button) {
                System.out.println("Queuing build...");
                startBuild();
            }
        });

        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        client.addFilter(new HTTPBasicAuthFilter("admin", "admin"));
        resource = client.resource(BASE);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(new Poll(), 0, 2, TimeUnit.SECONDS);

        // Exit app
        System.in.read();
        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);
        lcd.clearDisplay();
        lcd.backlightOff();
        ip.destroy();
    }

    public static class Poll implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("Polling TeamCity...");
                updateLCD();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateLCD() throws Exception {
        String json = resource.path("app/rest/builds")
                .queryParam("buildTypeId", BUILD_ID)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);
        Map map = new ObjectMapper().readValue(json, Map.class);
        List<Map> build = (List<Map>) map.get("build");

        Result current = new Result(build.get(0));
        Result previous = new Result(build.get(1));
        Result previous2 = new Result(build.get(2));

        lcd.clearDisplay();
        lcd.backlightOn();
        lcd.writeLine((short) 0, (short) 0, getProject());
        lcd.writeLine((short) 1, (short) 0, current.toString());
        lcd.writeLine((short) 2, (short) 0, previous.toString());
        lcd.writeLine((short) 3, (short) 0, previous2.toString());
    }

    public static void startBuild() {
        resource.path("action.html")
                .queryParam("add2Queue", BUILD_ID)
                .get(String.class);
    }

    static class Result {
        String id;
        String result;
        Date date;

        Result(Map map) {
            id = (String) map.get("number");
            result = (String) map.get("status");
            try {
                date = new SimpleDateFormat("yyyyMMdd'T'HHmmss").parse((String) map.get("startDate"));
            } catch (ParseException e) {
            }
        }

        @Override
        public String toString() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            return "#" + id + " " + result + " " + sdf.format(date);
        }

    }

    private static String getProject() throws IOException {
        String json = resource.path("app/rest/projects/" + PROJECT_ID)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);
        Map map = new ObjectMapper().readValue(json, Map.class);
        return (String) map.get("name");
    }


}
