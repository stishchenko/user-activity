package com.tish.daos;

import com.tish.mappers.UserMapper;
import com.tish.models.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

	@Override
	public List<Visit> getSingleUsersList() {
		return null;
	}

	@Override
	public Integer getFirstPageUsersAmount() {
		return null;
	}
}
