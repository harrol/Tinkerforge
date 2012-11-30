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
 * Device for sensing Temperature
 */
public class BrickletTemperature extends Device {
	private final static byte FUNCTION_GET_TEMPERATURE = (byte)1;
	private final static byte FUNCTION_SET_TEMPERATURE_CALLBACK_PERIOD = (byte)2;
	private final static byte FUNCTION_GET_TEMPERATURE_CALLBACK_PERIOD = (byte)3;
	private final static byte FUNCTION_SET_TEMPERATURE_CALLBACK_THRESHOLD = (byte)4;
	private final static byte FUNCTION_GET_TEMPERATURE_CALLBACK_THRESHOLD = (byte)5;
	private final static byte FUNCTION_SET_DEBOUNCE_PERIOD = (byte)6;
	private final static byte FUNCTION_GET_DEBOUNCE_PERIOD = (byte)7;
	private final static byte CALLBACK_TEMPERATURE = (byte)8;
	private final static byte CALLBACK_TEMPERATURE_REACHED = (byte)9;

	public class TemperatureCallbackThreshold {
		public char option;
		public short min;
		public short max;

		public String toString() {
			return "[" + "option = " + option + ", " + "min = " + min + ", " + "max = " + max + "]";
		}
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickletTemperature.setTemperatureCallbackPeriod}. The parameter is the temperature
	 * of the sensor.
	 * 
	 * {@link com.tinkerforge.BrickletTemperature.TemperatureListener} is only triggered if the temperature has changed since the
	 * last triggering.
	 */
	public interface TemperatureListener {
		public void temperature(short temperature);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link BrickletTemperature.setTemperatureCallbackThreshold} is reached.
	 * The parameter is the temperature of the sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link BrickletTemperature.setDebouncePeriod}.
	 */
	public interface TemperatureReachedListener {
		public void temperatureReached(short temperature);
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickletTemperature(String uid) {
		super(uid);

		expectedName = "Temperature Bricklet";

		bindingVersion[0] = 1;
		bindingVersion[1] = 0;
		bindingVersion[2] = 0;

		callbacks[CALLBACK_TEMPERATURE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short temperature = (bb.getShort());

				((TemperatureListener)listenerObjects[CALLBACK_TEMPERATURE]).temperature(temperature);
			}
		};

		callbacks[CALLBACK_TEMPERATURE_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short temperature = (bb.getShort());

				((TemperatureReachedListener)listenerObjects[CALLBACK_TEMPERATURE_REACHED]).temperatureReached(temperature);
			}
		};
	}

	/**
	 * Returns the temperature of the sensor. The value
	 * has a range of -2500 to 8500 and is given in °C/100,
	 * e.g. a value of 4223 means that a temperature of 42.23 °C is measured.
	 * 
	 * If you want to get the temperature periodically, it is recommended 
	 * to use the listener {@link com.tinkerforge.BrickletTemperature.TemperatureListener} and set the period with
	 * {@link BrickletTemperature.setTemperatureCallbackPeriod}.
	 */
	public short getTemperature() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_TEMPERATURE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_TEMPERATURE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short temperature = (bb.getShort());

		return temperature;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletTemperature.TemperatureListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletTemperature.TemperatureListener} is only triggered if the temperature has changed since the
	 * last triggering.
	 * 
	 * The default value is 0.
	 */
	public void setTemperatureCallbackPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_TEMPERATURE_CALLBACK_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickletTemperature.setTemperatureCallbackPeriod}.
	 */
	public long getTemperatureCallbackPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_TEMPERATURE_CALLBACK_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_TEMPERATURE_CALLBACK_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletTemperature.TemperatureReachedListener} listener.
	 * 
	 * The following options are possible:
	 * 
	 * \verbatim
	 *  "Option", "Description"
	 * 
	 *  "'x'",    "Listener is turned off"
	 *  "'o'",    "Listener is triggered when the temperature is *outside* the min and max values"
	 *  "'i'",    "Listener is triggered when the temperature is *inside* the min and max values"
	 *  "'<'",    "Listener is triggered when the temperature is smaller than the min value (max is ignored)"
	 *  "'>'",    "Listener is triggered when the temperature is greater than the min value (max is ignored)"
	 * \endverbatim
	 * 
	 * The default value is ('x', 0, 0).
	 */
	public void setTemperatureCallbackThreshold(char option, short min, short max)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_TEMPERATURE_CALLBACK_THRESHOLD, (short)9);
		bb.put((byte)option);
		bb.putShort((short)min);
		bb.putShort((short)max);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the threshold as set by {@link BrickletTemperature.setTemperatureCallbackThreshold}.
	 */
	public TemperatureCallbackThreshold getTemperatureCallbackThreshold() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_TEMPERATURE_CALLBACK_THRESHOLD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_TEMPERATURE_CALLBACK_THRESHOLD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		TemperatureCallbackThreshold obj = new TemperatureCallbackThreshold();
		obj.option = (char)(bb.get());
		obj.min = (bb.getShort());
		obj.max = (bb.getShort());

		return obj;
	}

	/**
	 * Sets the period in ms with which the threshold listener
	 * 
	 *  {@link com.tinkerforge.BrickletTemperature.TemperatureReachedListener}
	 * 
	 * is triggered, if the threshold
	 * 
	 *  {@link BrickletTemperature.setTemperatureCallbackThreshold}
	 * 
	 * keeps being reached.
	 * 
	 * The default value is 100.
	 */
	public void setDebouncePeriod(long debounce)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_DEBOUNCE_PERIOD, (short)8);
		bb.putInt((int)debounce);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the debounce period as set by {@link BrickletTemperature.setDebouncePeriod}.
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
		if(o instanceof TemperatureListener) {
			listenerObjects[CALLBACK_TEMPERATURE] = o;
		} else if(o instanceof TemperatureReachedListener) {
			listenerObjects[CALLBACK_TEMPERATURE_REACHED] = o;
		}
	}
}