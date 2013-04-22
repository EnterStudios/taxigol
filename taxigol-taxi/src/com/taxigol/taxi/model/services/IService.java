package com.taxigol.taxi.model.services;

import java.io.IOException;
import java.util.List;

public interface IService<T> {

	public void deleteAll() throws IOException;
	public List<T> getAll() throws IOException;
	public T get(String id) throws IOException;
	
}
