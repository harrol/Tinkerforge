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
 * Device for controlling up to 4 general purpose input/output pins
 */
public class BrickletIO4 extends Device {
	private final static byte FUNCTION_SET_VALUE = (byte)1;
	private final static byte FUNCTION_GET_VALUE = (byte)2;
	private final static byte FUNCTION_SET_CONFIGURATION = (byte)3;
	private final static byte FUNCTION_GET_CONFIGURATION = (byte)4;
	private final static byte FUNCTION_SET_DEBOUNCE_PERIOD = (byte)5;
	private final static byte FUNCTION_GET_DEBOUNCE_PERIOD = (byte)6;
	private final static byte FUNCTION_SET_INTERRUPT = (byte)7;
	private final static byte FUNCTION_GET_INTERRUPT = (byte)8;
	private final static byte CALLBACK_INTERRUPT = (byte)9;
	private final static byte FUNCTION_SET_MONOFLOP = (byte)10;
	private final static byte FUNCTION_GET_MONOFLOP = (byte)11;
	private final static byte CALLBACK_MONOFLOP_DONE = (byte)12;

	public class Configuration {
		public short directionMask;
		public short valueMask;

		public String toString() {
			return "[" + "directionMask = " + directionMask + ", " + "valueMask = " + valueMask + "]";
		}
	}

	public class Monoflop {
		public short pin;
		public short value;
		public long time;
		public long timeRemaining;

		public String toString() {
			return "[" + "pin = " + pin + ", " + "value = " + value + ", " + "time = " + time + ", " + "timeRemaining = " + timeRemaining + "]";
		}
	}

	/**
	 * This listener is triggered whenever a change of the voltage level is detected
	 * on pins where the interrupt was activated with {@link BrickletIO4.setInterrupt}.
	 * 
	 * The values are a bitmask that specifies which interrupts occurred
	 * and the current value bitmask.
	 * 
	 * For example:
	 * 
	 * * (1, 1) means that an interrupt on pin 0 occurred and
	 *   currently pin 0 is high and pins 1-3 are low.
	 * * (9, 14) means that interrupts on pins 0 and 3
	 *   occurred and currently pin 0 is low and pins 1-3 are high.
	 */
	public interface InterruptListener {
		public void interrupt(short interruptMask, short valueMask);
	}

	/**
	 * This listener is triggered whenever a monoflop timer reaches 0. The
	 * parameters contain the involved pins and the current value of the pins
	 * (the value after the monoflop).
	 * 
	 * .. versionadded:: 1.1.1~(Plugin)
	 */
	public interface MonoflopDoneListener {
		public void monoflopDone(short pinMask, short valueMask);
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickletIO4(String uid) {
		super(uid);

		expectedName = "IO-4 Bricklet";

		bindingVersion[0] = 1;
		bindingVersion[1] = 0;
		bindingVersion[2] = 1;

		callbacks[CALLBACK_INTERRUPT] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short interruptMask = IPConnection.unsignedByte(bb.get());
				short valueMask = IPConnection.unsignedByte(bb.get());

				((InterruptListener)listenerObjects[CALLBACK_INTERRUPT]).interrupt(interruptMask, valueMask);
			}
		};

		callbacks[CALLBACK_MONOFLOP_DONE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short pinMask = IPConnection.unsignedByte(bb.get());
				short valueMask = IPConnection.unsignedByte(bb.get());

				((MonoflopDoneListener)listenerObjects[CALLBACK_MONOFLOP_DONE]).monoflopDone(pinMask, valueMask);
			}
		};
	}

	/**
	 * Sets the output value (high or low) with a bitmask. The bitmask
	 * is 4 bit long, *true* refers to high and *false* refers to low.
	 * 
	 * For example: The value 0b0011 will turn the pins 0-1 high and the
	 * pins 2-3 low.
	 * 
	 * \note
	 *  This function does nothing for pins that are configured as input.
	 *  Pull-up resistors can be switched on with {@link BrickletIO4.setConfiguration}.
	 */
	public void setValue(short valueMask)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_VALUE, (short)5);
		bb.put((byte)valueMask);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns a bitmask of the values that are currently measured.
	 * This function works if the pin is configured to input
	 * as well as if it is configured to output.
	 */
	public short getValue() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_VALUE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_VALUE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short valueMask = IPConnection.unsignedByte(bb.get());

		return valueMask;
	}

	/**
	 * Configures the value and direction of the specified pins. Possible directions
	 * are "i" and "o" for input and output.
	 * 
	 * If the direction is configured as output, the value is either high or low
	 * (set as *true* or *false*).
	 * 
	 * If the direction is configured as input, the value is either pull-up or
	 * default (set as *true* or *false*).
	 * 
	 * For example:
	 * 
	 * * (15, 'i', true) will set all pins of as input pull-up.
	 * * (8, 'i', false) will set pin 3 of as input default (floating if nothing is connected).
	 * * (3, 'o', false) will set pins 0 and 1 as output low.
	 * * (4, 'o', true) will set pin 2 of as output high.
	 */
	public void setConfiguration(short pinMask, char direction, boolean value)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_CONFIGURATION, (short)7);
		bb.put((byte)pinMask);
		bb.put((byte)direction);
		bb.put((byte)(value ? 1 : 0));

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns a value bitmask and a direction bitmask.
	 * 
	 * For example: A return value of 0b0011 and 0b0101 for
	 * direction and value means that:
	 * 
	 * * pin 0 is configured as input pull-up,
	 * * pin 1 is configured as input default,
	 * * pin 2 is configured as output high
	 * * and pin 3 is are configured as output low.
	 */
	public Configuration getConfiguration() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_CONFIGURATION, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CONFIGURATION);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		Configuration obj = new Configuration();
		obj.directionMask = IPConnection.unsignedByte(bb.get());
		obj.valueMask = IPConnection.unsignedByte(bb.get());

		return obj;
	}

	/**
	 * Sets the debounce period of the {@link com.tinkerforge.BrickletIO4.InterruptListener} listener in ms.
	 * 
	 * For example: If you set this value to 100, you will get the interrupt
	 * maximal every 100ms. This is necessary if something that bounces is
	 * connected to the IO-4 Bricklet, such as a button.
	 * 
	 * The default value is 100.
	 */
	public void setDebouncePeriod(long debounce)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_DEBOUNCE_PERIOD, (short)8);
		bb.putInt((int)debounce);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the debounce period as set by {@link BrickletIO4.setDebouncePeriod}.
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
	 * Sets the pins on which an interrupt is activated with a bitmask.
	 * Interrupts are triggered on changes of the voltage level of the pin,
	 * i.e. changes from high to low and low to high.
	 * 
	 * For example: An interrupt bitmask of 9 will enable the interrupt for
	 * pins 0 and 3.
	 * 
	 * The interrupt is delivered with the listener {@link com.tinkerforge.BrickletIO4.InterruptListener}.
	 */
	public void setInterrupt(short interruptMask)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_INTERRUPT, (short)5);
		bb.put((byte)interruptMask);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the interrupt bitmask as set by {@link BrickletIO4.setInterrupt}.
	 */
	public short getInterrupt() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_INTERRUPT, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_INTERRUPT);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short interruptMask = IPConnection.unsignedByte(bb.get());

		return interruptMask;
	}

	/**
	 * Configures a monoflop of the pins specified by the first parameter as 4 bit
	 * long bitmask. The specified pins must be configured for output. Non-output
	 * pins will be ignored.
	 * 
	 * The second parameter is a bitmask with the desired value of the specified
	 * output pins (*true* means high and *false* means low).
	 * 
	 * The third parameter indicates the time (in ms) that the pins should hold
	 * the value.
	 * 
	 * If this function is called with the parameters ((1 << 0) | (1 << 3), (1 << 0), 1500):
	 * Pin 0 will get high and pin 3 will get low. In 1.5s pin 0 will get low and pin
	 * 3 will get high again.
	 * 
	 * A monoflop can be used as a fail-safe mechanism. For example: Lets assume you
	 * have a RS485 bus and an IO-4 Bricklet connected to one of the slave
	 * stacks. You can now call this function every second, with a time parameter
	 * of two seconds and pin 0 set to high. Pin 0 will be high all the time. If now
	 * the RS485 connection is lost, then pin 0 will get low in at most two seconds.
	 * 
	 * .. versionadded:: 1.1.1~(Plugin)
	 */
	public void setMonoflop(short pinMask, short valueMask, long time)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_MONOFLOP, (short)10);
		bb.put((byte)pinMask);
		bb.put((byte)valueMask);
		bb.putInt((int)time);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns (for the given pin) the current value and the time as set by
	 * {@link BrickletIO4.setMonoflop} as well as the remaining time until the value flips.
	 * 
	 * If the timer is not running currently, the remaining time will be returned
	 * as 0.
	 * 
	 * .. versionadded:: 1.1.1~(Plugin)
	 */
	public Monoflop getMonoflop(short pin) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_MONOFLOP, (short)5);
		bb.put((byte)pin);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_MONOFLOP);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		Monoflop obj = new Monoflop();
		obj.value = IPConnection.unsignedByte(bb.get());
		obj.time = IPConnection.unsignedInt(bb.getInt());
		obj.timeRemaining = IPConnection.unsignedInt(bb.getInt());

		return obj;
	}

	/**
	 * Registers a listener object.
	 */
	public void addListener(Object o) {
		if(o instanceof InterruptListener) {
			listenerObjects[CALLBACK_INTERRUPT] = o;
		} else if(o instanceof MonoflopDoneListener) {
			listenerObjects[CALLBACK_MONOFLOP_DONE] = o;
		}
	}
}