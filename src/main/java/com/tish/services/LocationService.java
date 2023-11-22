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

	public List<Map<String, Double>> getCountriesStatistics(String type) {
		List<Map<String, Double>> mapList = null;

		switch (type) {
			case "user":
				mapList = getCountriesForUsers();
				break;
			case "visit":
				mapList = getCountriesForVisits();
				break;
		}

		return mapList;
	}

	private List<Map<String, Double>> getCountriesForUsers() {
		Map<String, Double> valueMap = new HashMap<>();
		Map<String, Double> percentMap = new HashMap<>();
		List<Map<String, Double>> listMap = new ArrayList<>();
		Integer totalUsers = userDao.getTotalUsersAmount();
		List<IntegerStatisticsPair> pairList = locationDao.getCountryStatisticsByUsers();

		pairList.forEach(pair -> valueMap.put(pair.getItem(), pair.getValue().doubleValue()));
		listMap.add(valueMap);
		pairList.forEach(pair -> percentMap.put(pair.getItem(), pair.getValue() / totalUsers * 100.0));
		listMap.add(percentMap);

		return listMap;
	}

	private List<Map<String, Double>> getCountriesForVisits() {
		Map<String, Double> valueMap = new HashMap<>();
		Map<String, Double> percentMap = new HashMap<>();
		List<Map<String, Double>> listMap = new ArrayList<>();
		Integer totalVisits = visitDao.getTotalVisitsAmount();
		List<IntegerStatisticsPair> pairList = locationDao.getCountryStatisticsByVisits();

		pairList.forEach(pair -> valueMap.put(pair.getItem(), pair.getValue().doubleValue()));
		listMap.add(valueMap);
		pairList.forEach(pair -> percentMap.put(pair.getItem(), pair.getValue() / totalVisits * 100.0));
		listMap.add(percentMap);

		return listMap;
	}

	public List<Map<String, Double>> getCitiesStatistics(String type) {
		List<Map<String, Double>> mapList = null;

		switch (type) {
			case "user":
				mapList = getCitiesForUsers();
				break;
			case "visit":
				mapList = getCitiesForVisits();
				break;
		}

		return mapList;
	}

	private List<Map<String, Double>> getCitiesForUsers() {
		Map<String, Double> valueMap = new HashMap<>();
		Map<String, Double> percentMap = new HashMap<>();
		List<Map<String, Double>> listMap = new ArrayList<>();
		Integer totalUsers = userDao.getTotalUsersAmount();
		List<IntegerStatisticsPair> pairList = locationDao.getCountryStatisticsByUsers();

		pairList.forEach(pair -> valueMap.put(pair.getItem(), pair.getValue().doubleValue()));
		listMap.add(valueMap);
		pairList.forEach(pair -> percentMap.put(pair.getItem(), pair.getValue() / totalUsers * 100.0));
		listMap.add(percentMap);

		return listMap;
	}

	private List<Map<String, Double>> getCitiesForVisits() {
		Map<String, Double> valueMap = new HashMap<>();
		Map<String, Double> percentMap = new HashMap<>();
		List<Map<String, Double>> listMap = new ArrayList<>();
		Integer totalVisits = visitDao.getTotalVisitsAmount();
		List<IntegerStatisticsPair> pairList = locationDao.getCityStatisticsByVisits();

		pairList.forEach(pair -> valueMap.put(pair.getItem(), pair.getValue().doubleValue()));
		listMap.add(valueMap);
		pairList.forEach(pair -> percentMap.put(pair.getItem(), pair.getValue() / totalVisits * 100.0));
		listMap.add(percentMap);

		return listMap;
	}

}
