package com.tish.controllers;

import com.tish.services.UserAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping(path = {"/ratio"})
	public void getUsersAmount() {
		List<Map<String, Double>> mapList = userAmountService.getUsersAmountAsSingleAndRepeat();
	}

	@GetMapping(path = {"/user-page-visit"})
	public void getUserAmountByPageVisits() {
		List<Map<String, Double>> mapList = userAmountService.getUsersAmountByVisitTimes();
	}
}
