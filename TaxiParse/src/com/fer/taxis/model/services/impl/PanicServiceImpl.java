package com.fer.taxis.model.services.impl;

import java.io.IOException;

import com.fer.taxis.model.Panic;
import com.fer.taxis.model.services.PanicService;
import com.fer.taxis.model.services.parsers.PanicJsonParser;
import com.taxigol.restz.Restz;
import com.taxigol.restz.requests.PostRequest;

public class PanicServiceImpl extends AbstractService<Panic> implements
		PanicService {

	public PanicServiceImpl(Restz client,String baseUrl) {
		super(baseUrl, "panics", client, new PanicJsonParser());
		
	}

	@Override
	public Panic createPanic(String id) throws IOException{
		PostRequest post = client.post(baseUrl+"/panics.json","taxi_id",id);
		String json = post.getContent();
		return parser.parse(json);
	}
	

}
