package com.tish.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tish.models.Settings;
import com.tish.services.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
//@RestController
@RequestMapping(path = {"/conversion"})
public class ConvertionController {

	private final ConversionService conversionService;

	public ConvertionController(@Autowired ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	@GetMapping(path = {""})
	public String getConvertionPage(Model model) {

		model.addAttribute("type", "bar");
		model.addAttribute("axis", "x");
		model.addAttribute("values", Arrays.asList(10, 15, 12, 17, 30, 22));
		model.addAttribute("labels", Arrays.asList("1", "2", "3", "4", "5", "6"));
		model.addAttribute("settings", new Settings());

		return "conversion-statistics";
	}

	@PostMapping(path = {""})
	public String getConvertionByPeriod(/*@RequestBody(required = false) String chartType, @RequestBody String periodType,
													 @RequestBody String webApp,
													 @RequestBody(required = false) String fromDate,
													 @RequestBody(required = false) String toDate*/

			/*@RequestBody Map<String, String> params,*/ @ModelAttribute("settings") Settings settings, Model model) {
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
		return "conversion-statistics";
	}
}
