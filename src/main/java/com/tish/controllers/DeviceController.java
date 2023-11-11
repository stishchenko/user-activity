package com.tish.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/devices")
public class DeviceController {

	@GetMapping(path = "/type")
	public void getTypes() {
		Integer totalDevices = 0;
		Integer pc = 0;
		Integer tablet = 0;
		Integer mobile = 0;

		Double pcPercent = pc / totalDevices * 100.0;
		Double tabletPercent = tablet / totalDevices * 100.0;
		Double mobilePercent = mobile / totalDevices * 100.0;

		//send it back
		Map<String, Number> deviceMap = new HashMap<>();
		deviceMap.put("pcPercent", pcPercent);
		deviceMap.put("tabletPercent", tabletPercent);
		deviceMap.put("mobilePercent", mobilePercent);
	}

	@GetMapping(path = "/os")
	public void getOS() {
		Integer totalDevices = 0;
		Integer type1 = 0;
		Integer type2 = 0;

		Double pcPercent = type1 / totalDevices * 100.0;
		Double tabletPercent = type2 / totalDevices * 100.0;

		//send it back
		Map<String, Number> osMap = new HashMap<>();
		osMap.put("type1Percent", pcPercent);
		osMap.put("type2Percent", tabletPercent);
	}

	@GetMapping(path = "/browser")
	public void getBrowsers() {
		Integer totalDevices = 0;
		Integer browser1 = 0;
		Integer browser2 = 0;

		Double pcPercent = browser1 / totalDevices * 100.0;
		Double tabletPercent = browser2 / totalDevices * 100.0;

		//send it back
		Map<String, Number> browserMap = new HashMap<>();
		browserMap.put("browser1Percent", pcPercent);
		browserMap.put("browser2Percent", tabletPercent);
	}
}
