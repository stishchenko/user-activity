package com.tish.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/welcome"})
public class WelcomeController {

	@GetMapping(path = {"", "/", "/index"})
	public String index(Model model) {
		return "index";
	}
}
