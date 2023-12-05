package com.tish.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Settings {
	private String chartType;
	private String periodType = "month";
	private List<String> dataTypes = Arrays.asList("value", "percent");
	private List<String> statisticObjectType = Arrays.asList("user", "visit");;
	private String startDate = "2022-01-01";
	private String endDate = "2023-12-31";
	private String webApp;
}
