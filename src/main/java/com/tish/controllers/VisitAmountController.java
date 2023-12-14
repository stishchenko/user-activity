package com.tish.controllers;

import com.tish.models.Account;
import com.tish.models.Settings;
import com.tish.services.AccountService;
import com.tish.services.VisitAmountService;
import jakarta.servlet.http.HttpSession;
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
	public String getVisitAmountPage(HttpSession session, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		model.addAttribute("type", "pie");
		model.addAttribute("axis", "x");
		model.addAttribute("settings", new Settings());

		String dataType = "value+percent";
		Map<String, List> map = visitAmountService.getVisitAmount(dataType, null, null, "app1");
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType);

		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("percent", map.get("percent"));
		session.setAttribute("metric", "visit amount");
		session.setAttribute("app", "app1");

		return "visit-amount-statistics";
	}

	@PostMapping(path = {"/amount"})
	public String getTotalVisits(@ModelAttribute("settings") Settings settings, HttpSession session, Model model) {
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
		model.addAttribute("dataType", dataType);

		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("percent", map.get("percent"));
		session.setAttribute("metric", "visit amount");
		session.setAttribute("app", settings.getWebApp());

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
