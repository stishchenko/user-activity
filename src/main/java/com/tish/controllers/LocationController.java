package com.tish.controllers;

import com.tish.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
@RestController
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
	public Map<Object, Object> getCountries(/*@RequestBody(required = false) String graphicType,
											@RequestBody String statisticsType,
											@RequestBody String dataType,
											@RequestBody String webApp,
											@RequestBody String fromDate, @RequestBody String toDate*/
			@RequestBody Map<String, String> params) {
		// dataType  = value, percent or value+percent
		List<Map<String, Double>> userMapList = locationService.getCountriesStatistics("user", params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		List<Map<String, Double>> visitMapList = locationService.getCountriesStatistics("visit", params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		Map<Object, Object> allMap = new HashMap<>();
		allMap.put("users", userMapList);
		allMap.put("visits", visitMapList);

		return allMap;
	}

	@PostMapping(path = {"/city"})
	public Map<Object, Object> getCities(/*@RequestBody(required = false) String graphicType,
										 @RequestBody String statisticsType,
										 @RequestBody String dataType,
										 @RequestBody String webApp,
										 @RequestBody String fromDate, @RequestBody String toDate*/
			@RequestBody Map<String, String> params) {
		// dataType  = value, percent or value+percent
		List<Map<String, Double>> userMapList = locationService.getCitiesStatistics("user", params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		List<Map<String, Double>> visitMapList = locationService.getCitiesStatistics("visit", params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));

		Map<Object, Object> allMap = new HashMap<>();
		allMap.put("users", userMapList);
		allMap.put("visits", visitMapList);

		return allMap;
	}
}
