package com.tish.controllers;

import com.tish.services.VisitTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/visit-time")
public class VisitTimeController {

	private final VisitTimeService visitTimeService;

	public VisitTimeController(@Autowired VisitTimeService visitTimeService) {
		this.visitTimeService = visitTimeService;
	}

	@GetMapping(path = {"", "/"})
	public String getVisitAimePage(Model model) {

		return "";
	}

	@PostMapping(path = {"/avg-time-period"})
	public void getAverageVisitTimeByPeriod(@RequestBody(required = false) String barType,
											@RequestBody String fromDate, @RequestBody String toDate) {
		Map<String, Double> map = visitTimeService.getAvgVisitTimeByPeriod(fromDate, toDate);
	}

	@PostMapping(path = {"/avg-page-period"})
	public void getAveragePagePeriod(@RequestBody(required = false) String barType,
									 @RequestBody String fromDate, @RequestBody String toDate) {
		Map<String, Double> avgMap = visitTimeService.getAvgVisitTimeByPage(fromDate, toDate);
	}

	@PostMapping(path = {"/cancellation"})
	public void getCancellationAmount(@RequestBody(required = false) String graphicType,
									  @RequestBody String dataType,
									  @RequestBody String fromDate, @RequestBody String toDate) {
		List<Map<String, Double>> mapList = visitTimeService.getCancellations(dataType, fromDate, toDate);
	}
}
