package com.fer.taxis.model.services;

import java.io.IOException;
import java.util.List;

import com.fer.taxis.model.Panic;


public interface PanicService {

	public Panic createPanic(String id) throws IOException;
	public void deleteAll() throws IOException;
	public List<Panic> getAll() throws IOException;
	
}
