package com.basics.util;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UtilityMain {

	public static final Logger LOGGER = Logger.getLogger( UtilityMain.class.getName( ) );
	public static final String RESET = "\u001b[0m";
	public static final String GREEN = "\u001b[32,1m";
	public static final String DLM = "\n";

	public static final String FLD_SAMPLE = "static/" ;
	public static final String TXT_SAMPLE = "Genesis_01.txt" ;
	public static final String XML_SAMPLE = "xml/books.xml" ;
	public static final String ZIP_SAMPLE = "xml_wav_plants_w10.zip" ;

	public static void main( String[ ] args ) {
		//
		System.out.println( GREEN + "DONE" + RESET );
	}

	public static String showTime( ) {
		//
		String txtLine = "";
		txtLine = GREEN + new Date( ).toString( ) + RESET;
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

	public static List<File> getFilesFromZip( String fileName ) {
		//
		// https://www.baeldung.com/java-compress-and-uncompress
		List<File> list = new ArrayList<>( );
		if ( fileName == null || fileName.equals( "" ) ) { fileName = FLD_SAMPLE + ZIP_SAMPLE; }
		int BUFFER_SIZE = 4096;
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
				file = new File( fileItem );
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

	public static String getFileList( String fileName , String delim ) {
		//
		String txtLines = "";
		if ( delim == null || delim.equals( "" ) ) { delim = DLM; }
		//
		List<File> list = UtilityMain.getFilesFromZip( fileName );
		for (File file : list) { txtLines += file.getName( ) + delim; }
		return txtLines;
	}

	public static File putFilesIntoZip( List<File> list ) {
		//
		// https://www.baeldung.com/java-compress-and-uncompress
		File file = null;
		return null;
	}

	public static String getXmlNode( String xml, String xpath ) {
		//
		// jaxen?
		String txtLines = "";
		return txtLines;
	}
}
