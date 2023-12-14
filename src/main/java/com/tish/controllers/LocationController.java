package com.tish.controllers;

import com.tish.models.Account;
import com.tish.models.Settings;
import com.tish.services.AccountService;
import com.tish.services.LocationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	public String getLocationCountryPage(HttpSession session, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}

		model.addAttribute("settings", new Settings());

		model.addAttribute("type", "bar");
		model.addAttribute("axis", "x");

		String dataType = "value+percent";
		String objectType = "user+visit";

		Map<String, List> map = locationService.getCountriesStatistics(objectType, dataType, null, null, "app1");
		fillModel(model, map, objectType);

		model.addAttribute("objectType", objectType);
		model.addAttribute("dataType", dataType);

		session.setAttribute("objectType", objectType);
		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("userValues", map.get("userValues"));
		session.setAttribute("userPercent", map.get("userPercent"));
		session.setAttribute("visitValues", map.get("visitValues"));
		session.setAttribute("visitPercent", map.get("visitPercent"));
		session.setAttribute("metric", "countries");
		session.setAttribute("app", "app1");

		return "country-statistics";
	}

	@GetMapping(path = {"/city"})
	public String getLocationCityPage(HttpSession session, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		model.addAttribute("settings", new Settings());

		model.addAttribute("type", "bar");
		model.addAttribute("axis", "x");

		String dataType = "value+percent";
		String objectType = "user+visit";

		Map<String, List> map = locationService.getCitiesStatistics(objectType, dataType, null, null, "app1");
		fillModel(model, map, objectType);

		model.addAttribute("objectType", objectType);
		model.addAttribute("dataType", dataType);

		session.setAttribute("objectType", objectType);
		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("userValues", map.get("userValues"));
		session.setAttribute("userPercent", map.get("userPercent"));
		session.setAttribute("visitValues", map.get("visitValues"));
		session.setAttribute("visitPercent", map.get("visitPercent"));
		session.setAttribute("metric", "cities");
		session.setAttribute("app", "app1");

		return "city-statistics";
	}

	@PostMapping(path = {"/country"})
	public String getCountries(@ModelAttribute("settings") Settings settings, BindingResult result, HttpSession session, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}

		if (settings.getChkTypeValues() == null && settings.getChkTypePercents() == null) {
			result.rejectValue("dataTypes", null, "At least one type of data - values or percents - must be chosen");
		}
		if (settings.getChkObjectTypeUsers() == null && settings.getChkObjectTypeVisits() == null) {
			result.rejectValue("dataTypes", null, "At least one type of object - users or visits - must be chosen");
		}

		if (result.hasErrors()) {
			model.addAttribute("chkErrorClick", true);
			return "country-statistics";
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

		model.addAttribute("objectType", objectType);
		model.addAttribute("dataType", dataType);

		session.setAttribute("objectType", objectType);
		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("userValues", map.get("userValues"));
		session.setAttribute("userPercent", map.get("userPercent"));
		session.setAttribute("visitValues", map.get("visitValues"));
		session.setAttribute("visitPercent", map.get("visitPercent"));
		session.setAttribute("metric", "countries");
		session.setAttribute("app", settings.getWebApp());

		return "country-statistics";
	}

	@PostMapping(path = {"/city"})
	public String getCities(@ModelAttribute("settings") Settings settings, BindingResult result, HttpSession session, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}

		if (settings.getChkTypeValues() == null && settings.getChkTypePercents() == null) {
			result.rejectValue("dataTypes", null, "At least one type of data - values or percents - must be chosen");
		}
		if (settings.getChkObjectTypeUsers() == null && settings.getChkObjectTypeVisits() == null) {
			result.rejectValue("dataTypes", null, "At least one type of object - users or visits - must be chosen");
		}

		if (result.hasErrors()) {
			model.addAttribute("chkErrorClick", true);
			return "city-statistics";
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

		model.addAttribute("objectType", objectType);
		model.addAttribute("dataType", dataType);

		session.setAttribute("objectType", objectType);
		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("userValues", map.get("userValues"));
		session.setAttribute("userPercent", map.get("userPercent"));
		session.setAttribute("visitValues", map.get("visitValues"));
		session.setAttribute("visitPercent", map.get("visitPercent"));
		session.setAttribute("metric", "cities");
		session.setAttribute("app", settings.getWebApp());

		return "city-statistics";
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
