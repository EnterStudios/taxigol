package com.taxigol.restz.requests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.message.BasicNameValuePair;

public class DefaultPutRequest implements PutRequest {

	
	private HttpClient client;
	private String baseUrl;
	private Map<String,String> params;
	
	/**
	 * Constructs a PUT request
	 * @param baseUrl the base URL of the post
	 * @param params the parameters as a map of name=value pairs
	 * @param client an instance of Apache HTTPClient that will be used to consume the request
	 */
	public DefaultPutRequest(String baseUrl, Map<String,String> params, HttpClient client) {
		super();
		this.baseUrl = baseUrl;
		this.client = client;
		this.params = params;
	}
	
	@Override
	public String getBaseUrl() {
		return baseUrl;
	}

	@Override
	public String getContent() throws IOException {
		HttpPut put = new HttpPut(baseUrl);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		for(Entry<String,String> entry : params.entrySet()){
			nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		
		put.setEntity(new UrlEncodedFormEntity(nvps));
		
		ResponseHandler<String> handler = new BasicResponseHandler();
		String response = client.execute(put,handler);
		return response;
	}

}
