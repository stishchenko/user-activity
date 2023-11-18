package com.tish.services;

import com.tish.daos.DeviceDao;
import com.tish.models.StatisticsPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceService {

	private final DeviceDao deviceDao;

	public DeviceService(@Autowired DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}


	public List<Map<String, Double>> getDevicesByType() {
		List<Map<String, Double>> mapList = new ArrayList<>();
		mapList.add(new HashMap<>());
		mapList.add(new HashMap<>());
		List<StatisticsPair> pairList = deviceDao.getDeviceStatisticsByType();
		Integer totalDevicesAmount = deviceDao.getDevicesAmount();
		for (StatisticsPair pair : pairList) {
			mapList.get(0).put(pair.getItem(), pair.getValue().doubleValue());
			mapList.get(1).put(pair.getItem(), pair.getValue() / totalDevicesAmount * 100.0);
		}

		return mapList;
	}

	public List<Map<String, Double>> getDevicesByOS() {
		List<Map<String, Double>> mapList = new ArrayList<>();
		mapList.add(new HashMap<>());
		mapList.add(new HashMap<>());
		List<StatisticsPair> pairList = deviceDao.getDeviceStatisticsByOS();
		Integer totalDevicesAmount = deviceDao.getDevicesAmount();
		for (StatisticsPair pair : pairList) {
			mapList.get(0).put(pair.getItem(), pair.getValue().doubleValue());
			mapList.get(1).put(pair.getItem(), pair.getValue() / totalDevicesAmount * 100.0);
		}

		return mapList;
	}

	public List<Map<String, Double>> getDevicesByBrowser() {
		List<Map<String, Double>> mapList = new ArrayList<>();
		mapList.add(new HashMap<>());
		mapList.add(new HashMap<>());
		List<StatisticsPair> pairList = deviceDao.getDeviceStatisticsByBrowser();
		Integer totalDevicesAmount = deviceDao.getDevicesAmount();
		for (StatisticsPair pair : pairList) {
			mapList.get(0).put(pair.getItem(), pair.getValue().doubleValue());
			mapList.get(1).put(pair.getItem(), pair.getValue() / totalDevicesAmount * 100.0);
		}

		return mapList;
	}

}
