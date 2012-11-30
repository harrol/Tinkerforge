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
 * Device for non-contact temperature sensing
 */
public class BrickletTemperatureIR extends Device {
	private final static byte FUNCTION_GET_AMBIENT_TEMPERATURE = (byte)1;
	private final static byte FUNCTION_GET_OBJECT_TEMPERATURE = (byte)2;
	private final static byte FUNCTION_SET_EMISSIVITY = (byte)3;
	private final static byte FUNCTION_GET_EMISSIVITY = (byte)4;
	private final static byte FUNCTION_SET_AMBIENT_TEMPERATURE_CALLBACK_PERIOD = (byte)5;
	private final static byte FUNCTION_GET_AMBIENT_TEMPERATURE_CALLBACK_PERIOD = (byte)6;
	private final static byte FUNCTION_SET_OBJECT_TEMPERATURE_CALLBACK_PERIOD = (byte)7;
	private final static byte FUNCTION_GET_OBJECT_TEMPERATURE_CALLBACK_PERIOD = (byte)8;
	private final static byte FUNCTION_SET_AMBIENT_TEMPERATURE_CALLBACK_THRESHOLD = (byte)9;
	private final static byte FUNCTION_GET_AMBIENT_TEMPERATURE_CALLBACK_THRESHOLD = (byte)10;
	private final static byte FUNCTION_SET_OBJECT_TEMPERATURE_CALLBACK_THRESHOLD = (byte)11;
	private final static byte FUNCTION_GET_OBJECT_TEMPERATURE_CALLBACK_THRESHOLD = (byte)12;
	private final static byte FUNCTION_SET_DEBOUNCE_PERIOD = (byte)13;
	private final static byte FUNCTION_GET_DEBOUNCE_PERIOD = (byte)14;
	private final static byte CALLBACK_AMBIENT_TEMPERATURE = (byte)15;
	private final static byte CALLBACK_OBJECT_TEMPERATURE = (byte)16;
	private final static byte CALLBACK_AMBIENT_TEMPERATURE_REACHED = (byte)17;
	private final static byte CALLBACK_OBJECT_TEMPERATURE_REACHED = (byte)18;

	public class AmbientTemperatureCallbackThreshold {
		public char option;
		public short min;
		public short max;

		public String toString() {
			return "[" + "option = " + option + ", " + "min = " + min + ", " + "max = " + max + "]";
		}
	}

	public class ObjectTemperatureCallbackThreshold {
		public char option;
		public short min;
		public short max;

		public String toString() {
			return "[" + "option = " + option + ", " + "min = " + min + ", " + "max = " + max + "]";
		}
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickletTemperatureIR.setAmbientTemperatureCallbackPeriod}. The parameter is the ambient
	 * temperature of the sensor.
	 * 
	 * {@link com.tinkerforge.BrickletTemperatureIR.AmbientTemperatureListener} is only triggered if the ambient temperature
	 * has changed since the last triggering.
	 */
	public interface AmbientTemperatureListener {
		public void ambientTemperature(short temperature);
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickletTemperatureIR.setObjectTemperatureCallbackPeriod}. The parameter is the object
	 * temperature of the sensor.
	 * 
	 * {@link com.tinkerforge.BrickletTemperatureIR.ObjectTemperatureListener} is only triggered if the object temperature
	 * has changed since the last triggering.
	 */
	public interface ObjectTemperatureListener {
		public void objectTemperature(short temperature);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link BrickletTemperatureIR.setAmbientTemperatureCallbackThreshold} is reached.
	 * The parameter is the ambient temperature of the sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link BrickletTemperatureIR.setDebouncePeriod}.
	 */
	public interface AmbientTemperatureReachedListener {
		public void ambientTemperatureReached(short temperature);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link BrickletTemperatureIR.setObjectTemperatureCallbackThreshold} is reached.
	 * The parameter is the object temperature of the sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link BrickletTemperatureIR.setDebouncePeriod}.
	 */
	public interface ObjectTemperatureReachedListener {
		public void objectTemperatureReached(short temperature);
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickletTemperatureIR(String uid) {
		super(uid);

		expectedName = "Temperature IR Bricklet";

		bindingVersion[0] = 1;
		bindingVersion[1] = 0;
		bindingVersion[2] = 0;

		callbacks[CALLBACK_AMBIENT_TEMPERATURE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short temperature = (bb.getShort());

				((AmbientTemperatureListener)listenerObjects[CALLBACK_AMBIENT_TEMPERATURE]).ambientTemperature(temperature);
			}
		};

		callbacks[CALLBACK_OBJECT_TEMPERATURE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short temperature = (bb.getShort());

				((ObjectTemperatureListener)listenerObjects[CALLBACK_OBJECT_TEMPERATURE]).objectTemperature(temperature);
			}
		};

		callbacks[CALLBACK_AMBIENT_TEMPERATURE_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short temperature = (bb.getShort());

				((AmbientTemperatureReachedListener)listenerObjects[CALLBACK_AMBIENT_TEMPERATURE_REACHED]).ambientTemperatureReached(temperature);
			}
		};

		callbacks[CALLBACK_OBJECT_TEMPERATURE_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short temperature = (bb.getShort());

				((ObjectTemperatureReachedListener)listenerObjects[CALLBACK_OBJECT_TEMPERATURE_REACHED]).objectTemperatureReached(temperature);
			}
		};
	}

	/**
	 * Returns the ambient temperature of the sensor. The value
	 * has a range of -400 to 1250 and is given in °C/10,
	 * e.g. a value of 423 means that an ambient temperature of 42.3 °C is 
	 * measured.
	 * 
	 * If you want to get the ambient temperature periodically, it is recommended 
	 * to use the listener {@link com.tinkerforge.BrickletTemperatureIR.AmbientTemperatureListener} and set the period with
	 * {@link BrickletTemperatureIR.setAmbientTemperatureCallbackPeriod}.
	 */
	public short getAmbientTemperature() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_AMBIENT_TEMPERATURE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_AMBIENT_TEMPERATURE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short temperature = (bb.getShort());

		return temperature;
	}

	/**
	 * Returns the object temperature of the sensor, i.e. the temperature
	 * of the surface of the object the sensor is aimed at. The value
	 * has a range of -700 to 3800 and is given in °C/10,
	 * e.g. a value of 3001 means that a temperature of 300.1 °C is measured
	 * on the surface of the object.
	 * 
	 * The temperature of different materials is dependent on their `emissivity 
	 * <http://en.wikipedia.org/wiki/Emissivity>`__. The emissivity of the material
	 * can be set with {@link BrickletTemperatureIR.setEmissivity}.
	 * 
	 * If you want to get the object temperature periodically, it is recommended 
	 * to use the listener {@link com.tinkerforge.BrickletTemperatureIR.ObjectTemperatureListener} and set the period with
	 * {@link BrickletTemperatureIR.setObjectTemperatureCallbackPeriod}.
	 */
	public short getObjectTemperature() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_OBJECT_TEMPERATURE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_OBJECT_TEMPERATURE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short temperature = (bb.getShort());

		return temperature;
	}

	/**
	 * Sets the `emissivity <http://en.wikipedia.org/wiki/Emissivity>`__ that is
	 * used to calculate the surface temperature as returned by 
	 * {@link BrickletTemperatureIR.getObjectTemperature}.
	 * 
	 * The emissivity is usually given as a value between 0.0 and 1.0. A list of
	 * emissivities of different materials can be found 
	 * `here <http://www.infrared-thermography.com/material.htm>`__.
	 * 
	 * The parameter of {@link BrickletTemperatureIR.setEmissivity} has to be given with a factor of
	 * 65535 (16-bit). For example: An emissivity of 0.1 can be set with the
	 * value 6553, an emissivity of 0.5 with the value 32767 and so on.
	 * 
	 * \note
	 *  If you need a precise measurement for the object temperature, it is
	 *  absolutely crucial that you also provide a precise emissivity.
	 * 
	 * The default emissivity is 1.0 (value of 65535) and the minimum emissivity the
	 * sensor can handle is 0.1 (value of 6553).
	 */
	public void setEmissivity(int emissivity)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_EMISSIVITY, (short)6);
		bb.putShort((short)emissivity);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the emissivity as set by {@link BrickletTemperatureIR.setEmissivity}.
	 */
	public int getEmissivity() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_EMISSIVITY, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_EMISSIVITY);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int emissivity = IPConnection.unsignedShort(bb.getShort());

		return emissivity;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletTemperatureIR.AmbientTemperatureListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletTemperatureIR.AmbientTemperatureListener} is only triggered if the temperature has changed since the
	 * last triggering.
	 * 
	 * The default value is 0.
	 */
	public void setAmbientTemperatureCallbackPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_AMBIENT_TEMPERATURE_CALLBACK_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickletTemperatureIR.setAmbientTemperatureCallbackPeriod}.
	 */
	public long getAmbientTemperatureCallbackPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_AMBIENT_TEMPERATURE_CALLBACK_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_AMBIENT_TEMPERATURE_CALLBACK_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletTemperatureIR.ObjectTemperatureListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletTemperatureIR.ObjectTemperatureListener} is only triggered if the temperature has changed since the
	 * last triggering.
	 * 
	 * The default value is 0.
	 */
	public void setObjectTemperatureCallbackPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_OBJECT_TEMPERATURE_CALLBACK_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickletTemperatureIR.setObjectTemperatureCallbackPeriod}.
	 */
	public long getObjectTemperatureCallbackPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_OBJECT_TEMPERATURE_CALLBACK_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_OBJECT_TEMPERATURE_CALLBACK_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletTemperatureIR.AmbientTemperatureReachedListener} listener.
	 * 
	 * The following options are possible:
	 * 
	 * \verbatim
	 *  "Option", "Description"
	 * 
	 *  "'x'",    "Listener is turned off"
	 *  "'o'",    "Listener is triggered when the ambient temperature is *outside* the min and max values"
	 *  "'i'",    "Listener is triggered when the ambient temperature is *inside* the min and max values"
	 *  "'<'",    "Listener is triggered when the ambient temperature is smaller than the min value (max is ignored)"
	 *  "'>'",    "Listener is triggered when the ambient temperature is greater than the min value (max is ignored)"
	 * \endverbatim
	 * 
	 * The default value is ('x', 0, 0).
	 */
	public void setAmbientTemperatureCallbackThreshold(char option, short min, short max)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_AMBIENT_TEMPERATURE_CALLBACK_THRESHOLD, (short)9);
		bb.put((byte)option);
		bb.putShort((short)min);
		bb.putShort((short)max);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the threshold as set by {@link BrickletTemperatureIR.setAmbientTemperatureCallbackThreshold}.
	 */
	public AmbientTemperatureCallbackThreshold getAmbientTemperatureCallbackThreshold() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_AMBIENT_TEMPERATURE_CALLBACK_THRESHOLD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_AMBIENT_TEMPERATURE_CALLBACK_THRESHOLD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		AmbientTemperatureCallbackThreshold obj = new AmbientTemperatureCallbackThreshold();
		obj.option = (char)(bb.get());
		obj.min = (bb.getShort());
		obj.max = (bb.getShort());

		return obj;
	}

	/**
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletTemperatureIR.ObjectTemperatureReachedListener} listener.
	 * 
	 * The following options are possible:
	 * 
	 * \verbatim
	 *  "Option", "Description"
	 * 
	 *  "'x'",    "Listener is turned off"
	 *  "'o'",    "Listener is triggered when the object temperature is *outside* the min and max values"
	 *  "'i'",    "Listener is triggered when the object temperature is *inside* the min and max values"
	 *  "'<'",    "Listener is triggered when the object temperature is smaller than the min value (max is ignored)"
	 *  "'>'",    "Listener is triggered when the object temperature is greater than the min value (max is ignored)"
	 * \endverbatim
	 * 
	 * The default value is ('x', 0, 0).
	 */
	public void setObjectTemperatureCallbackThreshold(char option, short min, short max)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_OBJECT_TEMPERATURE_CALLBACK_THRESHOLD, (short)9);
		bb.put((byte)option);
		bb.putShort((short)min);
		bb.putShort((short)max);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the threshold as set by {@link BrickletTemperatureIR.setObjectTemperatureCallbackThreshold}.
	 */
	public ObjectTemperatureCallbackThreshold getObjectTemperatureCallbackThreshold() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_OBJECT_TEMPERATURE_CALLBACK_THRESHOLD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_OBJECT_TEMPERATURE_CALLBACK_THRESHOLD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		ObjectTemperatureCallbackThreshold obj = new ObjectTemperatureCallbackThreshold();
		obj.option = (char)(bb.get());
		obj.min = (bb.getShort());
		obj.max = (bb.getShort());

		return obj;
	}

	/**
	 * Sets the period in ms with which the threshold listeners
	 * 
	 *  {@link com.tinkerforge.BrickletTemperatureIR.AmbientTemperatureReachedListener}, {@link com.tinkerforge.BrickletTemperatureIR.ObjectTemperatureReachedListener}
	 * 
	 * are triggered, if the thresholds
	 * 
	 *  {@link BrickletTemperatureIR.setAmbientTemperatureCallbackThreshold}, {@link BrickletTemperatureIR.setObjectTemperatureCallbackThreshold}
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
	 * Returns the debounce period as set by {@link BrickletTemperatureIR.setDebouncePeriod}.
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
		if(o instanceof AmbientTemperatureListener) {
			listenerObjects[CALLBACK_AMBIENT_TEMPERATURE] = o;
		} else if(o instanceof ObjectTemperatureListener) {
			listenerObjects[CALLBACK_OBJECT_TEMPERATURE] = o;
		} else if(o instanceof AmbientTemperatureReachedListener) {
			listenerObjects[CALLBACK_AMBIENT_TEMPERATURE_REACHED] = o;
		} else if(o instanceof ObjectTemperatureReachedListener) {
			listenerObjects[CALLBACK_OBJECT_TEMPERATURE_REACHED] = o;
		}
	}
}