package com.lissenberg.tinker;

import com.tinkerforge.BrickletLCD20x4;
import com.tinkerforge.IPConnection;

public class Text {

    public static void main(String[] args) throws Exception {

        IPConnection ip = new IPConnection("localhost", 4223) ;
        BrickletLCD20x4 lcd = new BrickletLCD20x4("bfL") ;
        ip.addDevice(lcd);

        lcd.backlightOff();

        lcd.clearDisplay();

        lcd.writeLine((short)0, (short)0, "Hi there!");

        lcd.backlightOn();

        lcd.addListener(new BrickletLCD20x4.ButtonPressedListener() {
            @Override
            public void buttonPressed(short button) {
                System.out.println("Button pressed: " + button);
            }
        });

        System.in.read();

        lcd.backlightOff();

        ip.destroy();
    }
}
