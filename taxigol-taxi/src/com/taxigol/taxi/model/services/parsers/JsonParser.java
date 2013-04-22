package com.taxigol.taxi.model.services.parsers;

import java.io.IOException;
import java.util.List;

public interface JsonParser<T> {

	public T parse(String json) throws IOException;
	public List<T> parseList(String json) throws IOException;
	
	
}
