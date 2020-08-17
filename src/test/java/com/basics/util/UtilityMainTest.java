package com.basics.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static com.basics.util.UtilityMain.PAR;
import static com.basics.util.UtilityMain.LOGGER;

public class UtilityMainTest {

	@Before public void setUp( ) throws Exception { }

	@Test public void showSys( ) throws Exception {
		//
		String txtLines = UtilityMain.showSys( );
		// LOGGER.info( PAR + txtLines );
		LOGGER.info( PAR + txtLines.substring( 0, 10 ) );
		assertTrue( txtLines.length( ) > 1 );
	}

	@Test public void showTime( ) throws Exception {
		//
		String txtLine = UtilityMain.showTime( );
		LOGGER.info( PAR + txtLine );
		assertTrue( txtLine.length( ) > 1 );
	}

	@Test public void getFileLines( ) throws Exception {
		//
		String fileName = "C:/workspace/resources/xml/Genesis_01.txt";
		String txtLines = UtilityMain.getFileLines( fileName , "" );
		LOGGER.info( PAR + txtLines.substring( 0, 7 ) );
		assertTrue( txtLines.substring( 0, 7 ).equals( "Genesis" ) );
	}

	@Test public void getFileLocal( ) throws Exception {
		//
		String txtLines = UtilityMain.getFileLocal( "" , "" );
		LOGGER.info( PAR + txtLines.substring( 0, 7 ) );
		assertTrue( txtLines.substring( 0, 7 ).equals( "Genesis" ) );
	}

	@Test public void urlGet( ) {
		//
		String txtLines = "";
		String link = "http://www.google.com";
		//
		txtLines = UtilityMain.urlGet( link );
		LOGGER.info( PAR + txtLines.substring( 0, 60 ) );
		assertTrue( txtLines.length( ) > 1 );
	}

	@Test public void urlPost( ) {
		//
		String txtLines = "";
		String link = "https://httpbin.org/post";
		String postParms = "name=Martin&occupation=programmer";
		//
		txtLines = UtilityMain.urlPost( link , postParms );
		LOGGER.info( PAR + txtLines.substring( 0, 60 ) );
		assertTrue( txtLines.length( ) > 1 );
	}

	@Test public void urlPostFile( ) {
		//
		String txtLines = "";
		String link = "https://httpbin.org/post";
		String postParms = "name=Martin&occupation=programmer";
		String pathTxt = "static/xml/books.json";
		String pathBin = "static/xml/hal9000.wav";
		//
		txtLines = UtilityMain.urlPostFile( link, postParms, pathTxt, pathBin );
		LOGGER.info( PAR + txtLines );
		assertTrue( txtLines.length( ) > 1 );
	}

	@Test public void getXmlNode( ) throws Exception {
		//
		String txtLine = "";
		String xml = "<a><b id = 'aleph' ><c><d>alpha</d><d>beta</d></c></b><b id = 'beth' ></b></a>";
		String xpath = "a/b/c/d";
		String delim = "";
		//
		txtLine += PAR + UtilityMain.getXmlNode( xml, xpath, delim ) ;
		txtLine += PAR + UtilityMain.getXmlNode( xml, "a/b/c/d[2]", delim ) ;
		txtLine += PAR + UtilityMain.getXmlNode( xml, "a/b[2]/@id", delim ) ;
		//
		LOGGER.info( txtLine );
		assertTrue( txtLine.length( ) > 1 );
	}

	@Test public void convertXml2Json( ) throws Exception {
		//
		String xml = "<a><b id = 'aleph' ><c><d>alpha</d><d>beta</d></c></b><b id = 'beth' ></b></a>";
		String txtLines = UtilityMain.convertXml2Json( xml ) ;
		LOGGER.info( PAR + txtLines.replaceAll( "\n" , "" ).replaceAll( "\t" , "" ).replaceAll( "  " , "" ) );
		assertTrue( txtLines.length( ) > 1 );
	}

	@Test public void convertJson2Xml( ) throws Exception {
		//
		String json = "{ a: { b: [ { c: { d: [ alpha, beta ] }, id: aleph }, { id: beth } ] } }";
		String txtLines = UtilityMain.convertJson2Xml( json ) ;
		LOGGER.info( PAR + txtLines.replaceAll( "\n" , "" ) );
		assertTrue( txtLines.length( ) > 1 );
	}

	@Test public void formatXml( ) throws Exception {
		//
		String xml = "<a><b><c><d>alpha</d><d>beta</d></c><id>aleph</id></b><b><id>beth</id></b></a>";
		String txtLines = UtilityMain.formatXml( xml ) ;
		LOGGER.info( PAR + txtLines.substring( 0 , 20 ) );
		assertTrue( txtLines.length( ) > 1 );
	}
}
