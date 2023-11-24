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
	public Integer getTotalUsersAmountWithTimePeriod(String fromDate, String toDate, String webApp) {
		return userMapper.getTotalUsersAmount(fromDate, toDate, webApp);
	}

	@Override
	public Integer getSingleUsersListWithTimePeriod(String fromDate, String toDate, String webApp) {
		return userMapper.getSingleUsersList(fromDate, toDate, webApp);
	}

	@Override
	public Integer getFirstPageUsersAmountWithTimePeriod(String fromDate, String toDate, String webApp) {
		return userMapper.getFirstPageUsersAmount(fromDate, toDate, webApp);
	}
}
