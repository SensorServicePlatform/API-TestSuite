package edu.cmu.sv.sdsp.api;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import edu.cmu.sv.sdsp.factory.JsonObjectFactory;
import edu.cmu.sv.sdsp.factory.QueryStringFactory;
import edu.cmu.sv.sdsp.util.APIHelper;
import edu.cmu.sv.sdsp.util.APIHelper.ResultType;

/**
 * Class to perform individual unit testing on API to check if the API
 * operations work independently.
 * 
 * @author Surya Kiran, Gonghan Wang
 * 
 */
@RunWith(JUnit4.class)
// @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class APIUnitTest extends BaseTest {
	private JsonObjectFactory jsonFactory;
	private QueryStringFactory queryStringFactory;
	private final int TIMEOUT=2*60*1000;//2 minutes

	public APIUnitTest() {
		jsonFactory = JsonObjectFactory.getInstance();
		queryStringFactory = QueryStringFactory.getInstance();
	}

	
	// If the response time>timeout, cause a failure.
	@Rule
	public Timeout globalTimeout = new Timeout(TIMEOUT);
	
	@Test
	public void testGetDevicesJson() throws IOException {
		String response = APIHelper.processGetDevices(ResultType.JSON);

		// Tests
		// 1. Check for NOT NULL
		assertReponseNotNull(response);
		// 2. Check if the result is a proper JSON
		getArrayFromJsonString(response);
	}

	@Test
	public void testGetDevicesCSV() throws IOException {
		String response = APIHelper.processGetDevices(ResultType.CSV);

		// Tests
		assertReponseNotNull(response);
	}

	@Test
	public void testPublishSensorReadings() throws IOException {
		String response = APIHelper.processPublishSensorReadings(jsonFactory
				.generateSensorReadings());

		// Tests
		assertResponseSaved(response);
	}

	@Test
	public void testQuerySensorReadings() throws IOException {
		String response = APIHelper
				.processQuerySensorReadings(queryStringFactory
						.generateSensorReadingsQuery());

		// Tests
		assertReponseNotNull(response);
	}

	// GET SENSOR READINGS IN A TIME RANGE FOR A DEVICE
	@Test
	public void testSensorReadingBYTimeDevice() throws IOException {
		String response = APIHelper
				.processGetSensorReadingBYTimeDevice(queryStringFactory
						.generateSensorReadingTimeDeviceQuery());
		assertReponseNotNull(response);
	}

	@Test
	public void testSensorReadingsAllDevices() throws IOException {
		String response = APIHelper
				.processGetSensorReadingsAllDevices(queryStringFactory
						.generateSensorReadingsAllDevicesQuery());

		// Tests
		// 1. Check for NOT NULL
		assertReponseNotNull(response);
	}

	@Test
	public void testLastestSensorReadings() throws IOException {
		String response = APIHelper
				.processGetLastestSensorReadings(queryStringFactory
						.generateLastestSensorReadings());

		// Tests
		// 1. Check for NOT NULL
		assertReponseNotNull(response);
	}

	@Test
	public void testGetSensorTypesJson() throws IOException {
		String response = APIHelper.processGetSensorTypes("firefly_v3",
				ResultType.JSON);

		// Tests
		// 1. Check for NOT NULL
		assertReponseNotNull(response);
	}

	@Test
	public void testGetSensorTypesCSV() throws IOException {
		String response = APIHelper.processGetSensorTypes("firefly_v3",
				ResultType.CSV);

		// Tests
		assertReponseNotNull(response);
	}

	@Test
	public void testAddSensorType() throws IOException {
		// Invoke the API to add a sensor type
		String response = APIHelper.addSensorType(jsonFactory
				.generateSensorTypeObject());

		// Tests
		assertReponseNotNull(response);
		Assert.assertTrue("Error when adding a new sensor type",
				response.equalsIgnoreCase(ADD_SENSOR_TYPE_SUCCESSFUL));
	}

	@Test
	public void testAddDeviceType() throws IOException {
		// Invoke the API to add a new device type
		String response = APIHelper.addDeviceType(jsonFactory
				.generateDeviceTypeObject());

		// Tests
		assertReponseNotNull(response);
		Assert.assertTrue("Error when adding a new device type",
				response.equalsIgnoreCase(ADD_DEVICE_TYPE_SUCCESSFUL));
	}

	@Test
	public void testAddDevice() throws IOException {
		// Invoke the API to add a new device type
		String response = APIHelper.addDevice(jsonFactory
				.generateDeviceObject());

		// Tests
		assertReponseNotNull(response);
		Assert.assertTrue("Error when adding a new device",
				response.equalsIgnoreCase(ADD_DEVICE_SUCCESSFUL));
	}

	@Test
	public void testAddSensor() throws IOException {
		// Invoke the API to add a new device type
		String response = APIHelper.addSensor(jsonFactory
				.generateSensorObject("TemperatureSensor"));

		// Tests
		assertReponseNotNull(response);
		Assert.assertTrue("Error when adding a new device",
				response.equalsIgnoreCase(ADD_SENSOR_SUCCESSFUL));
	}
	
	@Test
	public void testSpecificSensorReadings() throws IOException {
		String response = APIHelper
				.processGetSpecificSensorReadings(queryStringFactory
						.generateSpecificSensorReadingsQuery());
		// Tests
		assertReponseNotNull(response);
	}

	@Test
	public void testSensorReadingsTimeRange() throws IOException {
		String response = APIHelper
				.processGetSensorReadingsTimeRange(queryStringFactory
						.generateSensorReadingsTimeRangeQuery());
		// Tests
		assertReponseNotNull(response);
	}

}
