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

	public List<Map<String, Double>> getUsersAmountAsSingleAndRepeat(String dataType, String fromDate, String toDate) {
		Integer totalUsersAmount = userDao.getTotalUsersAmountWithTimePeriod(fromDate, toDate);
		Integer singleUsersAmount = userDao.getSingleUsersListWithTimePeriod(fromDate, toDate).size();
		Integer repeatUsersAmount = totalUsersAmount - singleUsersAmount;

		List<Map<String, Double>> mapList = new ArrayList<>();
		if (dataType.contains("value")) {
			Map<String, Double> valueMap = new HashMap<>();
			valueMap.put("singleUsers", singleUsersAmount.doubleValue());
			valueMap.put("repeatUsers", repeatUsersAmount.doubleValue());
			mapList.add(valueMap);
		}
		if (dataType.contains("percent")) {
			Map<String, Double> percentMap = new HashMap<>();
			percentMap.put("singleUsersPercent", singleUsersAmount / totalUsersAmount * 100.0);
			percentMap.put("repeatUsersPercent", repeatUsersAmount / totalUsersAmount * 100.0);
			mapList.add(percentMap);
		}
		return mapList;
	}

	public List<Map<String, Double>> getUsersAmountByVisitTimes(String dataType, String fromDate, String toDate) {
		Integer totalUsersAmount = userDao.getTotalUsersAmountWithTimePeriod(fromDate, toDate);
		Integer firstPageUsersAmount = userDao.getFirstPageUsersAmountWithTimePeriod(fromDate, toDate);
		Integer severalPageUsersAmount = totalUsersAmount - firstPageUsersAmount;

		List<Map<String, Double>> mapList = new ArrayList<>();
		if (dataType.contains("value")) {
			Map<String, Double> valueMap = new HashMap<>();
			valueMap.put("firstPageUsers", firstPageUsersAmount.doubleValue());
			valueMap.put("severalPageUsers", severalPageUsersAmount.doubleValue());
			mapList.add(valueMap);
		}
		if (dataType.contains("percent")) {
			Map<String, Double> percentMap = new HashMap<>();
			percentMap.put("firstPageUsersPercent", firstPageUsersAmount / totalUsersAmount * 100.0);
			percentMap.put("severalPageUsersPercent", severalPageUsersAmount / totalUsersAmount * 100.0);
			mapList.add(percentMap);
		}

		return mapList;
	}

}
