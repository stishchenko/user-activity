package com.tish.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Settings {
	private String chartType;
	private String periodType;
	private List<String> dataTypes;
	private List<String> statisticObjectType;
	private String dates;
	private String webApp;
}
