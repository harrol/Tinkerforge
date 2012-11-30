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
 * Device for controlling two relays
 */
public class BrickletDualRelay extends Device {
	private final static byte FUNCTION_SET_STATE = (byte)1;
	private final static byte FUNCTION_GET_STATE = (byte)2;
	private final static byte FUNCTION_SET_MONOFLOP = (byte)3;
	private final static byte FUNCTION_GET_MONOFLOP = (byte)4;
	private final static byte CALLBACK_MONOFLOP_DONE = (byte)5;

	public class State {
		public boolean relay1;
		public boolean relay2;

		public String toString() {
			return "[" + "relay1 = " + relay1 + ", " + "relay2 = " + relay2 + "]";
		}
	}

	public class Monoflop {
		public short relay;
		public boolean state;
		public long time;
		public long timeRemaining;

		public String toString() {
			return "[" + "relay = " + relay + ", " + "state = " + state + ", " + "time = " + time + ", " + "timeRemaining = " + timeRemaining + "]";
		}
	}

	/**
	 * This listener is triggered whenever a monoflop timer reaches 0. The 
	 * parameter contain the relay (1 or 2) and the current state of the relay 
	 * (the state after the monoflop).
	 * 
	 * .. versionadded:: 1.1.1~(Plugin)
	 */
	public interface MonoflopDoneListener {
		public void monoflopDone(short relay, boolean state);
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickletDualRelay(String uid) {
		super(uid);

		expectedName = "Dual Relay Bricklet";

		bindingVersion[0] = 1;
		bindingVersion[1] = 0;
		bindingVersion[2] = 1;

		callbacks[CALLBACK_MONOFLOP_DONE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short relay = IPConnection.unsignedByte(bb.get());
				boolean state = (bb.get()) != 0;

				((MonoflopDoneListener)listenerObjects[CALLBACK_MONOFLOP_DONE]).monoflopDone(relay, state);
			}
		};
	}

	/**
	 * Sets the state of the relays, *true* means on and *false* means off. 
	 * For example: (true, false) turns relay 1 on and relay 2 off.
	 * 
	 * If you just want to set one of the relays and don't know the current state
	 * of the other relay, you can get the state with {@link BrickletDualRelay.getState}.
	 * 
	 * Running monoflop timers will be overwritten if this function is called.
	 * 
	 * The default value is (false, false).
	 */
	public void setState(boolean relay1, boolean relay2)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_STATE, (short)6);
		bb.put((byte)(relay1 ? 1 : 0));
		bb.put((byte)(relay2 ? 1 : 0));

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the state of the relays, *true* means on and *false* means off.
	 */
	public State getState() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_STATE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_STATE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		State obj = new State();
		obj.relay1 = (bb.get()) != 0;
		obj.relay2 = (bb.get()) != 0;

		return obj;
	}

	/**
	 * The first parameter can be 1 or 2 (relay 1 or relay 2). The second parameter 
	 * is the desired state of the relay (*true* means on and *false* means off).
	 * The third parameter indicates the time (in ms) that the relay should hold 
	 * the state.
	 * 
	 * If this function is called with the parameters (1, true, 1500):
	 * Relay 1 will turn on and in 1.5s it will turn off again.
	 * 
	 * A monoflop can be used as a failsafe mechanism. For example: Lets assume you 
	 * have a RS485 bus and a Dual Relay Bricklet connected to one of the slave 
	 * stacks. You can now call this function every second, with a time parameter
	 * of two seconds. The relay will be on all the time. If now the RS485 
	 * connection is lost, the relay will turn off in at most two seconds.
	 * 
	 * .. versionadded:: 1.1.1~(Plugin)
	 */
	public void setMonoflop(short relay, boolean state, long time)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_MONOFLOP, (short)10);
		bb.put((byte)relay);
		bb.put((byte)(state ? 1 : 0));
		bb.putInt((int)time);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns (for the given relay) the current state and the time as set by 
	 * {@link BrickletDualRelay.setMonoflop} as well as the remaining time until the state flips.
	 * 
	 * If the timer is not running currently, the remaining time will be returned
	 * as 0.
	 * 
	 * .. versionadded:: 1.1.1~(Plugin)
	 */
	public Monoflop getMonoflop(short relay) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_MONOFLOP, (short)5);
		bb.put((byte)relay);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_MONOFLOP);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		Monoflop obj = new Monoflop();
		obj.state = (bb.get()) != 0;
		obj.time = IPConnection.unsignedInt(bb.getInt());
		obj.timeRemaining = IPConnection.unsignedInt(bb.getInt());

		return obj;
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