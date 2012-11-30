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
 * Device for sensing air pressure and altitude changes
 */
public class BrickletBarometer extends Device {
	private final static byte FUNCTION_GET_AIR_PRESSURE = (byte)1;
	private final static byte FUNCTION_GET_ALTITUDE = (byte)2;
	private final static byte FUNCTION_SET_AIR_PRESSURE_CALLBACK_PERIOD = (byte)3;
	private final static byte FUNCTION_GET_AIR_PRESSURE_CALLBACK_PERIOD = (byte)4;
	private final static byte FUNCTION_SET_ALTITUDE_CALLBACK_PERIOD = (byte)5;
	private final static byte FUNCTION_GET_ALTITUDE_CALLBACK_PERIOD = (byte)6;
	private final static byte FUNCTION_SET_AIR_PRESSURE_CALLBACK_THRESHOLD = (byte)7;
	private final static byte FUNCTION_GET_AIR_PRESSURE_CALLBACK_THRESHOLD = (byte)8;
	private final static byte FUNCTION_SET_ALTITUDE_CALLBACK_THRESHOLD = (byte)9;
	private final static byte FUNCTION_GET_ALTITUDE_CALLBACK_THRESHOLD = (byte)10;
	private final static byte FUNCTION_SET_DEBOUNCE_PERIOD = (byte)11;
	private final static byte FUNCTION_GET_DEBOUNCE_PERIOD = (byte)12;
	private final static byte FUNCTION_SET_REFERENCE_AIR_PRESSURE = (byte)13;
	private final static byte FUNCTION_GET_CHIP_TEMPERATURE = (byte)14;
	private final static byte CALLBACK_AIR_PRESSURE = (byte)15;
	private final static byte CALLBACK_ALTITUDE = (byte)16;
	private final static byte CALLBACK_AIR_PRESSURE_REACHED = (byte)17;
	private final static byte CALLBACK_ALTITUDE_REACHED = (byte)18;
	private final static byte FUNCTION_GET_REFERENCE_AIR_PRESSURE = (byte)19;

	public class AirPressureCallbackThreshold {
		public char option;
		public int min;
		public int max;

		public String toString() {
			return "[" + "option = " + option + ", " + "min = " + min + ", " + "max = " + max + "]";
		}
	}

	public class AltitudeCallbackThreshold {
		public char option;
		public int min;
		public int max;

		public String toString() {
			return "[" + "option = " + option + ", " + "min = " + min + ", " + "max = " + max + "]";
		}
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickletBarometer.setAirPressureCallbackPeriod}. The parameter is the air pressure of the
	 * air pressure sensor.
	 * 
	 * {@link com.tinkerforge.BrickletBarometer.AirPressureListener} is only triggered if the air pressure has changed since the
	 * last triggering.
	 */
	public interface AirPressureListener {
		public void airPressure(int airPressure);
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickletBarometer.setAltitudeCallbackPeriod}. The parameter is the altitude of the
	 * air pressure sensor.
	 * 
	 * {@link com.tinkerforge.BrickletBarometer.AltitudeListener} is only triggered if the altitude has changed since the
	 * last triggering.
	 */
	public interface AltitudeListener {
		public void altitude(int altitude);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link BrickletBarometer.setAirPressureCallbackThreshold} is reached.
	 * The parameter is the air pressure of the air pressure sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link BrickletBarometer.setDebouncePeriod}.
	 */
	public interface AirPressureReachedListener {
		public void airPressureReached(int airPressure);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link BrickletBarometer.setAltitudeCallbackThreshold} is reached.
	 * The parameter is the altitude of the air pressure sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link BrickletBarometer.setDebouncePeriod}.
	 */
	public interface AltitudeReachedListener {
		public void altitudeReached(int altitude);
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickletBarometer(String uid) {
		super(uid);

		expectedName = "Barometer Bricklet";

		bindingVersion[0] = 1;
		bindingVersion[1] = 1;
		bindingVersion[2] = 0;

		callbacks[CALLBACK_AIR_PRESSURE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int airPressure = (bb.getInt());

				((AirPressureListener)listenerObjects[CALLBACK_AIR_PRESSURE]).airPressure(airPressure);
			}
		};

		callbacks[CALLBACK_ALTITUDE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int altitude = (bb.getInt());

				((AltitudeListener)listenerObjects[CALLBACK_ALTITUDE]).altitude(altitude);
			}
		};

		callbacks[CALLBACK_AIR_PRESSURE_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int airPressure = (bb.getInt());

				((AirPressureReachedListener)listenerObjects[CALLBACK_AIR_PRESSURE_REACHED]).airPressureReached(airPressure);
			}
		};

		callbacks[CALLBACK_ALTITUDE_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int altitude = (bb.getInt());

				((AltitudeReachedListener)listenerObjects[CALLBACK_ALTITUDE_REACHED]).altitudeReached(altitude);
			}
		};
	}

	/**
	 * Returns the air pressure of the air pressure sensor. The value
	 * has a range of 10000 to 1200000 and is given in mbar/1000, i.e. a value
	 * of 1001092 means that an air pressure of 1001.092 mbar is measured.
	 * 
	 * If you want to get the air pressure periodically, it is recommended to use the
	 * listener {@link com.tinkerforge.BrickletBarometer.AirPressureListener} and set the period with
	 * {@link BrickletBarometer.setAirPressureCallbackPeriod}.
	 */
	public int getAirPressure() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_AIR_PRESSURE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_AIR_PRESSURE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int airPressure = (bb.getInt());

		return airPressure;
	}

	/**
	 * Returns the relative altitude of the air pressure sensor. The value is given in
	 * cm and is caluclated based on the difference between the current air pressure
	 * and the reference air pressure that can be set with {@link BrickletBarometer.setReferenceAirPressure}.
	 * 
	 * If you want to get the altitude periodically, it is recommended to use the
	 * listener {@link com.tinkerforge.BrickletBarometer.AltitudeListener} and set the period with
	 * {@link BrickletBarometer.setAltitudeCallbackPeriod}.
	 */
	public int getAltitude() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ALTITUDE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ALTITUDE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int altitude = (bb.getInt());

		return altitude;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletBarometer.AirPressureListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletBarometer.AirPressureListener} is only triggered if the air pressure has changed since the
	 * last triggering.
	 * 
	 * The default value is 0.
	 */
	public void setAirPressureCallbackPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_AIR_PRESSURE_CALLBACK_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickletBarometer.setAirPressureCallbackPeriod}.
	 */
	public long getAirPressureCallbackPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_AIR_PRESSURE_CALLBACK_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_AIR_PRESSURE_CALLBACK_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletBarometer.AltitudeListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletBarometer.AltitudeListener} is only triggered if the altitude has changed since the
	 * last triggering.
	 * 
	 * The default value is 0.
	 */
	public void setAltitudeCallbackPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_ALTITUDE_CALLBACK_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickletBarometer.setAltitudeCallbackPeriod}.
	 */
	public long getAltitudeCallbackPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ALTITUDE_CALLBACK_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ALTITUDE_CALLBACK_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletBarometer.AirPressureReachedListener} listener.
	 * 
	 * The following options are possible:
	 * 
	 * \verbatim
	 *  "Option", "Description"
	 * 
	 *  "'x'",    "Listener is turned off"
	 *  "'o'",    "Listener is triggered when the air pressure is *outside* the min and max values"
	 *  "'i'",    "Listener is triggered when the air pressure is *inside* the min and max values"
	 *  "'<'",    "Listener is triggered when the air pressure is smaller than the min value (max is ignored)"
	 *  "'>'",    "Listener is triggered when the air pressure is greater than the min value (max is ignored)"
	 * \endverbatim
	 * 
	 * The default value is ('x', 0, 0).
	 */
	public void setAirPressureCallbackThreshold(char option, int min, int max)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_AIR_PRESSURE_CALLBACK_THRESHOLD, (short)13);
		bb.put((byte)option);
		bb.putInt((int)min);
		bb.putInt((int)max);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the threshold as set by {@link BrickletBarometer.setAirPressureCallbackThreshold}.
	 */
	public AirPressureCallbackThreshold getAirPressureCallbackThreshold() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_AIR_PRESSURE_CALLBACK_THRESHOLD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_AIR_PRESSURE_CALLBACK_THRESHOLD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		AirPressureCallbackThreshold obj = new AirPressureCallbackThreshold();
		obj.option = (char)(bb.get());
		obj.min = (bb.getInt());
		obj.max = (bb.getInt());

		return obj;
	}

	/**
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletBarometer.AltitudeReachedListener} listener.
	 * 
	 * The following options are possible:
	 * 
	 * \verbatim
	 *  "Option", "Description"
	 * 
	 *  "'x'",    "Listener is turned off"
	 *  "'o'",    "Listener is triggered when the altitude is *outside* the min and max values"
	 *  "'i'",    "Listener is triggered when the altitude is *inside* the min and max values"
	 *  "'<'",    "Listener is triggered when the altitude is smaller than the min value (max is ignored)"
	 *  "'>'",    "Listener is triggered when the altitude is greater than the min value (max is ignored)"
	 * \endverbatim
	 * 
	 * The default value is ('x', 0, 0).
	 */
	public void setAltitudeCallbackThreshold(char option, int min, int max)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_ALTITUDE_CALLBACK_THRESHOLD, (short)13);
		bb.put((byte)option);
		bb.putInt((int)min);
		bb.putInt((int)max);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the threshold as set by {@link BrickletBarometer.setAltitudeCallbackThreshold}.
	 */
	public AltitudeCallbackThreshold getAltitudeCallbackThreshold() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ALTITUDE_CALLBACK_THRESHOLD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ALTITUDE_CALLBACK_THRESHOLD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		AltitudeCallbackThreshold obj = new AltitudeCallbackThreshold();
		obj.option = (char)(bb.get());
		obj.min = (bb.getInt());
		obj.max = (bb.getInt());

		return obj;
	}

	/**
	 * Sets the period in ms with which the threshold listeners
	 * 
	 *  {@link com.tinkerforge.BrickletBarometer.AirPressureReachedListener}, {@link com.tinkerforge.BrickletBarometer.AltitudeReachedListener}
	 * 
	 * are triggered, if the thresholds
	 * 
	 *  {@link BrickletBarometer.setAirPressureCallbackThreshold}, {@link BrickletBarometer.setAltitudeCallbackThreshold}
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
	 * Returns the debounce period as set by {@link BrickletBarometer.setDebouncePeriod}.
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
	 * Sets the reference air pressure in mbar/1000 for the altitude calculation.
	 * Setting the reference to the current air pressure results in a calculated
	 * altitude of 0cm. Passing 0 is a shortcut for passing the current air pressure as
	 * reference.
	 * 
	 * Well known reference values are the Q codes
	 * `QNH <http://en.wikipedia.org/wiki/QNH>`__ and
	 * `QFE <http://en.wikipedia.org/wiki/Mean_sea_level_pressure#Mean_sea_level_pressure>`__
	 * used in aviation.
	 * 
	 * The default value is 1013.25mbar.
	 * 
	 * .. versionadded:: 1.1.0~(Plugin)
	 */
	public void setReferenceAirPressure(int airPressure)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_REFERENCE_AIR_PRESSURE, (short)8);
		bb.putInt((int)airPressure);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the temperature of the air pressure sensor. The value
	 * has a range of -4000 to 8500 and is given in °C/100, i.e. a value
	 * of 2007 means that a temperature of 20.07 °C is measured.
	 * 
	 * This temperature is used internally for temperature compensation of the air
	 * pressure measurement. It is not as accurate as the temperature measured by the
	 * :ref:`temperature_bricklet` or the :ref:`temperature_ir_bricklet`.
	 */
	public short getChipTemperature() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_CHIP_TEMPERATURE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CHIP_TEMPERATURE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short temperature = (bb.getShort());

		return temperature;
	}

	/**
	 * Returns the reference air pressure as set by {@link BrickletBarometer.setReferenceAirPressure}.
	 * 
	 * .. versionadded:: 1.1.0~(Plugin)
	 */
	public int getReferenceAirPressure() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_REFERENCE_AIR_PRESSURE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_REFERENCE_AIR_PRESSURE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int airPressure = (bb.getInt());

		return airPressure;
	}

	/**
	 * Registers a listener object.
	 */
	public void addListener(Object o) {
		if(o instanceof AirPressureListener) {
			listenerObjects[CALLBACK_AIR_PRESSURE] = o;
		} else if(o instanceof AltitudeListener) {
			listenerObjects[CALLBACK_ALTITUDE] = o;
		} else if(o instanceof AirPressureReachedListener) {
			listenerObjects[CALLBACK_AIR_PRESSURE_REACHED] = o;
		} else if(o instanceof AltitudeReachedListener) {
			listenerObjects[CALLBACK_ALTITUDE_REACHED] = o;
		}
	}
}