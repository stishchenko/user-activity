package com.tish.services;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DownloadService {

	public StringBuilder createBodyForUsersAndVisits(String objectType, String dataType, List<String> labels, HttpSession session) {
		switch (objectType) {
			case "user" -> {
				List<Double> userValues = session.getAttribute("userValues") != null ? (List<Double>) session.getAttribute("userValues") : null;
				List<Double> userPercents = session.getAttribute("userPercent") != null ? (List<Double>) session.getAttribute("userPercent") : null;
				return createBodyForUsers(dataType, labels, userValues, userPercents);
			}
			case "visit" -> {
				List<Double> visitValues = session.getAttribute("visitValues") != null ? (List<Double>) session.getAttribute("visitValues") : null;
				List<Double> visitPercents = session.getAttribute("visitPercent") != null ? (List<Double>) session.getAttribute("visitPercent") : null;
				return createBodyForVisits(dataType, labels, visitValues, visitPercents);
			}
			default -> {
				List<Double> userValues = session.getAttribute("userValues") != null ? (List<Double>) session.getAttribute("userValues") : null;
				List<Double> userPercents = session.getAttribute("userPercent") != null ? (List<Double>) session.getAttribute("userPercent") : null;
				List<Double> visitValues = session.getAttribute("visitValues") != null ? (List<Double>) session.getAttribute("visitValues") : null;
				List<Double> visitPercents = session.getAttribute("visitPercent") != null ? (List<Double>) session.getAttribute("visitPercent") : null;
				StringBuilder builder = createBodyForUsers(dataType, labels, userValues, userPercents);
				builder.append("\n\n\n");
				builder.append(createBodyForVisits(dataType, labels, visitValues, visitPercents));
				return builder;
			}
		}
	}

	private StringBuilder createBodyForUsers(String dataType, List<String> labels, List<Double> values, List<Double> percents) {
		return new StringBuilder("Users data\n").append(createBodyForData(dataType, labels, values, percents));
	}

	private StringBuilder createBodyForVisits(String dataType, List<String> labels, List<Double> values, List<Double> percents) {
		return new StringBuilder("Visits data\n").append(createBodyForData(dataType, labels, values, percents));
	}

	public StringBuilder createBodyForData(String dataType, List<String> labels, List<Double> values, List<Double> percents) {
		StringBuilder builder = new StringBuilder();
		switch (dataType) {
			case "value" -> {
				builder.append("labels;values\n");
				for (int i = 0; i < labels.size(); i++) {
					builder.append(labels.get(i)).append(";").append(values.get(i)).append("\n");
				}
			}
			case "percent" -> {
				builder.append("labels;percents\n");
				for (int i = 0; i < labels.size(); i++) {
					builder.append(labels.get(i)).append(";").append(percents.get(i)).append("\n");
				}
			}
			default -> {
				builder.append("labels;values;percents\n");
				for (int i = 0; i < labels.size(); i++) {
					builder.append(labels.get(i)).append(";").append(values.get(i)).append(";").append(percents.get(i)).append("\n");
				}
			}
		}

		return builder;
	}
}
