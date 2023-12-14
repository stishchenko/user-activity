package com.tish.controllers;

import com.tish.models.Account;
import com.tish.models.Settings;
import com.tish.services.AccountService;
import com.tish.services.DeviceService;
import jakarta.servlet.http.HttpSession;
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
	public String getDeviceTypePage(HttpSession session, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		model.addAttribute("type", "pie");
		model.addAttribute("axis", "x");
		model.addAttribute("settings", new Settings());

		String dataType = "value+percent";
		Map<String, List> map = deviceService.getDevicesByType(dataType, null, null, "app1");
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType);

		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("percent", map.get("percent"));
		session.setAttribute("metric", "types");
		session.setAttribute("app", "app1");

		return "device-type-statistics";
	}

	@GetMapping(path = {"/os"})
	public String getDeviceOSPage(HttpSession session, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		model.addAttribute("type", "pie");
		model.addAttribute("axis", "x");
		model.addAttribute("settings", new Settings());

		String dataType = "value+percent";
		Map<String, List> map = deviceService.getDevicesByOS(dataType, null, null, "app1");
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType);

		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("percent", map.get("percent"));
		session.setAttribute("metric", "os platforms");
		session.setAttribute("app", "app1");

		return "device-os-statistics";
	}

	@GetMapping(path = {"/browser"})
	public String getDeviceBrowserPage(HttpSession session, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		model.addAttribute("type", "pie");
		model.addAttribute("axis", "x");
		model.addAttribute("settings", new Settings());

		String dataType = "value+percent";
		Map<String, List> map = deviceService.getDevicesByBrowser(dataType, null, null, "app1");
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType);

		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("percent", map.get("percent"));
		session.setAttribute("metric", "browsers");
		session.setAttribute("app", "app1");

		return "device-browser-statistics";
	}

	@PostMapping(path = {"/type"})
	public String getTypes(@ModelAttribute("settings") Settings settings, HttpSession session, Model model) {
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
		model.addAttribute("dataType", dataType);

		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("percent", map.get("percent"));
		session.setAttribute("metric", "types");
		session.setAttribute("app", settings.getWebApp());

		return "device-type-statistics";
	}

	@PostMapping(path = {"/os"})
	public String getOS(@ModelAttribute("settings") Settings settings, HttpSession session, Model model) {
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
		model.addAttribute("dataType", dataType);

		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("percent", map.get("percent"));
		session.setAttribute("metric", "os platforms");
		session.setAttribute("app", settings.getWebApp());

		return "device-os-statistics";
	}

	@PostMapping(path = {"/browser"})
	public String getBrowsers(@ModelAttribute("settings") Settings settings, HttpSession session, Model model) {
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
		model.addAttribute("dataType", dataType);

		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("percent", map.get("percent"));
		session.setAttribute("metric", "browsers");
		session.setAttribute("app", settings.getWebApp());

		return "device-browser-statistics";
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
