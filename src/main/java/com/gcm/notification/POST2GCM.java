package com.gcm.notification;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;

public class POST2GCM {

	
	public static void post(HashSet<String> tokenId,Boolean parent,String title,String msg){
		
		final String apiKey = 
				"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiIzYmJkZjU2Mi1lZjY3LTRlNzktYmNhYS03NjcxZThhYjIzN2IifQ._SrQDAOf1zjanL_n7I61BxLxxF4pUWXAG6jxYu5BuJY";
		
		final String apiKey1 = 
				"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiI3NzhkMTI0Ny1hMTJjLTRlNTItOWY5Yy04NzcxMDYxYzMzYzAifQ.7KU7k7N2YlTYQ99JuxEX-39nL4pEjuzqwapCcK5O6kA";
		
		tokenId.remove(null);
		
		HashMap<String,Object> content = new HashMap<String,Object>();
		content.put("tokens", tokenId);
		content.put("profile", "tushar");
		
		HashMap<String,Object> notification = new HashMap<String,Object>();
		notification.put("title", title);
		notification.put("message", msg);
		
		HashMap<String,Object> android = new HashMap<String,Object>();
		
		HashMap<String,Object> data = new HashMap<String,Object>();
		data.put("collapse_key", "foo");
		data.put("tag", "bar");
		data.put("image", "www/img/icon.png");
		data.put("notId",(int)UUID.randomUUID().getLeastSignificantBits());
		
		android.put("data", data);
		notification.put("android", android);
		
		HashMap<String,Object> ios = new HashMap<String,Object>();
		ios.put("priority", 10);
		ios.put("content_available", 1);
		
		notification.put("ios", ios);
		content.put("notification", notification);
		
		try{
			
		// 1. URL
		URL url = new URL("https://api.ionic.io/push/notifications");
					
		// 2. Open connection
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
		// 3. Specify POST method
		conn.setRequestMethod("POST");
		
		// 4. Set the headers
		conn.setRequestProperty("Content-Type", "application/json");
		
		if(parent)
			conn.setRequestProperty("Authorization", apiKey1);
		else
			conn.setRequestProperty("Authorization", apiKey);
		
		conn.setDoOutput(true);

			// 5. Add JSON data into POST request body 
		
			//`5.1 Use Jackson object mapper to convert Contnet object into JSON
	    	ObjectMapper mapper = new ObjectMapper();
	    	
	    	// 5.2 Get connection output stream
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			//mapper.writeV
			// 5.3 Copy Content "JSON" into 
			mapper.writeValue(wr, content);
			
			// 5.4 Send the request
			wr.flush();
			
			// 5.5 close
			wr.close();
			 
			// 6. Get the response
			int responseCode = conn.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	 
			// 7. Print result
			System.out.println(response.toString());
					
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
}
