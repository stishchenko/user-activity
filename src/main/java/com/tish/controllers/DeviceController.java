package com.tish.controllers;

import com.tish.models.Settings;
import com.tish.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
//@RestController
@RequestMapping(path = "/devices")
public class DeviceController {

	private final DeviceService deviceService;

	public DeviceController(@Autowired DeviceService deviceService) {
		this.deviceService = deviceService;
	}


	@GetMapping(path = {"/type"})
	public String getDeviceTypePage(Model model) {

		addDataToModel(model);

		return "device-type-statistics";
	}

	@GetMapping(path = {"/os"})
	public String getDeviceOSPage(Model model) {

		addDataToModel(model);

		return "device-os-statistics";
	}

	@GetMapping(path = {"/browser"})
	public String getDeviceBrowserPage(Model model) {

		addDataToModel(model);

		return "device-browser-statistics";
	}

	@PostMapping(path = {"/type"})
	public String getTypes(/*@RequestBody(required = false) String graphicType,
											  @RequestBody String dataType,
											  @RequestBody String webApp,
											  @RequestBody String fromDate, @RequestBody String toDate*/
			/*@RequestBody Map<String, String> params,*/ @ModelAttribute("settings") Settings settings, Model model) {
		//List<Map<String, Double>> deviceTypeMapList = deviceService.getDevicesByType(params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		if (!settings.getChartType().contains(" ")) {
			model.addAttribute("type", settings.getChartType());
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		String dataType = "value+percent";
		if (settings.getDataTypes().contains(null)) {
			dataType = settings.getDataTypes().get(0) != null ? "value" : "percent";
		}

		Map<String, List> map = deviceService.getDevicesByType(dataType, settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType.contains("+") ? settings.getDataTypes() : dataType);

		return "device-type-statistics";
	}

	@PostMapping(path = {"/os"})
	public String getOS(/*@RequestBody(required = false) String graphicType,
										   @RequestBody String dataType,
										   @RequestBody String webApp,
										   @RequestBody String fromDate, @RequestBody String toDate*/
			/*@RequestBody Map<String, String> params,*/@ModelAttribute("settings") Settings settings, Model model) {
		/*List<Map<String, Double>> deviceOSMapList = deviceService.getDevicesByOS(params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return deviceOSMapList;*/

		if (!settings.getChartType().contains(" ")) {
			model.addAttribute("type", settings.getChartType());
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		String dataType = "value+percent";
		if (settings.getDataTypes().contains(null)) {
			dataType = settings.getDataTypes().get(0) != null ? "value" : "percent";
		}

		Map<String, List> map = deviceService.getDevicesByOS(dataType, settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType.contains("+") ? settings.getDataTypes() : dataType);

		return "device-os-statistics";
	}

	@PostMapping(path = {"/browser"})
	public String getBrowsers(/*@RequestBody(required = false) String graphicType,
												 @RequestBody String dataType,
												 @RequestBody String webApp,
												 @RequestBody String fromDate, @RequestBody String toDate*/
			/*@RequestBody Map<String, String> params,*/@ModelAttribute("settings") Settings settings, Model model) {
		/*List<Map<String, Double>> deviceBrowserMapList = deviceService.getDevicesByBrowser(params.get("dataType"), params.get("fromDate"), params.get("toDate"), params.get("webApp"));
		return deviceBrowserMapList;*/

		if (!settings.getChartType().contains(" ")) {
			model.addAttribute("type", settings.getChartType());
		} else {
			String[] charts = settings.getChartType().split(" ");
			model.addAttribute("axis", charts[0]);
			model.addAttribute("type", charts[1]);
		}
		String dataType = "value+percent";
		if (settings.getDataTypes().contains(null)) {
			dataType = settings.getDataTypes().get(0) != null ? "value" : "percent";
		}

		Map<String, List> map = deviceService.getDevicesByBrowser(dataType, settings.getStartDate(), settings.getEndDate(), settings.getWebApp());
		model.addAttribute("labels", map.get("labels"));
		model.addAttribute("values", map.get("values"));
		model.addAttribute("percent", map.get("percent"));
		model.addAttribute("dataType", dataType.contains("+") ? settings.getDataTypes() : dataType);

		return "device-browser-statistics";
	}

	private void addDataToModel(Model model) {
		model.addAttribute("type", "bar");
		model.addAttribute("axis", "x");
		model.addAttribute("values", Arrays.asList(10, 15, 12, 17, 30, 22));
		model.addAttribute("percent", Arrays.asList(10, 15, 12, 17, 30, 22));
		model.addAttribute("labels", Arrays.asList("1", "2", "3", "4", "5", "6"));
		model.addAttribute("settings", new Settings());
		model.addAttribute("dataType", Arrays.asList("value", "percent"));
	}
}
