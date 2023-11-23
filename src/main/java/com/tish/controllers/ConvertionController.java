package com.tish.controllers;

import com.tish.services.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(path = "/conversion")
public class ConvertionController {

	private final ConversionService conversionService;

	public ConvertionController(@Autowired ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	@GetMapping(path = {"", "/"})
	public String getConvertionPage(Model model) {


		return "";
	}

	@PostMapping(path = {"", "/"})
	public Map<String, Double> getConvertionByPeriod(@RequestBody(required = false) String barType, @RequestBody String periodType,
													 @RequestBody String webApp,
													 @RequestBody(required = false) String fromDate,
													 @RequestBody(required = false) String toDate) {
		Map<String, Double> map = conversionService.getConversion(periodType, fromDate, toDate, webApp);
		return map;
	}
}
