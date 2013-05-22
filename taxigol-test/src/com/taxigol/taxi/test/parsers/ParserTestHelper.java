package com.taxigol.taxi.test.parsers;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import com.taxigol.taxi.model.services.parsers.AbstractParser;

public class ParserTestHelper<T> {

	private AbstractParser<T> parser;
	private Class<T> clazz;
	private Type listType;
	
	public ParserTestHelper(Class<T> clazz, AbstractParser<T> parser) {
		this.parser = parser;
		this.clazz = parser.getType();
		this.listType = parser.getListType();
	}
	
	public void matches(String json, T element) throws Exception{
		T element2 = parser.parse(json, clazz);
		
		Field[] fields = element.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			f.setAccessible(true);
			Object o1 = f.get(element2);
			Object o2 = f.get(element);
			if (o1 instanceof Double){
				Assert.assertEquals((Double) o1, (Double) o2, 0.001);
			}
			else if (o1 instanceof Long){
				Assert.assertEquals((Float)o1,(Float) o2, 0.001);
			}
			else if (o1 instanceof Date){
				System.err.println("Date comparison not supported");
			}else{
				Assert.assertEquals(o1,o2);
			}
		}
		
	}
	
	public void matches(String json, List<T> element) throws Exception{
		List<T> element2 = parser.parse(json, listType);
		Assert.assertEquals(element.size(), element2.size());
		for (int i = 0; i < element2.size(); i++) {
			T e1 = element2.get(i);
			T e2 = element.get(i);
			Field[] fields = e1.getClass().getDeclaredFields();
			for (int j = 0; j < fields.length; j++) {
				Field f = fields[j];
				f.setAccessible(true);
				Object o1 = f.get(e1);
				Object o2 = f.get(e2);
				
				if (o1 instanceof Double){
					Assert.assertEquals((Double) o1, (Double) o2, 0.001);
				}
				else if (o1 instanceof Long){
					Assert.assertEquals((Float)o1,(Float) o2, 0.001);
				}
				else if (o1 instanceof Date){
					System.err.println("Date comparison not supported");
				}else{
					Assert.assertEquals(o1,o2);
				}
			}
		}
	}
	
}
