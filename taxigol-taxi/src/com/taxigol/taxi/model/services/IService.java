package com.taxigol.taxi.model.services;

import java.util.List;

public interface IService<T> {

	public void deleteAll() throws Exception;
	public List<T> getAll() throws Exception;
	public T get(String id) throws Exception;
	
}
