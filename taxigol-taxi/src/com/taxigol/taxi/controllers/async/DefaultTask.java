package com.taxigol.taxi.controllers.async;

public abstract class DefaultTask<T> implements Task<T>{


	@Override
	public void onFailure(Throwable throwable) {
		throwable.printStackTrace();
	}

	
}
