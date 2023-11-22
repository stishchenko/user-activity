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
public class Device {
	private String device_id;
	private String type;
	private String os_platform;
	private String browser;
	private WebApp web_app;
	private LocalDate creation_date;
}
