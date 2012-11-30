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
 * Device for sensing Ambient Light
 */
public class BrickletAmbientLight extends Device {
	private final static byte FUNCTION_GET_ILLUMINANCE = (byte)1;
	private final static byte FUNCTION_GET_ANALOG_VALUE = (byte)2;
	private final static byte FUNCTION_SET_ILLUMINANCE_CALLBACK_PERIOD = (byte)3;
	private final static byte FUNCTION_GET_ILLUMINANCE_CALLBACK_PERIOD = (byte)4;
	private final static byte FUNCTION_SET_ANALOG_VALUE_CALLBACK_PERIOD = (byte)5;
	private final static byte FUNCTION_GET_ANALOG_VALUE_CALLBACK_PERIOD = (byte)6;
	private final static byte FUNCTION_SET_ILLUMINANCE_CALLBACK_THRESHOLD = (byte)7;
	private final static byte FUNCTION_GET_ILLUMINANCE_CALLBACK_THRESHOLD = (byte)8;
	private final static byte FUNCTION_SET_ANALOG_VALUE_CALLBACK_THRESHOLD = (byte)9;
	private final static byte FUNCTION_GET_ANALOG_VALUE_CALLBACK_THRESHOLD = (byte)10;
	private final static byte FUNCTION_SET_DEBOUNCE_PERIOD = (byte)11;
	private final static byte FUNCTION_GET_DEBOUNCE_PERIOD = (byte)12;
	private final static byte CALLBACK_ILLUMINANCE = (byte)13;
	private final static byte CALLBACK_ANALOG_VALUE = (byte)14;
	private final static byte CALLBACK_ILLUMINANCE_REACHED = (byte)15;
	private final static byte CALLBACK_ANALOG_VALUE_REACHED = (byte)16;

	public class IlluminanceCallbackThreshold {
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
	 * {@link BrickletAmbientLight.setIlluminanceCallbackPeriod}. The parameter is the illuminance of the
	 * ambient light sensor.
	 * 
	 * {@link com.tinkerforge.BrickletAmbientLight.IlluminanceListener} is only triggered if the illuminance has changed since the
	 * last triggering.
	 */
	public interface IlluminanceListener {
		public void illuminance(int illuminance);
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickletAmbientLight.setAnalogValueCallbackPeriod}. The parameter is the analog value of the
	 * ambient light sensor.
	 * 
	 * {@link com.tinkerforge.BrickletAmbientLight.AnalogValueListener} is only triggered if the analog value has changed since the
	 * last triggering.
	 */
	public interface AnalogValueListener {
		public void analogValue(int value);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link BrickletAmbientLight.setIlluminanceCallbackThreshold} is reached.
	 * The parameter is the illuminance of the ambient light sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link BrickletAmbientLight.setDebouncePeriod}.
	 */
	public interface IlluminanceReachedListener {
		public void illuminanceReached(int illuminance);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link BrickletAmbientLight.setAnalogValueCallbackThreshold} is reached.
	 * The parameter is the analog value of the ambient light sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link BrickletAmbientLight.setDebouncePeriod}.
	 */
	public interface AnalogValueReachedListener {
		public void analogValueReached(int value);
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickletAmbientLight(String uid) {
		super(uid);

		expectedName = "Ambient Light Bricklet";

		bindingVersion[0] = 1;
		bindingVersion[1] = 0;
		bindingVersion[2] = 0;

		callbacks[CALLBACK_ILLUMINANCE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int illuminance = IPConnection.unsignedShort(bb.getShort());

				((IlluminanceListener)listenerObjects[CALLBACK_ILLUMINANCE]).illuminance(illuminance);
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

		callbacks[CALLBACK_ILLUMINANCE_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int illuminance = IPConnection.unsignedShort(bb.getShort());

				((IlluminanceReachedListener)listenerObjects[CALLBACK_ILLUMINANCE_REACHED]).illuminanceReached(illuminance);
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
	 * Returns the illuminance of the ambient light sensor. The value
	 * has a range of 0 to 9000 and is given in Lux/10, i.e. a value
	 * of 4500 means that an illuminance of 450 Lux is measured.
	 * 
	 * If you want to get the illuminance periodically, it is recommended to use the
	 * listener {@link com.tinkerforge.BrickletAmbientLight.IlluminanceListener} and set the period with
	 * {@link BrickletAmbientLight.setIlluminanceCallbackPeriod}.
	 */
	public int getIlluminance() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ILLUMINANCE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ILLUMINANCE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int illuminance = IPConnection.unsignedShort(bb.getShort());

		return illuminance;
	}

	/**
	 * Returns the value as read by a 12-bit analog-to-digital converter.
	 * The value is between 0 and 4095.
	 * 
	 * \note
	 *  The value returned by {@link BrickletAmbientLight.getIlluminance} is averaged over several samples
	 *  to yield less noise, while {@link BrickletAmbientLight.getAnalogValue} gives back raw
	 *  unfiltered analog values. The only reason to use {@link BrickletAmbientLight.getAnalogValue} is,
	 *  if you need the full resolution of the analog-to-digital converter.
	 * 
	 *  Also, the analog-to-digital converter covers three different ranges that are
	 *  set dynamically depending on the light intensity. It is impossible to
	 *  distinguish between these ranges with the analog value.
	 * 
	 * If you want the analog value periodically, it is recommended to use the 
	 * listener {@link com.tinkerforge.BrickletAmbientLight.AnalogValueListener} and set the period with
	 * {@link BrickletAmbientLight.setAnalogValueCallbackPeriod}.
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
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletAmbientLight.IlluminanceListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletAmbientLight.IlluminanceListener} is only triggered if the illuminance has changed since the
	 * last triggering.
	 * 
	 * The default value is 0.
	 */
	public void setIlluminanceCallbackPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_ILLUMINANCE_CALLBACK_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickletAmbientLight.setIlluminanceCallbackPeriod}.
	 */
	public long getIlluminanceCallbackPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ILLUMINANCE_CALLBACK_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ILLUMINANCE_CALLBACK_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletAmbientLight.AnalogValueListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletAmbientLight.AnalogValueListener} is only triggered if the analog value has changed since the
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
	 * Returns the period as set by {@link BrickletAmbientLight.setAnalogValueCallbackPeriod}.
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
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletAmbientLight.IlluminanceReachedListener} listener.
	 * 
	 * The following options are possible:
	 * 
	 * \verbatim
	 *  "Option", "Description"
	 * 
	 *  "'x'",    "Listener is turned off"
	 *  "'o'",    "Listener is triggered when the illuminance is *outside* the min and max values"
	 *  "'i'",    "Listener is triggered when the illuminance is *inside* the min and max values"
	 *  "'<'",    "Listener is triggered when the illuminance is smaller than the min value (max is ignored)"
	 *  "'>'",    "Listener is triggered when the illuminance is greater than the min value (max is ignored)"
	 * \endverbatim
	 * 
	 * The default value is ('x', 0, 0).
	 */
	public void setIlluminanceCallbackThreshold(char option, short min, short max)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_ILLUMINANCE_CALLBACK_THRESHOLD, (short)9);
		bb.put((byte)option);
		bb.putShort((short)min);
		bb.putShort((short)max);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the threshold as set by {@link BrickletAmbientLight.setIlluminanceCallbackThreshold}.
	 */
	public IlluminanceCallbackThreshold getIlluminanceCallbackThreshold() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ILLUMINANCE_CALLBACK_THRESHOLD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ILLUMINANCE_CALLBACK_THRESHOLD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		IlluminanceCallbackThreshold obj = new IlluminanceCallbackThreshold();
		obj.option = (char)(bb.get());
		obj.min = (bb.getShort());
		obj.max = (bb.getShort());

		return obj;
	}

	/**
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletAmbientLight.AnalogValueReachedListener} listener.
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
	 * Returns the threshold as set by {@link BrickletAmbientLight.setAnalogValueCallbackThreshold}.
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
	 *  {@link com.tinkerforge.BrickletAmbientLight.IlluminanceReachedListener}, {@link com.tinkerforge.BrickletAmbientLight.AnalogValueReachedListener}
	 * 
	 * are triggered, if the thresholds
	 * 
	 *  {@link BrickletAmbientLight.setIlluminanceCallbackThreshold}, {@link BrickletAmbientLight.setAnalogValueCallbackThreshold}
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
	 * Returns the debounce period as set by {@link BrickletAmbientLight.setDebouncePeriod}.
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
		if(o instanceof IlluminanceListener) {
			listenerObjects[CALLBACK_ILLUMINANCE] = o;
		} else if(o instanceof AnalogValueListener) {
			listenerObjects[CALLBACK_ANALOG_VALUE] = o;
		} else if(o instanceof IlluminanceReachedListener) {
			listenerObjects[CALLBACK_ILLUMINANCE_REACHED] = o;
		} else if(o instanceof AnalogValueReachedListener) {
			listenerObjects[CALLBACK_ANALOG_VALUE_REACHED] = o;
		}
	}
}