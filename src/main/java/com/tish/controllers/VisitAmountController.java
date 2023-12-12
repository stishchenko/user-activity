package com.tish.controllers;

import com.tish.models.Account;
import com.tish.models.Settings;
import com.tish.services.AccountService;
import com.tish.services.VisitAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/visits")
public class VisitAmountController {

	private final VisitAmountService visitAmountService;
	private final AccountService accountService;

	public VisitAmountController(@Autowired VisitAmountService visitAmountService, @Autowired AccountService accountService) {
		this.visitAmountService = visitAmountService;
		this.accountService = accountService;
	}

	@GetMapping(path = {"/amount"})
	public String getVisitAmountPage(Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		model.addAttribute("type", "bar");
		model.addAttribute("axis", "x");
		model.addAttribute("values", Arrays.asList(10, 15, 12, 17, 30, 22));
		model.addAttribute("percent", Arrays.asList(10, 15, 12, 17, 30, 22));
		model.addAttribute("labels", Arrays.asList("1", "2", "3", "4", "5", "6"));
		model.addAttribute("settings", new Settings());
		model.addAttribute("dataType", Arrays.asList("value", "percent"));
		return "visit-amount-statistics";
	}

	@PostMapping(path = {"/amount"})
	public String getTotalVisits(@ModelAttribute("settings") Settings settings, Model model) {
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
		if (settings.getChkTypeValues() == null || settings.getChkTypePercents() == null) {
			dataType = settings.getChkTypeValues() != null ? "value" : "percent";
		}

		Map<String, List> map = visitAmountService.getVisitAmount(dataType, settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType.contains("+") ? settings.getDataTypes() : dataType);

		return "visit-amount-statistics";
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
