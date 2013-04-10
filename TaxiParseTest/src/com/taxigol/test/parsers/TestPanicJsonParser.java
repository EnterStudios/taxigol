package com.taxigol.test.parsers;

import java.util.List;

import junit.framework.TestCase;

import com.fer.taxis.model.Panic;
import com.fer.taxis.model.services.parsers.PanicJsonParser;

public class TestPanicJsonParser extends TestCase{
	
	//[{"created_at":"2013-04-04T05:51:16Z","id":2,"latitude":23213.2131,"longitude":12312.1,"position_id":1,"updated_at":"2013-04-04T06:16:35Z"},{"created_at":"2013-04-04T06:15:55Z","id":4,"latitude":23123.121,"longitude":3242.21,"position_id":2,"updated_at":"2013-04-04T06:17:34Z"}]
	
	public void testGetPanicFromJson(){
		
		PanicJsonParser parser = new PanicJsonParser();
		Panic panic = parser.parse("{\"created_at\":\"2013-04-04T05:51:16Z\",\"id\":2,\"position_id\":987,\"updated_at\":\"2013-04-04T06:16:35Z\"}");
		assertEquals(987, panic.getPositionId());
		
	}
	
	public void testGetPanicFromJson2(){
		
		PanicJsonParser parser = new PanicJsonParser();
		Panic panic = parser.parse("{\"created_at\":\"2013-04-04T05:51:16Z\",\"id\":2,\"position_id\":876,\"updated_at\":\"2013-04-04T06:16:35Z\"}");
		assertEquals(876, panic.getPositionId());
		
	}
	
	public void testGetPanicFromJsonArray1(){
		String json = "[{\"created_at\":\"2013-04-04T05:51:16Z\",\"id\":2,\"position_id\":876,\"updated_at\":\"2013-04-04T06:16:35Z\"},{\"created_at\":\"2013-04-04T05:51:16Z\",\"id\":2,\"position_id\":876,\"updated_at\":\"2013-04-04T06:16:35Z\"}]";
		PanicJsonParser parser = new PanicJsonParser();
		List<Panic> Panics = parser.parseList(json);
		assertEquals(Panics.size(), 2);
		
		Panic panic = Panics.get(0);
		assertEquals(876, panic.getPositionId());
	}
	
	public void testGetPanicFromJsonArray2(){
		String json = "[]";
		PanicJsonParser parser = new PanicJsonParser();
		List<Panic> Panics = parser.parseList(json);
		assertEquals(Panics.size(), 0);
		
	}

}
