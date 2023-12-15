package com.tish.controllers;

import com.tish.models.Account;
import com.tish.models.Settings;
import com.tish.services.AccountService;
import com.tish.services.VisitTimeService;
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
@RequestMapping(path = "/visit-time")
public class VisitTimeController {

	private final VisitTimeService visitTimeService;
	private final AccountService accountService;

	public VisitTimeController(@Autowired VisitTimeService visitTimeService, @Autowired AccountService accountService) {
		this.visitTimeService = visitTimeService;
		this.accountService = accountService;
	}

	@GetMapping(path = {"/avg-time-period"})
	public String getAvgVisitTimePage(HttpSession session, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}

		model.addAttribute("type", "bar");
		model.addAttribute("axis", "x");
		model.addAttribute("settings", new Settings());

		Map<String, List> map = visitTimeService.getAvgVisitTimeByPeriod("year", null, null, "app1");
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));

		session.setAttribute("dataType", "value");
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("metric", "avg visit time");
		session.setAttribute("app", "app1");

		return "visit-avg-time-statistics";
	}

	@GetMapping(path = {"/avg-page-period"})
	public String getAvgPageStatisticsPage(HttpSession session, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		model.addAttribute("type", "pie");
		model.addAttribute("axis", "x");
		model.addAttribute("settings", new Settings());

		Map<String, List> map = visitTimeService.getAvgVisitTimeByPage(null, null, "app1");
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));

		session.setAttribute("dataType", "value");
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("metric", "avg page visit time");
		session.setAttribute("app", "app1");

		return "visit-avg-page-statistics";
	}

	@GetMapping(path = {"/cancellation"})
	public String getCancellationPage(HttpSession session, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		model.addAttribute("type", "pie");
		model.addAttribute("axis", "x");
		model.addAttribute("settings", new Settings());

		String dataType = "value+percent";

		Map<String, List> map = visitTimeService.getCancellations(dataType, null, null, "app1");
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType);

		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("percent", map.get("percent"));
		session.setAttribute("metric", "cancellation");
		session.setAttribute("app", "app1");

		return "visit-cancellation-statistics";
	}

	@PostMapping(path = {"/avg-time-period"})
	public String getAverageVisitTimeByPeriod(@ModelAttribute("settings") Settings settings, HttpSession session, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		if (settings.getChartType().equals("line")) {
			model.addAttribute("type", "line");
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}

		Map<String, List> map = visitTimeService.getAvgVisitTimeByPeriod(settings.getPeriodType(), settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));

		session.setAttribute("dataType", "value");
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("metric", "avg visit time");
		session.setAttribute("app", settings.getWebApp());

		return "visit-avg-time-statistics";
	}

	@PostMapping(path = {"/avg-page-period"})
	public String getAveragePagePeriod(@ModelAttribute("settings") Settings settings, HttpSession session, Model model) {
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

		Map<String, List> map = visitTimeService.getAvgVisitTimeByPage(settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));

		session.setAttribute("dataType", "value");
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("metric", "avg page visit time");
		session.setAttribute("app", settings.getWebApp());

		return "visit-avg-page-statistics";
	}

	@PostMapping(path = {"/cancellation"})
	public String getCancellationAmount(@ModelAttribute("settings") Settings settings, BindingResult result, HttpSession session, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}

		if (settings.getChkTypeValues() == null && settings.getChkTypePercents() == null) {
			result.rejectValue("dataTypes", null, "At least one type of data - values or percents - must be chosen");
		}

		if (result.hasErrors()) {
			model.addAttribute("chkErrorClick", true);
			return "visit-cancellation-statistics";
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

		Map<String, List> map = visitTimeService.getCancellations(dataType, settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType);

		session.setAttribute("dataType", dataType);
		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("percent", map.get("percent"));
		session.setAttribute("metric", "cancellation");
		session.setAttribute("app", settings.getWebApp());

		return "visit-cancellation-statistics";
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
