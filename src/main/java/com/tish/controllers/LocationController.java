package com.tish.controllers;

import com.tish.models.Account;
import com.tish.models.Settings;
import com.tish.services.AccountService;
import com.tish.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = {"/location"})
public class LocationController {

	private final LocationService locationService;
	private final AccountService accountService;

	public LocationController(@Autowired LocationService locationService, @Autowired AccountService accountService) {
		this.locationService = locationService;
		this.accountService = accountService;
	}


	@GetMapping(path = {"/country"})
	public String getLocationCountryPage(Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		fillModel(model);

		return "country-statistics";
	}

	@GetMapping(path = {"/city"})
	public String getLocationCityPage(Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		fillModel(model);
		
		return "city-statistics";
	}

	@PostMapping(path = {"/country"})
	public String getCountries(@ModelAttribute("settings") Settings settings, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}

		if (settings.getChartType().equals("radar")) {
			model.addAttribute("type", "radar");
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		String dataType = "value+percent";
		if (settings.getChkTypeValues() == null || settings.getChkTypePercents() == null) {
			dataType = settings.getChkTypeValues() != null ? "value" : "percent";
		}

		String objectType = "user+visit";
		if (settings.getChkObjectTypeUsers() == null || settings.getChkObjectTypeVisits() == null) {
			objectType = settings.getChkObjectTypeUsers() != null ? "user" : "visit";
		}

		Map<String, List> map = locationService.getCountriesStatistics(objectType, dataType, settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		fillModel(model, map, objectType);

		model.addAttribute("objectType", objectType.contains("+") ? settings.getStatisticObjectType() : objectType);
		model.addAttribute("dataType", dataType.contains("+") ? settings.getDataTypes() : dataType);


		return "country-statistics";
	}

	@PostMapping(path = {"/city"})
	public String getCities(@ModelAttribute("settings") Settings settings, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}

		if (settings.getChartType().equals("radar")) {
			model.addAttribute("type", "radar");
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		String dataType = "value+percent";
		if (settings.getChkTypeValues() == null || settings.getChkTypePercents() == null) {
			dataType = settings.getChkTypeValues() != null ? "value" : "percent";
		}

		String objectType = "user+visit";
		if (settings.getChkObjectTypeUsers() == null || settings.getChkObjectTypeVisits() == null) {
			objectType = settings.getChkObjectTypeUsers() != null ? "user" : "visit";
		}

		Map<String, List> map = locationService.getCitiesStatistics(objectType, dataType, settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		fillModel(model, map, objectType);

		model.addAttribute("objectType", objectType.contains("+") ? settings.getStatisticObjectType() : objectType);
		model.addAttribute("dataType", dataType.contains("+") ? settings.getDataTypes() : dataType);

		return "city-statistics";
	}

	private void fillModel(Model model) {
		model.addAttribute("type", "bar");
		model.addAttribute("axis", "x");

		model.addAttribute("labels", Arrays.asList("1", "2", "3", "4", "5", "6"));
		model.addAttribute("userValues", Arrays.asList(10, 15, 17, 13, 20, 8));
		model.addAttribute("visitValues", Arrays.asList(12, 17, 23, 12, 22, 14));
		model.addAttribute("userPercent", Arrays.asList(10, 15, 17, 13, 20, 8));
		model.addAttribute("visitPercent", Arrays.asList(12, 17, 23, 12, 22, 14));

		model.addAttribute("settings", new Settings());

		model.addAttribute("objectType", Arrays.asList("user", "visit"));
		model.addAttribute("dataType", Arrays.asList("value", "percent"));
	}

	private void fillModel(Model model, Map<String, List> map, String objectType) {
		switch (objectType) {
			case "user" -> {
				model.addAttribute("labels", map.get("labels"));
				model.addAttribute("userValues", map.get("userValues"));
				model.addAttribute("userPercent", map.get("userPercent"));
			}
			case "visit" -> {
				model.addAttribute("labels", map.get("labels"));
				model.addAttribute("visitValues", map.get("visitValues"));
				model.addAttribute("visitPercent", map.get("visitPercent"));
			}
			default -> {
				model.addAttribute("labels", map.get("labels"));
				model.addAttribute("userValues", map.get("userValues"));
				model.addAttribute("userPercent", map.get("userPercent"));
				model.addAttribute("visitValues", map.get("visitValues"));
				model.addAttribute("visitPercent", map.get("visitPercent"));
			}
		}
	}

	private boolean checkLoggedAccount(Model model) {
		Account account = accountService.checkIfLoggedAccountExists();

		if (account != null) {
			model.addAttribute("accountLogin", account.getLogin());
			return true;
		}

		return false;
	}
}
