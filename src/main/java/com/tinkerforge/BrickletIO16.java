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
 * Device for controlling up to 16 general purpose input/output pins
 */
public class BrickletIO16 extends Device {
	private final static byte FUNCTION_SET_PORT = (byte)1;
	private final static byte FUNCTION_GET_PORT = (byte)2;
	private final static byte FUNCTION_SET_PORT_CONFIGURATION = (byte)3;
	private final static byte FUNCTION_GET_PORT_CONFIGURATION = (byte)4;
	private final static byte FUNCTION_SET_DEBOUNCE_PERIOD = (byte)5;
	private final static byte FUNCTION_GET_DEBOUNCE_PERIOD = (byte)6;
	private final static byte FUNCTION_SET_PORT_INTERRUPT = (byte)7;
	private final static byte FUNCTION_GET_PORT_INTERRUPT = (byte)8;
	private final static byte CALLBACK_INTERRUPT = (byte)9;
	private final static byte FUNCTION_SET_PORT_MONOFLOP = (byte)10;
	private final static byte FUNCTION_GET_PORT_MONOFLOP = (byte)11;
	private final static byte CALLBACK_MONOFLOP_DONE = (byte)12;

	public class PortConfiguration {
		public char port;
		public short directionMask;
		public short valueMask;

		public String toString() {
			return "[" + "port = " + port + ", " + "directionMask = " + directionMask + ", " + "valueMask = " + valueMask + "]";
		}
	}

	public class PortMonoflop {
		public char port;
		public short pin;
		public short value;
		public long time;
		public long timeRemaining;

		public String toString() {
			return "[" + "port = " + port + ", " + "pin = " + pin + ", " + "value = " + value + ", " + "time = " + time + ", " + "timeRemaining = " + timeRemaining + "]";
		}
	}

	/**
	 * This listener is triggered whenever a change of the voltage level is detected
	 * on pins where the interrupt was activated with {@link BrickletIO16.setPortInterrupt}.
	 * 
	 * The values are the port, a bitmask that specifies which interrupts occurred
	 * and the current value bitmask of the port.
	 * 
	 * For example:
	 * 
	 * * ("a", 1, 1) means that on port a an interrupt on pin 0 occurred and
	 *   currently pin 0 is high and pins 1-7 are low.
	 * * ("b", 128, 254) means that on port b interrupts on pins 0 and 7
	 *   occurred and currently pin 0 is low and pins 1-7 are high.
	 */
	public interface InterruptListener {
		public void interrupt(char port, short interruptMask, short valueMask);
	}

	/**
	 * This listener is triggered whenever a monoflop timer reaches 0. The
	 * parameters contain the port, the involved pins and the current value of
	 * the pins (the value after the monoflop).
	 * 
	 * .. versionadded:: 1.1.2~(Plugin)
	 */
	public interface MonoflopDoneListener {
		public void monoflopDone(char port, short pinMask, short valueMask);
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickletIO16(String uid) {
		super(uid);

		expectedName = "IO-16 Bricklet";

		bindingVersion[0] = 1;
		bindingVersion[1] = 0;
		bindingVersion[2] = 1;

		callbacks[CALLBACK_INTERRUPT] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				char port = (char)(bb.get());
				short interruptMask = IPConnection.unsignedByte(bb.get());
				short valueMask = IPConnection.unsignedByte(bb.get());

				((InterruptListener)listenerObjects[CALLBACK_INTERRUPT]).interrupt(port, interruptMask, valueMask);
			}
		};

		callbacks[CALLBACK_MONOFLOP_DONE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				char port = (char)(bb.get());
				short pinMask = IPConnection.unsignedByte(bb.get());
				short valueMask = IPConnection.unsignedByte(bb.get());

				((MonoflopDoneListener)listenerObjects[CALLBACK_MONOFLOP_DONE]).monoflopDone(port, pinMask, valueMask);
			}
		};
	}

	/**
	 * Sets the output value (high or low) for a port ("a" or "b") with a bitmask.
	 * The bitmask is 8 bit long, *true* refers to high and *false* refers to low.
	 * 
	 * For example: The value 0b00001111 will turn the pins 0-3 high and the
	 * pins 4-7 low for the specified port.
	 * 
	 * \note
	 *  This function does nothing for pins that are configured as input.
	 *  Pull-up resistors can be switched on with {@link BrickletIO16.setPortConfiguration}.
	 */
	public void setPort(char port, short valueMask)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_PORT, (short)6);
		bb.put((byte)port);
		bb.put((byte)valueMask);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns a bitmask of the values that are currently measured on the
	 * specified port. This function works if the pin is configured to input
	 * as well as if it is configured to output.
	 */
	public short getPort(char port) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_PORT, (short)5);
		bb.put((byte)port);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_PORT);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short valueMask = IPConnection.unsignedByte(bb.get());

		return valueMask;
	}

	/**
	 * Configures the value and direction of a specified port. Possible directions
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
	 * * ("a", 0xFF, 'i', true) will set all pins of port a as input pull-up.
	 * * ("a", 128, 'i', false) will set pin 7 of port a as input default (floating if nothing is connected).
	 * * ("b", 3, 'o', false) will set pins 0 and 1 of port b as output low.
	 * * ("b", 4, 'o', true) will set pin 2 of port b as output high.
	 */
	public void setPortConfiguration(char port, short pinMask, char direction, boolean value)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_PORT_CONFIGURATION, (short)8);
		bb.put((byte)port);
		bb.put((byte)pinMask);
		bb.put((byte)direction);
		bb.put((byte)(value ? 1 : 0));

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns a direction bitmask and a value bitmask for the specified port.
	 * 
	 * For example: A return value of 0b00001111 and 0b00110011 for
	 * direction and value means that:
	 * 
	 * * pins 0 and 1 are configured as input pull-up,
	 * * pins 2 and 3 are configured as input default,
	 * * pins 4 and 5 are configured as output high
	 * * and pins 6 and 7 are configured as output low.
	 */
	public PortConfiguration getPortConfiguration(char port) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_PORT_CONFIGURATION, (short)5);
		bb.put((byte)port);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_PORT_CONFIGURATION);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		PortConfiguration obj = new PortConfiguration();
		obj.directionMask = IPConnection.unsignedByte(bb.get());
		obj.valueMask = IPConnection.unsignedByte(bb.get());

		return obj;
	}

	/**
	 * Sets the debounce period of the {@link com.tinkerforge.BrickletIO16.InterruptListener} listener in ms.
	 * 
	 * For example: If you set this value to 100, you will get the interrupt
	 * maximal every 100ms. This is necessary if something that bounces is
	 * connected to the IO-16 Bricklet, such as a button.
	 * 
	 * The default value is 100.
	 */
	public void setDebouncePeriod(long debounce)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_DEBOUNCE_PERIOD, (short)8);
		bb.putInt((int)debounce);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the debounce period as set by {@link BrickletIO16.setDebouncePeriod}.
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
	 * For example: ('a', 129) will enable the interrupt for pins 0 and 7 of
	 * port a.
	 * 
	 * The interrupt is delivered with the listener {@link com.tinkerforge.BrickletIO16.InterruptListener}.
	 */
	public void setPortInterrupt(char port, short interruptMask)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_PORT_INTERRUPT, (short)6);
		bb.put((byte)port);
		bb.put((byte)interruptMask);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the interrupt bitmask for the specified port as set by
	 * {@link BrickletIO16.setPortInterrupt}.
	 */
	public short getPortInterrupt(char port) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_PORT_INTERRUPT, (short)5);
		bb.put((byte)port);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_PORT_INTERRUPT);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short interruptMask = IPConnection.unsignedByte(bb.get());

		return interruptMask;
	}

	/**
	 * Configures a monoflop of the pins specified by the second parameter as 8 bit
	 * long bitmask. The specified pins must be configured for output. Non-output
	 * pins will be ignored.
	 * 
	 * The third parameter is a bitmask with the desired value of the specified
	 * output pins (*true* means high and *false* means low).
	 * 
	 * The forth parameter indicates the time (in ms) that the pins should hold
	 * the value.
	 * 
	 * If this function is called with the parameters ('a', (1 << 0) | (1 << 3), (1 << 0), 1500):
	 * Pin 0 will get high and pin 3 will get low on port 'a'. In 1.5s pin 0 will get
	 * low and pin 3 will get high again.
	 * 
	 * A monoflop can be used as a fail-safe mechanism. For example: Lets assume you
	 * have a RS485 bus and an IO-16 Bricklet connected to one of the slave
	 * stacks. You can now call this function every second, with a time parameter
	 * of two seconds and pin 0 set to high. Pin 0 will be high all the time. If now
	 * the RS485 connection is lost, then pin 0 will get low in at most two seconds.
	 * 
	 * .. versionadded:: 1.1.2~(Plugin)
	 */
	public void setPortMonoflop(char port, short pinMask, short valueMask, long time)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_PORT_MONOFLOP, (short)11);
		bb.put((byte)port);
		bb.put((byte)pinMask);
		bb.put((byte)valueMask);
		bb.putInt((int)time);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns (for the given pin) the current value and the time as set by
	 * {@link BrickletIO16.setPortMonoflop} as well as the remaining time until the value flips.
	 * 
	 * If the timer is not running currently, the remaining time will be returned
	 * as 0.
	 * 
	 * .. versionadded:: 1.1.2~(Plugin)
	 */
	public PortMonoflop getPortMonoflop(char port, short pin) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_PORT_MONOFLOP, (short)6);
		bb.put((byte)port);
		bb.put((byte)pin);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_PORT_MONOFLOP);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		PortMonoflop obj = new PortMonoflop();
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