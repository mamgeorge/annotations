package com.basics.controller;

import com.basics.services.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

// new
import static com.basics.util.UtilityMain.GREEN;
import static com.basics.util.UtilityMain.RESET;
import com.basics.util.UtilityMain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;

// @Controller annotation marks a class as a web controller
// @RequestMapping maps HTTP request with a path to a controller method
// In the second case, it maps the /cities URL to the showCities() method.
// cd c:\workspace\github\spring_annotations
// mvn clean install
@RestController 
public class BasicsController {

	@Autowired private ICityService cityService;

	@Autowired private ApplicationContext applicationContext;

	@GetMapping( "/" ) 
	public ModelAndView root(Model model) {
		//
		System.out.println("index");
		Map<String, Object> params = new HashMap<>( );
		params.put( "index", "index" );
		// return "index";
		return new ModelAndView("index", params);		
	}

	@RequestMapping( "/cities" ) 
	public ModelAndView showCities( ) {
		//
		System.out.println("cities");
		List cities = cityService.findAll( );
		Map<String, Object> params = new HashMap<>( );
		params.put( "cities", cities );
		return new ModelAndView("showCities", params);
	}

	@GetMapping( "/timer" )
	public String showTimer( ) { 
		//
		System.out.println( GREEN + "timer" + RESET );
		System.out.println( UtilityMain.showTime( ) );
		return UtilityMain.showTime( ); 
	}	

	@GetMapping( "/utils" )
	public String showUtils( ) { 
		//
		String txtlines = "";
		System.out.println( GREEN + "utils" + RESET );
		// txtlines = UtilityMain.getFileLocal( "" , "<br />" );
		// txtlines = UtilityMain.getZipFileList( "" , "<br />" );
		// txtlines = UtilityMain.getXmlFileNode( "" , "" , "" );
		// txtlines = UtilityMain.convertXml2Json( "" );
		// txtlines = UtilityMain.convertJson2Xml( "" );
		txtlines = UtilityMain.formatXml( UtilityMain.convertJson2Xml( "" ) );
		System.out.println( txtlines );
		return txtlines; 
	}

	@GetMapping( "/exits" )
	public void exits( ) {
		//
		System.out.println( GREEN + "EXIT" + RESET );
		SpringApplication.exit(applicationContext);
	}

}