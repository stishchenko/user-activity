package com.tish.services;

import com.tish.daos.VisitDao;
import com.tish.models.DoubleStatisticsPair;
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
public class VisitTimeService {

	private final VisitDao visitDao;

	public VisitTimeService(@Autowired VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	public Map<String, List> getAvgVisitTimeByPeriod(String periodType, String fromDate, String toDate, String webApp) {
		Map<String, List> avgMap = new HashMap<>();
		List<DoubleStatisticsPair> avgVisitsList = visitDao.getAvgVisitTimeByPeriod(periodType, fromDate, toDate, webApp);
		List<String> labels = new ArrayList<>();
		avgVisitsList.forEach(pair -> labels.add(pair.getItem()));
		avgMap.put("labels", labels);
		List<Double> values = new ArrayList<>();
		avgVisitsList.forEach(pair -> values.add(BigDecimal.valueOf(pair.getValue()).setScale(2, RoundingMode.HALF_UP).doubleValue()));
		avgMap.put("values", values);

		return avgMap;
	}

	public Map<String, List> getAvgVisitTimeByPage(String fromDate, String toDate, String webApp) {
		Map<String, List> avgMap = new HashMap<>();
		List<DoubleStatisticsPair> avgVisitsList = visitDao.getAvgVisitTimeByPage(fromDate, toDate, webApp);
		List<String> labels = new ArrayList<>();
		avgVisitsList.forEach(pair -> labels.add(pair.getItem()));
		avgMap.put("labels", labels);
		List<Double> values = new ArrayList<>();
		avgVisitsList.forEach(pair -> values.add(BigDecimal.valueOf(pair.getValue()).setScale(2, RoundingMode.HALF_UP).doubleValue()));
		avgMap.put("values", values);

		return avgMap;
	}

	public Map<String, List> getCancellations(String dataType, String fromDate, String toDate, String webApp) {
		Integer totalVisitsAmount = visitDao.getTotalVisitsAmountWithTimePeriod(fromDate, toDate, webApp);
		Integer cancellationVisitsAmount = visitDao.getCancellationAmountWithTimePeriod(fromDate, toDate, webApp);
		Integer otherVisitsAmount = totalVisitsAmount - cancellationVisitsAmount;

		Map<String, List> returnMap = new HashMap<>();
		List<Double> valueList = new ArrayList<>();
		List<Double> percentList = new ArrayList<>();

		List<String> labels = new ArrayList<>();
		labels.add("Cancellations");
		labels.add("Other visits");
		returnMap.put("labels", labels);

		if (dataType.contains("value")) {
			valueList.add(cancellationVisitsAmount.doubleValue());
			valueList.add(otherVisitsAmount.doubleValue());
			returnMap.put("values", valueList);
		}
		if (dataType.contains("percent")) {
			percentList.add(BigDecimal.valueOf(cancellationVisitsAmount.doubleValue() / totalVisitsAmount * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
			percentList.add(BigDecimal.valueOf(otherVisitsAmount.doubleValue() / totalVisitsAmount * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
			returnMap.put("percent", percentList);
		}
		return returnMap;
	}
}
