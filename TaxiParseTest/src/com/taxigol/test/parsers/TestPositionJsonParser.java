package com.taxigol.test.parsers;

import java.util.List;

import junit.framework.TestCase;

import com.fer.taxis.model.Position;
import com.fer.taxis.model.services.parsers.PositionJsonParser;

public class TestPositionJsonParser extends TestCase{
	
	//[{"created_at":"2013-04-04T05:51:16Z","id":2,"latitude":23213.2131,"longitude":12312.1,"taxi_id":1,"updated_at":"2013-04-04T06:16:35Z"},{"created_at":"2013-04-04T06:15:55Z","id":4,"latitude":23123.121,"longitude":3242.21,"taxi_id":2,"updated_at":"2013-04-04T06:17:34Z"}]
	
	@SuppressWarnings("deprecation")
	public void testGetPositionFromJson(){
		
		PositionJsonParser parser = new PositionJsonParser();
		Position position = parser.parse("{\"created_at\":\"2013-04-04T05:51:16Z\",\"id\":2,\"latitude\":23213.2131,\"longitude\":12312.1,\"taxi_id\":1,\"updated_at\":\"2013-04-04T06:16:35Z\"}");
		System.out.println(position);
		assertEquals(position.getTaxiId(), 1);
		assertEquals(position.getLatitude(), 23213.2131);
		assertEquals(position.getLongitude(), 12312.1);
		assertEquals(position.getCreatedAt().getMinutes(), 51);
		assertEquals(position.getCreatedAt().getSeconds(), 16);
		
	}
	
	@SuppressWarnings("deprecation")
	public void testGetPositionFromJson2(){
		
		PositionJsonParser parser = new PositionJsonParser();
		Position position = parser.parse("{\"created_at\":\"2013-04-04T05:50:00Z\",\"id\":2,\"latitude\":75.234567,\"longitude\":-4.9876501,\"taxi_id\":1234,\"updated_at\":\"2013-04-04T06:16:35Z\"}");
		System.out.println(position);
		assertEquals(position.getTaxiId(), 1234);
		assertEquals(position.getLatitude(), 75.234567);
		assertEquals(position.getLongitude(), -4.9876501);
		assertEquals(position.getCreatedAt().getMinutes(), 50);
		assertEquals(position.getCreatedAt().getSeconds(), 00);
		
	}
	
	@SuppressWarnings("deprecation")
	public void testGetPositionFromJsonArray1(){
		String json = "[{\"created_at\":\"2013-04-04T05:51:16Z\",\"id\":2,\"latitude\":23213.2131,\"longitude\":12312.1,\"taxi_id\":1,\"updated_at\":\"2013-04-04T06:16:35Z\"}," +
				"{\"created_at\":\"2013-04-04T06:15:55Z\",\"id\":4,\"latitude\":23123.121,\"longitude\":3242.21,\"taxi_id\":2,\"updated_at\":\"2013-04-04T06:17:34Z\"}]";
		PositionJsonParser parser = new PositionJsonParser();
		List<Position> positions = parser.parseList(json);
		assertEquals(positions.size(), 2);
		
		Position position = positions.get(0);
		assertEquals(position.getTaxiId(), 1);
		assertEquals(position.getLatitude(), 23213.2131);
		assertEquals(position.getLongitude(), 12312.1);
		assertEquals(position.getCreatedAt().getMinutes(), 51);
		assertEquals(position.getCreatedAt().getSeconds(), 16);
	}

}
