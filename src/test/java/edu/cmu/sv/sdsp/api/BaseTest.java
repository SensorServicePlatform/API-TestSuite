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

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import edu.cmu.sv.sdsp.util.APIConstants;

/**
* This class will contain all the common code between a 
* GET and POST test.
* 
* @author Surya Kiran
*/
public class BaseTest implements APIConstants {
	// Add any code that is common to all Test Case classes
	private static final Logger log = Logger.getLogger(BaseTest.class);
	private Date testStart, testEnd;
	
	/**
	 * This method is called before every test case is run.
	 */
	@Before
	public void recordStartTimeForTest() {
		log.debug("====================================================");
		testStart = new Date();
		log.debug("Test case started at :: " + testStart.getTime());
	}
	
	/**
	 * This method is called after every test case is run.
	 */
	@After
	public void recordEndTimeForTest() {
		testEnd = new Date();
		log.debug("Test case completed at :: " + testEnd.getTime());
		log.debug("Time taken to process this testcase: "
				+ (testEnd.getTime() - testStart.getTime()) + " ms\n");
	}
	
	/**
	 * Utility method to check if the  object is NOT NULL.
	 * 
	 * @param obj - Object to be asserted for NOT NULL
	 */
	protected void assertReponseNotNull(Object obj) {
		Assert.assertTrue("Error :: Object passed for NOT NULL verification was found NULL.", obj != null);
	}
	
	/*
	 * 
	 */
	protected void assertResponseSaved(String str){
		Assert.assertTrue("Error :: The request is not SAVED.", str.equals("saved"));
	}
	
	
	/**
	 * Utility method to convert a JsonArray represented in a string
	 * into a JsonArray object.
	 * 
	 * @param jsonStr - JsonArray represented in a string
	 * 
	 * @return - String content as a JsonArray
	 */
	protected JsonArray getArrayFromJsonString(String jsonStr) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(jsonStr);
		JsonArray jsonArray = element.getAsJsonArray();
		
		return jsonArray;
	}
}
