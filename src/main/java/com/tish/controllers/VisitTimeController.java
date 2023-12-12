package com.tish.controllers;

import com.tish.models.Account;
import com.tish.models.Settings;
import com.tish.services.AccountService;
import com.tish.services.VisitTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String getAvgVisitTimePage(Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		addDataToModel(model);

		return "visit-avg-time-statistics";
	}

	@GetMapping(path = {"/avg-page-period"})
	public String getAvgPageStatisticsPage(Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		addDataToModel(model);

		return "visit-avg-page-statistics";
	}

	@GetMapping(path = {"/cancellation"})
	public String getCancellationPage(Model model) {
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
		return "visit-cancellation-statistics";
	}

	@PostMapping(path = {"/avg-time-period"})
	public String getAverageVisitTimeByPeriod(@ModelAttribute("settings") Settings settings, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		Map<String, List> map = visitTimeService.getAvgVisitTimeByPeriod(settings.getPeriodType(), settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		if (settings.getChartType().equals("line")) {
			model.addAttribute("type", "line");
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));

		return "visit-avg-time-statistics";
	}

	@PostMapping(path = {"/avg-page-period"})
	public String getAveragePagePeriod(@ModelAttribute("settings") Settings settings, Model model) {
		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		Map<String, List> map = visitTimeService.getAvgVisitTimeByPage(settings.getStartDate(), settings.getEndDate(), settings.getWebApp());

		if (!settings.getChartType().contains(" ")) {
			model.addAttribute("type", settings.getChartType());
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));

		return "visit-avg-page-statistics";
	}

	@PostMapping(path = {"/cancellation"})
	public String getCancellationAmount(@ModelAttribute("settings") Settings settings, Model model) {
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
		if (settings.getDataTypes().contains(null)) {
			dataType = settings.getDataTypes().get(0) != null ? "value" : "percent";
		}

		Map<String, List> map = visitTimeService.getCancellations(dataType, settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType.contains("+") ? settings.getDataTypes() : dataType);

		return "visit-cancellation-statistics";
	}

	private void addDataToModel(Model model) {
		model.addAttribute("type", "bar");
		model.addAttribute("axis", "x");
		model.addAttribute("values", Arrays.asList(10, 15, 12, 17, 30, 22));
		model.addAttribute("labels", Arrays.asList("1", "2", "3", "4", "5", "6"));
		model.addAttribute("settings", new Settings());
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
