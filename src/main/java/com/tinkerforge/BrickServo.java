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
 * Device for controlling up to seven servos
 */
public class BrickServo extends Device {
	private final static byte FUNCTION_ENABLE = (byte)1;
	private final static byte FUNCTION_DISABLE = (byte)2;
	private final static byte FUNCTION_IS_ENABLED = (byte)3;
	private final static byte FUNCTION_SET_POSITION = (byte)4;
	private final static byte FUNCTION_GET_POSITION = (byte)5;
	private final static byte FUNCTION_GET_CURRENT_POSITION = (byte)6;
	private final static byte FUNCTION_SET_VELOCITY = (byte)7;
	private final static byte FUNCTION_GET_VELOCITY = (byte)8;
	private final static byte FUNCTION_GET_CURRENT_VELOCITY = (byte)9;
	private final static byte FUNCTION_SET_ACCELERATION = (byte)10;
	private final static byte FUNCTION_GET_ACCELERATION = (byte)11;
	private final static byte FUNCTION_SET_OUTPUT_VOLTAGE = (byte)12;
	private final static byte FUNCTION_GET_OUTPUT_VOLTAGE = (byte)13;
	private final static byte FUNCTION_SET_PULSE_WIDTH = (byte)14;
	private final static byte FUNCTION_GET_PULSE_WIDTH = (byte)15;
	private final static byte FUNCTION_SET_DEGREE = (byte)16;
	private final static byte FUNCTION_GET_DEGREE = (byte)17;
	private final static byte FUNCTION_SET_PERIOD = (byte)18;
	private final static byte FUNCTION_GET_PERIOD = (byte)19;
	private final static byte FUNCTION_GET_SERVO_CURRENT = (byte)20;
	private final static byte FUNCTION_GET_OVERALL_CURRENT = (byte)21;
	private final static byte FUNCTION_GET_STACK_INPUT_VOLTAGE = (byte)22;
	private final static byte FUNCTION_GET_EXTERNAL_INPUT_VOLTAGE = (byte)23;
	private final static byte FUNCTION_SET_MINIMUM_VOLTAGE = (byte)24;
	private final static byte FUNCTION_GET_MINIMUM_VOLTAGE = (byte)25;
	private final static byte CALLBACK_UNDER_VOLTAGE = (byte)26;
	private final static byte CALLBACK_POSITION_REACHED = (byte)27;
	private final static byte CALLBACK_VELOCITY_REACHED = (byte)28;
	private final static byte FUNCTION_RESET = (byte)243;
	private final static byte FUNCTION_GET_CHIP_TEMPERATURE = (byte)242;

	public class PulseWidth {
		public short servoNum;
		public int min;
		public int max;

		public String toString() {
			return "[" + "servoNum = " + servoNum + ", " + "min = " + min + ", " + "max = " + max + "]";
		}
	}

	public class Degree {
		public short servoNum;
		public short min;
		public short max;

		public String toString() {
			return "[" + "servoNum = " + servoNum + ", " + "min = " + min + ", " + "max = " + max + "]";
		}
	}

	/**
	 * This listener is triggered when the input voltage drops below the value set by
	 * {@link BrickServo.setMinimumVoltage}. The parameter is the current voltage given
	 * in mV.
	 */
	public interface UnderVoltageListener {
		public void underVoltage(int voltage);
	}

	/**
	 * This listener is triggered when a position set by {@link BrickServo.setPosition}
	 * is reached. The parameters are the servo and the position that is reached.
	 * 
	 * \note
	 *  Since we can't get any feedback from the servo, this only works if the
	 *  velocity (see {@link BrickServo.setVelocity}) is set smaller or equal to the
	 *  maximum velocity of the servo. Otherwise the servo will lag behind the
	 *  control value and the listener will be triggered too early.
	 */
	public interface PositionReachedListener {
		public void positionReached(short servoNum, short position);
	}

	/**
	 * This listener is triggered when a velocity set by {@link BrickServo.setVelocity}
	 * is reached. The parameters are the servo and the velocity that is reached.
	 * 
	 * \note
	 *  Since we can't get any feedback from the servo, this only works if the
	 *  acceleration (see {@link BrickServo.setAcceleration}) is set smaller or equal to the
	 *  maximum acceleration of the servo. Otherwise the servo will lag behind the
	 *  control value and the listener will be triggered too early.
	 */
	public interface VelocityReachedListener {
		public void velocityReached(short servoNum, short velocity);
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickServo(String uid) {
		super(uid);

		expectedName = "Servo Brick";

		bindingVersion[0] = 1;
		bindingVersion[1] = 0;
		bindingVersion[2] = 1;

		callbacks[CALLBACK_UNDER_VOLTAGE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int voltage = IPConnection.unsignedShort(bb.getShort());

				((UnderVoltageListener)listenerObjects[CALLBACK_UNDER_VOLTAGE]).underVoltage(voltage);
			}
		};

		callbacks[CALLBACK_POSITION_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short servoNum = IPConnection.unsignedByte(bb.get());
				short position = (bb.getShort());

				((PositionReachedListener)listenerObjects[CALLBACK_POSITION_REACHED]).positionReached(servoNum, position);
			}
		};

		callbacks[CALLBACK_VELOCITY_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short servoNum = IPConnection.unsignedByte(bb.get());
				short velocity = (bb.getShort());

				((VelocityReachedListener)listenerObjects[CALLBACK_VELOCITY_REACHED]).velocityReached(servoNum, velocity);
			}
		};
	}

	/**
	 * Enables a servo (0 to 6). If a servo is enabled, the configured position,
	 * velocity, acceleration, etc. are applied immediately.
	 */
	public void enable(short servoNum)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_ENABLE, (short)5);
		bb.put((byte)servoNum);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Disables a servo (0 to 6). Disabled servos are not driven at all, i.e. a
	 * disabled servo will not hold its position if a load is applied.
	 */
	public void disable(short servoNum)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_DISABLE, (short)5);
		bb.put((byte)servoNum);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns *true* if the specified servo is enabled, *false* otherwise.
	 */
	public boolean isEnabled(short servoNum) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_IS_ENABLED, (short)5);
		bb.put((byte)servoNum);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_IS_ENABLED);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		boolean enabled = (bb.get()) != 0;

		return enabled;
	}

	/**
	 * Sets the position in °/100 for the specified servo. 
	 * 
	 * The default range of the position is -9000 to 9000, but it can be specified
	 * according to your servo with {@link BrickServo.setDegree}.
	 * 
	 * If you want to control a linear servo or RC brushless motor controller or
	 * similar with the Servo Brick, you can also define lengths or speeds with
	 * {@link BrickServo.setDegree}.
	 */
	public void setPosition(short servoNum, short position)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_POSITION, (short)7);
		bb.put((byte)servoNum);
		bb.putShort((short)position);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the position of the specified servo as set by {@link BrickServo.setPosition}.
	 */
	public short getPosition(short servoNum) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_POSITION, (short)5);
		bb.put((byte)servoNum);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_POSITION);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short position = (bb.getShort());

		return position;
	}

	/**
	 * Returns the *current* position of the specified servo. This may not be the
	 * value of {@link BrickServo.setPosition} if the servo is currently approaching a
	 * position goal.
	 */
	public short getCurrentPosition(short servoNum) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_CURRENT_POSITION, (short)5);
		bb.put((byte)servoNum);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CURRENT_POSITION);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short position = (bb.getShort());

		return position;
	}

	/**
	 * Sets the maximum velocity of the specified servo in °/100s. The velocity
	 * is accelerated according to the value set by {@link BrickServo.setAcceleration}.
	 * 
	 * The minimum velocity is 0 (no movement) and the maximum velocity is 65535.
	 * With a value of 65535 the position will be set immediately (no velocity).
	 * 
	 * The default value is 65535.
	 */
	public void setVelocity(short servoNum, int velocity)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_VELOCITY, (short)7);
		bb.put((byte)servoNum);
		bb.putShort((short)velocity);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the velocity of the specified servo as set by {@link BrickServo.setVelocity}.
	 */
	public int getVelocity(short servoNum) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_VELOCITY, (short)5);
		bb.put((byte)servoNum);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_VELOCITY);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int velocity = IPConnection.unsignedShort(bb.getShort());

		return velocity;
	}

	/**
	 * Returns the *current* velocity of the specified servo. This may not be the
	 * value of {@link BrickServo.setVelocity} if the servo is currently approaching a
	 * velocity goal.
	 */
	public int getCurrentVelocity(short servoNum) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_CURRENT_VELOCITY, (short)5);
		bb.put((byte)servoNum);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CURRENT_VELOCITY);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int velocity = IPConnection.unsignedShort(bb.getShort());

		return velocity;
	}

	/**
	 * Sets the acceleration of the specified servo in °/100s².
	 * 
	 * The minimum acceleration is 1 and the maximum acceleration is 65535.
	 * With a value of 65535 the velocity will be set immediately (no acceleration).
	 * 
	 * The default value is 65535.
	 */
	public void setAcceleration(short servoNum, int acceleration)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_ACCELERATION, (short)7);
		bb.put((byte)servoNum);
		bb.putShort((short)acceleration);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the acceleration for the specified servo as set by 
	 * {@link BrickServo.setAcceleration}.
	 */
	public int getAcceleration(short servoNum) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ACCELERATION, (short)5);
		bb.put((byte)servoNum);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ACCELERATION);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int acceleration = IPConnection.unsignedShort(bb.getShort());

		return acceleration;
	}

	/**
	 * Sets the output voltages with which the servos are driven in mV.
	 * The minimum output voltage is 5000mV and the maximum output voltage is 
	 * 9000mV.
	 * 
	 * \note
	 *  We recommend that you set this value to the maximum voltage that is
	 *  specified for your servo, most servos achieve their maximum force only
	 *  with high voltages.
	 * 
	 * The default value is 5000.
	 */
	public void setOutputVoltage(int voltage)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_OUTPUT_VOLTAGE, (short)6);
		bb.putShort((short)voltage);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the output voltage as specified by {@link BrickServo.setOutputVoltage}.
	 */
	public int getOutputVoltage() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_OUTPUT_VOLTAGE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_OUTPUT_VOLTAGE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int voltage = IPConnection.unsignedShort(bb.getShort());

		return voltage;
	}

	/**
	 * Sets the minimum and maximum pulse width of the specified servo in µs.
	 * 
	 * Usually, servos are controlled with a 
	 * `PWM <http://en.wikipedia.org/wiki/Pulse-width_modulation>`__, whereby the
	 * length of the pulse controls the position of the servo. Every servo has
	 * different minimum and maximum pulse widths, these can be specified with
	 * this function.
	 * 
	 * If you have a datasheet for your servo that specifies the minimum and
	 * maximum pulse width, you should set the values accordingly. If your servo
	 * comes without any datasheet you have to find the values via trial and error.
	 * 
	 * Both values have a range from 1 to 65535 (unsigned 16-bit integer). The
	 * minimum must be smaller than the maximum.
	 * 
	 * The default values are 1000µs (1ms) and 2000µs (2ms) for minimum and 
	 * maximum pulse width.
	 */
	public void setPulseWidth(short servoNum, int min, int max)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_PULSE_WIDTH, (short)9);
		bb.put((byte)servoNum);
		bb.putShort((short)min);
		bb.putShort((short)max);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the minimum and maximum pulse width for the specified servo as set by
	 * {@link BrickServo.setPulseWidth}.
	 */
	public PulseWidth getPulseWidth(short servoNum) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_PULSE_WIDTH, (short)5);
		bb.put((byte)servoNum);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_PULSE_WIDTH);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		PulseWidth obj = new PulseWidth();
		obj.min = IPConnection.unsignedShort(bb.getShort());
		obj.max = IPConnection.unsignedShort(bb.getShort());

		return obj;
	}

	/**
	 * Sets the minimum and maximum degree for the specified servo (by default
	 * given as °/100).
	 * 
	 * This only specifies the abstract values between which the minimum and maximum
	 * pulse width is scaled. For example: If you specify a pulse width of 1000µs
	 * to 2000µs and a degree range of -90° to 90°, a call of {@link BrickServo.setPosition}
	 * with 0 will result in a pulse width of 1500µs 
	 * (-90° = 1000µs, 90° = 2000µs, etc.).
	 * 
	 * Possible usage:
	 * 
	 * * The datasheet of your servo specifies a range of 200° with the middle position
	 *   at 110°. In this case you can set the minimum to -9000 and the maximum to 11000.
	 * * You measure a range of 220° on your servo and you don't have or need a middle
	 *   position. In this case you can set the minimum to 0 and the maximum to 22000.
	 * * You have a linear servo with a drive length of 20cm, In this case you could
	 *   set the minimum to 0 and the maximum to 20000. Now you can set the Position
	 *   with {@link BrickServo.setPosition} with a resolution of cm/100. Also the velocity will
	 *   have a resolution of cm/100s and the acceleration will have a resolution of
	 *   cm/100s².
	 * * You don't care about units and just want the highest possible resolution. In
	 *   this case you should set the minimum to -32767 and the maximum to 32767.
	 * * You have a brushless motor with a maximum speed of 10000 rpm and want to
	 *   control it with a RC brushless motor controller. In this case you can set the
	 *   minimum to 0 and the maximum to 10000. {@link BrickServo.setPosition} now controls the rpm.
	 * 
	 * Both values have a possible range from -32767 to 32767 
	 * (signed 16-bit integer). The minimum must be smaller than the maximum.
	 * 
	 * The default values are -9000 and 9000 for the minimum and maximum degree.
	 */
	public void setDegree(short servoNum, short min, short max)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_DEGREE, (short)9);
		bb.put((byte)servoNum);
		bb.putShort((short)min);
		bb.putShort((short)max);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the minimum and maximum degree for the specified servo as set by
	 * {@link BrickServo.setDegree}.
	 */
	public Degree getDegree(short servoNum) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_DEGREE, (short)5);
		bb.put((byte)servoNum);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_DEGREE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		Degree obj = new Degree();
		obj.min = (bb.getShort());
		obj.max = (bb.getShort());

		return obj;
	}

	/**
	 * Sets the period of the specified servo in µs.
	 * 
	 * Usually, servos are controlled with a 
	 * `PWM <http://en.wikipedia.org/wiki/Pulse-width_modulation>`__. Different
	 * servos expect PWMs with different periods. Most servos run well with a 
	 * period of about 20ms.
	 * 
	 * If your servo comes with a datasheet that specifies a period, you should
	 * set it accordingly. If you don't have a datasheet and you have no idea
	 * what the correct period is, the default value (19.5ms) will most likely
	 * work fine. 
	 * 
	 * The minimum possible period is 2000µs and the maximum is 65535µs.
	 * 
	 * The default value is 19.5ms (19500µs).
	 */
	public void setPeriod(short servoNum, int period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_PERIOD, (short)7);
		bb.put((byte)servoNum);
		bb.putShort((short)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period for the specified servo as set by {@link BrickServo.setPeriod}.
	 */
	public int getPeriod(short servoNum) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_PERIOD, (short)5);
		bb.put((byte)servoNum);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int period = IPConnection.unsignedShort(bb.getShort());

		return period;
	}

	/**
	 * Returns the current consumption of the specified servo in mA.
	 */
	public int getServoCurrent(short servoNum) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_SERVO_CURRENT, (short)5);
		bb.put((byte)servoNum);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_SERVO_CURRENT);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int current = IPConnection.unsignedShort(bb.getShort());

		return current;
	}

	/**
	 * Returns the current consumption of all servos together in mA.
	 */
	public int getOverallCurrent() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_OVERALL_CURRENT, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_OVERALL_CURRENT);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int current = IPConnection.unsignedShort(bb.getShort());

		return current;
	}

	/**
	 * Returns the stack input voltage in mV. The stack input voltage is the
	 * voltage that is supplied via the stack, i.e. it is given by a 
	 * Step-Down or Step-Up Power Supply.
	 */
	public int getStackInputVoltage() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_STACK_INPUT_VOLTAGE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_STACK_INPUT_VOLTAGE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int voltage = IPConnection.unsignedShort(bb.getShort());

		return voltage;
	}

	/**
	 * Returns the external input voltage in mV. The external input voltage is
	 * given via the black power input connector on the Servo Brick. 
	 *  
	 * If there is an external input voltage and a stack input voltage, the motors
	 * will be driven by the external input voltage. If there is only a stack 
	 * voltage present, the motors will be driven by this voltage.
	 * 
	 * \warning
	 *  This means, if you have a high stack voltage and a low external voltage,
	 *  the motors will be driven with the low external voltage. If you then remove
	 *  the external connection, it will immediately be driven by the high
	 *  stack voltage
	 */
	public int getExternalInputVoltage() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_EXTERNAL_INPUT_VOLTAGE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_EXTERNAL_INPUT_VOLTAGE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int voltage = IPConnection.unsignedShort(bb.getShort());

		return voltage;
	}

	/**
	 * Sets the minimum voltage in mV, below which the {@link com.tinkerforge.BrickServo.UnderVoltageListener} listener
	 * is triggered. The minimum possible value that works with the Servo Brick is 5V.
	 * You can use this function to detect the discharge of a battery that is used
	 * to drive the stepper motor. If you have a fixed power supply, you likely do 
	 * not need this functionality.
	 * 
	 * The default value is 5V (5000mV).
	 */
	public void setMinimumVoltage(int voltage)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_MINIMUM_VOLTAGE, (short)6);
		bb.putShort((short)voltage);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the minimum voltage as set by {@link BrickServo.setMinimumVoltage}
	 */
	public int getMinimumVoltage() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_MINIMUM_VOLTAGE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_MINIMUM_VOLTAGE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int voltage = IPConnection.unsignedShort(bb.getShort());

		return voltage;
	}

	/**
	 * Calling this function will reset the Brick. Calling this function
	 * on a Brick inside of a stack will reset the whole stack.
	 * 
	 * After a reset you have to create new device objects,
	 * calling functions on the existing ones will result in
	 * undefined behavior!
	 * 
	 * .. versionadded:: 1.1.3~(Firmware)
	 */
	public void reset()  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_RESET, (short)4);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the temperature in °C/10 as measured inside the microcontroller. The
	 * value returned is not the ambient temperature!
	 * 
	 * The temperature is only proportional to the real temperature and it has an
	 * accuracy of +-15%. Practically it is only useful as an indicator for
	 * temperature changes.
	 * 
	 * .. versionadded:: 1.1.3~(Firmware)
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
	 * Registers a listener object.
	 */
	public void addListener(Object o) {
		if(o instanceof UnderVoltageListener) {
			listenerObjects[CALLBACK_UNDER_VOLTAGE] = o;
		} else if(o instanceof PositionReachedListener) {
			listenerObjects[CALLBACK_POSITION_REACHED] = o;
		} else if(o instanceof VelocityReachedListener) {
			listenerObjects[CALLBACK_VELOCITY_REACHED] = o;
		}
	}
}