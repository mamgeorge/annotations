package com.basics.controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static com.basics.util.UtilityMain.PAR;
import static com.basics.util.UtilityMain.LOGGER;

public class BasicsControllerTest {

	@Before public void setUp( ) throws Exception { }
	
	@Test public void index() { 
		//
		String txtLine = "index";
		LOGGER.info( PAR + txtLine );
		assertTrue( txtLine.equals( "index" ) );
	}
	public void showCities( ) { }
	public void showTimer( ) { }
	public void showUtils( ) { }
	public void close( ) { }
}