package com.tish.services;

import com.tish.daos.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAmountService {

	private final UserDao userDao;

	public UserAmountService(@Autowired UserDao userDao) {
		this.userDao = userDao;
	}
}
