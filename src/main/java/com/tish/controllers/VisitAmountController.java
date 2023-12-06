package com.tish.controllers;

import com.tish.models.Settings;
import com.tish.services.VisitAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
//@RestController
@RequestMapping(path = "/visits")
public class VisitAmountController {

	private final VisitAmountService visitAmountService;

	public VisitAmountController(@Autowired VisitAmountService visitAmountService) {
		this.visitAmountService = visitAmountService;
	}

	@GetMapping(path = {"/amount"})
	public String getVisitAmountPage(Model model) {
		model.addAttribute("type", "bar");
		model.addAttribute("axis", "x");
		model.addAttribute("values", Arrays.asList(10, 15, 12, 17, 30, 22));
		model.addAttribute("percent", Arrays.asList(10, 15, 12, 17, 30, 22));
		model.addAttribute("labels", Arrays.asList("1", "2", "3", "4", "5", "6"));
		model.addAttribute("settings", new Settings());
		model.addAttribute("dataType", Arrays.asList("value", "percent"));
		return "visit-amount-statistics";
	}

	@PostMapping(path = {"/amount"})
	public String getTotalVisits(/*@RequestBody(required = false) String graphicType,
													@RequestBody String webApp,
													@RequestBody String dataType,
													@RequestBody String fromDate, @RequestBody String toDate*/
			/*@RequestBody Map<String, String> params,*/@ModelAttribute("settings") Settings settings, Model model) {
		/*List<Map<String, Double>> mapList = visitAmountService.getVisitAmount(params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return mapList;*/

		if (settings.getChartType().equals("pie")) {
			model.addAttribute("type", "pie");
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		String dataType = "value+percent";
		if (settings.getDataTypes().contains(null)) {
			dataType = settings.getDataTypes().get(0) != null ? "value" : "percent";
		}

		Map<String, List> map = visitAmountService.getVisitAmount(dataType, settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType.contains("+") ? settings.getDataTypes() : dataType);

		return "visit-amount-statistics";
	}
}
