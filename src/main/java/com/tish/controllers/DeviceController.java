package com.tish.controllers;

import com.tish.services.DeviceService;
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
@RequestMapping(path = "/devices")
public class DeviceController {

	private final DeviceService deviceService;

	public DeviceController(@Autowired DeviceService deviceService) {
		this.deviceService = deviceService;
	}


	@GetMapping(path = {"", "/"})
	public String getDevicePage(Model model) {

		return "";
	}

	@PostMapping(path = {"/type"})
	public List<Map<String, Double>> getTypes(@RequestBody(required = false) String graphicType,
											  @RequestBody String dataType,
											  @RequestBody String fromDate, @RequestBody String toDate) {
		List<Map<String, Double>> deviceTypeMapList = deviceService.getDevicesByType(dataType, fromDate, toDate);
		return deviceTypeMapList;
	}

	@PostMapping(path = {"/os"})
	public List<Map<String, Double>> getOS(@RequestBody(required = false) String graphicType,
										   @RequestBody String dataType,
										   @RequestBody String fromDate, @RequestBody String toDate) {
		List<Map<String, Double>> deviceOSMapList = deviceService.getDevicesByOS(dataType, fromDate, toDate);
		return deviceOSMapList;
	}

	@PostMapping(path = {"/browser"})
	public List<Map<String, Double>> getBrowsers(@RequestBody(required = false) String graphicType,
												 @RequestBody String dataType,
												 @RequestBody String fromDate, @RequestBody String toDate) {
		List<Map<String, Double>> deviceBrowserMapList = deviceService.getDevicesByBrowser(dataType, fromDate, toDate);
		return deviceBrowserMapList;
	}
}
