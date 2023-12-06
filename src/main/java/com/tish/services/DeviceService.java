package com.tish.services;

import com.tish.daos.DeviceDao;
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
public class DeviceService {

	private final DeviceDao deviceDao;

	public DeviceService(@Autowired DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}


	public Map<String, List> getDevicesByType(String dataType, String fromDate, String toDate, String webApp) {
		Map<String, List> returnMap = new HashMap<>();
		List<Double> valueList = new ArrayList<>();
		List<Double> percentList = new ArrayList<>();
		List<IntegerStatisticsPair> pairList = deviceDao.getDeviceStatisticsByTypeWithTimePeriod(fromDate, toDate, webApp);
		Integer totalDevicesAmount = deviceDao.getDevicesAmountWithTimePeriod(fromDate, toDate, webApp);

		List<String> labels = new ArrayList<>();
		pairList.forEach(pair -> labels.add(pair.getItem()));
		returnMap.put("labels", labels);

		if (dataType.contains("value")) {
			pairList.forEach(pair -> valueList.add(pair.getValue().doubleValue()));
			returnMap.put("values", valueList);
		}
		if (dataType.contains("percent")) {
			pairList.forEach(pair -> percentList.add(BigDecimal.valueOf(pair.getValue().doubleValue() / totalDevicesAmount * 100).setScale(2, RoundingMode.HALF_UP).doubleValue()));
			returnMap.put("percent", percentList);
		}

		return returnMap;
	}

	public Map<String, List> getDevicesByOS(String dataType, String fromDate, String toDate, String webApp) {
		Map<String, List> returnMap = new HashMap<>();
		List<Double> valueList = new ArrayList<>();
		List<Double> percentList = new ArrayList<>();
		List<IntegerStatisticsPair> pairList = deviceDao.getDeviceStatisticsByOSWithTimePeriod(fromDate, toDate, webApp);
		Integer totalDevicesAmount = deviceDao.getDevicesAmountWithTimePeriod(fromDate, toDate, webApp);

		List<String> labels = new ArrayList<>();
		pairList.forEach(pair -> labels.add(pair.getItem()));
		returnMap.put("labels", labels);

		if (dataType.contains("value")) {
			pairList.forEach(pair -> valueList.add(pair.getValue().doubleValue()));
			returnMap.put("values", valueList);
		}
		if (dataType.contains("percent")) {
			pairList.forEach(pair -> percentList.add(BigDecimal.valueOf(pair.getValue().doubleValue() / totalDevicesAmount * 100).setScale(2, RoundingMode.HALF_UP).doubleValue()));
			returnMap.put("percent", percentList);
		}

		return returnMap;
	}

	public Map<String, List> getDevicesByBrowser(String dataType, String fromDate, String toDate, String webApp) {
		Map<String, List> returnMap = new HashMap<>();
		List<Double> valueList = new ArrayList<>();
		List<Double> percentList = new ArrayList<>();
		List<IntegerStatisticsPair> pairList = deviceDao.getDeviceStatisticsByBrowserWithTimePeriod(fromDate, toDate, webApp);
		Integer totalDevicesAmount = deviceDao.getDevicesAmountWithTimePeriod(fromDate, toDate, webApp);

		List<String> labels = new ArrayList<>();
		pairList.forEach(pair -> labels.add(pair.getItem()));
		returnMap.put("labels", labels);

		if (dataType.contains("value")) {
			pairList.forEach(pair -> valueList.add(pair.getValue().doubleValue()));
			returnMap.put("values", valueList);
		}
		if (dataType.contains("percent")) {
			pairList.forEach(pair -> percentList.add(BigDecimal.valueOf(pair.getValue().doubleValue() / totalDevicesAmount * 100).setScale(2, RoundingMode.HALF_UP).doubleValue()));
			returnMap.put("percent", percentList);
		}

		return returnMap;
	}

}
