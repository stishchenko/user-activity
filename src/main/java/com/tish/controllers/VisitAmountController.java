package com.tish.controllers;

import com.tish.services.VisitAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@Controller
@RestController
@RequestMapping(path = "/visits")
public class VisitAmountController {

	private final VisitAmountService visitAmountService;

	public VisitAmountController(@Autowired VisitAmountService visitAmountService) {
		this.visitAmountService = visitAmountService;
	}

	@GetMapping(path = {"", "/"})
	public String getVisitAmountPage(Model model) {

		return "";
	}

	@PostMapping(path = {"/amount"})
	public List<Map<String, Double>> getTotalVisits(/*@RequestBody(required = false) String graphicType,
													@RequestBody String webApp,
													@RequestBody String dataType,
													@RequestBody String fromDate, @RequestBody String toDate*/
			@RequestBody Map<String, String> params) {
		List<Map<String, Double>> mapList = visitAmountService.getVisitAmount(params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return mapList;
	}
}
