package edu.cmu.sv.sdsp.factory;

import java.util.Random;

public class QueryStringFactory extends DataFactory{
	private static QueryStringFactory current_instance;
	
	public static final QueryStringFactory getInstance() {
		if(current_instance == null) {
			current_instance = new QueryStringFactory();
		}
		rand = new Random();
		
		return current_instance;
	}

	public String generateSensorReadingsQuery() {
		int deviceID=10170102;
		long timestamp=generateRandomTimeStamp();
		String sensorType=generateRandomSensorType();
		String format=generateRandomFormat();
		return String.format("%d/%d/%s/%s",deviceID,timestamp,sensorType,format);
	}

	public String generateSensorReadingTimeDeviceQuery() {
		int deviceID=10170102;
		//long begin_time=generateRandomTimeStamp();
		//long end_time=generateRandomTimeStamp()+generateRandomNumber(10000, 100000);
		String begin_time="1368568896000";
		String end_time="1368568996000";
		String sensorType=generateRandomSensorType();
		String format=generateRandomFormat();
		return String.format("%d/%s/%s/%s/%s",deviceID,begin_time,end_time,sensorType,format);
	}

	public String generateSensorReadingsAllDevicesQuery() {
		// http://einstein.sv.cmu.edu/last_readings_from_all_devices/1368568896000/temp/csv
		long timestamp=generateRandomTimeStamp();
		String sensorType=generateRandomSensorType();
		String format=generateRandomFormat();
		return String.format("%d/%s/%s",timestamp,sensorType,format);
	}
	
	
	public String generateLastestSensorReadings(){
		//http://einstein.sv.cmu.edu/lastest_readings_from_all_devices/temp/csv
		String sensorType=generateRandomSensorType();
		String format=generateRandomFormat();
		return String.format("%s/%s",sensorType,format);
	}

	public String generateSpecificSensorReadingsQuery() {
		// http://einstein.sv.cmu.edu/sensors/10170102/05-14-2013T15:01:36/temp/csv?dateformat=ISO8601
		int deviceID=10170102;
		String time="05-14-2013T15:01:36";
		String sensorType=generateRandomSensorType();
		String format=generateRandomFormat();
		String dateformat="ISO8601";
		return String.format("%d/%s/%s/%s?dateformat=%s", deviceID,time,sensorType,format,dateformat);
	}

	public String generateSensorReadingsTimeRangeQuery() {
		// http://einstein.sv.cmu.edu/sensors/10170102/05-04-2013T12:00:00/05-05-2013T12:00:00/temp/csv?dateformat=ISO8601
		int deviceID=10170102;
		String startTime="05-04-2013T12:00:00";
		String endTime="05-05-2013T12:00:00";
		String sensorType=generateRandomSensorType();
		String format=generateRandomFormat();
		String dateformat="ISO8601";
		return String.format("%d/%s/%s/%s/%s?dateformat=%s", deviceID,startTime,endTime,sensorType,format,dateformat);
	}

	
}
