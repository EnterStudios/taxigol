package com.taxigol.taxi.test.parsers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import junit.framework.TestCase;

import com.taxigol.taxi.model.Position;
import com.taxigol.taxi.model.services.parsers.PositionJsonParser;

public class TestPositionJsonParser extends TestCase{
	
	private ParserTestHelper<Position> helper;
	
	public TestPositionJsonParser() {
		this.helper = new ParserTestHelper<Position>(Position.class, new PositionJsonParser());
	}
	
	public void testParseSingleObject() throws Exception{
		String json = "{\"created_at\":\"2013-05-15T01:48:27Z\",\"id\":6026,\"latitude\":4.7164482,\"longitude\":-74.0473139,\"taxi_id\":34,\"updated_at\":\"2013-05-15T01:48:27Z\"}";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", new Locale("es","CO"));
		Position p = new Position(4.7164482f, -74.0473139f, 34, dateFormat.parse("2013-05-15T01:48:27Z") , 6026);
		helper.matches(json, p);
	}
	
	public void testParseList() throws Exception{
		String json = "[" +
				"{\"created_at\":\"2013-05-15T01:48:27Z\",\"id\":1,\"latitude\":1.0,\"longitude\":1.0,\"taxi_id\":1,\"updated_at\":\"2013-05-15T01:48:27Z\"}," +
				"{\"created_at\":\"2013-05-15T01:48:27Z\",\"id\":2,\"latitude\":2.0,\"longitude\":2.0,\"taxi_id\":2,\"updated_at\":\"2013-05-15T01:48:27Z\"}," +
				"{\"created_at\":\"2013-05-15T01:48:27Z\",\"id\":3,\"latitude\":3.0,\"longitude\":3.0,\"taxi_id\":3,\"updated_at\":\"2013-05-15T01:48:27Z\"}" +
				"]";
		ArrayList<Position> positions = new ArrayList<Position>();
		for (int i = 1; i<= 3; i++){
			Position p = new Position(i, i, i, new Date(), i);
			positions.add(p);
		}
		helper.matches(json, positions);
	}

}
