package com.lissenberg.tinker;


import com.tinkerforge.BrickServo;
import com.tinkerforge.IPConnection;

public class Servo {

    private static final Short S1 = 0;
    private static final Short S2 = 1;

    public static void main(String[] args) throws Exception {

        IPConnection ip = new IPConnection("localhost", 4223);

        BrickServo servo = new BrickServo("94yAGXiUGQz");
        ip.addDevice(servo);

        servo.setOutputVoltage(4800);

        servo.setDegree(S1, Short.MIN_VALUE, Short.MAX_VALUE);
        servo.setPulseWidth(S1, 1000, 2000);
        servo.setAcceleration(S1, 0xFFFF);
        servo.setVelocity(S1, 0xFFFF); // Full speed

        servo.setDegree(S2, Short.MIN_VALUE, Short.MAX_VALUE);
        servo.setPulseWidth(S2, 1000, 2000);
        servo.setAcceleration(S2, 0xEEEE);
        servo.setVelocity(S2, 0xFFFF); // Full speed

        servo.enable(S1);
        servo.enable(S2);
        for (int i = 0; i < 5; i++) {
            servo.setPosition(S1, Short.MIN_VALUE);
            servo.setPosition(S2, Short.MAX_VALUE);
            Thread.sleep(2000l);
            servo.setPosition(S1, Short.MAX_VALUE);
            servo.setPosition(S2, Short.MIN_VALUE);
            Thread.sleep(2000l);
            servo.setPosition(S1, Short.MIN_VALUE);
            servo.setPosition(S2, Short.MAX_VALUE);

        }

        ip.destroy();

    }
}
