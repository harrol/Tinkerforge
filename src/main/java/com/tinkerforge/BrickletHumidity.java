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
 * Device for sensing Humidity
 */
public class BrickletHumidity extends Device {
	private final static byte FUNCTION_GET_HUMIDITY = (byte)1;
	private final static byte FUNCTION_GET_ANALOG_VALUE = (byte)2;
	private final static byte FUNCTION_SET_HUMIDITY_CALLBACK_PERIOD = (byte)3;
	private final static byte FUNCTION_GET_HUMIDITY_CALLBACK_PERIOD = (byte)4;
	private final static byte FUNCTION_SET_ANALOG_VALUE_CALLBACK_PERIOD = (byte)5;
	private final static byte FUNCTION_GET_ANALOG_VALUE_CALLBACK_PERIOD = (byte)6;
	private final static byte FUNCTION_SET_HUMIDITY_CALLBACK_THRESHOLD = (byte)7;
	private final static byte FUNCTION_GET_HUMIDITY_CALLBACK_THRESHOLD = (byte)8;
	private final static byte FUNCTION_SET_ANALOG_VALUE_CALLBACK_THRESHOLD = (byte)9;
	private final static byte FUNCTION_GET_ANALOG_VALUE_CALLBACK_THRESHOLD = (byte)10;
	private final static byte FUNCTION_SET_DEBOUNCE_PERIOD = (byte)11;
	private final static byte FUNCTION_GET_DEBOUNCE_PERIOD = (byte)12;
	private final static byte CALLBACK_HUMIDITY = (byte)13;
	private final static byte CALLBACK_ANALOG_VALUE = (byte)14;
	private final static byte CALLBACK_HUMIDITY_REACHED = (byte)15;
	private final static byte CALLBACK_ANALOG_VALUE_REACHED = (byte)16;

	public class HumidityCallbackThreshold {
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
	 * {@link BrickletHumidity.setHumidityCallbackPeriod}. The parameter is the humidity of the
	 * sensor.
	 * 
	 * {@link com.tinkerforge.BrickletHumidity.HumidityListener} is only triggered if the humidity has changed since the
	 * last triggering.
	 */
	public interface HumidityListener {
		public void humidity(int humidity);
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickletHumidity.setAnalogValueCallbackPeriod}. The parameter is the analog value of the
	 * sensor.
	 * 
	 * {@link com.tinkerforge.BrickletHumidity.AnalogValueListener} is only triggered if the humidity has changed since the
	 * last triggering.
	 */
	public interface AnalogValueListener {
		public void analogValue(int value);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link BrickletHumidity.setHumidityCallbackThreshold} is reached.
	 * The parameter is the humidity of the sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link BrickletHumidity.setDebouncePeriod}.
	 */
	public interface HumidityReachedListener {
		public void humidityReached(int humidity);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link BrickletHumidity.setAnalogValueCallbackThreshold} is reached.
	 * The parameter is the analog value of the sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link BrickletHumidity.setDebouncePeriod}.
	 */
	public interface AnalogValueReachedListener {
		public void analogValueReached(int value);
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickletHumidity(String uid) {
		super(uid);

		expectedName = "Humidity Bricklet";

		bindingVersion[0] = 1;
		bindingVersion[1] = 0;
		bindingVersion[2] = 0;

		callbacks[CALLBACK_HUMIDITY] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int humidity = IPConnection.unsignedShort(bb.getShort());

				((HumidityListener)listenerObjects[CALLBACK_HUMIDITY]).humidity(humidity);
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

		callbacks[CALLBACK_HUMIDITY_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int humidity = IPConnection.unsignedShort(bb.getShort());

				((HumidityReachedListener)listenerObjects[CALLBACK_HUMIDITY_REACHED]).humidityReached(humidity);
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
	}

	/**
	 * Returns the humidity of the sensor. The value
	 * has a range of 0 to 1000 and is given in %RH/10 (Relative Humidity), 
	 * i.e. a value of 421 means that a humidity of 42.1 %RH is measured.
	 * 
	 * If you want to get the humidity periodically, it is recommended to use the
	 * listener {@link com.tinkerforge.BrickletHumidity.HumidityListener} and set the period with
	 * {@link BrickletHumidity.setHumidityCallbackPeriod}.
	 */
	public int getHumidity() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_HUMIDITY, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_HUMIDITY);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int humidity = IPConnection.unsignedShort(bb.getShort());

		return humidity;
	}

	/**
	 * Returns the value as read by a 12-bit analog-to-digital converter.
	 * The value is between 0 and 4095.
	 * 
	 * \note
	 *  The value returned by {@link BrickletHumidity.getHumidity} is averaged over several samples
	 *  to yield less noise, while {@link BrickletHumidity.getAnalogValue} gives back raw
	 *  unfiltered analog values. The returned humidity value is calibrated for
	 *  room temperatures, if you use the sensor in extreme cold or extreme
	 *  warm environments, you might want to calculate the humidity from
	 *  the analog value yourself. See the `HIH 5030 datasheet
	 *  <https://github.com/Tinkerforge/humidity-bricklet/raw/master/datasheets/hih-5030.pdf>`__.
	 * 
	 * If you want the analog value periodically, it is recommended to use the 
	 * listener {@link com.tinkerforge.BrickletHumidity.AnalogValueListener} and set the period with
	 * {@link BrickletHumidity.setAnalogValueCallbackPeriod}.
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
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletHumidity.HumidityListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletHumidity.HumidityListener} is only triggered if the humidity has changed since the
	 * last triggering.
	 * 
	 * The default value is 0.
	 */
	public void setHumidityCallbackPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_HUMIDITY_CALLBACK_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickletHumidity.setHumidityCallbackPeriod}.
	 */
	public long getHumidityCallbackPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_HUMIDITY_CALLBACK_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_HUMIDITY_CALLBACK_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletHumidity.AnalogValueListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletHumidity.AnalogValueListener} is only triggered if the analog value has changed since the
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
	 * Returns the period as set by {@link BrickletHumidity.setAnalogValueCallbackPeriod}.
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
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletHumidity.HumidityReachedListener} listener.
	 * 
	 * The following options are possible:
	 * 
	 * \verbatim
	 *  "Option", "Description"
	 * 
	 *  "'x'",    "Listener is turned off"
	 *  "'o'",    "Listener is triggered when the humidity is *outside* the min and max values"
	 *  "'i'",    "Listener is triggered when the humidity is *inside* the min and max values"
	 *  "'<'",    "Listener is triggered when the humidity is smaller than the min value (max is ignored)"
	 *  "'>'",    "Listener is triggered when the humidity is greater than the min value (max is ignored)"
	 * \endverbatim
	 * 
	 * The default value is ('x', 0, 0).
	 */
	public void setHumidityCallbackThreshold(char option, short min, short max)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_HUMIDITY_CALLBACK_THRESHOLD, (short)9);
		bb.put((byte)option);
		bb.putShort((short)min);
		bb.putShort((short)max);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the threshold as set by {@link BrickletHumidity.setHumidityCallbackThreshold}.
	 */
	public HumidityCallbackThreshold getHumidityCallbackThreshold() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_HUMIDITY_CALLBACK_THRESHOLD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_HUMIDITY_CALLBACK_THRESHOLD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		HumidityCallbackThreshold obj = new HumidityCallbackThreshold();
		obj.option = (char)(bb.get());
		obj.min = (bb.getShort());
		obj.max = (bb.getShort());

		return obj;
	}

	/**
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletHumidity.AnalogValueReachedListener} listener.
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
	 * Returns the threshold as set by {@link BrickletHumidity.setAnalogValueCallbackThreshold}.
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
	 *  {@link com.tinkerforge.BrickletHumidity.HumidityReachedListener}, {@link com.tinkerforge.BrickletHumidity.AnalogValueReachedListener}
	 * 
	 * are triggered, if the thresholds
	 * 
	 *  {@link BrickletHumidity.setHumidityCallbackThreshold}, {@link BrickletHumidity.setAnalogValueCallbackThreshold}
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
	 * Returns the debounce period as set by {@link BrickletHumidity.setDebouncePeriod}.
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
		if(o instanceof HumidityListener) {
			listenerObjects[CALLBACK_HUMIDITY] = o;
		} else if(o instanceof AnalogValueListener) {
			listenerObjects[CALLBACK_ANALOG_VALUE] = o;
		} else if(o instanceof HumidityReachedListener) {
			listenerObjects[CALLBACK_HUMIDITY_REACHED] = o;
		} else if(o instanceof AnalogValueReachedListener) {
			listenerObjects[CALLBACK_ANALOG_VALUE_REACHED] = o;
		}
	}
}