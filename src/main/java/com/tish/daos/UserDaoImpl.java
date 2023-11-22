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
	public Integer getTotalUsersAmountWithTimePeriod(String fromDate, String toDate) {
		return userMapper.getTotalUsersAmount(fromDate, toDate);
	}

	@Override
	public List<Visit> getSingleUsersListWithTimePeriod(String fromDate, String toDate) {
		return userMapper.getSingleUsersList(fromDate, toDate);
	}

	@Override
	public Integer getFirstPageUsersAmountWithTimePeriod(String fromDate, String toDate) {
		return userMapper.getFirstPageUsersAmount(fromDate, toDate);
	}
}
