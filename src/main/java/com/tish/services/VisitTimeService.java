package com.tish.services;

import com.tish.daos.VisitDao;
import com.tish.models.DoubleStatisticsPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VisitTimeService {

	private final VisitDao visitDao;

	public VisitTimeService(@Autowired VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	public Map<String, Double> getAvgVisitTimeByPeriod(String fromDate, String toDate) {
		Map<String, Double> avgMap = new HashMap<>();
		List<DoubleStatisticsPair> avgVisitsList = visitDao.getAvgVisitTimeByPeriod(fromDate, toDate);
		avgVisitsList.forEach(pair -> avgMap.put(pair.getItem(), pair.getValue()));

		return avgMap;
	}

	public Map<String, Double> getAvgVisitTimeByPage(String fromDate, String toDate) {
		Map<String, Double> avgMap = new HashMap<>();
		List<DoubleStatisticsPair> avgVisitsList = visitDao.getAvgVisitTimeByPage(fromDate, toDate);
		avgVisitsList.forEach(pair -> avgMap.put(pair.getItem(), pair.getValue()));

		return avgMap;
	}

	public List<Map<String, Double>> getCancellations(String dataType, String fromDate, String toDate) {
		Integer totalVisitsAmount = visitDao.getTotalVisitsAmountWithTimePeriod(fromDate, toDate);
		Integer cancellationVisitsAmount = visitDao.getCancellationAmountWithTimePeriod(fromDate, toDate);
		Integer otherVisitsAmount = totalVisitsAmount - cancellationVisitsAmount;

		List<Map<String, Double>> mapList = new ArrayList<>();
		if (dataType.contains("value")) {
			Map<String, Double> valueMap = new HashMap<>();
			valueMap.put("cancellationVisits", cancellationVisitsAmount.doubleValue());
			valueMap.put("otherVisits", otherVisitsAmount.doubleValue());
			mapList.add(valueMap);
		}
		if (dataType.contains("percent")) {
			Map<String, Double> percentMap = new HashMap<>();
			percentMap.put("cancellationVisitsPercent", cancellationVisitsAmount / totalVisitsAmount * 100.0);
			percentMap.put("otherVisitsPercent", otherVisitsAmount / totalVisitsAmount * 100.0);
			mapList.add(percentMap);
		}
		return mapList;
	}
}
