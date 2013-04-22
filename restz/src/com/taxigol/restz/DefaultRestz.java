package com.taxigol.restz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.taxigol.restz.requests.DefaultGetRequest;
import com.taxigol.restz.requests.DefaultPostRequest;
import com.taxigol.restz.requests.DefaultPutRequest;
import com.taxigol.restz.requests.GetRequest;
import com.taxigol.restz.requests.PostRequest;
import com.taxigol.restz.requests.PutRequest;

public class DefaultRestz implements Restz{

	private HttpClient client;
	
	public DefaultRestz() {
		client = new DefaultHttpClient();
	}
	
	@Override
	public GetRequest get(String request) {
		GetRequest get = new DefaultGetRequest(request, client);
		return get;
	}

	@Override
	public GetRequest get(String baseUrl, String params) {
		return get(baseUrl + "?" + params);
	}
	
	@Override
	public GetRequest get(String baseUrl, String... params) {
		String request = "";
		for(int i=0; i<params.length-1; i+=2){
			String key = params[i];
			String val = params[i+1];
			
			request += addKeyVal(key, val);
			if (i+1<params.length-1){
				request += "&";
			}
		}
		
		return get(baseUrl,request);
	}
	
	private String addKeyVal(String key, String val){
		return key + "=" + val;
	}
	

	@Override
	public GetRequest get(String baseUrl, Map<String, String> params) {
		String request = "";
		ArrayList<Entry<String, String>> parameters = new ArrayList<Entry<String,String>>(params.entrySet());
		for(int i=0; i<parameters.size();i++){
			Entry<String, String> entry = parameters.get(i);
			request += addKeyVal(entry.getKey(), entry.getValue());
			if (i < parameters.size() -1){
				request += "&";
			}
		}
		return get(baseUrl, request);
	}

	@Override
	public PostRequest post(String baseUrl) {
		return new DefaultPostRequest(baseUrl, new HashMap<String,String>(), client);
	}
	
	@Override
	public PostRequest post(String baseUrl, String... params) {
		Map<String,String> map = new HashMap<String,String>();
		for(int i=0; i<params.length-1; i+=2){
			String key = params[i];
			String val = params[i+1];
			map.put(key, val);
			
		}
		return new DefaultPostRequest(baseUrl, map, client);
	}
	
	@Override
	public PutRequest put(String baseUrl) {
		return put(baseUrl, new String []{});
	}
	
	@Override
	public PutRequest put(String baseUrl, String... params) {
		Map<String,String> map = new HashMap<String,String>();
		for(int i=0; i<params.length-1; i+=2){
			String key = params[i];
			String val = params[i+1];
			map.put(key, val);
			
		}
		return new DefaultPutRequest(baseUrl, map, client);
	}
	
	@Override
	public void close() {
		client.getConnectionManager().shutdown();
	}
}
