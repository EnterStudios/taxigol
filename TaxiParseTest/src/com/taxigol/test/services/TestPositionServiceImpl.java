package com.taxigol.test.services;

import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import com.fer.taxis.model.Position;
import com.fer.taxis.model.services.PositionService;
import com.fer.taxis.model.services.impl.PositionServiceImpl;
import com.restz.net.DefaultRestz;
import com.restz.net.Restz;

public class TestPositionServiceImpl extends TestCase{

	private PositionService service;
	
	public TestPositionServiceImpl() {
		Restz client = new DefaultRestz();
		service = new PositionServiceImpl(client, "http://192.168.0.10:3000");
	}
	
	public void testUpdateLocation() throws IOException{
		
		Position position = service.updatePosition("123", 123, 9183);
		similar(Math.abs(position.getLongitude()-9183.0), 0.001);
		similar(Math.abs(position.getLatitude()-123), 0.001);
		
		
	}
	
	public void testGetLocations() throws IOException{
		//When I delete the locations
		service.deleteAll();
		service.updatePosition("56", 123.01f, 4f);
		List<Position> positions = service.getAll();
		System.out.println(positions);
		assertEquals(positions.size(), 1);
		similar(positions.get(0).getLatitude(), 123.01);
		
		service.updatePosition("56", 1098.01f, 4f);
		positions = service.getAll();
		 
		assertEquals(positions.size(), 2);
		similar(positions.get(1).getLatitude(), 1098.01);
	}
	
	public void testDeleteLocations() throws IOException{
		
		service.updatePosition("1", 123.0f, 121.02f);
		service.deleteAll();
		List<Position> positions = service.getAll();
		similar(positions.size(), 0);
	}
	
	static void similar(double a, double b){
		assertTrue(Math.abs(a-b)<0.01);
	}
	
}
