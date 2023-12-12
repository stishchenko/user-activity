package com.tish.controllers;

import com.tish.models.Account;
import com.tish.models.Settings;
import com.tish.services.AccountService;
import com.tish.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/devices")
public class DeviceController {

	private final DeviceService deviceService;
	private final AccountService accountService;

	public DeviceController(@Autowired DeviceService deviceService, @Autowired AccountService accountService) {
		this.deviceService = deviceService;
		this.accountService = accountService;
	}


	@GetMapping(path = {"/type"})
	public String getDeviceTypePage(Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		addDataToModel(model);

		return "device-type-statistics";
	}

	@GetMapping(path = {"/os"})
	public String getDeviceOSPage(Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		addDataToModel(model);

		return "device-os-statistics";
	}

	@GetMapping(path = {"/browser"})
	public String getDeviceBrowserPage(Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		addDataToModel(model);

		return "device-browser-statistics";
	}

	@PostMapping(path = {"/type"})
	public String getTypes( @ModelAttribute("settings") Settings settings, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		if (!settings.getChartType().contains(" ")) {
			model.addAttribute("type", settings.getChartType());
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		String dataType = "value+percent";
		if (settings.getChkTypeValues() == null || settings.getChkTypePercents() == null) {
			dataType = settings.getChkTypeValues() != null ? "value" : "percent";
		}

		Map<String, List> map = deviceService.getDevicesByType(dataType, settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType.contains("+") ? settings.getDataTypes() : dataType);

		return "device-type-statistics";
	}

	@PostMapping(path = {"/os"})
	public String getOS(@ModelAttribute("settings") Settings settings, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		if (!settings.getChartType().contains(" ")) {
			model.addAttribute("type", settings.getChartType());
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		String dataType = "value+percent";
		if (settings.getChkTypeValues() == null || settings.getChkTypePercents() == null) {
			dataType = settings.getChkTypeValues() != null ? "value" : "percent";
		}

		Map<String, List> map = deviceService.getDevicesByOS(dataType, settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType.contains("+") ? settings.getDataTypes() : dataType);

		return "device-os-statistics";
	}

	@PostMapping(path = {"/browser"})
	public String getBrowsers(@ModelAttribute("settings") Settings settings, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}

		if (!settings.getChartType().contains(" ")) {
			model.addAttribute("type", settings.getChartType());
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		String dataType = "value+percent";
		if (settings.getChkTypeValues() == null || settings.getChkTypePercents() == null) {
			dataType = settings.getChkTypeValues() != null ? "value" : "percent";
		}

		Map<String, List> map = deviceService.getDevicesByBrowser(dataType, settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType.contains("+") ? settings.getDataTypes() : dataType);

		return "device-browser-statistics";
	}

	private void addDataToModel(Model model) {
		model.addAttribute("type", "bar");
		model.addAttribute("axis", "x");
		model.addAttribute("values", Arrays.asList(10, 15, 12, 17, 30, 22));
		model.addAttribute("percent", Arrays.asList(10, 15, 12, 17, 30, 22));
		model.addAttribute("labels", Arrays.asList("1", "2", "3", "4", "5", "6"));
		model.addAttribute("settings", new Settings());
		model.addAttribute("dataType", Arrays.asList("value", "percent"));
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
