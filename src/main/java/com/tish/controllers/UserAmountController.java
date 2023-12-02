package com.tish.controllers;

import com.tish.services.UserAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@Controller
@RestController
@RequestMapping(path = "/users")
public class UserAmountController {

	private final UserAmountService userAmountService;

	public UserAmountController(@Autowired UserAmountService userAmountService) {
		this.userAmountService = userAmountService;
	}

	@GetMapping(path = { "/ratio"})
	public String getUsersRatioPage(Model model) {

		return "user-ratio-statistics";
	}

	@GetMapping(path = { "/user-page-visit"})
	public String getUsersPageVisitPage(Model model) {

		return "user-page-statistics";
	}

	@PostMapping(path = {"/ratio"})
	public List<Map<String, Double>> getUsersAmount(/*@RequestBody(required = false) String graphicType,
													@RequestBody String dataType,
													@RequestBody String webApp,
													@RequestBody String fromDate, @RequestBody String toDate*/
			@RequestBody Map<String, String> params/*, @ModelAttribute("settings") Settings settings, Model model*/) {
		List<Map<String, Double>> mapList = userAmountService.getUsersAmountAsSingleAndRepeat(params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return mapList;
	}

	@PostMapping(path = {"/user-page-visit"})
	public List<Map<String, Double>> getUserAmountByPageVisits(/*@RequestBody(required = false) String graphicType,
															   @RequestBody String dataType,
															   @RequestBody String webApp,
															   @RequestBody String fromDate, @RequestBody String toDate*/
			@RequestBody Map<String, String> params/*, @ModelAttribute("settings") Settings settings, Model model*/) {
		List<Map<String, Double>> mapList = userAmountService.getUsersAmountByVisitTimes(params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return mapList;
	}
}
