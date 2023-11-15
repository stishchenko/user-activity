package com.tish.daos;

import com.tish.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

	private final UserMapper userMapper;

	public UserDaoImpl(@Autowired UserMapper userMapper) {
		this.userMapper = userMapper;
	}


	@Override
	public Integer getTotalUsersAmount() {
		return null;
	}
}
