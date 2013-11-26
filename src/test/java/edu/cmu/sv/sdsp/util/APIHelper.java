package edu.cmu.sv.sdsp.util;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

/**
 * Helper class which has utility methods to perform GET and POST operations on
 * all operations supported by the Sensor Data Platform Service API.
 * 
 * @author Surya Kiran, Gonghan Wang
 * 
 */
public class APIHelper {
	// Host name
	public static final String HOST_NAME = "http://einstein.sv.cmu.edu";

	// URL used for GET operation on API to get Devices
	public static final String GET_DEVICES_URL = HOST_NAME + "/get_devices";

	// URL used for GET operation on API to get Sensor Types
	public static final String GET_SENSOR_TYPES_URL = HOST_NAME
			+ "/get_sensor_types";

	// URL used for GET operation on API to get Sensors
	public static final String GET_SENSORS_URL = HOST_NAME + "";

	// URL used to POST a new Sensor Type
	public static final String ADD_SENSOR_TYPE = HOST_NAME + "/add_sensor_type";

	// URL used to POST a new Sensor
	public static final String ADD_SENSOR = HOST_NAME + "/add_sensor";

	// URL used to POST a new Device Type
	public static final String ADD_DEVICE_TYPE = HOST_NAME + "/add_device_type";

	// URL used to POST a new Device
	public static final String ADD_DEVICE = HOST_NAME + "/add_device";

	// URL used to publish sensor readings to sensor data service platform
	public static final String PUBLISH_SENSOR_READINGS = HOST_NAME + "/sensors";

	// URL used to query sensor readings
	public static final String QUERY_SENSOR_READINGS = HOST_NAME + "/sensors";

	// Result types that are returned by
	public static enum ResultType {
		JSON, CSV
	}

	static final Logger log = Logger.getLogger(APIHelper.class);

	/**
	 * Helper method to invoke get_devices depending on the result type.
	 * 
	 * @param resultType
	 *            - JSON or CSV
	 * 
	 * @return - Value returned by API
	 * @throws IOException
	 */

	// https://github.com/SensorServicePlatform/APIs#1
	public static final String processGetDevices(ResultType resultType)
			throws IOException {
		return invokeHttpGet(GET_DEVICES_URL + "/"
				+ resultType.toString().toLowerCase());
	}

	// https://github.com/SensorServicePlatform/APIs#2
	public static final String processGetSensorTypes(String deviceType,
			ResultType resultType) throws IOException {
		return invokeHttpGet(GET_SENSOR_TYPES_URL + "/" + deviceType + "/"
				+ resultType.toString().toLowerCase());
	}

	// https://github.com/SensorServicePlatform/APIs#3
	public static final String processPublishSensorReadings(JsonObject param)
			throws IOException {
		return invokeHttpPost(PUBLISH_SENSOR_READINGS, param.toString());
	}

	// https://github.com/SensorServicePlatform/APIs#4
	public static final String processQuerySensorReadings(String query)
			throws IOException {
		return invokeHttpGet(QUERY_SENSOR_READINGS + "/" + query);
	}

	// https://github.com/SensorServicePlatform/APIs#5
	public static String processGetSensorReadingBYTimeDevice(String query)
			throws IOException {
		return invokeHttpGet(QUERY_SENSOR_READINGS + "/" + query);
	}

	// https://github.com/SensorServicePlatform/APIs#6
	public static String processGetSensorReadingsAllDevices(String query) throws IOException{
		return invokeHttpGet(HOST_NAME + "/last_readings_from_all_devices/" + query);
	}

	// https://github.com/SensorServicePlatform/APIs#7
	public static String processGetLastestSensorReadings(String query) throws IOException{
		return invokeHttpGet(HOST_NAME + "/lastest_readings_from_all_devices/" + query);
	}
	
	// https://github.com/SensorServicePlatform/APIs#8
	public static final String addSensorType(JsonObject param)
			throws IOException {
		return invokeHttpPost(ADD_SENSOR_TYPE, param.toString());
	}

	// https://github.com/SensorServicePlatform/APIs#9
	public static final String addSensor(JsonObject param) throws IOException {
		return invokeHttpPost(ADD_SENSOR, param.toString());
	}

	// https://github.com/SensorServicePlatform/APIs#10
	public static final String addDeviceType(JsonObject param)
			throws IOException {
		return invokeHttpPost(ADD_DEVICE_TYPE, param.toString());
	}

	// https://github.com/SensorServicePlatform/APIs#11
	public static final String addDevice(JsonObject param) throws IOException {
		return invokeHttpPost(ADD_DEVICE, param.toString());
	}
	
	// https://github.com/SensorServicePlatform/APIs#12
	public static final String processGetSpecificSensorReadings(String query) throws IOException{
		return invokeHttpGet(QUERY_SENSOR_READINGS+"/"+ query);
	}
	
	// https://github.com/SensorServicePlatform/APIs#13
	public static final String processGetSensorReadingsTimeRange(
			String query) throws IOException {
		return invokeHttpGet(QUERY_SENSOR_READINGS+"/"+ query);
	}

	/**
	 * Utility function to invoke the HTTP GET operation.
	 * 
	 * @param url
	 *            - URL to request for.
	 * 
	 * @return - Response from server.
	 * 
	 * @throws IOException
	 *             - Cascade Exception if any raised.
	 */
	private static final String invokeHttpGet(String url) throws IOException {
		log.trace("Invoking a GET request for URL: " + url);

		String response = null;
		try {
			response = HttpHelper.httpGet(url);
		} finally {
			log.trace("Response String: " + response);
		}

		return response;
	}

	/**
	 * Utility method to invoke a POST operation.
	 * 
	 * @param url
	 *            - URL to send the POST request on.
	 * @param content
	 *            - Data that should be part of the body of POST request.
	 * 
	 * @return - Response from server.
	 * 
	 * @throws IOException
	 *             - Cascade Exception if any raised.
	 */
	private static final String invokeHttpPost(String url, String content)
			throws IOException {
		log.trace("Invoking POST for URL: " + url);
		log.trace("Data for POST: " + content);

		String response = null;

		try {
			response = HttpHelper.httpPostSensorReading(url, content);
		} finally {
			log.trace("Response String: " + response);
		}

		return response;
	}



}
