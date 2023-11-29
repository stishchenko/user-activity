package com.tish.controllers;

import com.tish.services.VisitTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@Controller
@RestController
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
	public Map<String, Double> getAverageVisitTimeByPeriod(/*@RequestBody(required = false) String barType,
														   @RequestBody String webApp,
														   @RequestBody String fromDate, @RequestBody String toDate*/
			@RequestBody Map<String, String> params) {
		Map<String, Double> map = visitTimeService.getAvgVisitTimeByPeriod(params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return map;
	}

	@PostMapping(path = {"/avg-page-period"})
	public Map<String, Double> getAveragePagePeriod(/*@RequestBody(required = false) String barType,
													@RequestBody String webApp,
													@RequestBody String fromDate, @RequestBody String toDate*/
			@RequestBody Map<String, String> params) {
		Map<String, Double> avgMap = visitTimeService.getAvgVisitTimeByPage(params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return avgMap;
	}

	@PostMapping(path = {"/cancellation"})
	public List<Map<String, Double>> getCancellationAmount(/*@RequestBody(required = false) String graphicType,
														   @RequestBody String dataType,
														   @RequestBody String webApp,
														   @RequestBody String fromDate, @RequestBody String toDate*/
			@RequestBody Map<String, String> params) {
		List<Map<String, Double>> mapList = visitTimeService.getCancellations(params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return mapList;
	}
}
