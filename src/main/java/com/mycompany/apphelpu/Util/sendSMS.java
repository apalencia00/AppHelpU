/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;

/**
 *
 * @author apalencia
 */
public class sendSMS {
    
    public String sendSms(String contacto) {
		try {
			// Construct data
			String apiKey  = "apikey="   + "kgbkEchKSGc-bwGfKJ1w5UW6fa0RpDMvL4j7zs8AEJ";
			String message = "&message=" + "Bienvenido a Helpdesk, este codigo es para ingreso en la aplicacion  "+System.currentTimeMillis()+ ", por favor no compartir  ";
			String sender  = "&sender="  + "Info Super!";
			String numbers = "&numbers=" + contacto;

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();

			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
    
}
