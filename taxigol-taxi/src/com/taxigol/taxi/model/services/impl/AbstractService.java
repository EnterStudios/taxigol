package com.taxigol.taxi.model.services.impl;

import java.util.HashMap;
import java.util.List;

import co.fernandohur.restz.Restz;

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

	public void deleteAll() throws Exception{
		client.post(baseUrl+"/"+resourceName+"/reset.json", new HashMap<String, Object>());
	}
	
	public List<T> getAll() throws Exception{
		return client.get(baseUrl+"/"+resourceName+".json", parser.getListType());
	}
	
	public T get(String id) throws Exception{
		return client.get(baseUrl+"/"+resourceName+"/"+id+".json", parser.getType());
	}
}
