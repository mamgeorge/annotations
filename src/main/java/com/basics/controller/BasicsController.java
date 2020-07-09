package com.basics.controller;

import com.basics.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

// @Controller annotation marks a class as a web controller
// @RequestMapping maps HTTP request with a path to a controller method
// In the second case, it maps the /cities URL to the showCities() method.
@Controller
public class BasicsController {

	@Autowired
	private ICityService cityService;

	@RequestMapping( "/" )
	public String index(Model model) {
		//
		System.out.println("index");
		return "index";
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
}