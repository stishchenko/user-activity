package com.tish.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/users")
public class UserAmountController {

	@GetMapping(path = {"/ratio"})
	public void getUsersAmount() {
		Integer totalUsers = 0;
		Integer repeatUsers = 0;
		Integer singleUsers = totalUsers - repeatUsers;

		Double usersRation = repeatUsers / singleUsers * 1.0;

		//send back to page

		Map<String, Number> userMap = new HashMap<>();
		userMap.put("repeatUsers", repeatUsers);
		userMap.put("singleUsers", singleUsers);
		userMap.put("usersRation", usersRation);
	}

	@GetMapping(path = {"/user-page-visit"})
	public void getUserAmountByPageVisits() {
		Integer firstPageUsers = 0;
		Integer severalPageUsers = 0;

		Double usersRation = firstPageUsers / severalPageUsers * 1.0;

		//send back to page

		Map<String, Number> userMap = new HashMap<>();
		userMap.put("firstPageUsers", firstPageUsers);
		userMap.put("severalPageUsers", severalPageUsers);
		userMap.put("usersRation", usersRation);
	}

	@GetMapping(path = {"/user-page-visit-percent"})
	public void getUserAmountByPageVisitsPercent() {
		Integer totalUsers = 0;
		Integer firstPageUsers = 0;
		Integer severalPageUsers = 0;

		Double firstPagePercent = firstPageUsers / totalUsers * 100.0;
		Double severalPagePercent = severalPageUsers / totalUsers * 100.0;

		//send back to page

		Map<String, Number> userMap = new HashMap<>();
		userMap.put("firstPagePercent", firstPagePercent);
		userMap.put("severalPagePercent", severalPagePercent);
	}
}
