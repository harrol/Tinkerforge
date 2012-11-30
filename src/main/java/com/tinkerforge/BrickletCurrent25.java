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
 * Device for sensing current of up to 25A
 */
public class BrickletCurrent25 extends Device {
	private final static byte FUNCTION_GET_CURRENT = (byte)1;
	private final static byte FUNCTION_CALIBRATE = (byte)2;
	private final static byte FUNCTION_IS_OVER_CURRENT = (byte)3;
	private final static byte FUNCTION_GET_ANALOG_VALUE = (byte)4;
	private final static byte FUNCTION_SET_CURRENT_CALLBACK_PERIOD = (byte)5;
	private final static byte FUNCTION_GET_CURRENT_CALLBACK_PERIOD = (byte)6;
	private final static byte FUNCTION_SET_ANALOG_VALUE_CALLBACK_PERIOD = (byte)7;
	private final static byte FUNCTION_GET_ANALOG_VALUE_CALLBACK_PERIOD = (byte)8;
	private final static byte FUNCTION_SET_CURRENT_CALLBACK_THRESHOLD = (byte)9;
	private final static byte FUNCTION_GET_CURRENT_CALLBACK_THRESHOLD = (byte)10;
	private final static byte FUNCTION_SET_ANALOG_VALUE_CALLBACK_THRESHOLD = (byte)11;
	private final static byte FUNCTION_GET_ANALOG_VALUE_CALLBACK_THRESHOLD = (byte)12;
	private final static byte FUNCTION_SET_DEBOUNCE_PERIOD = (byte)13;
	private final static byte FUNCTION_GET_DEBOUNCE_PERIOD = (byte)14;
	private final static byte CALLBACK_CURRENT = (byte)15;
	private final static byte CALLBACK_ANALOG_VALUE = (byte)16;
	private final static byte CALLBACK_CURRENT_REACHED = (byte)17;
	private final static byte CALLBACK_ANALOG_VALUE_REACHED = (byte)18;
	private final static byte CALLBACK_OVER_CURRENT = (byte)19;

	public class CurrentCallbackThreshold {
		public char option;
		public short min;
		public short max;

		public String toString() {
			return "[" + "option = " + option + ", " + "min = " + min + ", " + "max = " + max + "]";
		}
	}

	public class AnalogValueCallbackThreshold {
		public char option;
		public int min;
		public int max;

		public String toString() {
			return "[" + "option = " + option + ", " + "min = " + min + ", " + "max = " + max + "]";
		}
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickletCurrent25.setCurrentCallbackPeriod}. The parameter is the current of the
	 * sensor.
	 * 
	 * {@link com.tinkerforge.BrickletCurrent25.CurrentListener} is only triggered if the current has changed since the
	 * last triggering.
	 */
	public interface CurrentListener {
		public void current(short current);
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickletCurrent25.setAnalogValueCallbackPeriod}. The parameter is the analog value of the
	 * sensor.
	 * 
	 * {@link com.tinkerforge.BrickletCurrent25.AnalogValueListener} is only triggered if the current has changed since the
	 * last triggering.
	 */
	public interface AnalogValueListener {
		public void analogValue(int value);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link BrickletCurrent25.setCurrentCallbackThreshold} is reached.
	 * The parameter is the current of the sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link BrickletCurrent25.setDebouncePeriod}.
	 */
	public interface CurrentReachedListener {
		public void currentReached(short current);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link BrickletCurrent25.setAnalogValueCallbackThreshold} is reached.
	 * The parameter is the analog value of the sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link BrickletCurrent25.setDebouncePeriod}.
	 */
	public interface AnalogValueReachedListener {
		public void analogValueReached(int value);
	}

	/**
	 * This listener is triggered when an over current is measured
	 * (see {@link BrickletCurrent25.isOverCurrent}).
	 */
	public interface OverCurrentListener {
		public void overCurrent();
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickletCurrent25(String uid) {
		super(uid);

		expectedName = "Current25 Bricklet";

		bindingVersion[0] = 1;
		bindingVersion[1] = 0;
		bindingVersion[2] = 0;

		callbacks[CALLBACK_CURRENT] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short current = (bb.getShort());

				((CurrentListener)listenerObjects[CALLBACK_CURRENT]).current(current);
			}
		};

		callbacks[CALLBACK_ANALOG_VALUE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int value = IPConnection.unsignedShort(bb.getShort());

				((AnalogValueListener)listenerObjects[CALLBACK_ANALOG_VALUE]).analogValue(value);
			}
		};

		callbacks[CALLBACK_CURRENT_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short current = (bb.getShort());

				((CurrentReachedListener)listenerObjects[CALLBACK_CURRENT_REACHED]).currentReached(current);
			}
		};

		callbacks[CALLBACK_ANALOG_VALUE_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int value = IPConnection.unsignedShort(bb.getShort());

				((AnalogValueReachedListener)listenerObjects[CALLBACK_ANALOG_VALUE_REACHED]).analogValueReached(value);
			}
		};

		callbacks[CALLBACK_OVER_CURRENT] = new CallbackListener() {
			public void callback(byte[] data) {
				((OverCurrentListener)listenerObjects[CALLBACK_OVER_CURRENT]).overCurrent();
			}
		};
	}

	/**
	 * Returns the current of the sensor. The value is in mA
	 * and between -25000mA and 25000mA.
	 * 
	 * If you want to get the current periodically, it is recommended to use the
	 * listener {@link com.tinkerforge.BrickletCurrent25.CurrentListener} and set the period with
	 * {@link BrickletCurrent25.setCurrentCallbackPeriod}.
	 */
	public short getCurrent() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_CURRENT, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CURRENT);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short current = (bb.getShort());

		return current;
	}

	/**
	 * Calibrates the 0 value of the sensor. You have to call this function
	 * when there is no current present. 
	 * 
	 * The zero point of the current sensor
	 * is depending on the exact properties of the analog-to-digital converter,
	 * the length of the Bricklet cable and the temperature. Thus, if you change
	 * the Brick or the environment in which the Bricklet is used, you might
	 * have to recalibrate.
	 * 
	 * The resulting calibration will be saved on the EEPROM of the Current
	 * Bricklet.
	 */
	public void calibrate()  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_CALIBRATE, (short)4);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns *true* if more than 25A were measured.
	 * 
	 * \note
	 *  To reset this value you have to power cycle the Bricklet.
	 */
	public boolean isOverCurrent() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_IS_OVER_CURRENT, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_IS_OVER_CURRENT);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		boolean over = (bb.get()) != 0;

		return over;
	}

	/**
	 * Returns the value as read by a 12-bit analog-to-digital converter.
	 * The value is between 0 and 4095.
	 * 
	 * \note
	 *  The value returned by {@link BrickletCurrent25.getCurrent} is averaged over several samples
	 *  to yield less noise, while {@link BrickletCurrent25.getAnalogValue} gives back raw
	 *  unfiltered analog values. The only reason to use {@link BrickletCurrent25.getAnalogValue} is,
	 *  if you need the full resolution of the analog-to-digital converter.
	 * 
	 * If you want the analog value periodically, it is recommended to use the 
	 * listener {@link com.tinkerforge.BrickletCurrent25.AnalogValueListener} and set the period with
	 * {@link BrickletCurrent25.setAnalogValueCallbackPeriod}.
	 */
	public int getAnalogValue() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ANALOG_VALUE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ANALOG_VALUE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int value = IPConnection.unsignedShort(bb.getShort());

		return value;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletCurrent25.CurrentListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletCurrent25.CurrentListener} is only triggered if the current has changed since the
	 * last triggering.
	 * 
	 * The default value is 0.
	 */
	public void setCurrentCallbackPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_CURRENT_CALLBACK_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickletCurrent25.setCurrentCallbackPeriod}.
	 */
	public long getCurrentCallbackPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_CURRENT_CALLBACK_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CURRENT_CALLBACK_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletCurrent25.AnalogValueListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletCurrent25.AnalogValueListener} is only triggered if the analog value has changed since the
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
	 * Returns the period as set by {@link BrickletCurrent25.setAnalogValueCallbackPeriod}.
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
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletCurrent25.CurrentReachedListener} listener.
	 * 
	 * The following options are possible:
	 * 
	 * \verbatim
	 *  "Option", "Description"
	 * 
	 *  "'x'",    "Listener is turned off"
	 *  "'o'",    "Listener is triggered when the current is *outside* the min and max values"
	 *  "'i'",    "Listener is triggered when the current is *inside* the min and max values"
	 *  "'<'",    "Listener is triggered when the current is smaller than the min value (max is ignored)"
	 *  "'>'",    "Listener is triggered when the current is greater than the min value (max is ignored)"
	 * \endverbatim
	 * 
	 * The default value is ('x', 0, 0).
	 */
	public void setCurrentCallbackThreshold(char option, short min, short max)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_CURRENT_CALLBACK_THRESHOLD, (short)9);
		bb.put((byte)option);
		bb.putShort((short)min);
		bb.putShort((short)max);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the threshold as set by {@link BrickletCurrent25.setCurrentCallbackThreshold}.
	 */
	public CurrentCallbackThreshold getCurrentCallbackThreshold() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_CURRENT_CALLBACK_THRESHOLD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CURRENT_CALLBACK_THRESHOLD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		CurrentCallbackThreshold obj = new CurrentCallbackThreshold();
		obj.option = (char)(bb.get());
		obj.min = (bb.getShort());
		obj.max = (bb.getShort());

		return obj;
	}

	/**
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletCurrent25.AnalogValueReachedListener} listener.
	 * 
	 * The following options are possible:
	 * 
	 * \verbatim
	 *  "Option", "Description"
	 * 
	 *  "'x'",    "Listener is turned off"
	 *  "'o'",    "Listener is triggered when the analog value is *outside* the min and max values"
	 *  "'i'",    "Listener is triggered when the analog value is *inside* the min and max values"
	 *  "'<'",    "Listener is triggered when the analog value is smaller than the min value (max is ignored)"
	 *  "'>'",    "Listener is triggered when the analog value is greater than the min value (max is ignored)"
	 * \endverbatim
	 * 
	 * The default value is ('x', 0, 0).
	 */
	public void setAnalogValueCallbackThreshold(char option, int min, int max)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_ANALOG_VALUE_CALLBACK_THRESHOLD, (short)9);
		bb.put((byte)option);
		bb.putShort((short)min);
		bb.putShort((short)max);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the threshold as set by {@link BrickletCurrent25.setAnalogValueCallbackThreshold}.
	 */
	public AnalogValueCallbackThreshold getAnalogValueCallbackThreshold() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ANALOG_VALUE_CALLBACK_THRESHOLD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ANALOG_VALUE_CALLBACK_THRESHOLD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		AnalogValueCallbackThreshold obj = new AnalogValueCallbackThreshold();
		obj.option = (char)(bb.get());
		obj.min = IPConnection.unsignedShort(bb.getShort());
		obj.max = IPConnection.unsignedShort(bb.getShort());

		return obj;
	}

	/**
	 * Sets the period in ms with which the threshold listeners
	 * 
	 *  {@link com.tinkerforge.BrickletCurrent25.CurrentReachedListener}, {@link com.tinkerforge.BrickletCurrent25.AnalogValueReachedListener}
	 * 
	 * are triggered, if the thresholds
	 * 
	 *  {@link BrickletCurrent25.setCurrentCallbackThreshold}, {@link BrickletCurrent25.setAnalogValueCallbackThreshold}
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
	 * Returns the debounce period as set by {@link BrickletCurrent25.setDebouncePeriod}.
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
		if(o instanceof CurrentListener) {
			listenerObjects[CALLBACK_CURRENT] = o;
		} else if(o instanceof AnalogValueListener) {
			listenerObjects[CALLBACK_ANALOG_VALUE] = o;
		} else if(o instanceof CurrentReachedListener) {
			listenerObjects[CALLBACK_CURRENT_REACHED] = o;
		} else if(o instanceof AnalogValueReachedListener) {
			listenerObjects[CALLBACK_ANALOG_VALUE_REACHED] = o;
		} else if(o instanceof OverCurrentListener) {
			listenerObjects[CALLBACK_OVER_CURRENT] = o;
		}
	}
}