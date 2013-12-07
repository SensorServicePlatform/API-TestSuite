/**
 * Copyright (c) 2013 Carnegie Mellon University Silicon Valley. 
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available
 * under the terms of dual licensing, one of which is the GNU 
 * Public License v2.0 which accompanies this distribution, and is 
 * available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * 
 * Please contact http://www.cmu.edu/silicon-valley/ for more specific
 * information.
 */
package edu.cmu.sv.sdsp.factory;

import java.util.Random;

import com.google.gson.JsonObject;

/**
 * This is a factory for generations of query string for API tests. This class
 * creates all query strings needed and generate random data to make the tests
 * more adequate.
 * 
 * @author Gonghan Wang, Surya Kiran
 * 
 */
public class JsonObjectFactory extends DataFactory {
	/**
	 * This is the only factory instance. Can't be initialized outside this
	 * class.
	 */
	private static JsonObjectFactory current_instance;

	/**
	 * @return the only instance(singleton) of this factory.
	 */
	public static final JsonObjectFactory getInstance() {
		if (current_instance == null) {
			current_instance = new JsonObjectFactory();
		}
		rand = new Random();

		return current_instance;
	}

	/**
	 * To generate sensor jsonobject.
	 * 
	 * @param sensorType
	 *            - type of sensor, such as temp, acc_x
	 * @return the json object of a sensor.
	 */
	public JsonObject generateSensorObject(String sensorType) {
		// Prepare a new Sensor type Json Object
		JsonObject sensor = new JsonObject();
		sensor.addProperty("print_name", "Sensor_" + generateRandomNumber());
		sensor.addProperty("sensor_type", sensorType);
		sensor.addProperty("device_id", generateRandomMacAddress());
		sensor.addProperty("user_defined_fields", "None");

		return sensor;
	}

	/**
	 * To generate a sensor type json object.
	 * 
	 * @return the json object of a sensor type.
	 */
	public JsonObject generateSensorTypeObject() {
		JsonObject sensorType = new JsonObject();
		sensorType.addProperty("sensor_type", "SensorType_"
				+ generateRandomNumber());
		sensorType.addProperty("user_defined_fields", "Test Value "
				+ generateRandomNumber());

		return sensorType;
	}

	/**
	 * To generate a sensor reading json object.
	 * 
	 * @return the json object of a sensor reading.
	 */
	public JsonObject generateSensorReadings() {
		JsonObject sensorReadings = new JsonObject();
		sensorReadings.addProperty("id", "10170202");
		sensorReadings.addProperty("timestamp", "1373566899000");
		int temp = generateRandomNumber(50, 100);
		sensorReadings.addProperty("temp", "" + temp);
		return sensorReadings;
	}

	/**
	 * To generate a device json object.
	 * 
	 * @return the json object of a device object.
	 */
	public JsonObject generateDeviceObject() {
		// Prepare a new Device type Json Object
		JsonObject device = new JsonObject();
		device.addProperty("device_type", "Device_" + generateRandomNumber());
		device.addProperty("device_agent", "DeviceAgent_"
				+ generateRandomNumber());
		device.addProperty("device_id", generateRandomMacAddress());
		device.addProperty("location_description",
				randomizeLocationBetween23and19());
		device.addProperty("latitude", generateRandomNumber(0, 99) + " "
				+ generateRandomNumber(0, 99));
		device.addProperty("longitude", generateRandomNumber(0, 99) + " "
				+ generateRandomNumber(0, 99));
		device.addProperty("altitude", generateRandomNumber(0, 900) + "");
		device.addProperty("position_format_system", "LLA");

		// Prepare URI as a user defined field and send it
		JsonObject userDefinedFeilds = new JsonObject();
		userDefinedFeilds.addProperty("uri",
				"URI_" + generateRandomNumber(0, 99999));

		device.addProperty("user_defined_fields", userDefinedFeilds.toString());

		return device;
	}

	/**
	 * To generate a device type json object.
	 * 
	 * @return the json object of a device type.
	 */
	public JsonObject generateDeviceTypeObject() {
		// Prepare a new Device type Json Object
		JsonObject deviceType = new JsonObject();
		deviceType.addProperty("device_type_name", "DeviceType_"
				+ generateRandomNumber());
		deviceType.addProperty("manufacturer", "Tesla");
		deviceType.addProperty("version", "1." + generateRandomNumber(0, 10));
		deviceType.addProperty("user_defined_fields", "Test Value "
				+ generateRandomNumber());

		return deviceType;
	}
}
