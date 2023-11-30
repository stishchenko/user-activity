package com.tish.controllers;

import com.tish.services.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@Controller
@RestController
@RequestMapping(path = {"/conversion"})
public class ConvertionController {

	private final ConversionService conversionService;

	public ConvertionController(@Autowired ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	@GetMapping(path = {"/hello"})
	public String getHello() {


		return "Hello";
	}

	@GetMapping(path = {"", "/"})
	public String getConvertionPage(Model model) {


		return "";
	}

	@PostMapping(path = {"/"})
	public Map<String, Double> getConvertionByPeriod(/*@RequestBody(required = false) String barType, @RequestBody String periodType,
													 @RequestBody String webApp,
													 @RequestBody(required = false) String fromDate,
													 @RequestBody(required = false) String toDate*/

			@RequestBody Map<String, String> params) {
		Map<String, Double> map = conversionService.getConversion(params.get("periodType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return map;
	}
}
