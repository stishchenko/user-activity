package com.tish.controllers;

import com.tish.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/location")
public class LocationController {

	private final LocationService locationService;

	public LocationController(@Autowired LocationService locationService) {
		this.locationService = locationService;
	}


	@GetMapping(path = {"/country"})
	public void getCountries() {
		List<Map<String, Double>> userMapList = locationService.getCountriesStatistics("user");
		List<Map<String, Double>> visitMapList = locationService.getCountriesStatistics("visit");
	}

	@GetMapping(path = {"/city"})
	public void getCities() {
		List<Map<String, Double>> userMapList = locationService.getCitiesStatistics("user");
		List<Map<String, Double>> visitMapList = locationService.getCitiesStatistics("visit");
	}
}
