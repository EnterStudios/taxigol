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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.message.BasicNameValuePair;

/**
 * Represents a default HTTP POST request
 * @author fhurtad
 *
 */
public class DefaultPostRequest implements PostRequest{

	private HttpClient client;
	private String baseUrl;
	private Map<String,String> params;
	
	/**
	 * Constructs a POST request
	 * @param baseUrl the base URL of the post
	 * @param params the parameters as a map of name=value pairs
	 * @param client an instance of Apache HTTPClient that will be used to consume the request
	 */
	public DefaultPostRequest(String baseUrl, Map<String,String> params, HttpClient client) {
		super();
		this.baseUrl = baseUrl;
		this.client = client;
		this.params = params;
	}
	
	
	public String getBaseUrl() {
		return baseUrl;
	}
	
	public String getContent() throws IOException {
		
		HttpPost post = new HttpPost(baseUrl);
		
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		for(Entry<String,String> entry : params.entrySet()){
			nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		
		post.setEntity(new UrlEncodedFormEntity(nvps));
		
		ResponseHandler<String> handler = new BasicResponseHandler();
		String response = client.execute(post,handler);
		
		
		return response;
	}

}
