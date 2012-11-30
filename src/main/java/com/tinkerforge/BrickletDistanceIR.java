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
 * Device for sensing distance via infrared
 */
public class BrickletDistanceIR extends Device {
	private final static byte FUNCTION_GET_DISTANCE = (byte)1;
	private final static byte FUNCTION_GET_ANALOG_VALUE = (byte)2;
	private final static byte FUNCTION_SET_SAMPLING_POINT = (byte)3;
	private final static byte FUNCTION_GET_SAMPLING_POINT = (byte)4;
	private final static byte FUNCTION_SET_DISTANCE_CALLBACK_PERIOD = (byte)5;
	private final static byte FUNCTION_GET_DISTANCE_CALLBACK_PERIOD = (byte)6;
	private final static byte FUNCTION_SET_ANALOG_VALUE_CALLBACK_PERIOD = (byte)7;
	private final static byte FUNCTION_GET_ANALOG_VALUE_CALLBACK_PERIOD = (byte)8;
	private final static byte FUNCTION_SET_DISTANCE_CALLBACK_THRESHOLD = (byte)9;
	private final static byte FUNCTION_GET_DISTANCE_CALLBACK_THRESHOLD = (byte)10;
	private final static byte FUNCTION_SET_ANALOG_VALUE_CALLBACK_THRESHOLD = (byte)11;
	private final static byte FUNCTION_GET_ANALOG_VALUE_CALLBACK_THRESHOLD = (byte)12;
	private final static byte FUNCTION_SET_DEBOUNCE_PERIOD = (byte)13;
	private final static byte FUNCTION_GET_DEBOUNCE_PERIOD = (byte)14;
	private final static byte CALLBACK_DISTANCE = (byte)15;
	private final static byte CALLBACK_ANALOG_VALUE = (byte)16;
	private final static byte CALLBACK_DISTANCE_REACHED = (byte)17;
	private final static byte CALLBACK_ANALOG_VALUE_REACHED = (byte)18;

	public class DistanceCallbackThreshold {
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
	 * {@link BrickletDistanceIR.setDistanceCallbackPeriod}. The parameter is the distance of the
	 * sensor.
	 * 
	 * {@link com.tinkerforge.BrickletDistanceIR.DistanceListener} is only triggered if the distance has changed since the
	 * last triggering.
	 */
	public interface DistanceListener {
		public void distance(int distance);
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickletDistanceIR.setAnalogValueCallbackPeriod}. The parameter is the analog value of the
	 * sensor.
	 * 
	 * {@link com.tinkerforge.BrickletDistanceIR.AnalogValueListener} is only triggered if the analog value has changed since the
	 * last triggering.
	 */
	public interface AnalogValueListener {
		public void analogValue(int value);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link BrickletDistanceIR.setDistanceCallbackThreshold} is reached.
	 * The parameter is the distance of the sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link BrickletDistanceIR.setDebouncePeriod}.
	 */
	public interface DistanceReachedListener {
		public void distanceReached(int distance);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link BrickletDistanceIR.setAnalogValueCallbackThreshold} is reached.
	 * The parameter is the analog value of the sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link BrickletDistanceIR.setDebouncePeriod}.
	 */
	public interface AnalogValueReachedListener {
		public void analogValueReached(int value);
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickletDistanceIR(String uid) {
		super(uid);

		expectedName = "Distance IR Bricklet";

		bindingVersion[0] = 1;
		bindingVersion[1] = 0;
		bindingVersion[2] = 0;

		callbacks[CALLBACK_DISTANCE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int distance = IPConnection.unsignedShort(bb.getShort());

				((DistanceListener)listenerObjects[CALLBACK_DISTANCE]).distance(distance);
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

		callbacks[CALLBACK_DISTANCE_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int distance = IPConnection.unsignedShort(bb.getShort());

				((DistanceReachedListener)listenerObjects[CALLBACK_DISTANCE_REACHED]).distanceReached(distance);
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
	 * Returns the distance measured by the sensor. The value is in mm and possible
	 * distance ranges are 40 to 300, 100 to 800 and 200 to 1500, depending on the
	 * selected IR sensor.
	 * 
	 * If you want to get the distance periodically, it is recommended to use the
	 * listener {@link com.tinkerforge.BrickletDistanceIR.DistanceListener} and set the period with
	 * {@link BrickletDistanceIR.setDistanceCallbackPeriod}.
	 */
	public int getDistance() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_DISTANCE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_DISTANCE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int distance = IPConnection.unsignedShort(bb.getShort());

		return distance;
	}

	/**
	 * Returns the value as read by a 12-bit analog-to-digital converter.
	 * The value is between 0 and 4095.
	 * 
	 * \note
	 *  The value returned by {@link BrickletDistanceIR.getDistance} is averaged over several samples
	 *  to yield less noise, while {@link BrickletDistanceIR.getAnalogValue} gives back raw
	 *  unfiltered analog values. The only reason to use {@link BrickletDistanceIR.getAnalogValue} is,
	 *  if you need the full resolution of the analog-to-digital converter.
	 * 
	 * If you want the analog value periodically, it is recommended to use the 
	 * listener {@link com.tinkerforge.BrickletDistanceIR.AnalogValueListener} and set the period with
	 * {@link BrickletDistanceIR.setAnalogValueCallbackPeriod}.
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
	 * Sets a sampling point value to a specific position of the lookup table.
	 * The lookup table comprises 128 equidistant analog values with
	 * corresponding distances.
	 * 
	 * If you measure a distance of 50cm at the analog value 2048, you
	 * should call this function with (64, 5000). The utilized analog-to-digital
	 * converter has a resolution of 12 bit. With 128 sampling points on the
	 * whole range, this means that every sampling point has a size of 32
	 * analog values. Thus the analog value 2048 has the corresponding sampling
	 * point 64 = 2048/32.
	 * 
	 * Sampling points are saved on the EEPROM of the Distance IR Bricklet and
	 * loaded again on startup.
	 * 
	 * \note
	 *  An easy way to calibrate the sampling points of the Distance IR Bricklet is
	 *  implemented in the Brick Viewer. If you want to calibrate your Bricklet it is
	 *  highly recommended to use this implementation.
	 */
	public void setSamplingPoint(short position, int distance)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_SAMPLING_POINT, (short)7);
		bb.put((byte)position);
		bb.putShort((short)distance);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the distance to a sampling point position as set by
	 * {@link BrickletDistanceIR.setSamplingPoint}.
	 */
	public int getSamplingPoint(short position) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_SAMPLING_POINT, (short)5);
		bb.put((byte)position);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_SAMPLING_POINT);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int distance = IPConnection.unsignedShort(bb.getShort());

		return distance;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletDistanceIR.DistanceListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletDistanceIR.DistanceListener} is only triggered if the distance has changed since the
	 * last triggering.
	 * 
	 * The default value is 0.
	 */
	public void setDistanceCallbackPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_DISTANCE_CALLBACK_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickletDistanceIR.setDistanceCallbackPeriod}.
	 */
	public long getDistanceCallbackPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_DISTANCE_CALLBACK_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_DISTANCE_CALLBACK_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletDistanceIR.AnalogValueListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletDistanceIR.AnalogValueListener} is only triggered if the analog value has changed since the
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
	 * Returns the period as set by {@link BrickletDistanceIR.setAnalogValueCallbackPeriod}.
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
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletDistanceIR.DistanceReachedListener} listener.
	 * 
	 * The following options are possible:
	 * 
	 * \verbatim
	 *  "Option", "Description"
	 * 
	 *  "'x'",    "Listener is turned off"
	 *  "'o'",    "Listener is triggered when the distance is *outside* the min and max values"
	 *  "'i'",    "Listener is triggered when the distance is *inside* the min and max values"
	 *  "'<'",    "Listener is triggered when the distance is smaller than the min value (max is ignored)"
	 *  "'>'",    "Listener is triggered when the distance is greater than the min value (max is ignored)"
	 * \endverbatim
	 * 
	 * The default value is ('x', 0, 0).
	 */
	public void setDistanceCallbackThreshold(char option, short min, short max)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_DISTANCE_CALLBACK_THRESHOLD, (short)9);
		bb.put((byte)option);
		bb.putShort((short)min);
		bb.putShort((short)max);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the threshold as set by {@link BrickletDistanceIR.setDistanceCallbackThreshold}.
	 */
	public DistanceCallbackThreshold getDistanceCallbackThreshold() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_DISTANCE_CALLBACK_THRESHOLD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_DISTANCE_CALLBACK_THRESHOLD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		DistanceCallbackThreshold obj = new DistanceCallbackThreshold();
		obj.option = (char)(bb.get());
		obj.min = (bb.getShort());
		obj.max = (bb.getShort());

		return obj;
	}

	/**
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletDistanceIR.AnalogValueReachedListener} listener.
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
	 * Returns the threshold as set by {@link BrickletDistanceIR.setAnalogValueCallbackThreshold}.
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
	 *  {@link com.tinkerforge.BrickletDistanceIR.DistanceReachedListener}, {@link com.tinkerforge.BrickletDistanceIR.AnalogValueReachedListener}
	 * 
	 * are triggered, if the thresholds
	 * 
	 *  {@link BrickletDistanceIR.setDistanceCallbackThreshold}, {@link BrickletDistanceIR.setAnalogValueCallbackThreshold}
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
	 * Returns the debounce period as set by {@link BrickletDistanceIR.setDebouncePeriod}.
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
		if(o instanceof DistanceListener) {
			listenerObjects[CALLBACK_DISTANCE] = o;
		} else if(o instanceof AnalogValueListener) {
			listenerObjects[CALLBACK_ANALOG_VALUE] = o;
		} else if(o instanceof DistanceReachedListener) {
			listenerObjects[CALLBACK_DISTANCE_REACHED] = o;
		} else if(o instanceof AnalogValueReachedListener) {
			listenerObjects[CALLBACK_ANALOG_VALUE_REACHED] = o;
		}
	}
}