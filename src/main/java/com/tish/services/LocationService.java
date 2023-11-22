package com.tish.services;

import com.tish.daos.LocationDao;
import com.tish.daos.UserDao;
import com.tish.daos.VisitDao;
import com.tish.models.IntegerStatisticsPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocationService {

	private final LocationDao locationDao;
	private final UserDao userDao;
	private final VisitDao visitDao;

	public LocationService(@Autowired LocationDao locationDao, @Autowired UserDao userDao, @Autowired VisitDao visitDao) {
		this.locationDao = locationDao;
		this.userDao = userDao;
		this.visitDao = visitDao;
	}

	public List<Map<String, Double>> getCountriesStatistics(String statType, String dataType, String fromDate, String toDate) {
		List<Map<String, Double>> mapList;

		switch (statType) {
			case "user":
				mapList = getCountriesForUsers(dataType, fromDate, toDate);
				break;
			case "visit":
				mapList = getCountriesForVisits(dataType, fromDate, toDate);
				break;
			default:
				mapList = getCountriesForUsers(dataType, fromDate, toDate);
				mapList.addAll(getCountriesForVisits(dataType, fromDate, toDate));
		}

		return mapList;
	}

	private List<Map<String, Double>> getCountriesForUsers(String dataType, String fromDate, String toDate) {
		Map<String, Double> valueMap = new HashMap<>();
		Map<String, Double> percentMap = new HashMap<>();
		List<Map<String, Double>> listMap = new ArrayList<>();
		Integer totalUsers = userDao.getTotalUsersAmountWithTimePeriod(fromDate, toDate);
		List<IntegerStatisticsPair> pairList = locationDao.getCountryStatisticsByUsersWithTimePeriod(fromDate, toDate);

		if (dataType.contains("value")) {
			pairList.forEach(pair -> valueMap.put(pair.getItem(), pair.getValue().doubleValue()));
			listMap.add(valueMap);
		}
		if (dataType.contains("percent")) {
			pairList.forEach(pair -> percentMap.put(pair.getItem(), pair.getValue() / totalUsers * 100.0));
			listMap.add(percentMap);
		}

		return listMap;
	}

	private List<Map<String, Double>> getCountriesForVisits(String dataType, String fromDate, String toDate) {
		Map<String, Double> valueMap = new HashMap<>();
		Map<String, Double> percentMap = new HashMap<>();
		List<Map<String, Double>> listMap = new ArrayList<>();
		Integer totalVisits = visitDao.getTotalVisitsAmountWithTimePeriod(fromDate, toDate);
		List<IntegerStatisticsPair> pairList = locationDao.getCountryStatisticsByVisitsWithTimePeriod(fromDate, toDate);

		if (dataType.contains("value")) {
			pairList.forEach(pair -> valueMap.put(pair.getItem(), pair.getValue().doubleValue()));
			listMap.add(valueMap);
		}
		if (dataType.contains("percent")) {
			pairList.forEach(pair -> percentMap.put(pair.getItem(), pair.getValue() / totalVisits * 100.0));
			listMap.add(percentMap);
		}

		return listMap;
	}

	public List<Map<String, Double>> getCitiesStatistics(String statType, String dataType, String fromDate, String toDate) {
		List<Map<String, Double>> mapList;

		switch (statType) {
			case "user":
				mapList = getCitiesForUsers(dataType, fromDate, toDate);
				break;
			case "visit":
				mapList = getCitiesForVisits(dataType, fromDate, toDate);
				break;
			default:
				mapList = getCitiesForUsers(dataType, fromDate, toDate);
				mapList.addAll(getCitiesForVisits(dataType, fromDate, toDate));
		}

		return mapList;
	}

	private List<Map<String, Double>> getCitiesForUsers(String dataType, String fromDate, String toDate) {
		Map<String, Double> valueMap = new HashMap<>();
		Map<String, Double> percentMap = new HashMap<>();
		List<Map<String, Double>> listMap = new ArrayList<>();
		Integer totalUsers = userDao.getTotalUsersAmountWithTimePeriod(fromDate, toDate);
		List<IntegerStatisticsPair> pairList = locationDao.getCityStatisticsByUsersWithTimePeriod(fromDate, toDate);
		if (dataType.contains("value")) {
			pairList.forEach(pair -> valueMap.put(pair.getItem(), pair.getValue().doubleValue()));
			listMap.add(valueMap);
		}
		if (dataType.contains("percent")) {
			pairList.forEach(pair -> percentMap.put(pair.getItem(), pair.getValue() / totalUsers * 100.0));
			listMap.add(percentMap);
		}

		return listMap;
	}

	private List<Map<String, Double>> getCitiesForVisits(String dataType, String fromDate, String toDate) {
		Map<String, Double> valueMap = new HashMap<>();
		Map<String, Double> percentMap = new HashMap<>();
		List<Map<String, Double>> listMap = new ArrayList<>();
		Integer totalVisits = visitDao.getTotalVisitsAmountWithTimePeriod(fromDate, toDate);
		List<IntegerStatisticsPair> pairList = locationDao.getCityStatisticsByVisitsWithTimePeriod(fromDate, toDate);

		if (dataType.contains("value")) {
			pairList.forEach(pair -> valueMap.put(pair.getItem(), pair.getValue().doubleValue()));
			listMap.add(valueMap);
		}
		if (dataType.contains("percent")) {
			pairList.forEach(pair -> percentMap.put(pair.getItem(), pair.getValue() / totalVisits * 100.0));
			listMap.add(percentMap);
		}

		return listMap;
	}

}
