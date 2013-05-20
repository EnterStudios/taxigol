package com.taxigol.taxi.helpers;

import com.taxigol.taxi.model.IdProvider;

public class MockIdProvider implements IdProvider {

	@Override
	public String getId() {
		return "1";
	}

}
