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
class APISimulationV13 extends Simulation {
	val httpConf_v13 = http.baseURL("http://einstein.sv.cmu.edu:9000")
		.acceptCharsetHeader("utf-8")
		.acceptHeader("text/html")
		.disableFollowRedirect

	val headers_1 = Map("Keep-Alive" -> "115")
	val headers_2 = Map("Accept" -> "application/json", "Keep-Alive" -> "115")
		
	val scn = scenario("Sensor Data Platform Service v1.3")
		.group("Get Devices v1.3") {
			exec(
				http("Get_Devices_R3")
					.get("/getAllDevices/json")
					.headers(headers_1)
					.check(status.is(200)))
				.pause(0 milliseconds, 100 milliseconds)
				.exec(
					http("Get_Devices_R4")
						.get("/getAllDevices/csv")
						.headers(headers_1))
		}
		.pause(0 milliseconds, 100 milliseconds)
		.group("Get Sensor Types") {
			exec(
				http("Get_SensorTypes_R3")
					.get("/getAllSensorTypes/json")
					.headers(headers_1)
					.check(status.is(200)))
				.pause(0 milliseconds, 100 milliseconds)
				.exec(
					http("Get_SensorTypes_R4")
						.get("/getAllSensorTypes/csv")
						.headers(headers_1))
		}
			
	setUp(scn.inject(ramp(100 users) over (10 seconds)))
		.protocols(httpConf_v13)
		.assertions(
			global.successfulRequests.percent.is(100),
			details("Get Devices v1.3" / "Get_Devices_R3").responseTime.max.lessThan(20000))
}
