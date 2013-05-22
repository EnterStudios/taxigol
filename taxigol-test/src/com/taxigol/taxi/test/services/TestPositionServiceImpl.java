package com.taxigol.taxi.test.services;

import java.util.List;

import junit.framework.TestCase;
import co.fernandohur.restz.DefaultRestz;
import co.fernandohur.restz.Restz;

import com.taxigol.taxi.model.Position;
import com.taxigol.taxi.model.services.PositionService;
import com.taxigol.taxi.model.services.impl.PositionServiceImpl;
import com.taxigol.taxi.test.Properties;

public class TestPositionServiceImpl extends TestCase{

	private PositionService service;
	
	public TestPositionServiceImpl() {
		Restz client = new DefaultRestz();
		service = new PositionServiceImpl(client, Properties.server_url);
	}
	
	public void testUpdateLocation() throws Exception{
		
		Position position = service.updatePosition("123", 123, 9183);
		similar(Math.abs(position.getLongitude()-9183.0), 0.001);
		similar(Math.abs(position.getLatitude()-123), 0.001);
		
		
	}
	
	public void testGetLocations() throws Exception{
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
	
	public void testDeleteLocations() throws Exception{
		
		service.updatePosition("1", 123.0f, 121.02f);
		service.deleteAll();
		List<Position> positions = service.getAll();
		similar(positions.size(), 0);
	}
	
	static void similar(double a, double b){
		assertTrue(Math.abs(a-b)<0.01);
	}
	
}
