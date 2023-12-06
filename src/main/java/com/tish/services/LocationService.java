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

	public Map<String, List> getCountriesStatistics(String statType, String dataType, String fromDate, String toDate, String webApp) {
		Map<String, List> mapList;

		switch (statType) {
			case "user":
				mapList = getCountriesForUsers(dataType, fromDate, toDate, webApp);
				break;
			case "visit":
				mapList = getCountriesForVisits(dataType, fromDate, toDate, webApp, true);
				break;
			default:
				mapList = getCountriesForUsers(dataType, fromDate, toDate, webApp);
				mapList.putAll(getCountriesForVisits(dataType, fromDate, toDate, webApp, false));
		}

		return mapList;
	}

	private Map<String, List> getCountriesForUsers(String dataType, String fromDate, String toDate, String webApp) {
		Map<String, List> returnMap = new HashMap<>();
		List<String> labels = new ArrayList<>();
		List<Double> valueList = new ArrayList<>();
		List<Double> percentList = new ArrayList<>();
		Integer totalUsers = userDao.getTotalUsersAmountWithTimePeriod(fromDate, toDate, webApp);
		List<IntegerStatisticsPair> pairList = locationDao.getCountryStatisticsByUsersWithTimePeriod(fromDate, toDate, webApp);

		pairList.forEach(pair -> labels.add(pair.getItem()));
		returnMap.put("labels", labels);

		if (dataType.contains("value")) {
			pairList.forEach(pair -> valueList.add(pair.getValue().doubleValue()));
			returnMap.put("userValues", valueList);
		}
		if (dataType.contains("percent")) {
			pairList.forEach(pair -> percentList.add(BigDecimal.valueOf(pair.getValue().doubleValue() / totalUsers * 100).setScale(2, RoundingMode.HALF_UP).doubleValue()));
			returnMap.put("userPercent", percentList);
		}

		return returnMap;
	}

	private Map<String, List> getCountriesForVisits(String dataType, String fromDate, String toDate, String webApp, boolean addLabels) {
		Map<String, List> returnMap = new HashMap<>();
		List<Double> valueList = new ArrayList<>();
		List<Double> percentList = new ArrayList<>();
		Integer totalVisits = visitDao.getTotalVisitsAmountWithTimePeriod(fromDate, toDate, webApp);
		List<IntegerStatisticsPair> pairList = locationDao.getCountryStatisticsByVisitsWithTimePeriod(fromDate, toDate, webApp);

		if (addLabels) {
			List<String> labels = new ArrayList<>();
			pairList.forEach(pair -> labels.add(pair.getItem()));
			returnMap.put("labels", labels);
		}
		if (dataType.contains("value")) {
			pairList.forEach(pair -> valueList.add(pair.getValue().doubleValue()));
			returnMap.put("visitValues", valueList);
		}
		if (dataType.contains("percent")) {
			pairList.forEach(pair -> percentList.add(BigDecimal.valueOf(pair.getValue().doubleValue() / totalVisits * 100).setScale(2, RoundingMode.HALF_UP).doubleValue()));
			returnMap.put("visitPercent", percentList);
		}

		return returnMap;
	}

	public Map<String, List> getCitiesStatistics(String statType, String dataType, String fromDate, String toDate, String webApp) {
		Map<String, List> mapList;

		switch (statType) {
			case "user":
				mapList = getCitiesForUsers(dataType, fromDate, toDate, webApp);
				break;
			case "visit":
				mapList = getCitiesForVisits(dataType, fromDate, toDate, webApp, true);
				break;
			default:
				mapList = getCitiesForUsers(dataType, fromDate, toDate, webApp);
				mapList.putAll(getCitiesForVisits(dataType, fromDate, toDate, webApp, false));
		}

		return mapList;
	}

	private Map<String, List> getCitiesForUsers(String dataType, String fromDate, String toDate, String webApp) {
		Map<String, List> returnMap = new HashMap<>();
		List<Double> valueList = new ArrayList<>();
		List<Double> percentList = new ArrayList<>();
		Integer totalUsers = userDao.getTotalUsersAmountWithTimePeriod(fromDate, toDate, webApp);
		List<IntegerStatisticsPair> pairList = locationDao.getCityStatisticsByUsersWithTimePeriod(fromDate, toDate, webApp);

		List<String> labels = new ArrayList<>();
		pairList.forEach(pair -> labels.add(pair.getItem()));
		returnMap.put("labels", labels);

		if (dataType.contains("value")) {
			pairList.forEach(pair -> valueList.add(pair.getValue().doubleValue()));
			returnMap.put("userValues", valueList);
		}
		if (dataType.contains("percent")) {
			pairList.forEach(pair -> percentList.add(BigDecimal.valueOf(pair.getValue().doubleValue() / totalUsers * 100).setScale(2, RoundingMode.HALF_UP).doubleValue()));
			returnMap.put("userPercent", percentList);
		}

		return returnMap;
	}

	private Map<String, List> getCitiesForVisits(String dataType, String fromDate, String toDate, String webApp, boolean addLabels) {
		Map<String, List> returnMap = new HashMap<>();
		List<Double> valueList = new ArrayList<>();
		List<Double> percentList = new ArrayList<>();
		Integer totalVisits = visitDao.getTotalVisitsAmountWithTimePeriod(fromDate, toDate, webApp);
		List<IntegerStatisticsPair> pairList = locationDao.getCityStatisticsByVisitsWithTimePeriod(fromDate, toDate, webApp);

		if (addLabels) {
			List<String> labels = new ArrayList<>();
			pairList.forEach(pair -> labels.add(pair.getItem()));
			returnMap.put("labels", labels);
		}
		if (dataType.contains("value")) {
			pairList.forEach(pair -> valueList.add(pair.getValue().doubleValue()));
			returnMap.put("visitValues", valueList);
		}
		if (dataType.contains("percent")) {
			pairList.forEach(pair -> percentList.add(BigDecimal.valueOf(pair.getValue().doubleValue() / totalVisits * 100).setScale(2, RoundingMode.HALF_UP).doubleValue()));
			returnMap.put("visitPercent", percentList);
		}

		return returnMap;
	}

}
