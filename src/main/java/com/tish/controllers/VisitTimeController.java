package com.tish.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/visit-time")
public class VisitTimeController {

	@GetMapping(path = {"/avg-site-period"})
	public void getAverageSitePeriod() {
		Double averagePeriod = 0.0;
	}

	@GetMapping(path = {"/avg-page-period"})
	public void getAveragePagePeriod() {
		Double averagePeriod = 0.0;

		//send it back
		Map<String, Double> avgMap = new HashMap<>();
		avgMap.put("page1", averagePeriod);
		avgMap.put("page2", averagePeriod);
		avgMap.put("page3", averagePeriod);
	}

	@GetMapping(path = {"/cancellation"})
	public void getCancellationAmount() {
		Integer cancellations = 0;
	}
}
