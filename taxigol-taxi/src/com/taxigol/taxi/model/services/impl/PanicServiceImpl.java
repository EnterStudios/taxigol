package com.taxigol.taxi.model.services.impl;

import java.io.IOException;

import com.taxigol.restz.Restz;
import com.taxigol.restz.requests.PostRequest;
import com.taxigol.taxi.model.Panic;
import com.taxigol.taxi.model.services.PanicService;
import com.taxigol.taxi.model.services.parsers.PanicJsonParser;

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
