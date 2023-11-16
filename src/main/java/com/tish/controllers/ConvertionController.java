package com.tish.controllers;

import com.tish.services.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping(path = "/conversion")
public class ConvertionController {

	private final ConversionService conversionService;

	public ConvertionController(@Autowired ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	@GetMapping(path = {"", "/"})
	public void getConvertionByPeriod(@RequestParam String fromDate, @RequestParam String toDate) {
		Map<String, Double> map = conversionService.getConversion(fromDate, toDate);
	}
}
