package com.tish.services;

import com.tish.daos.LocationDao;
import com.tish.daos.UserDao;
import com.tish.daos.VisitDao;
import com.tish.models.IntegerStatisticsPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

	public List<Map<String, Double>> getCountriesStatistics(String statType, String dataType, String fromDate, String toDate, String webApp) {
		List<Map<String, Double>> mapList;

		switch (statType) {
			case "user":
				mapList = getCountriesForUsers(dataType, fromDate, toDate, webApp);
				break;
			case "visit":
				mapList = getCountriesForVisits(dataType, fromDate, toDate, webApp);
				break;
			default:
				mapList = getCountriesForUsers(dataType, fromDate, toDate, webApp);
				mapList.addAll(getCountriesForVisits(dataType, fromDate, toDate, webApp));
		}

		return mapList;
	}

	private List<Map<String, Double>> getCountriesForUsers(String dataType, String fromDate, String toDate, String webApp) {
		Map<String, Double> valueMap = new HashMap<>();
		Map<String, Double> percentMap = new HashMap<>();
		List<Map<String, Double>> listMap = new ArrayList<>();
		Integer totalUsers = userDao.getTotalUsersAmountWithTimePeriod(fromDate, toDate, webApp);
		List<IntegerStatisticsPair> pairList = locationDao.getCountryStatisticsByUsersWithTimePeriod(fromDate, toDate, webApp);

		if (dataType.contains("value")) {
			pairList.forEach(pair -> valueMap.put(pair.getItem(), pair.getValue().doubleValue()));
			listMap.add(valueMap);
		}
		if (dataType.contains("percent")) {

			pairList.forEach(pair -> percentMap.put(pair.getItem(), BigDecimal.valueOf(pair.getValue().doubleValue() / totalUsers * 100).setScale(2, RoundingMode.HALF_UP).doubleValue()));
			listMap.add(percentMap);
		}

		return listMap;
	}

	private List<Map<String, Double>> getCountriesForVisits(String dataType, String fromDate, String toDate, String webApp) {
		Map<String, Double> valueMap = new HashMap<>();
		Map<String, Double> percentMap = new HashMap<>();
		List<Map<String, Double>> listMap = new ArrayList<>();
		Integer totalVisits = visitDao.getTotalVisitsAmountWithTimePeriod(fromDate, toDate, webApp);
		List<IntegerStatisticsPair> pairList = locationDao.getCountryStatisticsByVisitsWithTimePeriod(fromDate, toDate, webApp);

		if (dataType.contains("value")) {
			pairList.forEach(pair -> valueMap.put(pair.getItem(), pair.getValue().doubleValue()));
			listMap.add(valueMap);
		}
		if (dataType.contains("percent")) {
			pairList.forEach(pair -> percentMap.put(pair.getItem(), BigDecimal.valueOf(pair.getValue().doubleValue() / totalVisits * 100).setScale(2, RoundingMode.HALF_UP).doubleValue()));
			listMap.add(percentMap);
		}

		return listMap;
	}

	public List<Map<String, Double>> getCitiesStatistics(String statType, String dataType, String fromDate, String toDate, String webApp) {
		List<Map<String, Double>> mapList;

		switch (statType) {
			case "user":
				mapList = getCitiesForUsers(dataType, fromDate, toDate, webApp);
				break;
			case "visit":
				mapList = getCitiesForVisits(dataType, fromDate, toDate, webApp);
				break;
			default:
				mapList = getCitiesForUsers(dataType, fromDate, toDate, webApp);
				mapList.addAll(getCitiesForVisits(dataType, fromDate, toDate, webApp));
		}

		return mapList;
	}

	private List<Map<String, Double>> getCitiesForUsers(String dataType, String fromDate, String toDate, String webApp) {
		Map<String, Double> valueMap = new HashMap<>();
		Map<String, Double> percentMap = new HashMap<>();
		List<Map<String, Double>> listMap = new ArrayList<>();
		Integer totalUsers = userDao.getTotalUsersAmountWithTimePeriod(fromDate, toDate, webApp);
		List<IntegerStatisticsPair> pairList = locationDao.getCityStatisticsByUsersWithTimePeriod(fromDate, toDate, webApp);
		if (dataType.contains("value")) {
			pairList.forEach(pair -> valueMap.put(pair.getItem(), pair.getValue().doubleValue()));
			listMap.add(valueMap);
		}
		if (dataType.contains("percent")) {
			pairList.forEach(pair -> percentMap.put(pair.getItem(), BigDecimal.valueOf(pair.getValue().doubleValue() / totalUsers * 100).setScale(2, RoundingMode.HALF_UP).doubleValue()));
			listMap.add(percentMap);
		}

		return listMap;
	}

	private List<Map<String, Double>> getCitiesForVisits(String dataType, String fromDate, String toDate, String webApp) {
		Map<String, Double> valueMap = new HashMap<>();
		Map<String, Double> percentMap = new HashMap<>();
		List<Map<String, Double>> listMap = new ArrayList<>();
		Integer totalVisits = visitDao.getTotalVisitsAmountWithTimePeriod(fromDate, toDate, webApp);
		List<IntegerStatisticsPair> pairList = locationDao.getCityStatisticsByVisitsWithTimePeriod(fromDate, toDate, webApp);

		if (dataType.contains("value")) {
			pairList.forEach(pair -> valueMap.put(pair.getItem(), pair.getValue().doubleValue()));
			listMap.add(valueMap);
		}
		if (dataType.contains("percent")) {
			pairList.forEach(pair -> percentMap.put(pair.getItem(), BigDecimal.valueOf(pair.getValue().doubleValue() / totalVisits * 100).setScale(2, RoundingMode.HALF_UP).doubleValue()));
			listMap.add(percentMap);
		}

		return listMap;
	}

}
