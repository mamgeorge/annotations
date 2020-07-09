package com.basics.service;

import com.basics.model.City;
import com.basics.repository.CityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// @Service annotation declares CityService to be a service class; a class that provides business services
// @Autowired annotation marks cityRepository field to be injected with CityRepository

@Service
public class CityService implements ICityService {

	@Autowired
	private CityRepository cityRepository;

	@Override
	public List<City> findAll() { return (List<City>) cityRepository.findAll(); }
}