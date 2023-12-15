package com.tish.controllers;

import com.tish.models.Account;
import com.tish.models.Settings;
import com.tish.services.AccountService;
import com.tish.services.UserAmountService;
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
@RequestMapping(path = "/users")
public class UserAmountController {

	private final UserAmountService userAmountService;
	private final AccountService accountService;

	public UserAmountController(@Autowired UserAmountService userAmountService, @Autowired AccountService accountService) {
		this.userAmountService = userAmountService;
		this.accountService = accountService;
	}

	@GetMapping(path = {"/ratio"})
	public String getUsersRatioPage(HttpSession session, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		model.addAttribute("type", "pie");
		model.addAttribute("axis", "x");
		model.addAttribute("settings", new Settings());

		String dataType = "value+percent";

		Map<String, List> map = userAmountService.getUsersAmountAsSingleAndRepeat(dataType, null, null, "app1");
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType);

		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("percent", map.get("percent"));
		session.setAttribute("metric", "user ratio");
		session.setAttribute("app", "app1");

		return "user-ratio-statistics";
	}

	@GetMapping(path = {"/user-page-visit"})
	public String getUsersPageVisitPage(HttpSession session, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		model.addAttribute("type", "pie");
		model.addAttribute("axis", "x");
		model.addAttribute("settings", new Settings());

		String dataType = "value+percent";

		Map<String, List> map = userAmountService.getUsersAmountByVisitTimes(dataType, null, null, "app1");
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType);

		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("percent", map.get("percent"));
		session.setAttribute("metric", "user page ratio");
		session.setAttribute("app", "app1");

		return "user-page-statistics";
	}

	@PostMapping(path = {"/ratio"})
	public String getUsersAmount(@ModelAttribute("settings") Settings settings, BindingResult result, HttpSession session, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}

		if (settings.getChkTypeValues() == null && settings.getChkTypePercents() == null) {
			result.rejectValue("dataTypes", null, "At least one type of data - values or percents - must be chosen");
		}

		if (result.hasErrors()) {
			model.addAttribute("chkErrorClick", true);
			return "user-ratio-statistics";
		}

		if (settings.getChartType().equals("pie")) {
			model.addAttribute("type", "pie");
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		String dataType = "value+percent";
		if (settings.getChkTypeValues() == null || settings.getChkTypePercents() == null) {
			dataType = settings.getChkTypeValues() != null ? "value" : "percent";
		}

		Map<String, List> map = userAmountService.getUsersAmountAsSingleAndRepeat(dataType, settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType);

		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("percent", map.get("percent"));
		session.setAttribute("metric", "user ratio");
		session.setAttribute("app", settings.getWebApp());

		return "user-ratio-statistics";
	}

	@PostMapping(path = {"/user-page-visit"})
	public String getUserAmountByPageVisits(@ModelAttribute("settings") Settings settings, BindingResult result, HttpSession session, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}

		if (settings.getChkTypeValues() == null && settings.getChkTypePercents() == null) {
			result.rejectValue("dataTypes", null, "At least one type of data - values or percents - must be chosen");
		}

		if (result.hasErrors()) {
			model.addAttribute("chkErrorClick", true);
			return "user-page-statistics";
		}

		if (settings.getChartType().equals("pie")) {
			model.addAttribute("type", "pie");
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		String dataType = "value+percent";
		if (settings.getChkTypeValues() == null || settings.getChkTypePercents() == null) {
			dataType = settings.getChkTypeValues() != null ? "value" : "percent";
		}

		Map<String, List> map = userAmountService.getUsersAmountByVisitTimes(dataType, settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType);

		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("percent", map.get("percent"));
		session.setAttribute("metric", "user page ratio");
		session.setAttribute("app", settings.getWebApp());

		return "user-page-statistics";
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
