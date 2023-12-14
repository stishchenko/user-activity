package com.tish.controllers;

import com.tish.models.Account;
import com.tish.models.Settings;
import com.tish.services.AccountService;
import com.tish.services.ConversionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = {"/conversion"})
public class ConvertionController {

	private final ConversionService conversionService;
	private final AccountService accountService;

	public ConvertionController(@Autowired ConversionService conversionService, @Autowired AccountService accountService) {
		this.conversionService = conversionService;
		this.accountService = accountService;
	}

	@GetMapping(path = {""})
	public String getConvertionPage(HttpSession session, Model model) {

		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		model.addAttribute("type", "bar");
		model.addAttribute("axis", "x");
		Map<String, List> map = conversionService.getConversion("year", null, null, "app1");
		model.addAttribute("values", map.get("values"));
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("settings", new Settings());

		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("metric", "conversion");
		session.setAttribute("dataType", "value");
		session.setAttribute("app", "app1");

		return "conversion-statistics";
	}

	@PostMapping(path = {""})
	public String getConvertionByPeriod(@ModelAttribute("settings") Settings settings, HttpSession session, Model model) {

		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}

		Map<String, List> map = conversionService.getConversion(settings.getPeriodType(), settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		if (settings.getChartType().equals("line")) {
			model.addAttribute("type", "line");
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));

		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
		session.setAttribute("metric", "conversion");
		session.setAttribute("dataType", "value");
		session.setAttribute("app", settings.getWebApp());

		return "conversion-statistics";
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
