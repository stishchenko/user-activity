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

	@GetMapping(path = {"/avg-time-period"})
	public String getAvgVisitTimePage(Model model) {

		return "visit-avg-time-statistics";
	}

	@GetMapping(path = {"/avg-page-period"})
	public String getAvgPageStatisticsPage(Model model) {

		return "visit-avg-page-statistics";
	}

	@GetMapping(path = {"/cancellation"})
	public String getCancellationPage(Model model) {

		return "visit-cancellation-statistics";
	}

	@PostMapping(path = {"/avg-time-period"})
	public Map<String, Double> getAverageVisitTimeByPeriod(/*@RequestBody(required = false) String chartType,
														   @RequestBody String webApp,
														   @RequestBody String fromDate, @RequestBody String toDate*/
			@RequestBody Map<String, String> params/*, @ModelAttribute("settings") Settings settings, Model model*/) {
		Map<String, Double> map = visitTimeService.getAvgVisitTimeByPeriod(params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return map;
	}

	@PostMapping(path = {"/avg-page-period"})
	public Map<String, Double> getAveragePagePeriod(/*@RequestBody(required = false) String chartType,
													@RequestBody String webApp,
													@RequestBody String fromDate, @RequestBody String toDate*/
			@RequestBody Map<String, String> params/*, @ModelAttribute("settings") Settings settings, Model model*/) {
		Map<String, Double> avgMap = visitTimeService.getAvgVisitTimeByPage(params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return avgMap;
	}

	@PostMapping(path = {"/cancellation"})
	public List<Map<String, Double>> getCancellationAmount(/*@RequestBody(required = false) String graphicType,
														   @RequestBody String dataType,
														   @RequestBody String webApp,
														   @RequestBody String fromDate, @RequestBody String toDate*/
			@RequestBody Map<String, String> params/*, @ModelAttribute("settings") Settings settings, Model model*/) {
		List<Map<String, Double>> mapList = visitTimeService.getCancellations(params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return mapList;
	}
}
