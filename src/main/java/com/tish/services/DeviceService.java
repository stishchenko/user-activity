package com.tish.services;

import com.tish.daos.DeviceDao;
import com.tish.models.IntegerStatisticsPair;
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


	public List<Map<String, Double>> getDevicesByType(String dataType, String fromDate, String toDate) {
		List<Map<String, Double>> mapList = new ArrayList<>();
		mapList.add(new HashMap<>());
		mapList.add(new HashMap<>());
		List<IntegerStatisticsPair> pairList = deviceDao.getDeviceStatisticsByTypeWithTimePeriod(fromDate, toDate);
		Integer totalDevicesAmount = deviceDao.getDevicesAmountWithTimePeriod(fromDate, toDate);
		for (IntegerStatisticsPair pair : pairList) {
			if (dataType.contains("value"))
				mapList.get(0).put(pair.getItem(), pair.getValue().doubleValue());
			if (dataType.contains("percent"))
				mapList.get(1).put(pair.getItem(), pair.getValue() / totalDevicesAmount * 100.0);
		}

		return mapList;
	}

	public List<Map<String, Double>> getDevicesByOS(String dataType, String fromDate, String toDate) {
		List<Map<String, Double>> mapList = new ArrayList<>();
		mapList.add(new HashMap<>());
		mapList.add(new HashMap<>());
		List<IntegerStatisticsPair> pairList = deviceDao.getDeviceStatisticsByOSWithTimePeriod(fromDate, toDate);
		Integer totalDevicesAmount = deviceDao.getDevicesAmountWithTimePeriod(fromDate, toDate);
		for (IntegerStatisticsPair pair : pairList) {
			if (dataType.contains("value"))
				mapList.get(0).put(pair.getItem(), pair.getValue().doubleValue());
			if (dataType.contains("percent"))
				mapList.get(1).put(pair.getItem(), pair.getValue() / totalDevicesAmount * 100.0);
		}

		return mapList;
	}

	public List<Map<String, Double>> getDevicesByBrowser(String dataType, String fromDate, String toDate) {
		List<Map<String, Double>> mapList = new ArrayList<>();
		mapList.add(new HashMap<>());
		mapList.add(new HashMap<>());
		List<IntegerStatisticsPair> pairList = deviceDao.getDeviceStatisticsByBrowserWithTimePeriod(fromDate, toDate);
		Integer totalDevicesAmount = deviceDao.getDevicesAmountWithTimePeriod(fromDate, toDate);
		for (IntegerStatisticsPair pair : pairList) {
			if (dataType.contains("value"))
				mapList.get(0).put(pair.getItem(), pair.getValue().doubleValue());
			if (dataType.contains("percent"))
				mapList.get(1).put(pair.getItem(), pair.getValue() / totalDevicesAmount * 100.0);
		}

		return mapList;
	}

}
