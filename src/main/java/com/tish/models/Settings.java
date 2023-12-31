package com.tish.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Settings {
	private String chartType;
	private String periodType = "year";
	private String chkTypeValues="on";
	private String chkTypePercents="on";
	private String chkObjectTypeUsers="on";
	private String chkObjectTypeVisits="on";
	private List<String> dataTypes = new ArrayList<>();
	private List<String> statisticObjectType = new ArrayList<>();
	private String startDate = "2022-01-01";
	private String endDate = "2023-12-31";
	private String webApp;
}
