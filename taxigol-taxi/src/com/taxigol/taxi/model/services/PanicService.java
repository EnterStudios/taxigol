package com.taxigol.taxi.model.services;

import java.io.IOException;
import java.util.List;

import com.taxigol.taxi.model.Panic;


public interface PanicService extends IService<Panic>{

	public Panic createPanic(String id) throws IOException;
	public void deleteAll() throws IOException;
	public List<Panic> getAll() throws IOException;
	
}
