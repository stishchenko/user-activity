package com.tish.controllers;

import com.tish.models.Settings;
import com.tish.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RestController
@RequestMapping(path = {"/location"})
public class LocationController {

	private final LocationService locationService;

	public LocationController(@Autowired LocationService locationService) {
		this.locationService = locationService;
	}


	@GetMapping(path = { "/country"})
	public String getLocationCountryPage(Model model) {

		model.addAttribute("type", "bar");
		model.addAttribute("axis", "x");
		/*model.addAttribute("values", Arrays.asList(10, 15, 12, 17, 30, 22));*/

		model.addAttribute("labels", Arrays.asList("1", "2", "3", "4", "5", "6"));
		model.addAttribute("userValues", Arrays.asList(10, 15, 17, 13, 20, 8));
		model.addAttribute("visitValues", Arrays.asList(12, 17, 23, 12, 22, 14));
		model.addAttribute("userPercent", Arrays.asList(10, 15, 17, 13, 20, 8));
		model.addAttribute("visitPercent", Arrays.asList(12, 17, 23, 12, 22, 14));

		model.addAttribute("settings", new Settings());

		model.addAttribute("objectType", Arrays.asList("user", "visit"));
		model.addAttribute("dataType", Arrays.asList("value", "percent"));

		return "country-statistics";
	}

	@GetMapping(path = { "/city"})
	public String getLocationCityPage(Model model) {

		model.addAttribute("type", "bar");
		model.addAttribute("axis", "x");
		model.addAttribute("values", Arrays.asList(10, 15, 12, 17, 30, 22));

		model.addAttribute("labels", Arrays.asList("1", "2", "3", "4", "5", "6"));
		model.addAttribute("settings", new Settings());

		return "city-statistics";
	}

	@PostMapping(path = {"/country"})
	public String getCountries(/*@RequestBody(required = false) String graphicType,
											@RequestBody String statisticsType,
											@RequestBody String dataType,
											@RequestBody String webApp,
											@RequestBody String fromDate, @RequestBody String toDate*/
			/*@RequestBody Map<String, String> params,*/ @ModelAttribute("settings") Settings settings, Model model) {
		// dataType  = value, percent or value+percent
		/*List<Map<String, Double>> userMapList = locationService.getCountriesStatistics("user", params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		List<Map<String, Double>> visitMapList = locationService.getCountriesStatistics("visit", params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		Map<Object, Object> allMap = new HashMap<>();
		allMap.put("users", userMapList);
		allMap.put("visits", visitMapList);*/
		model.addAttribute("settings", new Settings());

		return "country-statistics";
	}

	@PostMapping(path = {"/city"})
	public String getCities(/*@RequestBody(required = false) String graphicType,
										 @RequestBody String statisticsType,
										 @RequestBody String dataType,
										 @RequestBody String webApp,
										 @RequestBody String fromDate, @RequestBody String toDate*/
			/*@RequestBody Map<String, String> params,*/ @ModelAttribute("settings") Settings settings, Model model) {
		// dataType  = value, percent or value+percent
		/*List<Map<String, Double>> userMapList = locationService.getCitiesStatistics("user", params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		List<Map<String, Double>> visitMapList = locationService.getCitiesStatistics("visit", params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));

		Map<Object, Object> allMap = new HashMap<>();
		allMap.put("users", userMapList);
		allMap.put("visits", visitMapList);*/

		return "city-statistics";
	}
}
