package edu.cmu.sv.sdsp.factory;

import java.util.Random;

import com.google.gson.JsonObject;

public class JsonObjectFactory extends DataFactory{
	private static JsonObjectFactory current_instance;
	
	public static final JsonObjectFactory getInstance() {
		if(current_instance == null) {
			current_instance = new JsonObjectFactory();
		}
		rand = new Random();
		
		return current_instance;
	}
	
	
	
	
	public JsonObject generateSensorObject(String sensorType) {
		// Prepare a new Sensor type Json Object
		JsonObject sensor = new JsonObject();
		sensor.addProperty("print_name", "Sensor_" + generateRandomNumber());
		sensor.addProperty("sensor_type", sensorType);
		sensor.addProperty("device_id", generateRandomMacAddress());
		sensor.addProperty("user_defined_fields", "None");
		
		return sensor;
	}
	
	public JsonObject generateSensorTypeObject() {
		JsonObject sensorType = new JsonObject();
		sensorType.addProperty("sensor_type", "SensorType_" + generateRandomNumber());
		sensorType.addProperty("user_defined_fields", "Test Value " + generateRandomNumber());
		
		return sensorType;
	}
	
	public JsonObject generateSensorReadings(){
		JsonObject sensorReadings=new JsonObject();
		sensorReadings.addProperty("id", "10170202");
		sensorReadings.addProperty("timestamp", "1373566899000");
		int temp=generateRandomNumber(50, 100);
		sensorReadings.addProperty("temp", ""+temp);
		return sensorReadings;
	}
	
	public JsonObject generateDeviceObject() {
		// Prepare a new Device type Json Object
		JsonObject device = new JsonObject();
		device.addProperty("device_type", "Device_" + generateRandomNumber());
		device.addProperty("device_agent", "DeviceAgent_" + generateRandomNumber());
		device.addProperty("device_id", generateRandomMacAddress());
		device.addProperty("location_description", randomizeLocationBetween23and19());
		device.addProperty("latitude", generateRandomNumber(0, 99) + " " + generateRandomNumber(0, 99));
		device.addProperty("longitude", generateRandomNumber(0, 99) + " " + generateRandomNumber(0, 99));
		device.addProperty("altitude", generateRandomNumber(0, 900) + "");
		device.addProperty("position_format_system", "LLA");
		
		// Prepare URI as a user defined field and send it
		JsonObject userDefinedFeilds = new JsonObject();
		userDefinedFeilds.addProperty("uri", "URI_" + generateRandomNumber(0,99999) );
		
		device.addProperty("user_defined_fields", userDefinedFeilds.toString());
		
		return device;
	}
	
	public JsonObject generateDeviceTypeObject() {
		// Prepare a new Device type Json Object
		JsonObject deviceType = new JsonObject();
		deviceType.addProperty("device_type_name", "DeviceType_" + generateRandomNumber());
		deviceType.addProperty("manufacturer", "Tesla");
		deviceType.addProperty("version", "1." + generateRandomNumber(0, 10));
		deviceType.addProperty("user_defined_fields", "Test Value " + generateRandomNumber());
		
		return deviceType;
	}
}
