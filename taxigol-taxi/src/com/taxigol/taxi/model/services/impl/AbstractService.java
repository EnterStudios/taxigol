package com.taxigol.taxi.model.services.impl;

import java.io.IOException;
import java.util.List;

import com.taxigol.restz.Restz;
import com.taxigol.restz.requests.GetRequest;
import com.taxigol.restz.requests.PostRequest;
import com.taxigol.taxi.model.services.IService;
import com.taxigol.taxi.model.services.parsers.AbstractParser;

public class AbstractService<T> implements IService<T>{

	protected String baseUrl;
	protected String resourceName;
	protected Restz client;
	
	protected AbstractParser<T> parser;
	
	public AbstractService(String baseUrl, String resourceName, Restz client, AbstractParser<T> parser) {
		super();
		this.baseUrl = baseUrl;
		this.resourceName = resourceName;
		this.client = client;
		this.parser = parser;
	}

	public void deleteAll() throws IOException{
		PostRequest post = client.post(baseUrl+"/"+resourceName+"/reset.json");
		String json = post.getContent();
		parser.throwIfError(json);
	}
	
	public List<T> getAll() throws IOException{
		GetRequest get = client.get(baseUrl+"/"+resourceName+".json");
		String content = get.getContent();
		parser.throwIfError(content);
		return parser.parseList(content);
	}
	
	public T get(String id) throws IOException{
		GetRequest get = client.get(baseUrl+"/"+resourceName+"/"+id+".json");
		String content = get.getContent();
		parser.throwIfError(content);
		return parser.parse(content);
	}
}
