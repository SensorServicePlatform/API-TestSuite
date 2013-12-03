/*
 * 
 Copyright (c) 2013 CMU-SV.
All rights reserved. This program and the accompanying materials
are made available under the terms of the GNU Public License v2.0
which accompanies this distribution, and is available at
http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 */

package edu.cmu.sv.sdsp.api;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.gson.JsonObject;

import edu.cmu.sv.sdsp.factory.JsonObjectFactory;
import edu.cmu.sv.sdsp.factory.QueryStringFactory;
import edu.cmu.sv.sdsp.util.APIHelper;
import edu.cmu.sv.sdsp.util.APIHelper.ResultType;

@RunWith(JUnit4.class)
public class APIIntegrationTest extends BaseTest {
	private JsonObjectFactory jsonFactory;
	private QueryStringFactory queryStringFactory;

	public APIIntegrationTest() {
		jsonFactory = JsonObjectFactory.getInstance();
		queryStringFactory = QueryStringFactory.getInstance();
	}

	@Rule
	public Timeout globalTimeout = new Timeout(1000*10);
	
	@Test
	public void testNewDevicesShowupInGet() throws IOException {
		JsonObject newDevice = jsonFactory.generateDeviceObject();

		// Add a new device to the system
		String response = APIHelper.addDevice(newDevice);

		// Test if the device was added successfully.
		assertReponseNotNull(response);
		Assert.assertTrue("Error when adding a new device",
				response.equalsIgnoreCase("device saved"));

		// Fetch existing devices in the system
		response = APIHelper.processGetDevices(ResultType.JSON);

		// Test if the device added above is available
		assertReponseNotNull(response);
		Assert.assertTrue("Error in GET for get_devices JSON",
				response.contains(newDevice.get("device_type").toString()));
	}

	@Test
	public void testSensorOperations() throws IOException {
		// Invoke the API to add a sensor type
		String response = APIHelper.addSensorType(jsonFactory
				.generateSensorTypeObject());

		// Tests
		assertReponseNotNull(response);
		Assert.assertTrue("Error when adding a new sensor type",
				response.equalsIgnoreCase(ADD_SENSOR_TYPE_SUCCESSFUL));
		// Invoke the API to add a new device type
		response = APIHelper.addSensor(jsonFactory
				.generateSensorObject("TemperatureSensor"));

		// Tests
		assertReponseNotNull(response);
		Assert.assertTrue("Error when adding a new device",
				response.equalsIgnoreCase(ADD_SENSOR_SUCCESSFUL));
	}

	@Test
	public void testDeviceOperations() throws IOException {
		String response = APIHelper.processGetSensorTypes("firefly_v3",
				ResultType.JSON);

		// Tests
		// 1. Check for NOT NULL
		assertReponseNotNull(response);

		// Invoke the API to add a new device type
		response = APIHelper.addDevice(jsonFactory.generateDeviceObject());

		// Tests
		assertReponseNotNull(response);
		Assert.assertTrue("Error when adding a new device",
				response.equalsIgnoreCase(ADD_DEVICE_SUCCESSFUL));
	}

	@Test
	public void testPostGetSensors() throws IOException {
		// post a sensor
		// then get that sensor
		// check if right
		String response = APIHelper.processPublishSensorReadings(jsonFactory
				.generateSensorReadings());

		// Tests
		assertResponseSaved(response);

		response = APIHelper.processQuerySensorReadings(queryStringFactory
				.generateSensorReadingsQuery());

		// Tests
		assertReponseNotNull(response);
	}

	@Test
	public void testGetReadingsByTimeFrame() throws IOException {
		String response = APIHelper
				.processGetSensorReadingBYTimeDevice(queryStringFactory
						.generateSensorReadingTimeDeviceQuery());
		assertReponseNotNull(response);

		response = APIHelper
				.processGetSensorReadingsAllDevices(queryStringFactory
						.generateSensorReadingsAllDevicesQuery());

		// Tests
		// 1. Check for NOT NULL
		assertReponseNotNull(response);
	}

	@Test
	public void testGetAll() throws IOException {
		// get all devices and all sensor types
		String response = APIHelper.processGetDevices(ResultType.JSON);

		// Tests
		// 1. Check for NOT NULL
		assertReponseNotNull(response);
		// 2. Check if the result is a proper JSON
		getArrayFromJsonString(response);
		
		response = APIHelper
				.processGetSensorReadingsAllDevices(queryStringFactory
						.generateSensorReadingsAllDevicesQuery());

		// Tests
		// 1. Check for NOT NULL
		assertReponseNotNull(response);
	}

	@Test
	public void testLastestReadings() throws IOException {
		// get latest reading for a sensor type in all registered devices
		String response = APIHelper
				.processGetLastestSensorReadings(queryStringFactory
						.generateLastestSensorReadings());

		// Tests
		// 1. Check for NOT NULL
		assertReponseNotNull(response);
		
		
	}

	@Test
	public void testSpecificSensor() throws IOException {
		
		String response = APIHelper
				.processGetSpecificSensorReadings(queryStringFactory
						.generateSpecificSensorReadingsQuery());
		// Tests
		assertReponseNotNull(response);
		response = APIHelper
				.processGetSensorReadingsTimeRange(queryStringFactory
						.generateSensorReadingsTimeRangeQuery());
		// Tests
		assertReponseNotNull(response);
	}
}
