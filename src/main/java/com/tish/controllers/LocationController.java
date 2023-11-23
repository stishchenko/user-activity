package com.tish.controllers;

import com.tish.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/location")
public class LocationController {

	private final LocationService locationService;

	public LocationController(@Autowired LocationService locationService) {
		this.locationService = locationService;
	}


	@GetMapping(path = {"", "/"})
	public String getLocationPage(Model model) {

		return "";
	}

	@PostMapping(path = {"/country"})
	public Map<Object, Object> getCountries(@RequestBody(required = false) String graphicType,
											@RequestBody String statisticsType,
											@RequestBody String dataType,
											@RequestBody String webApp,
											@RequestBody String fromDate, @RequestBody String toDate) {
		// dataType  = value, percent or value+percent
		List<Map<String, Double>> userMapList = locationService.getCountriesStatistics("user", dataType, fromDate, toDate, webApp);
		List<Map<String, Double>> visitMapList = locationService.getCountriesStatistics("visit", dataType, fromDate, toDate, webApp);
		Map<Object, Object> allMap = new HashMap<>();
		allMap.put("users", userMapList);
		allMap.put("visits", visitMapList);

		return allMap;
	}

	@PostMapping(path = {"/city"})
	public Map<Object, Object> getCities(@RequestBody(required = false) String graphicType,
										 @RequestBody String statisticsType,
										 @RequestBody String dataType,
										 @RequestBody String webApp,
										 @RequestBody String fromDate, @RequestBody String toDate) {
		// dataType  = value, percent or value+percent
		List<Map<String, Double>> userMapList = locationService.getCitiesStatistics("user", dataType, fromDate, toDate, webApp);
		List<Map<String, Double>> visitMapList = locationService.getCitiesStatistics("visit", dataType, fromDate, toDate, webApp);

		Map<Object, Object> allMap = new HashMap<>();
		allMap.put("users", userMapList);
		allMap.put("visits", visitMapList);

		return allMap;
	}
}
