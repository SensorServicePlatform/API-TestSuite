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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.gson.JsonObject;

import edu.cmu.sv.sdsp.factory.JsonObjectFactory;
import edu.cmu.sv.sdsp.util.APIHelper;
import edu.cmu.sv.sdsp.util.APIHelper.ResultType;

@RunWith(JUnit4.class)
public class APIIntegrationTest extends BaseTest {
	private JsonObjectFactory jsonFactory;
	
	public APIIntegrationTest() {
		jsonFactory = JsonObjectFactory.getInstance();
	}
	
	@Test
	public void testNewDevicesShowupInGet() throws IOException {
		JsonObject newDevice = jsonFactory.generateDeviceObject();
		
		// Add a new device to the system
		String response = APIHelper.addDevice(newDevice);
		
		// Test if the device was added successfully.
		assertReponseNotNull(response);
		Assert.assertTrue("Error when adding a new device", response.equalsIgnoreCase("device saved"));
		
		// Fetch existing devices in the system
		response = APIHelper.processGetDevices(ResultType.JSON);
		
		// Test if the device added above is available
		assertReponseNotNull(response);
		Assert.assertTrue("Error in GET for get_devices JSON", 
				response.contains(newDevice.get("device_type").toString()));
	}
}
