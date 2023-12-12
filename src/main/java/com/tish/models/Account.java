package com.tish.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

	private Integer id;
	private String login;
	private String password;
	private String apps;
	private String userName;
	private Boolean isLoggedIn;
}
