package com.tish.services;

import com.tish.daos.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

	public List<Map<String, Double>> getUsersAmountAsSingleAndRepeat(String dataType, String fromDate, String toDate, String webApp) {
		Integer totalUsersAmount = userDao.getTotalUsersAmountWithTimePeriod(fromDate, toDate, webApp);
		Integer singleUsersAmount = userDao.getSingleUsersListWithTimePeriod(fromDate, toDate, webApp);
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
			percentMap.put("singleUsersPercent", BigDecimal.valueOf(singleUsersAmount.doubleValue() / totalUsersAmount * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
			percentMap.put("repeatUsersPercent", BigDecimal.valueOf(repeatUsersAmount.doubleValue() / totalUsersAmount * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
			mapList.add(percentMap);
		}
		return mapList;
	}

	public List<Map<String, Double>> getUsersAmountByVisitTimes(String dataType, String fromDate, String toDate, String webApp) {
		Integer totalUsersAmount = userDao.getTotalUsersAmountWithTimePeriod(fromDate, toDate, webApp);
		Integer firstPageUsersAmount = userDao.getFirstPageUsersAmountWithTimePeriod(fromDate, toDate, webApp);
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
			percentMap.put("firstPageUsersPercent", BigDecimal.valueOf(firstPageUsersAmount.doubleValue() / totalUsersAmount * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
			percentMap.put("severalPageUsersPercent", BigDecimal.valueOf(severalPageUsersAmount.doubleValue() / totalUsersAmount * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
			mapList.add(percentMap);
		}

		return mapList;
	}

}
