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
 * Device for output of voltage between 0 and 5V
 */
public class BrickletAnalogOut extends Device {
	private final static byte FUNCTION_SET_VOLTAGE = (byte)1;
	private final static byte FUNCTION_GET_VOLTAGE = (byte)2;
	private final static byte FUNCTION_SET_MODE = (byte)3;
	private final static byte FUNCTION_GET_MODE = (byte)4;

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickletAnalogOut(String uid) {
		super(uid);

		expectedName = "Analog Out Bricklet";

		bindingVersion[0] = 1;
		bindingVersion[1] = 0;
		bindingVersion[2] = 0;
	}

	/**
	 * Sets the voltage in mV. The possible range is 0V to 5V (0-5000).
	 * Calling this function will set the mode to 0 (see `:func:SetMode`).
	 * 
	 * The default value is 0 (with mode 1).
	 */
	public void setVoltage(int voltage)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_VOLTAGE, (short)6);
		bb.putShort((short)voltage);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the voltage as set by {@link BrickletAnalogOut.setVoltage}.
	 */
	public int getVoltage() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_VOLTAGE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_VOLTAGE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int voltage = IPConnection.unsignedShort(bb.getShort());

		return voltage;
	}

	/**
	 * Sets the mode of the analog value. Possible modes:
	 * 
	 * * 0: Normal Mode (Analog value as set by {@link BrickletAnalogOut.setVoltage} is applied
	 * * 1: 1k Ohm resistor to ground
	 * * 2: 100k Ohm resistor to ground
	 * * 3: 500k Ohm resistor to ground
	 * 
	 * Setting the mode to 0 will result in an output voltage of 0. You can jump
	 * to a higher output voltage directly by calling {@link BrickletAnalogOut.setVoltage}.
	 * 
	 * The default mode is 1.
	 */
	public void setMode(short mode)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_MODE, (short)5);
		bb.put((byte)mode);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the mode as set by {@link BrickletAnalogOut.setMode}.
	 */
	public short getMode() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_MODE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_MODE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short mode = IPConnection.unsignedByte(bb.get());

		return mode;
	}
}