package com.tish.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Settings {
	private String chartType;
	private String periodType = "month";
	private List<String> dataTypes = new ArrayList<>();
	private List<String> statisticObjectType = new ArrayList<>();
	private String startDate = "2022-01-01";
	private String endDate = "2023-12-31";
	private String webApp;
}
