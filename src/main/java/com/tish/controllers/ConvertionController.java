package com.tish.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/conversion")
public class ConvertionController {

	@GetMapping(path = {"/"})
	public Double getConvertion() {
		Double convertion = 0.0;
		return convertion;
	}
}
