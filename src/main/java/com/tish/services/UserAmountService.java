package com.tish.services;

import com.tish.daos.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserAmountService {

	private final UserDao userDao;

	public UserAmountService(@Autowired UserDao userDao) {
		this.userDao = userDao;
	}

	public List<Map<String, Double>> getUsersAmountAsSingleAndRepeat() {
		Integer totalUsersAmount = userDao.getTotalUsersAmount();
		Integer singleUsersAmount = userDao.getSingleUsersList().size();
		Integer repeatUsersAmount = totalUsersAmount - singleUsersAmount;

		List<Map<String, Double>> mapList = new ArrayList<>();
		Map<String, Double> valueMap = new HashMap<>();
		valueMap.put("singleUsers", singleUsersAmount.doubleValue());
		valueMap.put("repeatUsers", repeatUsersAmount.doubleValue());
		mapList.add(valueMap);
		Map<String, Double> percentMap = new HashMap<>();
		percentMap.put("singleUsersPercent", singleUsersAmount / totalUsersAmount * 100.0);
		percentMap.put("repeatUsersPercent", repeatUsersAmount / totalUsersAmount * 100.0);
		mapList.add(percentMap);
		return mapList;
	}

	public List<Map<String, Double>> getUsersAmountByVisitTimes() {
		Integer totalUsersAmount = userDao.getTotalUsersAmount();
		Integer firstPageUsersAmount = userDao.getFirstPageUsersAmount();
		Integer severalPageUsersAmount = totalUsersAmount - firstPageUsersAmount;

		List<Map<String, Double>> mapList = new ArrayList<>();
		Map<String, Double> valueMap = new HashMap<>();
		valueMap.put("firstPageUsers", firstPageUsersAmount.doubleValue());
		valueMap.put("severalPageUsers", severalPageUsersAmount.doubleValue());
		mapList.add(valueMap);
		Map<String, Double> percentMap = new HashMap<>();
		percentMap.put("firstPageUsersPercent", firstPageUsersAmount / totalUsersAmount * 100.0);
		percentMap.put("severalPageUsersPercent", severalPageUsersAmount / totalUsersAmount * 100.0);
		mapList.add(percentMap);

		return mapList;
	}

}
