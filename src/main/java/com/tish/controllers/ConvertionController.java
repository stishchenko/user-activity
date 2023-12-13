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
	public String getConvertionPage(Model model) {

		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}
		model.addAttribute("type", "bar");
		model.addAttribute("axis", "x");
		model.addAttribute("values", Arrays.asList(10, 15, 12, 17, 30, 22));
		model.addAttribute("labels", Arrays.asList("1", "2", "3", "4", "5", "6"));
		model.addAttribute("settings", new Settings());


		return "conversion-statistics";
	}

	@GetMapping("/download")
	public ResponseEntity<String> download(HttpSession session) {

		StringBuilder builder = new StringBuilder("labels;values\n");
		List<String> labels = (List<String>) session.getAttribute("labels");
		List<Double> values = (List<Double>) session.getAttribute("values");

		for (int i = 0; i < labels.size(); i++) {
			builder.append(labels.get(i)).append(";").append(values.get(i)).append("\n");
		}

		return ResponseEntity
				.ok()
				.header("Content-Disposition", "attachment; filename=conversion.csv")
				.body(builder.toString());
	}

	@PostMapping(path = {""})
	public String getConvertionByPeriod(@ModelAttribute("settings") Settings settings, HttpSession session, Model model) {

		if (!checkLoggedAccount(model)) {
			return "logged-error";
		}

		Map<String, List> map = conversionService.getConversion(settings.getPeriodType(), settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		if (settings.getChartType().equals("line")) {
			model.addAttribute("type", "line");
			session.setAttribute("type", "line");
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);

			session.setAttribute("axis", charts[0]);
			session.setAttribute("type", charts[1]);
		}
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));

		session.setAttribute("labels", map.get("labels"));
		session.setAttribute("values", map.get("values"));
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
