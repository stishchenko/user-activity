package com.tish.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/visits")
public class VisitAmountController {

	@GetMapping(path = {"/amount"})
	public void getTotalVisits() {
		Integer totalAmount = 0;
		Integer repeatAmount = 0;
		Integer uniqueAmount = totalAmount - repeatAmount;

		//send back to page
		Map<String, Integer> amountMap = new HashMap<>();
		amountMap.put("totalAmount", totalAmount);
		amountMap.put("repeatAmount", repeatAmount);
		amountMap.put("uniqueAmount", uniqueAmount);
	}

	@GetMapping(path = {"/amount-percent"})
	public void getVisitPercents() {
		Integer totalAmount = 0;
		Integer repeatAmount = 0;
		Integer uniqueAmount = totalAmount - repeatAmount;

		Double repeatPercent = repeatAmount / totalAmount * 100.0;
		Double uniquePercent = uniqueAmount / totalAmount * 100.0;

		//send back to page
		Map<String, Double> percentMap = new HashMap<>();
		percentMap.put("repeatPercent", repeatPercent);
		percentMap.put("uniquePercent", uniquePercent);
	}
}
