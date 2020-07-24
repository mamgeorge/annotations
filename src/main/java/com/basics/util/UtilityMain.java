package com.basics.util;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.xml.xpath.XPathConstants.STRING;
import static javax.xml.xpath.XPathConstants.NODESET;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.ClassLoader;
import java.lang.StringBuffer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class UtilityMain {

	public static final Logger LOGGER = Logger.getLogger( UtilityMain.class.getName( ) );

	public static final String USER_AGENT = "Mozilla/5.0";
	public static final String CONTENTTYPE_FORM = "application/x-www-form-urlencoded";

	public static final String GREEN = "\u001b[32,1m";
	public static final String RESET = "\u001b[0m";
	public static final String DLM = "\n";
	public static final String PAR = "\n\t";

	public static final String FLD_SAMPLE = "static/" ;
	public static final String TXT_SAMPLE = "Genesis_01.txt" ;
	public static final String XML_SAMPLE = "xml/books.xml" ;
	public static final String JSN_SAMPLE = "xml/books.json" ;
	public static final String ZIP_SAMPLE = "xml_wav_plants_w10.zip" ;

	public static void main( String[ ] args ) {
		//
		System.out.println( GREEN + "DONE" + RESET );
	}

	public static String showSys( ) {
		//
		Map<String, String> mapEnv = System.getenv( );
		Map<String, String> mapEnvTree = new TreeMap<String, String>( mapEnv );
		StringBuffer stringBuffer = new StringBuffer( );
		stringBuffer.append( "[" );
		// env.forEach( ( key , val ) -> stringBuffer.append( key + ": " + val + dlm ) );
		mapEnvTree.forEach( ( key, val ) -> {
			//
			val = val.replace( "\\", "/" );
			val = val.replace( "\"", "'" );
			stringBuffer.append( "{\"" + key + "\":\"" + val + "\"}," );
		} );
		stringBuffer.append( "{\"" + "USERNAME" + "\":\"" + System.getenv( "USERNAME" ) + "\"}" );
		stringBuffer.append( "]" );
		return stringBuffer.toString( );
	}

	public static String showTime( ) {
		//
		String txtLine = "";
		txtLine = new Date( ).toString( );
		return txtLine;
	}

	public static String getFileLines( String fileName , String delim ) {
		//
		// https://mkyong.com/java8/java-8-stream-read-a-file-line-by-line/
		String txtLines = "";
		if ( fileName == null || fileName.equals( "" ) ) { fileName = FLD_SAMPLE + TXT_SAMPLE; }
		if ( delim == null || delim.equals( "" ) ) { delim = DLM; }
		//
		List<String> list = new ArrayList<>( );
		try ( BufferedReader bReader = Files.newBufferedReader( Paths.get( fileName) ) ) {
			//
			list = bReader.lines( ).collect( Collectors.toList( ) );
			txtLines = String.join( "\n" , list );
			txtLines = txtLines.replaceAll( "\n" , delim );
		}
		catch (IOException ex) { LOGGER.info( ex.getMessage( ) ); }
		//
		return txtLines;
	}

	public static String getFileLocal( String fileName , String delim ) {
		//
		// https://howtodoinjava.com/java/io/read-file-from-resources-folder/
		// File file = ResourceUtils.getFile("classpath:config/sample.txt")
		String txtLines = "";
		if ( fileName == null || fileName.equals( "" ) ) { fileName = FLD_SAMPLE + TXT_SAMPLE; }
		if ( delim == null || delim.equals( "" ) ) { delim = DLM; }
		try {
			//
			ClassLoader classLoader = ClassLoader.getSystemClassLoader( );
			File file = new File( classLoader.getResource( fileName ).getFile( ) );
			txtLines = new String( Files.readAllBytes( file.toPath( ) ), UTF_8 );
			txtLines = txtLines.replaceAll( "\n" , delim );
		}
		catch (IOException ex) { LOGGER.info( ex.getMessage( ) ); }
		return txtLines;
	}

	public static String urlGet( String link ) {
		//
		String txtLines = "";
		try{
			URL url = new URL( link );
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection( );
			httpConn.setRequestMethod( "GET" );
			httpConn.setRequestProperty( "User-Agent", USER_AGENT );
			httpConn.setConnectTimeout(5000);
			httpConn.setReadTimeout(5000);
			//
			int responseCode = httpConn.getResponseCode( );
			LOGGER.info( "Sending GET to: " + url );
			LOGGER.info( "Response code : " + responseCode );
			//
			InputStream inputStream = httpConn.getInputStream( );
			InputStreamReader isr = new InputStreamReader( inputStream );
			BufferedReader bufferedReader = new BufferedReader( isr );
			StringBuilder stringBuilder = new StringBuilder( );
			String txtLine = "";
			while ( ( txtLine = bufferedReader.readLine( ) ) != null ) {
				stringBuilder.append( txtLine );
			}
			txtLines = stringBuilder.toString( );
		} 
		catch( IOException ex ) {
			txtLines = ex.getMessage( );
			LOGGER.log( Level.SEVERE, "#### ERROR: {0} ", txtLines );
		}
		return txtLines;
	}

	public static String urlPost( String link, String postParms ) {
		//
		// http://zetcode.com/java/getpostrequest/
		String txtLines = "";
		try{
			URL url = new URL( link );
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection( );
			httpConn.setDoOutput(true);
			httpConn.setRequestMethod( "POST" );
			httpConn.setRequestProperty( "User-Agent", USER_AGENT );
			httpConn.setRequestProperty( "Content-Type", CONTENTTYPE_FORM );
			//
			OutputStream outputStream = httpConn.getOutputStream( );
			outputStream.write( postParms.getBytes( ) );
			outputStream.flush( );
			outputStream.close( );
			//
			int responseCode = httpConn.getResponseCode( );
			LOGGER.info( "Sending POST : " + url );
			LOGGER.info( "Response code: " + responseCode );
			//
			InputStream inputStream = httpConn.getInputStream( );
			InputStreamReader isr = new InputStreamReader( inputStream );
			BufferedReader bufferedReader = null;
			StringBuffer stringBuffer = new StringBuffer( );
			String txtLine = "";
			if (responseCode == HttpURLConnection.HTTP_OK ) {
				//
				bufferedReader = new BufferedReader(isr);
				while ( ( txtLine = bufferedReader.readLine( ) ) != null ) {
					stringBuffer.append(txtLine);
				}
				bufferedReader.close( );
				txtLines = stringBuffer.toString( );
			}
			else {
				LOGGER.info( "POST failed to: " +  link );
			}
		} 
		catch( IOException ex ) {
			txtLines = ex.getMessage( );
			LOGGER.log( Level.SEVERE, "#### ERROR: {0} ", txtLines );
		}
		return txtLines;
	}

	public static List<File> getFilesFromZip( String fileName ) {
		//
		// https://www.baeldung.com/java-compress-and-uncompress
		List<File> list = new ArrayList<>( );
		if ( fileName == null || fileName.equals( "" ) ) { fileName = FLD_SAMPLE + ZIP_SAMPLE; }
		int BUFFER_SIZE = 4096;
		String DIR_TEMP = "src/main/resources/temp/";
		String fileItem = "";
		try {
			//
			ClassLoader classLoader = ClassLoader.getSystemClassLoader( );
			File fileZip = new File( classLoader.getResource( fileName ).getFile( ) );
			FileInputStream fis = new FileInputStream( fileZip );
			ZipInputStream zis = new ZipInputStream( fis );
			ZipEntry zipEntry = zis.getNextEntry();
			//
			FileOutputStream fos = null;
			byte[] bytes = null;
			int intReadLen = 0;
			File file = null;
			while (zipEntry != null) {
				//
				fileItem = zipEntry.getName( );
				file = new File( DIR_TEMP + fileItem );
				intReadLen = 0;
				bytes = new byte[ BUFFER_SIZE ];
				fos = new FileOutputStream( file );
				while ( (intReadLen = zis.read( bytes ) ) > 0 ) {
					fos.write( bytes , 0, intReadLen );
				}
				fos.close( );
				zipEntry = zis.getNextEntry( );
				list.add( file );
			}
		}
		catch (IOException ex) { LOGGER.info( ex.getMessage( ) ); }
		return list;
	}

	public static String getZipFileList( String fileName , String delim ) {
		//
		String txtLines = "";
		if ( delim == null || delim.equals( "" ) ) { delim = DLM; }
		//
		List<File> list = UtilityMain.getFilesFromZip( fileName );
		for (File file : list) { txtLines += file.getName( ) + delim; }
		return txtLines;
	}

	public static File putFilesIntoZip( List<File> list ) { /* ??? */
		//
		// https://www.baeldung.com/java-compress-and-uncompress
		File file = null;
		return null;
	}

	public static String getXmlFileNode( String xmlfile, String xpathTxt, String delim ) {
		//
		// https://howtodoinjava.com/xml/evaluate-xpath-on-xml-string/
		String txtLines = "";
		if ( xmlfile == null || xmlfile.equals( "" ) ) { xmlfile = "src/main/resources/" + FLD_SAMPLE + XML_SAMPLE; }
		if ( xpathTxt == null || xpathTxt.equals( "" ) ) { xpathTxt = "/catalog/book/title"; }
		if ( delim == null || delim.equals( "" ) ) { delim = DLM; }
		//
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance( );
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder( ); // PCE
			Document document = documentBuilder.parse( xmlfile ); // SAXException
			XPathFactory xpf = XPathFactory.newInstance( );
			XPath xPath = xpf.newXPath( );
			txtLines = (String) xPath.evaluate( xpathTxt , document , STRING );
			//
			String xpathNode = "/catalog/book/@id";
			NodeList nodeList = (NodeList) xPath.evaluate( xpathNode , document, NODESET );
			for (int ictr = 0; ictr < nodeList.getLength( ); ictr++) {
				txtLines += delim + nodeList.item( ictr ).getNodeValue( );
			}
		}
		catch (ParserConfigurationException ex)	 { LOGGER.info( ex.getMessage( ) ); }
		catch (SAXException ex)					 { LOGGER.info( ex.getMessage( ) ); }
		catch (XPathExpressionException ex)		 { LOGGER.info( ex.getMessage( ) ); }
		catch (IOException ex)					 { LOGGER.info( ex.getMessage( ) ); }
		return txtLines;
	}

	public static String getXmlNode( String xml, String xpathTxt, String delim ) {
		//
		// https://howtodoinjava.com/xml/evaluate-xpath-on-xml-string/
		String txtLines = "";
		if ( delim == null || delim.equals( "" ) ) { delim = DLM; }
		//
		try {
			StringReader stringReader = new StringReader( xml );
			InputSource inputSource = new InputSource( stringReader );
			XPath xPath = XPathFactory.newInstance( ).newXPath( );
			txtLines = xPath.evaluate( xpathTxt , inputSource );
		}
		catch (XPathExpressionException ex)		 { LOGGER.info( ex.getMessage( ) ); }
		return txtLines;
	}

	public static String convertXml2Json( String xml ) {
		//
		String json = "";
		if ( xml == null || xml.equals( "" ) ) { xml = getFileLocal( FLD_SAMPLE + XML_SAMPLE, "" ); }
		int INDENT_FACTOR = 4;
		//
		try {
			JSONObject jsonObject = XML.toJSONObject( xml );
			json = jsonObject.toString( INDENT_FACTOR );
		}
		catch (JSONException ex) { LOGGER.info( ex.getMessage( ) ); }
		return json;
	}

	public static String convertJson2Xml( String json ) {
		//
		String xml = "";
		if ( json == null || json.equals( "" ) ) { json = getFileLocal( FLD_SAMPLE + JSN_SAMPLE, "" ); }
		//
		try {
			JSONObject jsonObj = new JSONObject( json );
			xml = XML.toString(jsonObj);
		}
		catch (JSONException ex) { LOGGER.info( ex.getMessage( ) ); }
		return xml;
	}

	public static String formatXml( String xmlOld ) {
		//
		String xml = "";
		if ( xmlOld == null || xmlOld.equals( "" ) ) { xmlOld = getFileLocal( FLD_SAMPLE + XML_SAMPLE, "" ); }
		//
		Document document = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance( );
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder( );
			StringReader stringReader = new StringReader( xmlOld );
			InputSource inputSource = new InputSource( stringReader );
			document = documentBuilder.parse( inputSource );
		}
		catch (ParserConfigurationException  ex) { LOGGER.info( ex.getMessage( ) ); }
		catch (SAXException ex) { LOGGER.info( ex.getMessage( ) ); }
		catch (IOException ex) { LOGGER.info( ex.getMessage( ) ); }
		try {
			StringWriter stringWriter = new StringWriter( );
			StreamResult xmlOutput = new StreamResult( stringWriter );
			TransformerFactory tf = TransformerFactory.newInstance( );
			// tf.setAttribute( "indent-number", 4 );
			Transformer transformer = tf.newTransformer( );
			transformer.setOutputProperty( OutputKeys.METHOD, "xml" );
			transformer.setOutputProperty( "{http://xml.apache.org/xslt}indent-amount", "4" );
			transformer.setOutputProperty( OutputKeys.OMIT_XML_DECLARATION, "no" );
			transformer.setOutputProperty( OutputKeys.INDENT, "yes" );
			transformer.setOutputProperty( OutputKeys.ENCODING, "UTF-8" );
			DOMSource domSource = new DOMSource( document );
			transformer.transform( domSource , xmlOutput );
			xml = xmlOutput.getWriter( ).toString( ) ;
		}
		catch (TransformerConfigurationException ex) { LOGGER.info( ex.getMessage( ) ); }
		catch (TransformerException ex) { LOGGER.info( ex.getMessage( ) ); }
		return xml;
	}

}
