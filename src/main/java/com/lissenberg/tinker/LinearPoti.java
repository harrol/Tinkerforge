package com.lissenberg.tinker;

import com.tinkerforge.BrickletDistanceIR;
import com.tinkerforge.BrickletLCD20x4;
import com.tinkerforge.BrickletLinearPoti;
import com.tinkerforge.IPConnection;

import java.io.IOException;

public class LinearPoti {

    public static void main(String[] args) throws Exception {

        IPConnection ip = new IPConnection("localhost", 4223);

        BrickletLinearPoti poti = new BrickletLinearPoti("bxs");
        final BrickletLCD20x4 lcd = new BrickletLCD20x4("bfL");
        final BrickletDistanceIR dist = new BrickletDistanceIR("aUM");

        ip.addDevice(lcd);
        ip.addDevice(poti);
        ip.addDevice(dist);

        poti.setPositionCallbackPeriod(500L);
        lcd.clearDisplay();
        lcd.backlightOn();
        lcd.writeLine((short) 0, (short) 0, "Tinkering /w LinPoti");

        poti.addListener(new BrickletLinearPoti.PositionListener() {

            @Override
            public void position(int position) {
                System.out.println("Position: " + position);
                lcd.writeLine((short)1, (short)0, "Position: " + position + "   ");
            }
        });

        dist.setDistanceCallbackPeriod(2000);
        dist.addListener(new BrickletDistanceIR.DistanceListener() {
            @Override
            public void distance(int distance) {
                System.out.println("Distance: " + distance);
                lcd.writeLine((short)2, (short)0, "Distance: " + distance + "   ");
            }
        });

        System.in.read();

        ip.destroy();


    }
}
