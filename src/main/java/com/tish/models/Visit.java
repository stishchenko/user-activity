package com.tish.models;

import com.tish.consts.WebApp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Visit {
	private Integer id;
	private String user_id;
	private String device_id;
	private Integer location_id;
	private WebApp web_app;
	private String page;
	private Double visit_time;
	private Integer target_action;
	private LocalDate creation_date;
}
