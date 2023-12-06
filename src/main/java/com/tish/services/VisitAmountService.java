package com.tish.services;

import com.tish.daos.VisitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VisitAmountService {

	private final VisitDao visitDao;

	public VisitAmountService(@Autowired VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	public Map<String, List> getVisitAmount(String dataType, String fromDate, String toDate, String webApp) {
		Integer totalVisitsAmount = visitDao.getTotalVisitsAmountWithTimePeriod(fromDate, toDate, webApp);
		Integer uniqueVisitsAmount = visitDao.getUniqueVisitsAmountWithTimePeriod(fromDate, toDate, webApp);
		Integer repeatVisitsAmount = totalVisitsAmount - uniqueVisitsAmount;

		Map<String, List> returnMap = new HashMap<>();
		List<Double> valueList = new ArrayList<>();
		List<Double> percentList = new ArrayList<>();

		List<String> labels = new ArrayList<>();
		labels.add("Unique visits");
		labels.add("Repeat visits");
		returnMap.put("labels", labels);

		if (dataType.contains("value")) {
			valueList.add(uniqueVisitsAmount.doubleValue());
			valueList.add(repeatVisitsAmount.doubleValue());
			returnMap.put("values", valueList);
		}
		if (dataType.contains("percent")) {
			percentList.add(BigDecimal.valueOf(uniqueVisitsAmount.doubleValue() / totalVisitsAmount * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
			percentList.add(BigDecimal.valueOf(repeatVisitsAmount.doubleValue() / totalVisitsAmount * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
			returnMap.put("percent", percentList);
		}

		return returnMap;
	}

}
