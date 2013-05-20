package com.taxigol.taxi.model.services.parsers;

import java.lang.reflect.Type;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.taxigol.taxi.model.Driver;

public class DriverJsonParser extends AbstractParser<Driver> {

	@Override
	public Class<Driver> getType() {
		return Driver.class;
	}

	@SuppressWarnings("serial")
	@Override
	public Type getListType() {
		return new TypeToken<List<Driver>>(){}.getType();
	}

}
