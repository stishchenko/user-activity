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

	public Map<String, List> getUsersAmountAsSingleAndRepeat(String dataType, String fromDate, String toDate, String webApp) {
		Integer totalUsersAmount = userDao.getTotalUsersAmountWithTimePeriod(fromDate, toDate, webApp);
		Integer singleUsersAmount = userDao.getSingleUsersListWithTimePeriod(fromDate, toDate, webApp);
		Integer repeatUsersAmount = totalUsersAmount - singleUsersAmount;

		Map<String, List> returnMap = new HashMap<>();
		List<Double> valueList = new ArrayList<>();
		List<Double> percentList = new ArrayList<>();

		List<String> labels = new ArrayList<>();
		labels.add("Single users");
		labels.add("Repeat users");
		returnMap.put("labels", labels);

		if (dataType.contains("value")) {
			valueList.add(singleUsersAmount.doubleValue());
			valueList.add(repeatUsersAmount.doubleValue());
			returnMap.put("values", valueList);
		}
		if (dataType.contains("percent")) {
			percentList.add(BigDecimal.valueOf(singleUsersAmount.doubleValue() / totalUsersAmount * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
			percentList.add(BigDecimal.valueOf(repeatUsersAmount.doubleValue() / totalUsersAmount * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
			returnMap.put("percent", percentList);
		}

		return returnMap;
	}

	public Map<String, List> getUsersAmountByVisitTimes(String dataType, String fromDate, String toDate, String webApp) {
		Integer totalUsersAmount = userDao.getTotalUsersAmountWithTimePeriod(fromDate, toDate, webApp);
		Integer firstPageUsersAmount = userDao.getFirstPageUsersAmountWithTimePeriod(fromDate, toDate, webApp);
		Integer severalPageUsersAmount = totalUsersAmount - firstPageUsersAmount;

		Map<String, List> returnMap = new HashMap<>();
		List<Double> valueList = new ArrayList<>();
		List<Double> percentList = new ArrayList<>();

		List<String> labels = new ArrayList<>();
		labels.add("First page users");
		labels.add("Several page users");
		returnMap.put("labels", labels);

		if (dataType.contains("value")) {
			valueList.add(firstPageUsersAmount.doubleValue());
			valueList.add(severalPageUsersAmount.doubleValue());
			returnMap.put("values", valueList);
		}
		if (dataType.contains("percent")) {
			percentList.add(BigDecimal.valueOf(firstPageUsersAmount.doubleValue() / totalUsersAmount * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
			percentList.add(BigDecimal.valueOf(severalPageUsersAmount.doubleValue() / totalUsersAmount * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
			returnMap.put("percent", percentList);
		}

		return returnMap;
	}

}
