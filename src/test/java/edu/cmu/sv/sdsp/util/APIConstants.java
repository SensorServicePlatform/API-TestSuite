/*
 * 
 Copyright (c) 2013 CMU-SV.
All rights reserved. This program and the accompanying materials
are made available under the terms of the GNU Public License v2.0
which accompanies this distribution, and is available at
http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 */
package edu.cmu.sv.sdsp.util;

/**
 * This file provides the API request results.
 * When requesting an API, the result should be a string like below.
 * 
 * @author Gonghan Wang, Surya Kiran
 *
 */
public interface APIConstants {
	/**
	 * A constant string shows the device is saved.
	 */
	public static final String ADD_DEVICE_SUCCESSFUL = "device saved";
	
	/**
	 * A constant string shows that the sensor is saved.
	 */
	public static final String ADD_SENSOR_SUCCESSFUL = "sensor saved";
	/**
	 * A constant string show that the device type is saved. 
	 */
	public static final String ADD_DEVICE_TYPE_SUCCESSFUL = "device type saved";
	/**
	 * A constant string show that the sensor type is saved.
	 */
	public static final String ADD_SENSOR_TYPE_SUCCESSFUL = "sensor type saved";
}
