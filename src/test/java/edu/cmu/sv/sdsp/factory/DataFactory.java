package edu.cmu.sv.sdsp.factory;

import java.util.Calendar;
import java.util.Random;

public class DataFactory {
	
	protected static Random rand;

	protected int generateRandomNumber() {
		return generateRandomNumber(0, 9999);
	}
	
	protected int generateRandomNumber(int minValue, int maxValue) {
		if(rand == null) {
			throw new RuntimeException("JsonObjectFactory instanciated incorrectly. "
					+ "Please use .getInstance() method to instanciate the object properly. ");
		}
		
		// nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((maxValue - minValue) + 1) + minValue;
	    
	    return randomNum;
	}
	
	protected String generateRandomMacAddress() {
		// Generate a random MAC address every time
		StringBuffer macAddress = new StringBuffer();
		for(int i=0; i<=5; i++) {
			macAddress.append(generateRandomNumber(0, 99));
			if( i!=5 ) {
				macAddress.append(":");
			}
		}
		
		return macAddress.toString();
	}
	
	protected String randomizeLocationBetween23and19() {
		// Randomize the locations between B23 and B19
		String location = "B23";
		if(new Random().nextBoolean()) {
			location = "B19";
		}
		
		return location;
	}
	
	protected int generateRandomTemp(){
		return generateRandomNumber(50, 120);		
	}
	
	protected long generateRandomTimeStamp(){
		Calendar cal=Calendar.getInstance();
		cal.set(2013, 11, 1,0,0,0);
		long begin=cal.getTimeInMillis();
		return begin+generateRandomNumber(0, 365*24*60*60);	
	}
	
	protected String generateRandomSensorType(){
		String [] types={"temp","digital_temp","light","pressure","humidity","motion","audio_p2p","acc_x","acc_y","acc_z"};
		int length=types.length;
		return types[generateRandomNumber(0, length-1)];
	}
	
	protected String generateRandomFormat(){
		String [] formats={"csv","json"};
		return formats[generateRandomNumber(0,1)];
	}
}
