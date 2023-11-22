package com.tish.controllers;

import com.tish.services.VisitTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/visit-time")
public class VisitTimeController {

	private final VisitTimeService visitTimeService;

	public VisitTimeController(@Autowired VisitTimeService visitTimeService) {
		this.visitTimeService = visitTimeService;
	}

	@GetMapping(path = {"/avg-time-period"})
	public void getAverageVisitTimeByPeriod(@RequestParam String fromDate, @RequestParam String toDate) {
		Map<String, Double> map = visitTimeService.getAvgVisitTimeByPeriod(fromDate, toDate);
	}

	@GetMapping(path = {"/avg-page-period"})
	public void getAveragePagePeriod(@RequestParam String fromDate, @RequestParam String toDate) {
		Map<String, Double> avgMap = visitTimeService.getAvgVisitTimeByPage(fromDate, toDate);
	}

	@GetMapping(path = {"/cancellation"})
	public void getCancellationAmount() {
		List<Map<String, Double>> mapList = visitTimeService.getCancellations();
	}
}
