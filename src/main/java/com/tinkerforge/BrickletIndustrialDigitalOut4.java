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
 * Device for controlling up to 4 optically coupled digital outputs
 */
public class BrickletIndustrialDigitalOut4 extends Device {
	private final static byte FUNCTION_SET_VALUE = (byte)1;
	private final static byte FUNCTION_GET_VALUE = (byte)2;
	private final static byte FUNCTION_SET_MONOFLOP = (byte)3;
	private final static byte FUNCTION_GET_MONOFLOP = (byte)4;
	private final static byte FUNCTION_SET_GROUP = (byte)5;
	private final static byte FUNCTION_GET_GROUP = (byte)6;
	private final static byte FUNCTION_GET_AVAILABLE_FOR_GROUP = (byte)7;
	private final static byte CALLBACK_MONOFLOP_DONE = (byte)8;

	public class Monoflop {
		public short pin;
		public int value;
		public long time;
		public long timeRemaining;

		public String toString() {
			return "[" + "pin = " + pin + ", " + "value = " + value + ", " + "time = " + time + ", " + "timeRemaining = " + timeRemaining + "]";
		}
	}

	/**
	 * This listener is triggered whenever a monoflop timer reaches 0. The
	 * parameters contain the involved pins and the current value of the pins
	 * (the value after the monoflop).
	 */
	public interface MonoflopDoneListener {
		public void monoflopDone(int pinMask, int valueMask);
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickletIndustrialDigitalOut4(String uid) {
		super(uid);

		expectedName = "Industrial Digital Out 4 Bricklet";

		bindingVersion[0] = 1;
		bindingVersion[1] = 0;
		bindingVersion[2] = 0;

		callbacks[CALLBACK_MONOFLOP_DONE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int pinMask = IPConnection.unsignedShort(bb.getShort());
				int valueMask = IPConnection.unsignedShort(bb.getShort());

				((MonoflopDoneListener)listenerObjects[CALLBACK_MONOFLOP_DONE]).monoflopDone(pinMask, valueMask);
			}
		};
	}

	/**
	 * Sets the output value with a bitmask. The bitmask
	 * is 16 bit long, *true* refers to high and *false* refers to 
	 * low.
	 * 
	 * For example: The value 0b0000000000000011 will turn pins 0-1 
	 * high and the other pins low.
	 * 
	 * If no groups are used (see {@link BrickletIndustrialDigitalOut4.setGroup}), the pins correspond to the
	 * markings on the Digital Out 4 Bricklet.
	 * 
	 * If groups are used, the pins correspond to the element in the group.
	 * Element 1 in the group will get pins 0-3, element 2 pins 4-7, element 3
	 * pins 8-11 and element 4 pins 12-15.
	 */
	public void setValue(int valueMask)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_VALUE, (short)6);
		bb.putShort((short)valueMask);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the bitmask as set by {@link BrickletIndustrialDigitalOut4.setValue}.
	 */
	public int getValue() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_VALUE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_VALUE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int valueMask = IPConnection.unsignedShort(bb.getShort());

		return valueMask;
	}

	/**
	 * Configures a monoflop of the pins specified by the first parameter
	 * bitmask.
	 * 
	 * The second parameter is a bitmask with the desired value of the specified
	 * pins (*true* means high and *false* means low).
	 * 
	 * The third parameter indicates the time (in ms) that the pins should hold
	 * the value.
	 * 
	 * If this function is called with the parameters 
	 * ((1 << 0) | (1 << 3), (1 << 0), 1500):
	 * Pin 0 will get high and pin 3 will get low. In 1.5s pin 0 will get low and
	 * pin 3 will get high again.
	 * 
	 * A monoflop can be used as a fail-safe mechanism. For example: Lets assume you
	 * have a RS485 bus and a Digital Out 4 Bricklet connected to one of the slave
	 * stacks. You can now call this function every second, with a time parameter
	 * of two seconds and pin 0 high. Pin 0 will be high all the time. If now
	 * the RS485 connection is lost, then pin 0 will turn low in at most two seconds.
	 */
	public void setMonoflop(int pinMask, int valueMask, long time)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_MONOFLOP, (short)12);
		bb.putShort((short)pinMask);
		bb.putShort((short)valueMask);
		bb.putInt((int)time);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns (for the given pin) the current value and the time as set by
	 * {@link BrickletIndustrialDigitalOut4.setMonoflop} as well as the remaining time until the value flips.
	 * 
	 * If the timer is not running currently, the remaining time will be returned
	 * as 0.
	 */
	public Monoflop getMonoflop(short pin) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_MONOFLOP, (short)5);
		bb.put((byte)pin);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_MONOFLOP);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		Monoflop obj = new Monoflop();
		obj.value = IPConnection.unsignedShort(bb.getShort());
		obj.time = IPConnection.unsignedInt(bb.getInt());
		obj.timeRemaining = IPConnection.unsignedInt(bb.getInt());

		return obj;
	}

	/**
	 * Sets a group of Digital Out 4 Bricklets that should work together. You can
	 * find Bricklets that can be grouped together with {@link BrickletIndustrialDigitalOut4.getAvailableForGroup}.
	 * 
	 * The group consists of 4 elements. Element 1 in the group will get pins 0-3,
	 * element 2 pins 4-7, element 3 pins 8-11 and element 4 pins 12-15.
	 * 
	 * Each element can either be one of the ports ('a' to 'd') or 'n' if it should
	 * not be used.
	 * 
	 * For example: If you have two Digital Out 4 Bricklets connected to port A and
	 * port B respectively, you could call with "['a', 'b', 'n', 'n']".
	 * 
	 * Now the pins on the Digital Out 4 on port A are assigned to 0-3 and the
	 * pins on the Digital Out 4 on port B are assigned to 4-7. It is now possible
	 * to call {@link BrickletIndustrialDigitalOut4.setValue} and control two Bricklets at the same time.
	 */
	public void setGroup(char[] group)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_GROUP, (short)8);
		for(int i = 0; i < 4; i++) {
			bb.put((byte)group[i]);
		}


		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the group as set by {@link BrickletIndustrialDigitalOut4.setGroup}
	 */
	public char[] getGroup() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_GROUP, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_GROUP);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		char[] group = new char[4];
		for(int i = 0; i < 4; i++) {
			group[i] = (char)(bb.get());
		}


		return group;
	}

	/**
	 * Returns a bitmask of ports that are available for grouping. For example the
	 * value 0b0101 means: Port *A* and Port *C* are connected to Bricklets that
	 * can be grouped together.
	 */
	public short getAvailableForGroup() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_AVAILABLE_FOR_GROUP, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_AVAILABLE_FOR_GROUP);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short available = IPConnection.unsignedByte(bb.get());

		return available;
	}

	/**
	 * Registers a listener object.
	 */
	public void addListener(Object o) {
		if(o instanceof MonoflopDoneListener) {
			listenerObjects[CALLBACK_MONOFLOP_DONE] = o;
		}
	}
}