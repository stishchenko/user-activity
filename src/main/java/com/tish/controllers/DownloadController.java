package com.tish.controllers;

import com.tish.services.DownloadService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = {"/download"})
public class DownloadController {

	private final DownloadService downloadService;

	public DownloadController(@Autowired DownloadService downloadService) {
		this.downloadService = downloadService;
	}

	@GetMapping({"", "/"})
	public ResponseEntity<String> download(HttpSession session) {

		StringBuilder body;
		String dataType = (String) session.getAttribute("dataType");
		List<String> labels = (List<String>) session.getAttribute("labels");
		List<Double> values = null;
		List<Double> percents = null;
		switch (dataType) {
			case "value" -> {
				values = (List<Double>) session.getAttribute("values");
			}
			case "percent" -> {
				percents = (List<Double>) session.getAttribute("percent");
			}
			default -> {
				values = (List<Double>) session.getAttribute("values");
				percents = (List<Double>) session.getAttribute("percent");
			}
		}
		if (session.getAttribute("objectType") != null) {
			String objectType = (String) session.getAttribute("objectType");

			body = downloadService.createBodyForUsersAndVisits(objectType, dataType, labels, session);
		} else {
			body = downloadService.createBodyForData(dataType, labels, values, percents);
		}

		StringBuilder header = new StringBuilder("attachment; filename=")
				.append((String) session.getAttribute("metric"))
				.append(" for ").append((String) session.getAttribute("app")).append(".csv");

		return ResponseEntity
				.ok()
				.header("Content-Disposition", header.toString())
				.body(body.toString());
	}


}
