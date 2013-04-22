package com.taxigol.restz.requests;

import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;

public class DefaultGetRequest implements GetRequest{

	private HttpClient client;
	private String requestString;
	
	public DefaultGetRequest(String requestString, HttpClient client) {
		super();
		this.requestString = requestString;
		this.client = client;
	}
	
	public String getRequestString() {
		return requestString;
	}
	
	public String getContent() throws IOException {
		
		HttpGet get = new HttpGet(requestString);
		ResponseHandler<String> handler = new BasicResponseHandler();
		String response = client.execute(get,handler);
		return response;
	}

}
