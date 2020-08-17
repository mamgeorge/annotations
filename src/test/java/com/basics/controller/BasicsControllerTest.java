package com.basics.controller;

import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static com.basics.util.UtilityMain.PAR;
import static com.basics.util.UtilityMain.LOGGER;

import com.basics.model.City;

import java.lang.StringBuffer;
import java.util.Map;
import java.util.ArrayList; // LinkedHashMap;

import org.junit.jupiter.api.Test;
import org.junit.Before;
// import org.junit.Test;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

//@WebMvcTest(BasicsController.class)
@SpringBootTest
public class BasicsControllerTest {

	@Autowired private BasicsController basicsController;

	@Before public void setUp( ) throws Exception { }

	@Test public void root( ) {
		//
		String txtLines = "";
		Model model = null;
		ModelAndView modelAndView = this.basicsController.root( null );
		View view = modelAndView.getView( );
		txtLines += PAR + "root! [ " + modelAndView + " ]" ;
		txtLines += PAR + "root! CONTEXT_PATH: " + basicsController.getContextPath( ) ;
		txtLines += PAR + "root! MAV viewName: " + modelAndView.getViewName( ) ;
		txtLines += PAR + "root! view content: " + view ;
		LOGGER.info( txtLines );
		assertThat( this.basicsController ).isNotNull( );
		assertTrue( basicsController.getContextPath( ).contains( "/basics" ) ) ;
		assertTrue( modelAndView.getViewName( ).contains( "index" ) );
	}

	@Test public void showCities( ) {
		//
		String txtLines = "";
		ModelAndView modelAndView = this.basicsController.showCities( );
		ModelMap modelMap = modelAndView.getModelMap( );
		ArrayList<City> arrayList = (ArrayList<City>) modelMap.get( "cities" );
		City[ ] cities = new City[ arrayList.size( ) ];
		cities = arrayList.toArray( cities );
		City city = (City) cities[ 0 ];
		String name = city.getName( );
		StringBuffer stringBuffer = new StringBuffer( );
		modelMap.forEach( ( key, val ) -> { stringBuffer.append( PAR + key + ": " + val ); } );
		//
		txtLines += PAR + "showCities! [ " + modelAndView + " ]" ;
		txtLines += PAR + "showCities! MAV viewName: " + modelAndView.getViewName( ) ;
		txtLines += PAR + "showCities! model map [ " + modelMap + " ]" ;
		txtLines += PAR + "showCities! model buffer [ " + stringBuffer.toString( ) + " ]" ;
		txtLines += PAR + "showCities! model arrayList [ " + arrayList.size( ) + " ]" ;
		txtLines += PAR + "showCities! model cities [ " + cities.length + " ]" ;
		txtLines += PAR + "showCities! model city [ " + city + " ]" ;
		txtLines += PAR + "showCities! model name [ " + name + " ]" ;
		LOGGER.info( txtLines );
		assertTrue( modelAndView.getViewName( ).contains( "showCities" ) );
	}

	@Test public void showTimer( ) {
		//
		String txtLines = this.basicsController.showTimer( );
		LOGGER.info( PAR + "showTimer! " + txtLines );
		assertTrue( txtLines.length( ) > 1 );
	}

	@Test public void showUtils( ) {
		//
		String txtLines = this.basicsController.showUtils( ).substring( 0 , 10 );
		LOGGER.info( PAR + "showUtils! " + txtLines );
		assertTrue( txtLines.length( ) > 1 );
	}
	
	@Test public void exits( ) throws Exception { LOGGER.info( PAR + "exits! " ); }
}
