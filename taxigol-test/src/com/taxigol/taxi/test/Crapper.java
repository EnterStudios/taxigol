package com.taxigol.taxi.test;

import junit.framework.TestCase;

public class Crapper extends TestCase{

	public void test1(){
		
		assertTrue("abc123".matches("[a-z]{3}[0-9]{3}"));
		assertFalse("abc1234".matches("[a-z]{3}[0-9]{3}"));
		assertFalse("abde123".matches("[a-z]{3}[0-9]{3}"));
		assertFalse("abde23".matches("[a-z]{3}[0-9]{3}"));
		assertFalse("abe12".matches("[a-z]{3}[0-9]{3}"));
		assertTrue("VEG954".matches("[a-zA-Z]{3}[0-9]{3}"));
		
	}

}
