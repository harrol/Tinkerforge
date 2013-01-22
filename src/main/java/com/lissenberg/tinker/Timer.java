package com.lissenberg.tinker;

import com.tinkerforge.BrickletLCD20x4;
import com.tinkerforge.IPConnection;

public class Timer {

    public static volatile boolean running = true;
    public static BrickletLCD20x4 lcd;

    public static void main(String[] args) throws Exception {

        IPConnection ip = new IPConnection("localhost", 4223);
        lcd = new BrickletLCD20x4("bfL");
        ip.addDevice(lcd);
        lcd.clearDisplay();
        lcd.backlightOn();

        new Thread(new CountDown(35)).start();

        System.in.read();
        running = false;
        lcd.backlightOff();
        Thread.sleep(1001);
        lcd.clearDisplay();
        ip.destroy();
    }


    public static class CountDown implements Runnable {
        int secs;

        public CountDown(int seconds) {
            this.secs = seconds;
        }

        @Override
        public void run() {
            while (running) {
                try {
                    Thread.sleep(1000);
                    secs--;
                    System.out.println(secs);
                    writeTime(secs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    running = false;
                }
                if(secs <= 0) {
                    System.out.println("TIME!");
                    running = false;
                }

            }
        }
    }


    private static void writeTime(int time) {
        // 5 ^ 2 = 32;
        if(time > 31) {
            time = 31;
        }
        boolean b0 = ((time >> 0) & 1) != 0;
        boolean b1 = ((time >> 1) & 1) != 0;
        boolean b2 = ((time >> 2) & 1) != 0;
        boolean b3 = ((time >> 3) & 1) != 0;
        boolean b4 = ((time >> 4) & 1) != 0;
        write(4, b0);
        write(3, b1);
        write(2, b2);
        write(1, b3);
        write(0, b4);
    }

    /**
     * Location 0 - 4
     * @param location
     */
    private static void write(int location, boolean one) {
        if(location < 0 || location > 4) {
            throw new IllegalArgumentException("Illegal location");
        }
        // 0 1 2 3
        //   x
        // x   x
        // x   x
        //   x
        short pos = (short)(location * 4);
        if(one) {
            lcd.writeLine((short)0, pos, " \uFFFF ");
            lcd.writeLine((short)1, pos, " \uFFFF ");
            lcd.writeLine((short)2, pos, " \uFFFF ");
            lcd.writeLine((short)3, pos, " \uFFFF ");
        } else {
            lcd.writeLine((short)0, pos, " \uFFFF ");
            lcd.writeLine((short)1, pos, "\uFFFF \uFFFF");
            lcd.writeLine((short)2, pos, "\uFFFF \uFFFF");
            lcd.writeLine((short)3, pos, " \uFFFF ");
        }
    }

}
