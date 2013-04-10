package com.fer.taxis.model.services.impl;

import java.io.IOException;
import java.util.List;

import com.fer.taxis.model.services.parsers.JsonParser;
import com.taxigol.restz.Restz;
import com.taxigol.restz.requests.GetRequest;
import com.taxigol.restz.requests.PostRequest;

public class AbstractService<T> {

	protected String baseUrl;
	protected String resourceName;
	protected Restz client;
	
	protected JsonParser<T> parser;
	
	public AbstractService(String baseUrl, String resourceName, Restz client, JsonParser<T> parser) {
		super();
		this.baseUrl = baseUrl;
		this.resourceName = resourceName;
		this.client = client;
		this.parser = parser;
	}

	public void deleteAll() throws IOException{
		PostRequest post = client.post(baseUrl+"/"+resourceName+"/reset.json");
		post.getContent();
	}
	
	public List<T> getAll() throws IOException{
		GetRequest get = client.get(baseUrl+"/"+resourceName+".json");
		String content = get.getContent();
		return parser.parseList(content);
	}
	
	public T get(String id) throws IOException{
		GetRequest get = client.get(baseUrl+"/"+resourceName+"/"+id+".json");
		String content = get.getContent();
		return parser.parse(content);
	}
}
