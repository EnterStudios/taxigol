package com.fer.taxis.model.services.parsers;

import java.util.List;

public interface JsonParser<T> {

	public T parse(String json);
	public List<T> parseList(String json);
}
