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
 * Device for controlling DC motors
 */
public class BrickDC extends Device {
	private final static byte FUNCTION_SET_VELOCITY = (byte)1;
	private final static byte FUNCTION_GET_VELOCITY = (byte)2;
	private final static byte FUNCTION_GET_CURRENT_VELOCITY = (byte)3;
	private final static byte FUNCTION_SET_ACCELERATION = (byte)4;
	private final static byte FUNCTION_GET_ACCELERATION = (byte)5;
	private final static byte FUNCTION_SET_PWM_FREQUENCY = (byte)6;
	private final static byte FUNCTION_GET_PWM_FREQUENCY = (byte)7;
	private final static byte FUNCTION_FULL_BRAKE = (byte)8;
	private final static byte FUNCTION_GET_STACK_INPUT_VOLTAGE = (byte)9;
	private final static byte FUNCTION_GET_EXTERNAL_INPUT_VOLTAGE = (byte)10;
	private final static byte FUNCTION_GET_CURRENT_CONSUMPTION = (byte)11;
	private final static byte FUNCTION_ENABLE = (byte)12;
	private final static byte FUNCTION_DISABLE = (byte)13;
	private final static byte FUNCTION_IS_ENABLED = (byte)14;
	private final static byte FUNCTION_SET_MINIMUM_VOLTAGE = (byte)15;
	private final static byte FUNCTION_GET_MINIMUM_VOLTAGE = (byte)16;
	private final static byte FUNCTION_SET_DRIVE_MODE = (byte)17;
	private final static byte FUNCTION_GET_DRIVE_MODE = (byte)18;
	private final static byte FUNCTION_SET_CURRENT_VELOCITY_PERIOD = (byte)19;
	private final static byte FUNCTION_GET_CURRENT_VELOCITY_PERIOD = (byte)20;
	private final static byte CALLBACK_UNDER_VOLTAGE = (byte)21;
	private final static byte CALLBACK_EMERGENCY_SHUTDOWN = (byte)22;
	private final static byte CALLBACK_VELOCITY_REACHED = (byte)23;
	private final static byte CALLBACK_CURRENT_VELOCITY = (byte)24;
	private final static byte FUNCTION_RESET = (byte)243;
	private final static byte FUNCTION_GET_CHIP_TEMPERATURE = (byte)242;

	/**
	 * This listener is triggered when the input voltage drops below the value set by
	 * {@link BrickDC.setMinimumVoltage}. The parameter is the current voltage given
	 * in mV.
	 */
	public interface UnderVoltageListener {
		public void underVoltage(int voltage);
	}

	/**
	 * This listener is triggered if either the current consumption
	 * is too high (above 5A) or the temperature of the driver chip is too high
	 * (above 175°C). These two possibilities are essentially the same, since the
	 * temperature will reach this threshold immediately if the motor consumes too
	 * much current. In case of a voltage below 3.3V (external or stack) this
	 * listener is triggered as well.
	 * 
	 * If this listener is triggered, the driver chip gets disabled at the same time.
	 * That means, {@link BrickDC.enable} has to be called to drive the motor again.
	 * 
	 * \note
	 *  This listener only works in Drive/Brake mode (see {@link BrickDC.setDriveMode}). In
	 *  Drive/Coast mode it is unfortunately impossible to reliably read the
	 *  overcurrent/overtemperature signal from the driver chip.
	 */
	public interface EmergencyShutdownListener {
		public void emergencyShutdown();
	}

	/**
	 * This listener is triggered whenever a set velocity is reached. For example:
	 * If a velocity of 0 is present, acceleration is set to 5000 and velocity
	 * to 10000, {@link com.tinkerforge.BrickDC.VelocityReachedListener} will be triggered after about 2 seconds, when
	 * the set velocity is actually reached.
	 * 
	 * \note
	 *  Since we can't get any feedback from the DC motor, this only works if the
	 *  acceleration (see {@link BrickDC.setAcceleration}) is set smaller or equal to the
	 *  maximum acceleration of the motor. Otherwise the motor will lag behind the
	 *  control value and the listener will be triggered too early.
	 */
	public interface VelocityReachedListener {
		public void velocityReached(short velocity);
	}

	/**
	 * This listener is triggered with the period that is set by
	 * {@link BrickDC.setCurrentVelocityPeriod}. The parameter is the *current* velocity
	 * used by the motor.
	 * 
	 * {@link com.tinkerforge.BrickDC.CurrentVelocityListener} is only triggered after the set period if there is
	 * a change in the velocity.
	 */
	public interface CurrentVelocityListener {
		public void currentVelocity(short velocity);
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickDC(String uid) {
		super(uid);

		expectedName = "DC Brick";

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

		callbacks[CALLBACK_EMERGENCY_SHUTDOWN] = new CallbackListener() {
			public void callback(byte[] data) {
				((EmergencyShutdownListener)listenerObjects[CALLBACK_EMERGENCY_SHUTDOWN]).emergencyShutdown();
			}
		};

		callbacks[CALLBACK_VELOCITY_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short velocity = (bb.getShort());

				((VelocityReachedListener)listenerObjects[CALLBACK_VELOCITY_REACHED]).velocityReached(velocity);
			}
		};

		callbacks[CALLBACK_CURRENT_VELOCITY] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short velocity = (bb.getShort());

				((CurrentVelocityListener)listenerObjects[CALLBACK_CURRENT_VELOCITY]).currentVelocity(velocity);
			}
		};
	}

	/**
	 * Sets the velocity of the motor. Whereas -32767 is full speed backward,
	 * 0 is stop and 32767 is full speed forward. Depending on the
	 * acceleration (see {@link BrickDC.setAcceleration}), the motor is not immediately
	 * brought to the velocity but smoothly accelerated.
	 * 
	 * The velocity describes the duty cycle of the PWM with which the motor is
	 * controlled, e.g. a velocity of 3277 sets a PWM with a 10% duty cycle.
	 * You can not only control the duty cycle of the PWM but also the frequency,
	 * see {@link BrickDC.setPWMFrequency}.
	 * 
	 * The default velocity is 0.
	 */
	public void setVelocity(short velocity)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_VELOCITY, (short)6);
		bb.putShort((short)velocity);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the velocity as set by {@link BrickDC.setVelocity}.
	 */
	public short getVelocity() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_VELOCITY, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_VELOCITY);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short velocity = (bb.getShort());

		return velocity;
	}

	/**
	 * Returns the *current* velocity of the motor. This value is different
	 * from {@link BrickDC.getVelocity} whenever the motor is currently accelerating
	 * to a goal set by {@link BrickDC.setVelocity}.
	 */
	public short getCurrentVelocity() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_CURRENT_VELOCITY, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CURRENT_VELOCITY);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short velocity = (bb.getShort());

		return velocity;
	}

	/**
	 * Sets the acceleration of the motor. It is given in *velocity/s*. An
	 * acceleration of 10000 means, that every second the velocity is increased
	 * by 10000 (or about 30% duty cycle).
	 * 
	 * For example: If the current velocity is 0 and you want to accelerate to a
	 * velocity of 16000 (about 50% duty cycle) in 10 seconds, you should set
	 * an acceleration of 1600.
	 * 
	 * If acceleration is set to 0, there is no speed ramping, i.e. a new velocity
	 * is immediately given to the motor.
	 * 
	 * The default acceleration is 10000.
	 */
	public void setAcceleration(int acceleration)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_ACCELERATION, (short)6);
		bb.putShort((short)acceleration);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the acceleration as set by {@link BrickDC.setAcceleration}.
	 */
	public int getAcceleration() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ACCELERATION, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ACCELERATION);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int acceleration = IPConnection.unsignedShort(bb.getShort());

		return acceleration;
	}

	/**
	 * Sets the frequency (in Hz) of the PWM with which the motor is driven.
	 * The possible range of the frequency is 1-20000Hz. Often a high frequency
	 * is less noisy and the motor runs smoother. However, with a low frequency
	 * there are less switches and therefore fewer switching losses. Also with
	 * most motors lower frequencies enable higher torque.
	 * 
	 * If you have no idea what all this means, just ignore this function and use
	 * the default frequency, it will very likely work fine.
	 * 
	 * The default frequency is 15 kHz.
	 */
	public void setPWMFrequency(int frequency)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_PWM_FREQUENCY, (short)6);
		bb.putShort((short)frequency);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the PWM frequency (in Hz) as set by {@link BrickDC.setPWMFrequency}.
	 */
	public int getPWMFrequency() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_PWM_FREQUENCY, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_PWM_FREQUENCY);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int frequency = IPConnection.unsignedShort(bb.getShort());

		return frequency;
	}

	/**
	 * Executes an active full brake.
	 * 
	 * \warning
	 *  This function is for emergency purposes,
	 *  where an immediate brake is necessary. Depending on the current velocity and
	 *  the strength of the motor, a full brake can be quite violent.
	 * 
	 * Call {@link BrickDC.setVelocity} with 0 if you just want to stop the motor.
	 */
	public void fullBrake()  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_FULL_BRAKE, (short)4);

		sendRequestNoResponse(bb.array());
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
	 * given via the black power input connector on the DC Brick.
	 * 
	 * If there is an external input voltage and a stack input voltage, the motor
	 * will be driven by the external input voltage. If there is only a stack
	 * voltage present, the motor will be driven by this voltage.
	 * 
	 * \warning
	 *  This means, if you have a high stack voltage and a low external voltage,
	 *  the motor will be driven with the low external voltage. If you then remove
	 *  the external connection, it will immediately be driven by the high
	 *  stack voltage.
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
	 * Returns the current consumption of the motor in mA.
	 */
	public int getCurrentConsumption() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_CURRENT_CONSUMPTION, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CURRENT_CONSUMPTION);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int voltage = IPConnection.unsignedShort(bb.getShort());

		return voltage;
	}

	/**
	 * Enables the driver chip. The driver parameters can be configured (velocity,
	 * acceleration, etc) before it is enabled.
	 */
	public void enable()  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_ENABLE, (short)4);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Disables the driver chip. The configurations are kept (velocity,
	 * acceleration, etc) but the motor is not driven until it is enabled again.
	 */
	public void disable()  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_DISABLE, (short)4);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns *true* if the driver chip is enabled, *false* otherwise.
	 */
	public boolean isEnabled() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_IS_ENABLED, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_IS_ENABLED);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		boolean enabled = (bb.get()) != 0;

		return enabled;
	}

	/**
	 * Sets the minimum voltage in mV, below which the {@link com.tinkerforge.BrickDC.UnderVoltageListener} listener
	 * is triggered. The minimum possible value that works with the DC Brick is 5V.
	 * You can use this function to detect the discharge of a battery that is used
	 * to drive the motor. If you have a fixed power supply, you likely do not need
	 * this functionality.
	 * 
	 * The default value is 5V.
	 */
	public void setMinimumVoltage(int voltage)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_MINIMUM_VOLTAGE, (short)6);
		bb.putShort((short)voltage);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the minimum voltage as set by {@link BrickDC.setMinimumVoltage}
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
	 * Sets the drive mode. Possible modes are:
	 * 
	 * * 0 = Drive/Brake
	 * * 1 = Drive/Coast
	 * 
	 * These modes are different kinds of motor controls.
	 * 
	 * In Drive/Brake mode, the motor is always either driving or braking. There
	 * is no freewheeling. Advantages are: A more linear correlation between
	 * PWM and velocity, more exact accelerations and the possibility to drive
	 * with slower velocities.
	 * 
	 * In Drive/Coast mode, the motor is always either driving or freewheeling.
	 * Advantages are: Less current consumption and less demands on the motor and
	 * driver chip.
	 * 
	 * The default value is 0 = Drive/Brake.
	 */
	public void setDriveMode(short mode)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_DRIVE_MODE, (short)5);
		bb.put((byte)mode);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the drive mode, as set by {@link BrickDC.setDriveMode}.
	 */
	public short getDriveMode() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_DRIVE_MODE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_DRIVE_MODE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short mode = IPConnection.unsignedByte(bb.get());

		return mode;
	}

	/**
	 * Sets a period in ms with which the {@link com.tinkerforge.BrickDC.CurrentVelocityListener} listener is triggered.
	 * A period of 0 turns the listener off.
	 * 
	 * The default value is 0.
	 */
	public void setCurrentVelocityPeriod(int period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_CURRENT_VELOCITY_PERIOD, (short)6);
		bb.putShort((short)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickDC.setCurrentVelocityPeriod}.
	 */
	public int getCurrentVelocityPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_CURRENT_VELOCITY_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CURRENT_VELOCITY_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int period = IPConnection.unsignedShort(bb.getShort());

		return period;
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
		} else if(o instanceof EmergencyShutdownListener) {
			listenerObjects[CALLBACK_EMERGENCY_SHUTDOWN] = o;
		} else if(o instanceof VelocityReachedListener) {
			listenerObjects[CALLBACK_VELOCITY_REACHED] = o;
		} else if(o instanceof CurrentVelocityListener) {
			listenerObjects[CALLBACK_CURRENT_VELOCITY] = o;
		}
	}
}