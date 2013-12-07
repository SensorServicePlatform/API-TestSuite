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

package edu.cmu.sv.sdsp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * Utility Helper class to make GET and POST requests to a URL. This class also
 * handles parameters you would like to send as part of the POST request. Note
 * that responses from the server are also returned to the caller of this API.
 * 
 * @author Surya Kiran
 */
public class HttpHelper {
	private static final Logger log = Logger.getLogger(HttpHelper.class);

	/**
	 * API method to process a GET operation on a given URL.
	 * 
	 * @param urlStr
	 *            - URL to perform a GET operation on
	 * 
	 * @return - Server response to this request.
	 * 
	 * @throws IOException
	 *             - Details of any exception raised when processing this
	 *             request.
	 */
	public static String httpGet(String urlStr) throws IOException {
		String response;

		// Prepare the URL object
		URL url = new URL(urlStr);

		HttpURLConnection conn = null;
		try {
			// Open a HTTP Connection for the URL
			conn = openConnection(url);

			// Check if the request was made successfully?
			validateConnectionStatus(conn);

			// If YES in the above - fetch the response from server and
			// return it as a String.
			response = readResponse(conn);
		} finally {
			// If the connection is not null, close it.
			if (conn != null) {
				conn.disconnect();
			}
		}

		return response;
	}

	/**
	 * API method to process a POST operation on a given URL
	 * 
	 * @param urlStr
	 *            - URL to perform the POST operation on
	 * @param content
	 *            - Content that should be sent in the request body.
	 * 
	 * @return - Response returned from the server for this POST operation.
	 * 
	 * @throws IOException
	 *             - Details of any exception raised when processing this
	 *             request.
	 */
	public static String httpPostSensorReading(String urlStr, String content)
			throws IOException {
		String response;

		// Prepare the URL object
		URL url = new URL(urlStr);
		HttpURLConnection conn = null;

		try {
			// Open a HTTP Connection for the URL and POST the content
			conn = openConnection(url);

			// Mark the connection as POST and send the data that needs to be
			// posted.
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);
			try (OutputStream out = conn.getOutputStream();
					Writer writer = new OutputStreamWriter(out, "UTF-8")) {
				writer.write(content);
			}

			// Check if the request was made successfully?
			validateConnectionStatus(conn);

			// If YES in the above - fetch the response from server and
			// return it as a String.
			response = readResponse(conn);
		} finally {
			// If the connection is not null, close it.
			if (conn != null) {
				conn.disconnect();
			}
		}

		return response;
	}

	/**
	 * Utility method to open a HTTPURLConnection for the specified URL.
	 * 
	 * @param url
	 *            - URL to connect to.
	 * 
	 * @return - Connection object.
	 * 
	 * @throws IOException
	 *             - Cascade any exception that might be raised in this process.
	 */
	private static HttpURLConnection openConnection(URL url) throws IOException {
		log.trace("Inside openConnection() method");
		// If the URL passed is NULL, return NULL
		if (url == null) {
			return null;
		}

		// Else, open the connection and return the connection object.
		return (HttpURLConnection) url.openConnection();
	}

	/**
	 * Utility method to check if the status of the connection is 200 (HTTP Code
	 * - OK)
	 * 
	 * @param conn
	 *            - Connection whose status should be checked for HTTP status OK
	 * 
	 * @throws IOException
	 *             - Raise an exception if the status is not OK (code 200).
	 */
	private static void validateConnectionStatus(HttpURLConnection conn)
			throws IOException {
		log.trace("Inside validateConnectionStatus() method");
		// We expect the connection to be initialized and opened before it is
		// validated.
		// So, raise an exception if it is NULL.
		if (conn == null) {
			throw new RuntimeException(
					"Connection object sent for validation is not initialized. It is NULL.");
		}

		// Check for HTTP status code 200 - which means OK.
		// All status code details at:
		// http://www.w3.org/Protocols/HTTP/HTRESP.html
		if (conn.getResponseCode() != 200) {
			throw new IOException(conn.getResponseMessage());
		}
	}

	/**
	 * Utility method to read response from a HttpURLConnection object.
	 * 
	 * @param conn
	 *            - Connection object
	 * 
	 * @return - Response from server as String
	 * 
	 * @throws IOException
	 *             - Cascade any exception that might be raised in this process.
	 */
	private static String readResponse(HttpURLConnection conn)
			throws IOException {
		log.trace("Inside readResponse() method");
		StringBuilder response = new StringBuilder();
		// Read all the content from the Connection, add it to a StringBuffer
		// and return the value as a String.
		try (BufferedReader rd = new BufferedReader(new InputStreamReader(
				conn.getInputStream()))) {
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
			}
		}

		return response.toString();
	}
}
