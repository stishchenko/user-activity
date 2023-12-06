package com.tish.controllers;

import com.tish.models.Settings;
import com.tish.services.VisitTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
//@RestController
@RequestMapping(path = "/visit-time")
public class VisitTimeController {

	private final VisitTimeService visitTimeService;

	public VisitTimeController(@Autowired VisitTimeService visitTimeService) {
		this.visitTimeService = visitTimeService;
	}

	@GetMapping(path = {"/avg-time-period"})
	public String getAvgVisitTimePage(Model model) {
		addDataToModel(model);

		return "visit-avg-time-statistics";
	}

	@GetMapping(path = {"/avg-page-period"})
	public String getAvgPageStatisticsPage(Model model) {
		addDataToModel(model);

		return "visit-avg-page-statistics";
	}

	@GetMapping(path = {"/cancellation"})
	public String getCancellationPage(Model model) {
		model.addAttribute("type", "bar");
		model.addAttribute("axis", "x");
		model.addAttribute("values", Arrays.asList(10, 15, 12, 17, 30, 22));
		model.addAttribute("percent", Arrays.asList(10, 15, 12, 17, 30, 22));
		model.addAttribute("labels", Arrays.asList("1", "2", "3", "4", "5", "6"));
		model.addAttribute("settings", new Settings());
		model.addAttribute("dataType", Arrays.asList("value", "percent"));
		return "visit-cancellation-statistics";
	}

	@PostMapping(path = {"/avg-time-period"})
	public String getAverageVisitTimeByPeriod(/*@RequestBody(required = false) String chartType,
														   @RequestBody String webApp,
														   @RequestBody String fromDate, @RequestBody String toDate*/
			/*@RequestBody Map<String, String> params,*/@ModelAttribute("settings") Settings settings, Model model) {
		/*Map<String, Double> map = visitTimeService.getAvgVisitTimeByPeriod(params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return map;*/
		Map<String, List> map = visitTimeService.getAvgVisitTimeByPeriod(settings.getPeriodType(), settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		if (settings.getChartType().equals("line")) {
			model.addAttribute("type", "line");
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));

		return "visit-avg-time-statistics";
	}

	@PostMapping(path = {"/avg-page-period"})
	public String getAveragePagePeriod(/*@RequestBody(required = false) String chartType,
													@RequestBody String webApp,
													@RequestBody String fromDate, @RequestBody String toDate*/
			/*@RequestBody Map<String, String> params,*/@ModelAttribute("settings") Settings settings, Model model) {
		/*Map<String, Double> avgMap = visitTimeService.getAvgVisitTimeByPage(params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return avgMap;*/
		Map<String, List> map = visitTimeService.getAvgVisitTimeByPage(settings.getStartDate(), settings.getEndDate(), settings.getWebApp());

		if (!settings.getChartType().contains(" ")) {
			model.addAttribute("type", settings.getChartType());
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));

		return "visit-avg-page-statistics";
	}

	@PostMapping(path = {"/cancellation"})
	public String getCancellationAmount(/*@RequestBody(required = false) String graphicType,
														   @RequestBody String dataType,
														   @RequestBody String webApp,
														   @RequestBody String fromDate, @RequestBody String toDate*/
			/*@RequestBody Map<String, String> params,*/@ModelAttribute("settings") Settings settings, Model model) {
		/*List<Map<String, Double>> mapList = visitTimeService.getCancellations(params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return mapList;*/

		if (!settings.getChartType().contains(" ")) {
			model.addAttribute("type", settings.getChartType());
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		String dataType = "value+percent";
		if (settings.getDataTypes().contains(null)) {
			dataType = settings.getDataTypes().get(0) != null ? "value" : "percent";
		}

		Map<String, List> map = visitTimeService.getCancellations(dataType, settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType.contains("+") ? settings.getDataTypes() : dataType);

		return "visit-cancellation-statistics";
	}

	private void addDataToModel(Model model) {
		model.addAttribute("type", "bar");
		model.addAttribute("axis", "x");
		model.addAttribute("values", Arrays.asList(10, 15, 12, 17, 30, 22));
		model.addAttribute("labels", Arrays.asList("1", "2", "3", "4", "5", "6"));
		model.addAttribute("settings", new Settings());
	}
}
