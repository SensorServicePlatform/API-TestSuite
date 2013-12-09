/**
 * Copyright (c) 2013 Carnegie Mellon University Silicon Valley. 
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available
 * under the terms of dual licensing(GPL V2 for Research/Education
 * purposes). GNU Public License v2.0 which accompanies this distribution
 * is available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * 
 * Please contact http://www.cmu.edu/silicon-valley/ for more specific
 * information.
 */
package unit

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import io.gatling.http.Headers.Names._
import scala.concurrent.duration._
import bootstrap._
import assertions._

/**
 * Author: Surya Kiran
 */
class APISimulationV12 extends Simulation {

	val httpConf_v12 = http.baseURL("http://einstein.sv.cmu.edu")
		.acceptCharsetHeader("utf-8")
		.acceptHeader("text/html")
		.disableFollowRedirect
		
	val headers_1 = Map("Keep-Alive" -> "115")
	val headers_2 = Map("Accept" -> "application/json", "Keep-Alive" -> "115")

	val scn = scenario("Sensor Data Platform Service v1.2")
		.group("Get Devices v1.2") {
			exec(
				http("Get_Devices_R1")
					.get("/get_devices/json")
					.headers(headers_1)
					.check(status.is(200)))
				.pause(0 milliseconds, 100 milliseconds)
				.exec(
					http("Get_Devices_R2")
						.get("/get_devices/csv")
						.headers(headers_1))
		}
		.pause(0 milliseconds, 100 milliseconds)
		.group("Get Sensor Types V1.2") {
			exec(
				http("Get_SensorTypes_R1")
					.get("/get_sensor_types/firefly_v3/json")
					.headers(headers_1)
					.check(status.is(200)))
				.pause(0 milliseconds, 100 milliseconds)
				.exec(
					http("Get_SensorTypes_R2")
						.get("/get_sensor_types/firefly_v3/csv")
						.headers(headers_1))
		}

	setUp(scn.inject(ramp(100 users) over (10 seconds)))
		.protocols(httpConf_v12)
		.assertions(
			global.successfulRequests.percent.is(100),
			details("Get Devices v1.2" / "Get_Devices_R1").responseTime.max.lessThan(20000))
			
}
