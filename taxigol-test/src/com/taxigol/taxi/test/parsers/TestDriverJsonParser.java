package com.taxigol.taxi.test.parsers;

import java.util.ArrayList;

import com.taxigol.taxi.model.Driver;
import com.taxigol.taxi.model.services.parsers.DriverJsonParser;

import junit.framework.TestCase;

public class TestDriverJsonParser extends TestCase{

	ParserTestHelper<Driver> helper = new ParserTestHelper<Driver>(Driver.class, new DriverJsonParser());
	
	public void testParsesSingleObject() throws Exception{
		
		String json = "{\"cedula\":\"1020761351\",\"created_at\":\"2013-05-20T17:19:40Z\",\"id\":1,\"name\":\"robert\",\"password\":\"mypass123\",\"photo_url\":null,\"taxi_id\":25,\"updated_at\":\"2013-05-20T17:19:40Z\"}";
		Driver driver = new Driver();
		driver.setId(1);
		driver.setCedula("1020761351");
		driver.setName("robert");
		driver.setTaxi_id(25);
		driver.setPassword("mypass123");
		
		helper.matches(json, driver);
	}
	
	public void testParseList() throws Exception {
		
		String json = "[" +
				"{ \"cedula\":\"1\", \"name\":\"robert1\", \"taxi_id\":1, \"password\":\"mypass1\"  }," +
				"{ \"cedula\":\"2\", \"name\":\"robert2\", \"taxi_id\":2, \"password\":\"mypass2\"  }," +
				"{ \"cedula\":\"3\", \"name\":\"robert3\", \"taxi_id\":3, \"password\":\"mypass3\"  }" +
				"]";
		ArrayList<Driver> drivers = new ArrayList<Driver>();
		for (int i = 1; i<= 3; i++){
			Driver d = new Driver();
			d.setCedula(""+i);
			d.setName("robert"+i);
			d.setTaxi_id(i);
			d.setPassword("mypass"+i);
			drivers.add(d);
		}
		helper.matches(json, drivers);
		
		
	}
	
}
