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
 * Device for controlling stepper motors
 */
public class BrickStepper extends Device {
	private final static byte FUNCTION_SET_MAX_VELOCITY = (byte)1;
	private final static byte FUNCTION_GET_MAX_VELOCITY = (byte)2;
	private final static byte FUNCTION_GET_CURRENT_VELOCITY = (byte)3;
	private final static byte FUNCTION_SET_SPEED_RAMPING = (byte)4;
	private final static byte FUNCTION_GET_SPEED_RAMPING = (byte)5;
	private final static byte FUNCTION_FULL_BRAKE = (byte)6;
	private final static byte FUNCTION_SET_CURRENT_POSITION = (byte)7;
	private final static byte FUNCTION_GET_CURRENT_POSITION = (byte)8;
	private final static byte FUNCTION_SET_TARGET_POSITION = (byte)9;
	private final static byte FUNCTION_GET_TARGET_POSITION = (byte)10;
	private final static byte FUNCTION_SET_STEPS = (byte)11;
	private final static byte FUNCTION_GET_STEPS = (byte)12;
	private final static byte FUNCTION_GET_REMAINING_STEPS = (byte)13;
	private final static byte FUNCTION_SET_STEP_MODE = (byte)14;
	private final static byte FUNCTION_GET_STEP_MODE = (byte)15;
	private final static byte FUNCTION_DRIVE_FORWARD = (byte)16;
	private final static byte FUNCTION_DRIVE_BACKWARD = (byte)17;
	private final static byte FUNCTION_STOP = (byte)18;
	private final static byte FUNCTION_GET_STACK_INPUT_VOLTAGE = (byte)19;
	private final static byte FUNCTION_GET_EXTERNAL_INPUT_VOLTAGE = (byte)20;
	private final static byte FUNCTION_GET_CURRENT_CONSUMPTION = (byte)21;
	private final static byte FUNCTION_SET_MOTOR_CURRENT = (byte)22;
	private final static byte FUNCTION_GET_MOTOR_CURRENT = (byte)23;
	private final static byte FUNCTION_ENABLE = (byte)24;
	private final static byte FUNCTION_DISABLE = (byte)25;
	private final static byte FUNCTION_IS_ENABLED = (byte)26;
	private final static byte FUNCTION_SET_DECAY = (byte)27;
	private final static byte FUNCTION_GET_DECAY = (byte)28;
	private final static byte FUNCTION_SET_MINIMUM_VOLTAGE = (byte)29;
	private final static byte FUNCTION_GET_MINIMUM_VOLTAGE = (byte)30;
	private final static byte CALLBACK_UNDER_VOLTAGE = (byte)31;
	private final static byte CALLBACK_POSITION_REACHED = (byte)32;
	private final static byte FUNCTION_SET_SYNC_RECT = (byte)33;
	private final static byte FUNCTION_IS_SYNC_RECT = (byte)34;
	private final static byte FUNCTION_SET_TIME_BASE = (byte)35;
	private final static byte FUNCTION_GET_TIME_BASE = (byte)36;
	private final static byte FUNCTION_GET_ALL_DATA = (byte)37;
	private final static byte FUNCTION_SET_ALL_DATA_PERIOD = (byte)38;
	private final static byte FUNCTION_GET_ALL_DATA_PERIOD = (byte)39;
	private final static byte CALLBACK_ALL_DATA = (byte)40;
	private final static byte CALLBACK_NEW_STATE = (byte)41;
	private final static byte FUNCTION_RESET = (byte)243;
	private final static byte FUNCTION_GET_CHIP_TEMPERATURE = (byte)242;

	public class SpeedRamping {
		public int acceleration;
		public int deacceleration;

		public String toString() {
			return "[" + "acceleration = " + acceleration + ", " + "deacceleration = " + deacceleration + "]";
		}
	}

	public class AllData {
		public int currentVelocity;
		public int currentPosition;
		public int remainingSteps;
		public int stackVoltage;
		public int externalVoltage;
		public int currentConsumption;

		public String toString() {
			return "[" + "currentVelocity = " + currentVelocity + ", " + "currentPosition = " + currentPosition + ", " + "remainingSteps = " + remainingSteps + ", " + "stackVoltage = " + stackVoltage + ", " + "externalVoltage = " + externalVoltage + ", " + "currentConsumption = " + currentConsumption + "]";
		}
	}

	/**
	 * This listener is triggered when the input voltage drops below the value set by
	 * {@link BrickStepper.setMinimumVoltage}. The parameter is the current voltage given
	 * in mV.
	 */
	public interface UnderVoltageListener {
		public void underVoltage(int voltage);
	}

	/**
	 * This listener is triggered when a position set by {@link BrickStepper.setSteps} or
	 * {@link BrickStepper.setTargetPosition} is reached.
	 * 
	 * \note
	 *  Since we can't get any feedback from the stepper motor, this only works if the
	 *  acceleration (see {@link BrickStepper.setSpeedRamping}) is set smaller or equal to the
	 *  maximum acceleration of the motor. Otherwise the motor will lag behind the
	 *  control value and the listener will be triggered too early.
	 */
	public interface PositionReachedListener {
		public void positionReached(int position);
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickStepper.setAllDataPeriod}. The parameters are: the current velocity,
	 * the current position, the remaining steps, the stack voltage, the external
	 * voltage and the current consumption of the stepper motor.
	 * 
	 * .. versionadded:: 1.1.6~(Firmware)
	 */
	public interface AllDataListener {
		public void allData(int currentVelocity, int currentPosition, int remainingSteps, int stackVoltage, int externalVoltage, int currentConsumption);
	}

	/**
	 * This listener is triggered whenever the Stepper Brick enters a new state. 
	 * It returns the new state as well as the previous state.
	 * 
	 * Possible states are:
	 * 
	 * * Stop = 1
	 * * Acceleration = 2
	 * * Run = 3
	 * * Deacceleration = 4
	 * * Direction change to forward = 5
	 * * Direction change to backward = 6
	 * 
	 * .. versionadded:: 1.1.6~(Firmware)
	 */
	public interface NewStateListener {
		public void newState(short stateNew, short statePrevious);
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickStepper(String uid) {
		super(uid);

		expectedName = "Stepper Brick";

		bindingVersion[0] = 1;
		bindingVersion[1] = 0;
		bindingVersion[2] = 2;

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

				int position = (bb.getInt());

				((PositionReachedListener)listenerObjects[CALLBACK_POSITION_REACHED]).positionReached(position);
			}
		};

		callbacks[CALLBACK_ALL_DATA] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int currentVelocity = IPConnection.unsignedShort(bb.getShort());
				int currentPosition = (bb.getInt());
				int remainingSteps = (bb.getInt());
				int stackVoltage = IPConnection.unsignedShort(bb.getShort());
				int externalVoltage = IPConnection.unsignedShort(bb.getShort());
				int currentConsumption = IPConnection.unsignedShort(bb.getShort());

				((AllDataListener)listenerObjects[CALLBACK_ALL_DATA]).allData(currentVelocity, currentPosition, remainingSteps, stackVoltage, externalVoltage, currentConsumption);
			}
		};

		callbacks[CALLBACK_NEW_STATE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short stateNew = IPConnection.unsignedByte(bb.get());
				short statePrevious = IPConnection.unsignedByte(bb.get());

				((NewStateListener)listenerObjects[CALLBACK_NEW_STATE]).newState(stateNew, statePrevious);
			}
		};
	}

	/**
	 * Sets the maximum velocity of the stepper motor in steps per second.
	 * This function does *not* start the motor, it merely sets the maximum
	 * velocity the stepper motor is accelerated to. To get the motor running use
	 * either {@link BrickStepper.setTargetPosition}, {@link BrickStepper.setSteps}, {@link BrickStepper.driveForward} or
	 * {@link BrickStepper.driveBackward}.
	 */
	public void setMaxVelocity(int velocity)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_MAX_VELOCITY, (short)6);
		bb.putShort((short)velocity);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the velocity as set by {@link BrickStepper.setMaxVelocity}.
	 */
	public int getMaxVelocity() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_MAX_VELOCITY, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_MAX_VELOCITY);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int velocity = IPConnection.unsignedShort(bb.getShort());

		return velocity;
	}

	/**
	 * Returns the *current* velocity of the stepper motor in steps per second.
	 */
	public int getCurrentVelocity() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_CURRENT_VELOCITY, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CURRENT_VELOCITY);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int velocity = IPConnection.unsignedShort(bb.getShort());

		return velocity;
	}

	/**
	 * Sets the acceleration and deacceleration of the stepper motor. The values
	 * are given in *steps/s²*. An acceleration of 1000 means, that
	 * every second the velocity is increased by 1000 *steps/s*.
	 * 
	 * For example: If the current velocity is 0 and you want to accelerate to a
	 * velocity of 8000 *steps/s* in 10 seconds, you should set an acceleration
	 * of 800 *steps/s²*.
	 * 
	 * An acceleration/deacceleration of 0 means instantaneous
	 * acceleration/deacceleration (not recommended)
	 * 
	 * The default value is 1000 for both
	 */
	public void setSpeedRamping(int acceleration, int deacceleration)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_SPEED_RAMPING, (short)8);
		bb.putShort((short)acceleration);
		bb.putShort((short)deacceleration);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the acceleration and deacceleration as set by 
	 * {@link BrickStepper.setSpeedRamping}.
	 */
	public SpeedRamping getSpeedRamping() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_SPEED_RAMPING, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_SPEED_RAMPING);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		SpeedRamping obj = new SpeedRamping();
		obj.acceleration = IPConnection.unsignedShort(bb.getShort());
		obj.deacceleration = IPConnection.unsignedShort(bb.getShort());

		return obj;
	}

	/**
	 * Executes an active full brake. 
	 *  
	 * \warning
	 *  This function is for emergency purposes,
	 *  where an immediate brake is necessary. Depending on the current velocity and
	 *  the strength of the motor, a full brake can be quite violent.
	 * 
	 * Call {@link BrickStepper.stop} if you just want to stop the motor.
	 */
	public void fullBrake()  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_FULL_BRAKE, (short)4);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Sets the current steps of the internal step counter. This can be used to
	 * set the current position to 0 when some kind of starting position
	 * is reached (e.g. when a CNC machine reaches a corner).
	 */
	public void setCurrentPosition(int position)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_CURRENT_POSITION, (short)8);
		bb.putInt((int)position);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the current position of the stepper motor in steps. On startup
	 * the position is 0. The steps are counted with all possible driving
	 * functions ({@link BrickStepper.setTargetPosition}, {@link BrickStepper.setSteps}, {@link BrickStepper.driveForward} or
	 * {@link BrickStepper.driveBackward}). It also is possible to reset the steps to 0 or
	 * set them to any other desired value with {@link BrickStepper.setCurrentPosition}.
	 */
	public int getCurrentPosition() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_CURRENT_POSITION, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CURRENT_POSITION);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int position = (bb.getInt());

		return position;
	}

	/**
	 * Sets the target position of the stepper motor in steps. For example,
	 * if the current position of the motor is 500 and {@link BrickStepper.setTargetPosition} is
	 * called with 1000, the stepper motor will drive 500 steps forward. It will
	 * use the velocity, acceleration and deacceleration as set by
	 * {@link BrickStepper.setMaxVelocity} and {@link BrickStepper.setSpeedRamping}.
	 * 
	 * A call of {@link BrickStepper.setTargetPosition} with the parameter *x* is equivalent to
	 * a call of {@link BrickStepper.setSteps} with the parameter
	 * (*x* - {@link BrickStepper.getCurrentPosition}).
	 */
	public void setTargetPosition(int position)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_TARGET_POSITION, (short)8);
		bb.putInt((int)position);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the last target position as set by {@link BrickStepper.setTargetPosition}.
	 */
	public int getTargetPosition() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_TARGET_POSITION, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_TARGET_POSITION);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int position = (bb.getInt());

		return position;
	}

	/**
	 * Sets the number of steps the stepper motor should run. Positive values
	 * will drive the motor forward and negative values backward. 
	 * The velocity, acceleration and deacceleration as set by
	 * {@link BrickStepper.setMaxVelocity} and {@link BrickStepper.setSpeedRamping} will be used.
	 */
	public void setSteps(int steps)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_STEPS, (short)8);
		bb.putInt((int)steps);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the last steps as set by {@link BrickStepper.setSteps}.
	 */
	public int getSteps() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_STEPS, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_STEPS);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int steps = (bb.getInt());

		return steps;
	}

	/**
	 * Returns the remaining steps of the last call of {@link BrickStepper.setSteps}.
	 * For example, if {@link BrickStepper.setSteps} is called with 2000 and
	 * {@link BrickStepper.getRemainingSteps} is called after the motor has run for 500 steps,
	 * it will return 1500.
	 */
	public int getRemainingSteps() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_REMAINING_STEPS, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_REMAINING_STEPS);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int steps = (bb.getInt());

		return steps;
	}

	/**
	 * Sets the step mode of the stepper motor. Possible values are:
	 * 
	 * * Full Step = 1
	 * * Half Step = 2
	 * * Quarter Step = 4
	 * * Eighth Step = 8
	 * 
	 * A higher value will increase the resolution and
	 * decrease the torque of the stepper motor.
	 * 
	 * The default value is 8 (Eighth Step).
	 */
	public void setStepMode(short mode)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_STEP_MODE, (short)5);
		bb.put((byte)mode);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the step mode as set by {@link BrickStepper.setStepMode}.
	 */
	public short getStepMode() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_STEP_MODE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_STEP_MODE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short mode = IPConnection.unsignedByte(bb.get());

		return mode;
	}

	/**
	 * Drives the stepper motor forward until {@link BrickStepper.driveBackward} or
	 * {@link BrickStepper.stop} is called. The velocity, acceleration and deacceleration as
	 * set by {@link BrickStepper.setMaxVelocity} and {@link BrickStepper.setSpeedRamping} will be used.
	 */
	public void driveForward()  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_DRIVE_FORWARD, (short)4);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Drives the stepper motor backward until {@link BrickStepper.driveForward} or
	 * {@link BrickStepper.stop} is triggered. The velocity, acceleration and deacceleration as
	 * set by {@link BrickStepper.setMaxVelocity} and {@link BrickStepper.setSpeedRamping} will be used.
	 */
	public void driveBackward()  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_DRIVE_BACKWARD, (short)4);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Stops the stepper motor with the deacceleration as set by 
	 * {@link BrickStepper.setSpeedRamping}.
	 */
	public void stop()  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_STOP, (short)4);

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
	 * given via the black power input connector on the Stepper Brick. 
	 *  
	 * If there is an external input voltage and a stack input voltage, the motor
	 * will be driven by the external input voltage. If there is only a stack 
	 * voltage present, the motor will be driven by this voltage.
	 * 
	 * \warning
	 *  This means, if you have a high stack voltage and a low external voltage,
	 *  the motor will be driven with the low external voltage. If you then remove
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
	 * Returns the current consumption of the motor in mA.
	 */
	public int getCurrentConsumption() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_CURRENT_CONSUMPTION, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CURRENT_CONSUMPTION);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int current = IPConnection.unsignedShort(bb.getShort());

		return current;
	}

	/**
	 * Sets the current in mA with which the motor will be driven.
	 * The minimum value is 100mA, the maximum value 2291mA and the 
	 * default value is 800mA.
	 * 
	 * \warning
	 *  Do not set this value above the specifications of your stepper motor.
	 *  Otherwise it may damage your motor.
	 */
	public void setMotorCurrent(int current)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_MOTOR_CURRENT, (short)6);
		bb.putShort((short)current);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the current as set by {@link BrickStepper.setMotorCurrent}.
	 */
	public int getMotorCurrent() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_MOTOR_CURRENT, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_MOTOR_CURRENT);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int current = IPConnection.unsignedShort(bb.getShort());

		return current;
	}

	/**
	 * Enables the driver chip. The driver parameters can be configured (maximum velocity,
	 * acceleration, etc) before it is enabled.
	 */
	public void enable()  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_ENABLE, (short)4);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Disables the driver chip. The configurations are kept (maximum velocity,
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
	 * Sets the decay mode of the stepper motor. The possible value range is
	 * between 0 and 65535. A value of 0 sets the fast decay mode, a value of
	 * 65535 sets the slow decay mode and a value in between sets the mixed
	 * decay mode.
	 * 
	 * Changing the decay mode is only possible if synchronous rectification
	 * is enabled (see {@link BrickStepper.setSyncRect}).
	 * 
	 * For a good explanation of the different decay modes see 
	 * `this <http://ebldc.com/?p=86/>`__ blog post by Avayan.
	 * 
	 * A good decay mode is unfortunately different for every motor. The best
	 * way to work out a good decay mode for your stepper motor, if you can't
	 * measure the current with an oscilloscope, is to listen to the sound of
	 * the motor. If the value is too low, you often hear a high pitched 
	 * sound and if it is too high you can often hear a humming sound.
	 * 
	 * Generally, fast decay mode (small value) will be noisier but also
	 * allow higher motor speeds.
	 * 
	 * The default value is 10000.
	 * 
	 * \note
	 *  There is unfortunately no formula to calculate a perfect decay
	 *  mode for a given stepper motor. If you have problems with loud noises
	 *  or the maximum motor speed is too slow, you should try to tinker with
	 *  the decay value
	 */
	public void setDecay(int decay)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_DECAY, (short)6);
		bb.putShort((short)decay);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the decay mode as set by {@link BrickStepper.setDecay}.
	 */
	public int getDecay() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_DECAY, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_DECAY);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int decay = IPConnection.unsignedShort(bb.getShort());

		return decay;
	}

	/**
	 * Sets the minimum voltage in mV, below which the {@link com.tinkerforge.BrickStepper.UnderVoltageListener} listener
	 * is triggered. The minimum possible value that works with the Stepper Brick is 8V.
	 * You can use this function to detect the discharge of a battery that is used
	 * to drive the stepper motor. If you have a fixed power supply, you likely do 
	 * not need this functionality.
	 * 
	 * The default value is 8V.
	 */
	public void setMinimumVoltage(int voltage)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_MINIMUM_VOLTAGE, (short)6);
		bb.putShort((short)voltage);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the minimum voltage as set by {@link BrickStepper.setMinimumVoltage}.
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
	 * Turns synchronous rectification on or off (true/false).
	 * 
	 * With synchronous rectification on, the decay can be changed
	 * (see {@link BrickStepper.setDecay}). Without synchronous rectification fast
	 * decay is used.
	 * 
	 * For an explanation of synchronous rectification see 
	 * `here <http://en.wikipedia.org/wiki/Active_rectification>`__.
	 * 
	 * \warning
	 *  If you want to use high speeds (> 10000 steps/s) for a large 
	 *  stepper motor with a large inductivity we strongly
	 *  suggest that you disable synchronous rectification. Otherwise the
	 *  Brick may not be able to cope with the load and overheat.
	 * 
	 * The default value is *false*.
	 */
	public void setSyncRect(boolean syncRect)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_SYNC_RECT, (short)5);
		bb.put((byte)(syncRect ? 1 : 0));

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns *true* if synchronous rectification is enabled, *false* otherwise.
	 */
	public boolean isSyncRect() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_IS_SYNC_RECT, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_IS_SYNC_RECT);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		boolean syncRect = (bb.get()) != 0;

		return syncRect;
	}

	/**
	 * Sets the time base of the velocity and the acceleration of the stepper brick
	 * (in seconds).
	 * 
	 * For example, if you want to make one step every 1.5 seconds, you can set 
	 * the time base to 15 and the velocity to 10. Now the velocity is 
	 * 10steps/15s = 1steps/1.5s.
	 * 
	 * The default value is 1.
	 * 
	 * .. versionadded:: 1.1.6~(Firmware)
	 */
	public void setTimeBase(long timeBase)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_TIME_BASE, (short)8);
		bb.putInt((int)timeBase);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the time base as set by {@link BrickStepper.setTimeBase}.
	 * 
	 * .. versionadded:: 1.1.6~(Firmware)
	 */
	public long getTimeBase() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_TIME_BASE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_TIME_BASE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long timeBase = IPConnection.unsignedInt(bb.getInt());

		return timeBase;
	}

	/**
	 * Returns the following parameters: The current velocity,
	 * the current position, the remaining steps, the stack voltage, the external
	 * voltage and the current consumption of the stepper motor.
	 * 
	 * There is also a listener for this function, see {@link com.tinkerforge.BrickStepper.AllDataListener}.
	 * 
	 * .. versionadded:: 1.1.6~(Firmware)
	 */
	public AllData getAllData() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ALL_DATA, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ALL_DATA);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		AllData obj = new AllData();
		obj.currentVelocity = IPConnection.unsignedShort(bb.getShort());
		obj.currentPosition = (bb.getInt());
		obj.remainingSteps = (bb.getInt());
		obj.stackVoltage = IPConnection.unsignedShort(bb.getShort());
		obj.externalVoltage = IPConnection.unsignedShort(bb.getShort());
		obj.currentConsumption = IPConnection.unsignedShort(bb.getShort());

		return obj;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickStepper.AllDataListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * .. versionadded:: 1.1.6~(Firmware)
	 */
	public void setAllDataPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_ALL_DATA_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickStepper.setAllDataPeriod}.
	 * 
	 * .. versionadded:: 1.1.6~(Firmware)
	 */
	public long getAllDataPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ALL_DATA_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ALL_DATA_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

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
	 * .. versionadded:: 1.1.4~(Firmware)
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
	 * .. versionadded:: 1.1.4~(Firmware)
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
		} else if(o instanceof AllDataListener) {
			listenerObjects[CALLBACK_ALL_DATA] = o;
		} else if(o instanceof NewStateListener) {
			listenerObjects[CALLBACK_NEW_STATE] = o;
		}
	}
}