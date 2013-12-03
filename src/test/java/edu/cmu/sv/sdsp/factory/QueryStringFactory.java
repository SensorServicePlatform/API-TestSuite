/*
 * 
 Copyright (c) 2013 CMU-SV.
All rights reserved. This program and the accompanying materials
are made available under the terms of the GNU Public License v2.0
which accompanies this distribution, and is available at
http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 */
package edu.cmu.sv.sdsp.factory;

import java.util.Random;

/**
 * This is a factory for generations of query string for API tests. This class
 * creates all query strings needed and generate random data to make the tests
 * more adequate.
 * 
 * @author Gonghan Wang
 * 
 */
public class QueryStringFactory extends DataFactory {
	/**
	 * This is the only factory instance. Can't be initialized outside this
	 * class.
	 */
	private static QueryStringFactory current_instance;

	/**
	 * @return the only instance(singleton) of this factory.
	 */
	public static final QueryStringFactory getInstance() {
		if (current_instance == null) {
			current_instance = new QueryStringFactory();
		}
		rand = new Random();

		return current_instance;
	}

	/**
	 * To generate sensor reading query.
	 * 
	 * @return the query string.
	 */
	public String generateSensorReadingsQuery() {
		int deviceID = 10170102;
		long timestamp = generateRandomTimeStamp();
		String sensorType = "temp";
		String format = generateRandomFormat();
		return String.format("%d/%d/%s/%s", deviceID, timestamp, sensorType,
				format);
	}

	/**
	 * To generate query for sensor reading of a device at a timeframe 
	 * 
	 * @return the query string for this request.
	 */
	public String generateSensorReadingTimeDeviceQuery() {
		int deviceID = 10170102;
		// long begin_time=generateRandomTimeStamp();
		// long end_time=generateRandomTimeStamp()+generateRandomNumber(10000,
		// 100000);
		String begin_time = "1368568896000";
		String end_time = "1368568996000";
		String sensorType = generateRandomSensorType();
		String format = generateRandomFormat();
		return String.format("%d/%s/%s/%s/%s", deviceID, begin_time, end_time,
				sensorType, format);
	}

	/**
	 * To generate a query of sensor readings of all devices.
	 * 
	 * @return the query string for this request.
	 */
	public String generateSensorReadingsAllDevicesQuery() {
		// http://einstein.sv.cmu.edu/last_readings_from_all_devices/1368568896000/temp/csv
		long timestamp = generateRandomTimeStamp();
		String sensorType = "temp";
		String format = generateRandomFormat();
		return String.format("%d/%s/%s", timestamp, sensorType, format);
	}

	/**
	 * To generate a query of latest sensor readings.
	 * 
	 * @return the query string for this request.
	 */
	public String generateLastestSensorReadings() {
		// http://einstein.sv.cmu.edu/lastest_readings_from_all_devices/temp/csv
		String sensorType = "temp";
		String format = generateRandomFormat();
		return String.format("%s/%s", sensorType, format);
	}

	/**
	 * To generate a query of specific sensor readings.
	 * 
	 * @return the query string for this request.
	 */
	public String generateSpecificSensorReadingsQuery() {
		// http://einstein.sv.cmu.edu/sensors/10170102/05-14-2013T15:01:36/temp/csv?dateformat=ISO8601
		int deviceID = 10170102;
		String time = "05-14-2013T15:01:36";
		String sensorType = "temp";
		String format = generateRandomFormat();
		String dateformat = "ISO8601";
		return String.format("%d/%s/%s/%s?dateformat=%s", deviceID, time,
				sensorType, format, dateformat);
	}

	/**
	 * To generate a query of sensor readings during a timeframe range.
	 * 
	 * @return the query string for this request.
	 */
	public String generateSensorReadingsTimeRangeQuery() {
		// http://einstein.sv.cmu.edu/sensors/10170102/05-04-2013T12:00:00/05-05-2013T12:00:00/temp/csv?dateformat=ISO8601
		int deviceID = 10170102;
		String startTime = "05-04-2013T12:00:00";
		String endTime = "05-05-2013T12:00:00";
		String sensorType = "temp";
		String format = generateRandomFormat();
		String dateformat = "ISO8601";
		return String.format("%d/%s/%s/%s/%s?dateformat=%s", deviceID,
				startTime, endTime, sensorType, format, dateformat);
	}

}
