/* ***********************************************************
 * This file was automatically generated on 2012-10-12.      *
 *                                                           *
 * If you have a bugfix for this file and want to commit it, *
 * please fix the bug in the generator. You can find a link  *
 * to the generator git on tinkerforge.com                   *
 *************************************************************/

package com.tinkerforge;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Device for controlling a piezo buzzer
 */
public class BrickletPiezoBuzzer extends Device {
	private final static byte FUNCTION_BEEP = (byte)1;
	private final static byte FUNCTION_MORSE_CODE = (byte)2;
	private final static byte CALLBACK_BEEP_FINISHED = (byte)3;
	private final static byte CALLBACK_MORSE_CODE_FINISHED = (byte)4;

	/**
	 * This listener is triggered if a beep set by {@link BrickletPiezoBuzzer.beep} is finished
	 */
	public interface BeepFinishedListener {
		public void beepFinished();
	}

	/**
	 * This listener is triggered if the playback of the morse code set by
	 * {@link BrickletPiezoBuzzer.morseCode} is finished.
	 */
	public interface MorseCodeFinishedListener {
		public void morseCodeFinished();
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickletPiezoBuzzer(String uid) {
		super(uid);

		expectedName = "Piezo Buzzer Bricklet";

		bindingVersion[0] = 1;
		bindingVersion[1] = 0;
		bindingVersion[2] = 0;

		callbacks[CALLBACK_BEEP_FINISHED] = new CallbackListener() {
			public void callback(byte[] data) {
				((BeepFinishedListener)listenerObjects[CALLBACK_BEEP_FINISHED]).beepFinished();
			}
		};

		callbacks[CALLBACK_MORSE_CODE_FINISHED] = new CallbackListener() {
			public void callback(byte[] data) {
				((MorseCodeFinishedListener)listenerObjects[CALLBACK_MORSE_CODE_FINISHED]).morseCodeFinished();
			}
		};
	}

	/**
	 * Beeps with the duration in ms. For example: If you set a value of 1000,
	 * the piezo buzzer will beep for one second.
	 */
	public void beep(long duration)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_BEEP, (short)8);
		bb.putInt((int)duration);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Sets morse code that will be played by the piezo buzzer. The morse code
	 * is given as a string consisting of "." (dot), "-" (minus) and " " (space)
	 * for *dits*, *dahs* and *pauses*. Every other character is ignored.
	 * 
	 * For example: If you set the string "...---...", the piezo buzzer will beep
	 * nine times with the durations "short short short long long long short 
	 * short short".
	 * 
	 * The maximum string size is 60.
	 */
	public void morseCode(String morse)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_MORSE_CODE, (short)64);
		for(int i = 0; i < 60; i++) {
			try {
				bb.put((byte)morse.charAt(i));
			} catch(Exception e) {
				bb.put((byte)0);
			}
		}


		sendRequestNoResponse(bb.array());
	}

	/**
	 * Registers a listener object.
	 */
	public void addListener(Object o) {
		if(o instanceof BeepFinishedListener) {
			listenerObjects[CALLBACK_BEEP_FINISHED] = o;
		} else if(o instanceof MorseCodeFinishedListener) {
			listenerObjects[CALLBACK_MORSE_CODE_FINISHED] = o;
		}
	}
}