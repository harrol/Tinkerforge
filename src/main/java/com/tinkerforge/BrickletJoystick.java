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
 * Dual-Axis Joystick with Button
 */
public class BrickletJoystick extends Device {
	private final static byte FUNCTION_GET_POSITION = (byte)1;
	private final static byte FUNCTION_IS_PRESSED = (byte)2;
	private final static byte FUNCTION_GET_ANALOG_VALUE = (byte)3;
	private final static byte FUNCTION_CALIBRATE = (byte)4;
	private final static byte FUNCTION_SET_POSITION_CALLBACK_PERIOD = (byte)5;
	private final static byte FUNCTION_GET_POSITION_CALLBACK_PERIOD = (byte)6;
	private final static byte FUNCTION_SET_ANALOG_VALUE_CALLBACK_PERIOD = (byte)7;
	private final static byte FUNCTION_GET_ANALOG_VALUE_CALLBACK_PERIOD = (byte)8;
	private final static byte FUNCTION_SET_POSITION_CALLBACK_THRESHOLD = (byte)9;
	private final static byte FUNCTION_GET_POSITION_CALLBACK_THRESHOLD = (byte)10;
	private final static byte FUNCTION_SET_ANALOG_VALUE_CALLBACK_THRESHOLD = (byte)11;
	private final static byte FUNCTION_GET_ANALOG_VALUE_CALLBACK_THRESHOLD = (byte)12;
	private final static byte FUNCTION_SET_DEBOUNCE_PERIOD = (byte)13;
	private final static byte FUNCTION_GET_DEBOUNCE_PERIOD = (byte)14;
	private final static byte CALLBACK_POSITION = (byte)15;
	private final static byte CALLBACK_ANALOG_VALUE = (byte)16;
	private final static byte CALLBACK_POSITION_REACHED = (byte)17;
	private final static byte CALLBACK_ANALOG_VALUE_REACHED = (byte)18;
	private final static byte CALLBACK_PRESSED = (byte)19;
	private final static byte CALLBACK_RELEASED = (byte)20;

	public class Position {
		public short x;
		public short y;

		public String toString() {
			return "[" + "x = " + x + ", " + "y = " + y + "]";
		}
	}

	public class AnalogValue {
		public int x;
		public int y;

		public String toString() {
			return "[" + "x = " + x + ", " + "y = " + y + "]";
		}
	}

	public class PositionCallbackThreshold {
		public char option;
		public short minX;
		public short maxX;
		public short minY;
		public short maxY;

		public String toString() {
			return "[" + "option = " + option + ", " + "minX = " + minX + ", " + "maxX = " + maxX + ", " + "minY = " + minY + ", " + "maxY = " + maxY + "]";
		}
	}

	public class AnalogValueCallbackThreshold {
		public char option;
		public int minX;
		public int maxX;
		public int minY;
		public int maxY;

		public String toString() {
			return "[" + "option = " + option + ", " + "minX = " + minX + ", " + "maxX = " + maxX + ", " + "minY = " + minY + ", " + "maxY = " + maxY + "]";
		}
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickletJoystick.setPositionCallbackPeriod}. The parameter is the position of the
	 * Joystick.
	 * 
	 * {@link com.tinkerforge.BrickletJoystick.PositionListener} is only triggered if the position has changed since the
	 * last triggering.
	 */
	public interface PositionListener {
		public void position(short x, short y);
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickletJoystick.setAnalogValueCallbackPeriod}. The parameters are the analog values
	 * of the Joystick.
	 * 
	 * {@link com.tinkerforge.BrickletJoystick.AnalogValueListener} is only triggered if the values have changed since the
	 * last triggering.
	 */
	public interface AnalogValueListener {
		public void analogValue(int x, int y);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link BrickletJoystick.setPositionCallbackThreshold} is reached.
	 * The parameters are the position of the Joystick.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link BrickletJoystick.setDebouncePeriod}.
	 */
	public interface PositionReachedListener {
		public void positionReached(short x, short y);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link BrickletJoystick.setAnalogValueCallbackThreshold} is reached.
	 * The parameters are the analog values of the Joystick.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link BrickletJoystick.setDebouncePeriod}.
	 */
	public interface AnalogValueReachedListener {
		public void analogValueReached(int x, int y);
	}

	/**
	 * This listener is triggered when the button is pressed.
	 */
	public interface PressedListener {
		public void pressed();
	}

	/**
	 * This listener is triggered when the button is released.
	 */
	public interface ReleasedListener {
		public void released();
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickletJoystick(String uid) {
		super(uid);

		expectedName = "Joystick Bricklet";

		bindingVersion[0] = 1;
		bindingVersion[1] = 0;
		bindingVersion[2] = 0;

		callbacks[CALLBACK_POSITION] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short x = (bb.getShort());
				short y = (bb.getShort());

				((PositionListener)listenerObjects[CALLBACK_POSITION]).position(x, y);
			}
		};

		callbacks[CALLBACK_ANALOG_VALUE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int x = IPConnection.unsignedShort(bb.getShort());
				int y = IPConnection.unsignedShort(bb.getShort());

				((AnalogValueListener)listenerObjects[CALLBACK_ANALOG_VALUE]).analogValue(x, y);
			}
		};

		callbacks[CALLBACK_POSITION_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short x = (bb.getShort());
				short y = (bb.getShort());

				((PositionReachedListener)listenerObjects[CALLBACK_POSITION_REACHED]).positionReached(x, y);
			}
		};

		callbacks[CALLBACK_ANALOG_VALUE_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int x = IPConnection.unsignedShort(bb.getShort());
				int y = IPConnection.unsignedShort(bb.getShort());

				((AnalogValueReachedListener)listenerObjects[CALLBACK_ANALOG_VALUE_REACHED]).analogValueReached(x, y);
			}
		};

		callbacks[CALLBACK_PRESSED] = new CallbackListener() {
			public void callback(byte[] data) {
				((PressedListener)listenerObjects[CALLBACK_PRESSED]).pressed();
			}
		};

		callbacks[CALLBACK_RELEASED] = new CallbackListener() {
			public void callback(byte[] data) {
				((ReleasedListener)listenerObjects[CALLBACK_RELEASED]).released();
			}
		};
	}

	/**
	 * Returns the position of the Joystick. The value ranges between -100 and
	 * 100 for both axis. The middle position of the joystick is x=0, y=0. The
	 * returned values are averaged and calibrated (see {@link BrickletJoystick.calibrate}).
	 * 
	 * If you want to get the position periodically, it is recommended to use the
	 * listener {@link com.tinkerforge.BrickletJoystick.PositionListener} and set the period with
	 * {@link BrickletJoystick.setPositionCallbackPeriod}.
	 */
	public Position getPosition() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_POSITION, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_POSITION);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		Position obj = new Position();
		obj.x = (bb.getShort());
		obj.y = (bb.getShort());

		return obj;
	}

	/**
	 * Returns *true* if the button is pressed and *false* otherwise.
	 * 
	 * It is recommended to use the {@link com.tinkerforge.BrickletJoystick.PressedListener} and {@link com.tinkerforge.BrickletJoystick.ReleasedListener} listeners
	 * to handle the button.
	 */
	public boolean isPressed() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_IS_PRESSED, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_IS_PRESSED);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		boolean pressed = (bb.get()) != 0;

		return pressed;
	}

	/**
	 * Returns the values as read by a 12-bit analog-to-digital converter.
	 * The values are between 0 and 4095 for both axis.
	 * 
	 * \note
	 *  The values returned by {@link BrickletJoystick.getPosition} are averaged over several samples
	 *  to yield less noise, while {@link BrickletJoystick.getAnalogValue} gives back raw
	 *  unfiltered analog values. The only reason to use {@link BrickletJoystick.getAnalogValue} is,
	 *  if you need the full resolution of the analog-to-digital converter.
	 * 
	 * If you want the analog values periodically, it is recommended to use the 
	 * listener {@link com.tinkerforge.BrickletJoystick.AnalogValueListener} and set the period with
	 * {@link BrickletJoystick.setAnalogValueCallbackPeriod}.
	 */
	public AnalogValue getAnalogValue() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ANALOG_VALUE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ANALOG_VALUE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		AnalogValue obj = new AnalogValue();
		obj.x = IPConnection.unsignedShort(bb.getShort());
		obj.y = IPConnection.unsignedShort(bb.getShort());

		return obj;
	}

	/**
	 * Calibrates the middle position of the Joystick. If your Joystick Bricklet
	 * does not return x=0 and y=0 in the middle position, call this function
	 * while the Joystick is standing still in the middle position.
	 * 
	 * The resulting calibration will be saved on the EEPROM of the Joystick
	 * Bricklet, thus you only have to calibrate it once.
	 */
	public void calibrate()  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_CALIBRATE, (short)4);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletJoystick.PositionListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletJoystick.PositionListener} is only triggered if the position has changed since the
	 * last triggering.
	 * 
	 * The default value is 0.
	 */
	public void setPositionCallbackPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_POSITION_CALLBACK_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickletJoystick.setPositionCallbackPeriod}.
	 */
	public long getPositionCallbackPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_POSITION_CALLBACK_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_POSITION_CALLBACK_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletJoystick.AnalogValueListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletJoystick.AnalogValueListener} is only triggered if the analog values have changed since the
	 * last triggering.
	 * 
	 * The default value is 0.
	 */
	public void setAnalogValueCallbackPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_ANALOG_VALUE_CALLBACK_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickletJoystick.setAnalogValueCallbackPeriod}.
	 */
	public long getAnalogValueCallbackPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ANALOG_VALUE_CALLBACK_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ANALOG_VALUE_CALLBACK_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletJoystick.PositionReachedListener} listener.
	 * 
	 * The following options are possible:
	 * 
	 * \verbatim
	 *  "Option", "Description"
	 * 
	 *  "'x'",    "Listener is turned off"
	 *  "'o'",    "Listener is triggered when the position is *outside* the min and max values"
	 *  "'i'",    "Listener is triggered when the position is *inside* the min and max values"
	 *  "'<'",    "Listener is triggered when the position is smaller than the min values (max is ignored)"
	 *  "'>'",    "Listener is triggered when the position is greater than the min values (max is ignored)"
	 * \endverbatim
	 * 
	 * The default value is ('x', 0, 0, 0, 0).
	 */
	public void setPositionCallbackThreshold(char option, short minX, short maxX, short minY, short maxY)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_POSITION_CALLBACK_THRESHOLD, (short)13);
		bb.put((byte)option);
		bb.putShort((short)minX);
		bb.putShort((short)maxX);
		bb.putShort((short)minY);
		bb.putShort((short)maxY);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the threshold as set by {@link BrickletJoystick.setPositionCallbackThreshold}.
	 */
	public PositionCallbackThreshold getPositionCallbackThreshold() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_POSITION_CALLBACK_THRESHOLD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_POSITION_CALLBACK_THRESHOLD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		PositionCallbackThreshold obj = new PositionCallbackThreshold();
		obj.option = (char)(bb.get());
		obj.minX = (bb.getShort());
		obj.maxX = (bb.getShort());
		obj.minY = (bb.getShort());
		obj.maxY = (bb.getShort());

		return obj;
	}

	/**
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletJoystick.AnalogValueReachedListener} listener.
	 * 
	 * The following options are possible:
	 * 
	 * \verbatim
	 *  "Option", "Description"
	 * 
	 *  "'x'",    "Listener is turned off"
	 *  "'o'",    "Listener is triggered when the analog values are *outside* the min and max values"
	 *  "'i'",    "Listener is triggered when the analog values are *inside* the min and max values"
	 *  "'<'",    "Listener is triggered when the analog values are smaller than the min values (max is ignored)"
	 *  "'>'",    "Listener is triggered when the analog values are greater than the min values (max is ignored)"
	 * \endverbatim
	 * 
	 * The default value is ('x', 0, 0, 0, 0).
	 */
	public void setAnalogValueCallbackThreshold(char option, int minX, int maxX, int minY, int maxY)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_ANALOG_VALUE_CALLBACK_THRESHOLD, (short)13);
		bb.put((byte)option);
		bb.putShort((short)minX);
		bb.putShort((short)maxX);
		bb.putShort((short)minY);
		bb.putShort((short)maxY);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the threshold as set by {@link BrickletJoystick.setAnalogValueCallbackThreshold}.
	 */
	public AnalogValueCallbackThreshold getAnalogValueCallbackThreshold() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ANALOG_VALUE_CALLBACK_THRESHOLD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ANALOG_VALUE_CALLBACK_THRESHOLD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		AnalogValueCallbackThreshold obj = new AnalogValueCallbackThreshold();
		obj.option = (char)(bb.get());
		obj.minX = IPConnection.unsignedShort(bb.getShort());
		obj.maxX = IPConnection.unsignedShort(bb.getShort());
		obj.minY = IPConnection.unsignedShort(bb.getShort());
		obj.maxY = IPConnection.unsignedShort(bb.getShort());

		return obj;
	}

	/**
	 * Sets the period in ms with which the threshold listeners
	 * 
	 *  {@link com.tinkerforge.BrickletJoystick.PositionReachedListener}, {@link com.tinkerforge.BrickletJoystick.AnalogValueReachedListener}
	 * 
	 * are triggered, if the thresholds
	 * 
	 *  {@link BrickletJoystick.setPositionCallbackThreshold}, {@link BrickletJoystick.setAnalogValueCallbackThreshold}
	 * 
	 * keep being reached.
	 * 
	 * The default value is 100.
	 */
	public void setDebouncePeriod(long debounce)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_DEBOUNCE_PERIOD, (short)8);
		bb.putInt((int)debounce);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the debounce period as set by {@link BrickletJoystick.setDebouncePeriod}.
	 */
	public long getDebouncePeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_DEBOUNCE_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_DEBOUNCE_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long debounce = IPConnection.unsignedInt(bb.getInt());

		return debounce;
	}

	/**
	 * Registers a listener object.
	 */
	public void addListener(Object o) {
		if(o instanceof PositionListener) {
			listenerObjects[CALLBACK_POSITION] = o;
		} else if(o instanceof AnalogValueListener) {
			listenerObjects[CALLBACK_ANALOG_VALUE] = o;
		} else if(o instanceof PositionReachedListener) {
			listenerObjects[CALLBACK_POSITION_REACHED] = o;
		} else if(o instanceof AnalogValueReachedListener) {
			listenerObjects[CALLBACK_ANALOG_VALUE_REACHED] = o;
		} else if(o instanceof PressedListener) {
			listenerObjects[CALLBACK_PRESSED] = o;
		} else if(o instanceof ReleasedListener) {
			listenerObjects[CALLBACK_RELEASED] = o;
		}
	}
}