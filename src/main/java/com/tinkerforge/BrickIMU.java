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
 * Device for sensing acceleration, magnetic field and angular velocity
 */
public class BrickIMU extends Device {
	private final static byte FUNCTION_GET_ACCELERATION = (byte)1;
	private final static byte FUNCTION_GET_MAGNETIC_FIELD = (byte)2;
	private final static byte FUNCTION_GET_ANGULAR_VELOCITY = (byte)3;
	private final static byte FUNCTION_GET_ALL_DATA = (byte)4;
	private final static byte FUNCTION_GET_ORIENTATION = (byte)5;
	private final static byte FUNCTION_GET_QUATERNION = (byte)6;
	private final static byte FUNCTION_GET_IMU_TEMPERATURE = (byte)7;
	private final static byte FUNCTION_LEDS_ON = (byte)8;
	private final static byte FUNCTION_LEDS_OFF = (byte)9;
	private final static byte FUNCTION_ARE_LEDS_ON = (byte)10;
	private final static byte FUNCTION_SET_ACCELERATION_RANGE = (byte)11;
	private final static byte FUNCTION_GET_ACCELERATION_RANGE = (byte)12;
	private final static byte FUNCTION_SET_MAGNETOMETER_RANGE = (byte)13;
	private final static byte FUNCTION_GET_MAGNETOMETER_RANGE = (byte)14;
	private final static byte FUNCTION_SET_CONVERGENCE_SPEED = (byte)15;
	private final static byte FUNCTION_GET_CONVERGENCE_SPEED = (byte)16;
	private final static byte FUNCTION_SET_CALIBRATION = (byte)17;
	private final static byte FUNCTION_GET_CALIBRATION = (byte)18;
	private final static byte FUNCTION_SET_ACCELERATION_PERIOD = (byte)19;
	private final static byte FUNCTION_GET_ACCELERATION_PERIOD = (byte)20;
	private final static byte FUNCTION_SET_MAGNETIC_FIELD_PERIOD = (byte)21;
	private final static byte FUNCTION_GET_MAGNETIC_FIELD_PERIOD = (byte)22;
	private final static byte FUNCTION_SET_ANGULAR_VELOCITY_PERIOD = (byte)23;
	private final static byte FUNCTION_GET_ANGULAR_VELOCITY_PERIOD = (byte)24;
	private final static byte FUNCTION_SET_ALL_DATA_PERIOD = (byte)25;
	private final static byte FUNCTION_GET_ALL_DATA_PERIOD = (byte)26;
	private final static byte FUNCTION_SET_ORIENTATION_PERIOD = (byte)27;
	private final static byte FUNCTION_GET_ORIENTATION_PERIOD = (byte)28;
	private final static byte FUNCTION_SET_QUATERNION_PERIOD = (byte)29;
	private final static byte FUNCTION_GET_QUATERNION_PERIOD = (byte)30;
	private final static byte CALLBACK_ACCELERATION = (byte)31;
	private final static byte CALLBACK_MAGNETIC_FIELD = (byte)32;
	private final static byte CALLBACK_ANGULAR_VELOCITY = (byte)33;
	private final static byte CALLBACK_ALL_DATA = (byte)34;
	private final static byte CALLBACK_ORIENTATION = (byte)35;
	private final static byte CALLBACK_QUATERNION = (byte)36;
	private final static byte FUNCTION_RESET = (byte)243;
	private final static byte FUNCTION_GET_CHIP_TEMPERATURE = (byte)242;

	public class Acceleration {
		public short x;
		public short y;
		public short z;

		public String toString() {
			return "[" + "x = " + x + ", " + "y = " + y + ", " + "z = " + z + "]";
		}
	}

	public class MagneticField {
		public short x;
		public short y;
		public short z;

		public String toString() {
			return "[" + "x = " + x + ", " + "y = " + y + ", " + "z = " + z + "]";
		}
	}

	public class AngularVelocity {
		public short x;
		public short y;
		public short z;

		public String toString() {
			return "[" + "x = " + x + ", " + "y = " + y + ", " + "z = " + z + "]";
		}
	}

	public class AllData {
		public short accX;
		public short accY;
		public short accZ;
		public short magX;
		public short magY;
		public short magZ;
		public short angX;
		public short angY;
		public short angZ;
		public short temperature;

		public String toString() {
			return "[" + "accX = " + accX + ", " + "accY = " + accY + ", " + "accZ = " + accZ + ", " + "magX = " + magX + ", " + "magY = " + magY + ", " + "magZ = " + magZ + ", " + "angX = " + angX + ", " + "angY = " + angY + ", " + "angZ = " + angZ + ", " + "temperature = " + temperature + "]";
		}
	}

	public class Orientation {
		public short roll;
		public short pitch;
		public short yaw;

		public String toString() {
			return "[" + "roll = " + roll + ", " + "pitch = " + pitch + ", " + "yaw = " + yaw + "]";
		}
	}

	public class Quaternion {
		public float x;
		public float y;
		public float z;
		public float w;

		public String toString() {
			return "[" + "x = " + x + ", " + "y = " + y + ", " + "z = " + z + ", " + "w = " + w + "]";
		}
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickIMU.setAccelerationPeriod}. The parameters are the acceleration
	 * for the x, y and z axis.
	 */
	public interface AccelerationListener {
		public void acceleration(short x, short y, short z);
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickIMU.setMagneticFieldPeriod}. The parameters are the magnetic field
	 * for the x, y and z axis.
	 */
	public interface MagneticFieldListener {
		public void magneticField(short x, short y, short z);
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickIMU.setAngularVelocityPeriod}. The parameters are the angular velocity
	 * for the x, y and z axis.
	 */
	public interface AngularVelocityListener {
		public void angularVelocity(short x, short y, short z);
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickIMU.setAllDataPeriod}. The parameters are the acceleration,
	 * the magnetic field and the angular velocity for the x, y and z axis as
	 * well as the temperature of the IMU Brick.
	 */
	public interface AllDataListener {
		public void allData(short accX, short accY, short accZ, short magX, short magY, short magZ, short angX, short angY, short angZ, short temperature);
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickIMU.setOrientationPeriod}. The parameters are the orientation
	 * (roll, pitch and yaw) of the IMU Brick in Euler angles. See
	 * {@link BrickIMU.getOrientation} for details.
	 */
	public interface OrientationListener {
		public void orientation(short roll, short pitch, short yaw);
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link BrickIMU.setQuaternionPeriod}. The parameters are the orientation
	 * (x, y, z, w) of the IMU Brick in quaternions. See {@link BrickIMU.getQuaternion}
	 * for details.
	 */
	public interface QuaternionListener {
		public void quaternion(float x, float y, float z, float w);
	}

	/**
	 * Creates an object with the unique device ID \c uid. This object can
	 * then be added to the IP connection.
	 */
	public BrickIMU(String uid) {
		super(uid);

		expectedName = "IMU Brick";

		bindingVersion[0] = 1;
		bindingVersion[1] = 0;
		bindingVersion[2] = 1;

		callbacks[CALLBACK_ACCELERATION] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short x = (bb.getShort());
				short y = (bb.getShort());
				short z = (bb.getShort());

				((AccelerationListener)listenerObjects[CALLBACK_ACCELERATION]).acceleration(x, y, z);
			}
		};

		callbacks[CALLBACK_MAGNETIC_FIELD] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short x = (bb.getShort());
				short y = (bb.getShort());
				short z = (bb.getShort());

				((MagneticFieldListener)listenerObjects[CALLBACK_MAGNETIC_FIELD]).magneticField(x, y, z);
			}
		};

		callbacks[CALLBACK_ANGULAR_VELOCITY] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short x = (bb.getShort());
				short y = (bb.getShort());
				short z = (bb.getShort());

				((AngularVelocityListener)listenerObjects[CALLBACK_ANGULAR_VELOCITY]).angularVelocity(x, y, z);
			}
		};

		callbacks[CALLBACK_ALL_DATA] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short accX = (bb.getShort());
				short accY = (bb.getShort());
				short accZ = (bb.getShort());
				short magX = (bb.getShort());
				short magY = (bb.getShort());
				short magZ = (bb.getShort());
				short angX = (bb.getShort());
				short angY = (bb.getShort());
				short angZ = (bb.getShort());
				short temperature = (bb.getShort());

				((AllDataListener)listenerObjects[CALLBACK_ALL_DATA]).allData(accX, accY, accZ, magX, magY, magZ, angX, angY, angZ, temperature);
			}
		};

		callbacks[CALLBACK_ORIENTATION] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short roll = (bb.getShort());
				short pitch = (bb.getShort());
				short yaw = (bb.getShort());

				((OrientationListener)listenerObjects[CALLBACK_ORIENTATION]).orientation(roll, pitch, yaw);
			}
		};

		callbacks[CALLBACK_QUATERNION] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 4, data.length - 4);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				float x = (bb.getFloat());
				float y = (bb.getFloat());
				float z = (bb.getFloat());
				float w = (bb.getFloat());

				((QuaternionListener)listenerObjects[CALLBACK_QUATERNION]).quaternion(x, y, z, w);
			}
		};
	}

	/**
	 * Returns the calibrated acceleration from the accelerometer for the 
	 * x, y and z axis in mG (G/1000, 1G = 9.80605m/s²).
	 * 
	 * If you want to get the acceleration periodically, it is recommended 
	 * to use the listener {@link com.tinkerforge.BrickIMU.AccelerationListener} and set the period with
	 * {@link BrickIMU.setAccelerationPeriod}.
	 */
	public Acceleration getAcceleration() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ACCELERATION, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ACCELERATION);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		Acceleration obj = new Acceleration();
		obj.x = (bb.getShort());
		obj.y = (bb.getShort());
		obj.z = (bb.getShort());

		return obj;
	}

	/**
	 * Returns the calibrated magnetic field from the magnetometer for the 
	 * x, y and z axis in mG (Milligauss or Nanotesla).
	 * 
	 * If you want to get the magnetic field periodically, it is recommended 
	 * to use the listener {@link com.tinkerforge.BrickIMU.MagneticFieldListener} and set the period with
	 * {@link BrickIMU.setMagneticFieldPeriod}.
	 */
	public MagneticField getMagneticField() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_MAGNETIC_FIELD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_MAGNETIC_FIELD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		MagneticField obj = new MagneticField();
		obj.x = (bb.getShort());
		obj.y = (bb.getShort());
		obj.z = (bb.getShort());

		return obj;
	}

	/**
	 * Returns the calibrated angular velocity from the gyroscope for the 
	 * x, y and z axis in °/17.5s (you have to divide by 17.5 to
	 * get the value in °/s).
	 * 
	 * If you want to get the angular velocity periodically, it is recommended 
	 * to use the listener {@link com.tinkerforge.BrickIMU.AngularVelocityListener} and set the period with
	 * {@link BrickIMU.setAngularVelocityPeriod}.
	 */
	public AngularVelocity getAngularVelocity() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ANGULAR_VELOCITY, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ANGULAR_VELOCITY);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		AngularVelocity obj = new AngularVelocity();
		obj.x = (bb.getShort());
		obj.y = (bb.getShort());
		obj.z = (bb.getShort());

		return obj;
	}

	/**
	 * Returns the data from {@link BrickIMU.getAcceleration}, {@link BrickIMU.getMagneticField}
	 * and {@link BrickIMU.getAngularVelocity} as well as the temperature of the IMU Brick.
	 * 
	 * The temperature is given in °C/100.
	 * 
	 * If you want to get the data periodically, it is recommended 
	 * to use the listener {@link com.tinkerforge.BrickIMU.AllDataListener} and set the period with
	 * {@link BrickIMU.setAllDataPeriod}.
	 */
	public AllData getAllData() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ALL_DATA, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ALL_DATA);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		AllData obj = new AllData();
		obj.accX = (bb.getShort());
		obj.accY = (bb.getShort());
		obj.accZ = (bb.getShort());
		obj.magX = (bb.getShort());
		obj.magY = (bb.getShort());
		obj.magZ = (bb.getShort());
		obj.angX = (bb.getShort());
		obj.angY = (bb.getShort());
		obj.angZ = (bb.getShort());
		obj.temperature = (bb.getShort());

		return obj;
	}

	/**
	 * Returns the current orientation (roll, pitch, yaw) of the IMU Brick as Euler
	 * angles in one-hundredth degree. Note that Euler angles always experience a
	 * `gimbal lock <http://en.wikipedia.org/wiki/Gimbal_lock>`__.
	 * 
	 * We recommend that you use quaternions instead.
	 * 
	 * The order to sequence in which the orientation values should be applied is 
	 * roll, yaw, pitch. 
	 * 
	 * If you want to get the orientation periodically, it is recommended 
	 * to use the listener {@link com.tinkerforge.BrickIMU.OrientationListener} and set the period with
	 * {@link BrickIMU.setOrientationPeriod}.
	 */
	public Orientation getOrientation() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ORIENTATION, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ORIENTATION);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		Orientation obj = new Orientation();
		obj.roll = (bb.getShort());
		obj.pitch = (bb.getShort());
		obj.yaw = (bb.getShort());

		return obj;
	}

	/**
	 * Returns the current orientation (x, y, z, w) of the IMU as 
	 * `quaternions <http://en.wikipedia.org/wiki/Quaternions_and_spatial_rotation>`__.
	 * 
	 * You can go from quaternions to Euler angles with the following formula::
	 * 
	 *  roll  = atan2(2*y*w - 2*x*z, 1 - 2*y*y - 2*z*z)
	 *  pitch = atan2(2*x*w - 2*y*z, 1 - 2*x*x - 2*z*z)
	 *  yaw   =  asin(2*x*y + 2*z*w)
	 * 
	 * This process is not reversible, because of the 
	 * `gimbal lock <http://en.wikipedia.org/wiki/Gimbal_lock>`__.
	 * 
	 * Converting the quaternions to an OpenGL transformation matrix is
	 * possible with the following formula::
	 * 
	 *  matrix = [[1 - 2*(y*y + z*z),     2*(x*y - w*z),     2*(x*z + w*y), 0],
	 *            [    2*(x*y + w*z), 1 - 2*(x*x + z*z),     2*(y*z - w*x), 0],
	 *            [    2*(x*z - w*y),     2*(y*z + w*x), 1 - 2*(x*x + y*y), 0],
	 *            [                0,                 0,                 0, 1]]
	 * 
	 * If you want to get the quaternions periodically, it is recommended 
	 * to use the listener {@link com.tinkerforge.BrickIMU.QuaternionListener} and set the period with
	 * {@link BrickIMU.setQuaternionPeriod}.
	 */
	public Quaternion getQuaternion() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_QUATERNION, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_QUATERNION);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		Quaternion obj = new Quaternion();
		obj.x = (bb.getFloat());
		obj.y = (bb.getFloat());
		obj.z = (bb.getFloat());
		obj.w = (bb.getFloat());

		return obj;
	}

	/**
	 * Returns the temperature of the IMU Brick. The temperature is given in 
	 * °C/100.
	 */
	public short getIMUTemperature() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_IMU_TEMPERATURE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_IMU_TEMPERATURE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short temperature = (bb.getShort());

		return temperature;
	}

	/**
	 * Turns the orientation and direction LEDs of the IMU Brick on.
	 */
	public void ledsOn()  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_LEDS_ON, (short)4);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Turns the orientation and direction LEDs of the IMU Brick off.
	 */
	public void ledsOff()  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_LEDS_OFF, (short)4);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns *true* if the orientation and direction LEDs of the IMU Brick
	 * are on, *false* otherwise.
	 */
	public boolean areLedsOn() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_ARE_LEDS_ON, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_ARE_LEDS_ON);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		boolean leds = (bb.get()) != 0;

		return leds;
	}

	/**
	 * Not implemented yet.
	 */
	public void setAccelerationRange(short range)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_ACCELERATION_RANGE, (short)5);
		bb.put((byte)range);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Not implemented yet.
	 */
	public short getAccelerationRange() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ACCELERATION_RANGE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ACCELERATION_RANGE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short range = IPConnection.unsignedByte(bb.get());

		return range;
	}

	/**
	 * Not implemented yet.
	 */
	public void setMagnetometerRange(short range)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_MAGNETOMETER_RANGE, (short)5);
		bb.put((byte)range);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Not implemented yet.
	 */
	public short getMagnetometerRange() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_MAGNETOMETER_RANGE, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_MAGNETOMETER_RANGE);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short range = IPConnection.unsignedByte(bb.get());

		return range;
	}

	/**
	 * Sets the convergence speed of the IMU Brick in °/s. The convergence speed 
	 * determines how the different sensor measurements are fused.
	 * 
	 * If the orientation of the IMU Brick is off by 10° and the convergence speed is 
	 * set to 20°/s, it will take 0.5s until the orientation is corrected. However,
	 * if the correct orientation is reached and the convergence speed is too high,
	 * the orientation will fluctuate with the fluctuations of the accelerometer and
	 * the magnetometer.
	 * 
	 * If you set the convergence speed to 0, practically only the gyroscope is used
	 * to calculate the orientation. This gives very smooth movements, but errors of the
	 * gyroscope will not be corrected. If you set the convergence speed to something
	 * above 500, practically only the magnetometer and the accelerometer are used to
	 * calculate the orientation. In this case the movements are abrupt and the values
	 * will fluctuate, but there won't be any errors that accumulate over time.
	 * 
	 * In an application with high angular velocities, we recommend a high convergence
	 * speed, so the errors of the gyroscope can be corrected fast. In applications with
	 * only slow movements we recommend a low convergence speed. You can change the
	 * convergence speed on the fly. So it is possible (and recommended) to increase 
	 * the convergence speed before an abrupt movement and decrease it afterwards 
	 * again.
	 * 
	 * You might want to play around with the convergence speed in the Brick Viewer to
	 * get a feeling for a good value for your application.
	 * 
	 * The default value is 30.
	 */
	public void setConvergenceSpeed(int speed)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_CONVERGENCE_SPEED, (short)6);
		bb.putShort((short)speed);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the convergence speed as set by {@link BrickIMU.setConvergenceSpeed}.
	 */
	public int getConvergenceSpeed() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_CONVERGENCE_SPEED, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CONVERGENCE_SPEED);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int speed = IPConnection.unsignedShort(bb.getShort());

		return speed;
	}

	/**
	 * There are several different types that can be calibrated:
	 * 
	 * \verbatim
	 *  "Type", "Description",        "Values"
	 * 
	 *  "0",    "Accelerometer Gain", "[mul x, mul y, mul z, div x, div y, div z, 0, 0, 0, 0]"
	 *  "1",    "Accelerometer Bias", "[bias x, bias y, bias z, 0, 0, 0, 0, 0, 0, 0]"
	 *  "2",    "Magnetometer Gain",  "[mul x, mul y, mul z, div x, div y, div z, 0, 0, 0, 0]"
	 *  "3",    "Magnetometer Bias",  "[bias x, bias y, bias z, 0, 0, 0, 0, 0, 0, 0]"
	 *  "4",    "Gyroscope Gain",     "[mul x, mul y, mul z, div x, div y, div z, 0, 0, 0, 0]"
	 *  "5",    "Gyroscope Bias",     "[bias xl, bias yl, bias zl, temp l, bias xh, bias yh, bias zh, temp h, 0, 0]"
	 * \endverbatim
	 * 
	 * The calibration via gain and bias is done with the following formula::
	 * 
	 *  new_value = (bias + orig_value) * gain_mul / gain_div
	 * 
	 * If you really want to write your own calibration software, please keep
	 * in mind that you first have to undo the old calibration (set bias to 0 and
	 * gain to 1/1) and that you have to average over several thousand values
	 * to obtain a usable result in the end.
	 * 
	 * The gyroscope bias is highly dependent on the temperature, so you have to
	 * calibrate the bias two times with different temperatures. The values xl, yl, zl 
	 * and temp l are the bias for x, y, z and the corresponding temperature for a 
	 * low temperature. The values xh, yh, zh and temp h are the same for a high 
	 * temperatures. The temperature difference should be at least 5°C. If you have 
	 * a temperature where the IMU Brick is mostly used, you should use this 
	 * temperature for one of the sampling points.
	 * 
	 * \note
	 *  We highly recommend that you use the Brick Viewer to calibrate your
	 *  IMU Brick.
	 */
	public void setCalibration(short typ, short[] data)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_CALIBRATION, (short)25);
		bb.put((byte)typ);
		for(int i = 0; i < 10; i++) {
			bb.putShort((short)data[i]);
		}


		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the calibration for a given type as set by {@link BrickIMU.setCalibration}.
	 */
	public short[] getCalibration(short typ) throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_CALIBRATION, (short)5);
		bb.put((byte)typ);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CALIBRATION);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short[] data = new short[10];
		for(int i = 0; i < 10; i++) {
			data[i] = (bb.getShort());
		}


		return data;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickIMU.AccelerationListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * The default value is 0.
	 */
	public void setAccelerationPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_ACCELERATION_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickIMU.setAccelerationPeriod}.
	 */
	public long getAccelerationPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ACCELERATION_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ACCELERATION_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickIMU.MagneticFieldListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 */
	public void setMagneticFieldPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_MAGNETIC_FIELD_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickIMU.setMagneticFieldPeriod}.
	 */
	public long getMagneticFieldPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_MAGNETIC_FIELD_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_MAGNETIC_FIELD_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickIMU.AngularVelocityListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 */
	public void setAngularVelocityPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_ANGULAR_VELOCITY_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickIMU.setAngularVelocityPeriod}.
	 */
	public long getAngularVelocityPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ANGULAR_VELOCITY_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ANGULAR_VELOCITY_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickIMU.AllDataListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 */
	public void setAllDataPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_ALL_DATA_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickIMU.setAllDataPeriod}.
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
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickIMU.OrientationListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 */
	public void setOrientationPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_ORIENTATION_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickIMU.setOrientationPeriod}.
	 */
	public long getOrientationPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_ORIENTATION_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ORIENTATION_PERIOD);

		bb = ByteBuffer.wrap(response, 4, response.length - 4);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickIMU.QuaternionListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 */
	public void setQuaternionPeriod(long period)  {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_SET_QUATERNION_PERIOD, (short)8);
		bb.putInt((int)period);

		sendRequestNoResponse(bb.array());
	}

	/**
	 * Returns the period as set by {@link BrickIMU.setQuaternionPeriod}.
	 */
	public long getQuaternionPeriod() throws IPConnection.TimeoutException {
		ByteBuffer bb = IPConnection.createRequestBuffer((byte)stackID, FUNCTION_GET_QUATERNION_PERIOD, (short)4);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_QUATERNION_PERIOD);

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
	 * .. versionadded:: 1.0.7~(Firmware)
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
	 * .. versionadded:: 1.0.7~(Firmware)
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
		if(o instanceof AccelerationListener) {
			listenerObjects[CALLBACK_ACCELERATION] = o;
		} else if(o instanceof MagneticFieldListener) {
			listenerObjects[CALLBACK_MAGNETIC_FIELD] = o;
		} else if(o instanceof AngularVelocityListener) {
			listenerObjects[CALLBACK_ANGULAR_VELOCITY] = o;
		} else if(o instanceof AllDataListener) {
			listenerObjects[CALLBACK_ALL_DATA] = o;
		} else if(o instanceof OrientationListener) {
			listenerObjects[CALLBACK_ORIENTATION] = o;
		} else if(o instanceof QuaternionListener) {
			listenerObjects[CALLBACK_QUATERNION] = o;
		}
	}
}