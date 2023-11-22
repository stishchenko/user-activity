package com.tish.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
	private Integer id;
	private String country;
	private String city;
	private LocalDate creation_date;
}
