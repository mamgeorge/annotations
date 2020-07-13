package com.basics.services;

import com.basics.BasicsApplication;
import com.basics.services.CityService;
import com.basics.model.City;

import java.util.ArrayList;
import java.util.List;
import java.lang.Iterable;

import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;
import static com.basics.util.UtilityMain.PAR;
import static com.basics.util.UtilityMain.LOGGER;

// @RunWith(SpringRunner.class)
// @SpringBootTest(classes = BasicsApplication.class)
public class CityServiceTest {

	@Autowired private ICityService cityService;

	@Before public void setUp( ) throws Exception { }

	@Test public void testing( ) {
		//
		String txtLine = "testing";
		LOGGER.info( PAR + txtLine );
		assertTrue( txtLine.equals( "testing" ) );
	}

	// @Test
	public void findAll( ) {
		//
		String txtLines = "";
        Iterable<City> iterable = cityService.findAll( );
		List<City> cities = new ArrayList<City>( );
		for (City city : iterable) {
			//
			txtLines += "\n";
			txtLines += "\t" + city.getId( );
			txtLines += "\t" + city.getName( );
			txtLines += "\t" + city.getPopulation( );
			cities.add( city );
		}
		txtLines += PAR + cities.size( );
		LOGGER.info( txtLines );
    }
}