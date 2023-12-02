package com.tish.controllers;

import com.tish.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@Controller
@RestController
@RequestMapping(path = "/devices")
public class DeviceController {

	private final DeviceService deviceService;

	public DeviceController(@Autowired DeviceService deviceService) {
		this.deviceService = deviceService;
	}


	@GetMapping(path = {"/type"})
	public String getDeviceTypePage(Model model) {

		return "device-type-statistics";
	}

	@GetMapping(path = {"/os"})
	public String getDeviceOSPage(Model model) {

		return "device-os-statistics";
	}

	@GetMapping(path = {"/browser"})
	public String getDeviceBrowserPage(Model model) {

		return "device-browser-statistics";
	}

	@PostMapping(path = {"/type"})
	public List<Map<String, Double>> getTypes(/*@RequestBody(required = false) String graphicType,
											  @RequestBody String dataType,
											  @RequestBody String webApp,
											  @RequestBody String fromDate, @RequestBody String toDate*/
			@RequestBody Map<String, String> params/*, @ModelAttribute("settings") Settings settings, Model model*/) {
		List<Map<String, Double>> deviceTypeMapList = deviceService.getDevicesByType(params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return deviceTypeMapList;
	}

	@PostMapping(path = {"/os"})
	public List<Map<String, Double>> getOS(/*@RequestBody(required = false) String graphicType,
										   @RequestBody String dataType,
										   @RequestBody String webApp,
										   @RequestBody String fromDate, @RequestBody String toDate*/
			@RequestBody Map<String, String> params/*, @ModelAttribute("settings") Settings settings, Model model*/) {
		List<Map<String, Double>> deviceOSMapList = deviceService.getDevicesByOS(params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return deviceOSMapList;
	}

	@PostMapping(path = {"/browser"})
	public List<Map<String, Double>> getBrowsers(/*@RequestBody(required = false) String graphicType,
												 @RequestBody String dataType,
												 @RequestBody String webApp,
												 @RequestBody String fromDate, @RequestBody String toDate*/
			@RequestBody Map<String, String> params/*, @ModelAttribute("settings") Settings settings, Model model*/) {
		List<Map<String, Double>> deviceBrowserMapList = deviceService.getDevicesByBrowser(params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return deviceBrowserMapList;
	}
}
