package com.taxigol.taxi.model.services;

import java.io.IOException;

import com.taxigol.taxi.model.Position;

public interface PositionService extends IService<Position>{

	/**
	 * Updates the position of the taxi in the server. Calling this method
	 * will actually create a new Position instance in the server and persist it in
	 * the database 
	 * @param taxiId
	 * @param latitude
	 * @param longitude
	 * @return
	 * @throws IOException
	 */
	public Position updatePosition(String taxiId, double latitude, double longitude) throws Exception;

	
}
