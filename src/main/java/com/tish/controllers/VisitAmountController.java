package com.tish.controllers;

import com.tish.services.VisitAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/visits")
public class VisitAmountController {

	private final VisitAmountService visitAmountService;

	public VisitAmountController(@Autowired VisitAmountService visitAmountService) {
		this.visitAmountService = visitAmountService;
	}

	@GetMapping(path = {"/amount"})
	public void getTotalVisits() {
		List<Map<String, Double>> mapList = visitAmountService.getVisitAmount();
	}
}
