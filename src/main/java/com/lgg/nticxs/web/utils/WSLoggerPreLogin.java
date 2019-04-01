package com.lgg.nticxs.web.utils;

import java.io.IOException;
import java.util.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;



public class WSLoggerPreLogin {
	//private static final Logger logger = LogManager.getLogger(WSLogger.class.getSimpleName());

	
	public void logger(String loglevel, String entity, String module, String procedure
			, String step, String function, String EID, String ICCID, String parameters, String description) {
		HttpClient httpClient = HttpClients.createDefault();
		
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000L);
		String user = System.getProperty("user.name");		
		String encoding = Base64.getEncoder().encodeToString(("loggeruser:password").getBytes());
		HttpPost httpPost = new HttpPost("http://127.0.0.1/ereachlogger/logging/createlog");
		try {
			httpPost.setHeader("Authorization", "Basic " + encoding);
			StringEntity input = new StringEntity(
					"{\"timestamp\":\""+ timestamp +"\",\"user\":\"" + user + "\",\"loglevel\":\""+ loglevel +
					"\",\"entity\":\""+ entity +"\",\"module\":\""+ module +"\",\"procedure\":\""+ procedure +
					"\",\"step\":\""+ step +"\",\"function\":\""+ function +"\",\"EID\":\""+ EID + "\",\"ICCID\":\""+ ICCID +
					"\",\"parameters\":\""+ parameters + "\",\"description\":\""+ description + "\"}");
			
			input.setContentType("application/json");
			httpPost.setEntity(input);
			try {
				@SuppressWarnings("unused")
				HttpResponse response = httpClient.execute(httpPost);
				
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}

		} catch (Exception e) {
		}

	}
}