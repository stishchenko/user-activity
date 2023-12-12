package com.tish.controllers;

import com.tish.models.Account;
import com.tish.models.Settings;
import com.tish.services.AccountService;
import com.tish.services.UserAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/users")
public class UserAmountController {

	private final UserAmountService userAmountService;
	private final AccountService accountService;

	public UserAmountController(@Autowired UserAmountService userAmountService, @Autowired AccountService accountService) {
		this.userAmountService = userAmountService;
		this.accountService = accountService;
	}

	@GetMapping(path = {"/ratio"})
	public String getUsersRatioPage(Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		addDataToModel(model);

		return "user-ratio-statistics";
	}

	@GetMapping(path = {"/user-page-visit"})
	public String getUsersPageVisitPage(Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		addDataToModel(model);

		return "user-page-statistics";
	}

	@PostMapping(path = {"/ratio"})
	public String getUsersAmount(@ModelAttribute("settings") Settings settings, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}

		if (settings.getChartType().equals("pie")) {
			model.addAttribute("type", "pie");
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		String dataType = "value+percent";
		if (settings.getDataTypes().contains(null)) {
			dataType = settings.getDataTypes().get(0) != null ? "value" : "percent";
		}

		Map<String, List> map = userAmountService.getUsersAmountAsSingleAndRepeat(dataType, settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType.contains("+") ? settings.getDataTypes() : dataType);

		return "user-ratio-statistics";
	}

	@PostMapping(path = {"/user-page-visit"})
	public String getUserAmountByPageVisits(@ModelAttribute("settings") Settings settings, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}

		if (settings.getChartType().equals("pie")) {
			model.addAttribute("type", "pie");
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		String dataType = "value+percent";
		if (settings.getDataTypes().contains(null)) {
			dataType = settings.getDataTypes().get(0) != null ? "value" : "percent";
		}

		Map<String, List> map = userAmountService.getUsersAmountByVisitTimes(dataType, settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType.contains("+") ? settings.getDataTypes() : dataType);

		return "user-page-statistics";
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
