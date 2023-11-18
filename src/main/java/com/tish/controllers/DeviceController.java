package com.tish.controllers;

import com.tish.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/devices")
public class DeviceController {

	private final DeviceService deviceService;

	public DeviceController(@Autowired DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@GetMapping(path = "/type")
	public void getTypes() {
		List<Map<String, Double>> deviceTypeMapList = deviceService.getDevicesByType();
	}

	@GetMapping(path = "/os")
	public void getOS() {
		List<Map<String, Double>> deviceOSMapList = deviceService.getDevicesByOS();
	}

	@GetMapping(path = "/browser")
	public void getBrowsers() {
		List<Map<String, Double>> deviceOSMapList = deviceService.getDevicesByBrowser();
	}
}
