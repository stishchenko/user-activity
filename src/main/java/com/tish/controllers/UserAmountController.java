package com.tish.controllers;

import com.tish.services.UserAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/users")
public class UserAmountController {

	private final UserAmountService userAmountService;

	public UserAmountController(@Autowired UserAmountService userAmountService) {
		this.userAmountService = userAmountService;
	}

	@GetMapping(path = {"", "/"})
	public String getUsersPage(Model model) {

		return "";
	}

	@PostMapping(path = {"/ratio"})
	public List<Map<String, Double>> getUsersAmount(@RequestBody(required = false) String graphicType,
													@RequestBody String dataType,
													@RequestBody String fromDate, @RequestBody String toDate) {
		List<Map<String, Double>> mapList = userAmountService.getUsersAmountAsSingleAndRepeat(dataType, fromDate, toDate);
		return mapList;
	}

	@PostMapping(path = {"/user-page-visit"})
	public List<Map<String, Double>> getUserAmountByPageVisits(@RequestBody(required = false) String graphicType,
															   @RequestBody String dataType,
															   @RequestBody String fromDate, @RequestBody String toDate) {
		List<Map<String, Double>> mapList = userAmountService.getUsersAmountByVisitTimes(dataType, fromDate, toDate);
		return mapList;
	}
}
