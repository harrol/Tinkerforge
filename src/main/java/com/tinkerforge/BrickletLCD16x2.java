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
 * Device for controlling a LCD with 2 lines a 16 characters
 */
public class BrickletLCD16x2 extends Device {
	private final static byte FUNCTION_WRITE_LINE = (byte)1;
	private final static byte FUNCTION_CLEAR_DISPLAY = (byte)2;
	private final static byte FUNCTION_BACKLIGHT_ON = (byte)3;
	private final static byte FUNCTION_BACKLIGHT_OFF = (byte)4;
	private final static byte FUNCTION_IS_BACKLIGHT_ON = (byte)5;
	private final static byte FUNCTION_SET_CONFIG = (byte)6;
	private final static byte FUNCTION_GET_CONFIG = (byte)7;
	private final static byte FUNCTION_IS_BUTTON_PRESSED = (byte)8;
	private final static byte CALLBACK_BUTTON_PRESSED = (byte)9;
	private final static byte CALLBACK_BUTTON_RELEASED = (byte)10;

	public class Config {
		public boolean cursor;
		public boolean blinking;

		public String toString() {
			return "[" + "cursor = " + cursor + ", " + "blinking = " + blinking + "]";
		}
	}

	/**
	 * This listener is triggered when a button is pressed. The parameter is
	 * the number of the button (0 to 2).
	 */
	public interface ButtonPressedListener {
		public void buttonPressed(short button);
	}

	/**
	 * This listener is triggered when a button is released. The parameter is
	 * the number of the button (0 to 2).
	 */
	public interface ButtonReleasedListener {
		public void buttonReleased(short button);
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickletLCD16x2(String uid) {
		super(uid);

		expectedName = "LCD 16x2 Bricklet";

		bindingVersion[0] = 1;
		bindingVersion[1] = 0;
		bindingVersion[2] = 0;

		callbacks[CALLBACK_BUTTON_PRESSED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short button = IPConnection.unsignedByte(bb.get());

				((ButtonPressedListener)listenerObjects[CALLBACK_BUTTON_PRESSED]).buttonPressed(button);
			}
		};

		callbacks[CALLBACK_BUTTON_RELEASED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short button = IPConnection.unsignedByte(bb.get());

				((ButtonReleasedListener)listenerObjects[CALLBACK_BUTTON_RELEASED]).buttonReleased(button);
			}
		};
	}

	/**
	 * Writes text to a specific line (0 to 1) with a specific position 
	 * (0 to 15). The text can have a maximum of 16 characters.
	 * 
	 * For example: (0, 5, "Hello") will write *Hello* in the middle of the
	 * first line of the display.
	 * 
	 * The display uses a special charset that includes all ASCII characters except
	 * backslash and tilde. The LCD charset also includes several other non-ASCII characters, see
	 * the `charset specification <https://github.com/Tinkerforge/lcd-16x2-bricklet/raw/master/datasheets/standard_charset.pdf>`__
	 * for details. The Unicode example above shows how to specify non-ASCII characters
	 * and how to translate from Unicode to the LCD charset.
	 */
	public void writeLine(short line, short position, String text)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_WRITE_LINE, (short)22);
		bb.put((byte)line);
		bb.put((byte)position);
		for(int i = 0; i < 16; i++) {
			try {
				bb.put((byte)text.charAt(i));
			} catch(Exception e) {
				bb.put((byte)0);
			}
		}


		sendRequestNoResponse(bb.array());
	}

	/**
	 * Deletes all characters from the display.
	 */
	public void clearDisplay()  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_CLEAR_DISPLAY, (short)4);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Turns the backlight on.
	 */
	public void backlightOn()  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_BACKLIGHT_ON, (short)4);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Turns the backlight off.
	 */
	public void backlightOff()  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_BACKLIGHT_OFF, (short)4);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns *true* if the backlight is on and *false* otherwise.
	 */
	public boolean isBacklightOn() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_IS_BACKLIGHT_ON, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_IS_BACKLIGHT_ON);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		boolean backlight = (bb.get()) != 0;

		return backlight;
	}

	/**
	 * Configures if the cursor (shown as "_") should be visible and if it
	 * should be blinking (shown as a blinking block). The cursor position
	 * is one character behind the the last text written with 
	 * {@link BrickletLCD16x2.writeLine}.
	 * 
	 * The default is (false, false).
	 */
	public void setConfig(boolean cursor, boolean blinking)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_CONFIG, (short)6);
		bb.put((byte)(cursor ? 1 : 0));
		bb.put((byte)(blinking ? 1 : 0));

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the configuration as set by {@link BrickletLCD16x2.setConfig}.
	 */
	public Config getConfig() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_CONFIG, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CONFIG);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		Config obj = new Config();
		obj.cursor = (bb.get()) != 0;
		obj.blinking = (bb.get()) != 0;

		return obj;
	}

	/**
	 * Returns *true* if the button (0 to 2) is pressed. If you want to react
	 * on button presses and releases it is recommended to use the
	 * {@link com.tinkerforge.BrickletLCD16x2.ButtonPressedListener} and {@link com.tinkerforge.BrickletLCD16x2.ButtonReleasedListener} listeners.
	 */
	public boolean isButtonPressed(short button) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_IS_BUTTON_PRESSED, (short)5);
		bb.put((byte)button);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_IS_BUTTON_PRESSED);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		boolean pressed = (bb.get()) != 0;

		return pressed;
	}

	/**
	 * Registers a listener object.
	 */
	public void addListener(Object o) {
		if(o instanceof ButtonPressedListener) {
			listenerObjects[CALLBACK_BUTTON_PRESSED] = o;
		} else if(o instanceof ButtonReleasedListener) {
			listenerObjects[CALLBACK_BUTTON_RELEASED] = o;
		}
	}
}