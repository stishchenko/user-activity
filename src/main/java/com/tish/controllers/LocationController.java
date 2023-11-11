package com.tish.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/location")
public class LocationController {

	@GetMapping(path = {"/country"})
	public void getCountries() {
		Integer totalVisits = 0;
		Integer totalUsers = 0;

		Integer countryVisitAmount = 0;
		Integer countryUserAmount = 0;

		//foreach country
		Double countryVisitPercent = countryVisitAmount / totalVisits * 100.0;
		Double countryUserPercent = countryUserAmount / totalUsers * 100.0;

		//send it back with map
	}

	@GetMapping(path = {"/city"})
	public void getCities() {
		Integer totalVisits = 0;
		Integer totalUsers = 0;

		Integer cityVisitAmount = 0;
		Integer cityUserAmount = 0;

		//foreach country
		Double cityVisitPercent = cityVisitAmount / totalVisits * 100.0;
		Double cityUserPercent = cityUserAmount / totalUsers * 100.0;

		//send it back with map
	}
}
