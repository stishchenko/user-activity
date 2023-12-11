package com.tish.services;

import com.tish.daos.VisitDao;
import com.tish.models.IntegerStatisticsPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Service
public class ConversionService {

	private final VisitDao visitDao;

	public ConversionService(@Autowired VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	public Map<String, List> getConversion(String periodType, String fromDate, String toDate, String webApp) {
		Map<String, List> map = new HashMap<>();
		List<String> labels = new ArrayList<>();
		List<Double> values = new ArrayList<>();

		List<IntegerStatisticsPair> totalVisits = visitDao.getVisitsAmountByPeriods(periodType, fromDate, toDate, webApp);
		List<IntegerStatisticsPair> targetVisits = visitDao.getTargetVisitsAmountByPeriods(periodType, fromDate, toDate, webApp);

		for (int i = 0, counter = 0; i < totalVisits.size(); i++) {
			Double result = 0.0;
			if (targetVisits.get(counter).getItem().equals(totalVisits.get(i).getItem())) {
				IntegerStatisticsPair pair = targetVisits.get(counter);
				result = BigDecimal.valueOf(pair.getValue().doubleValue() / totalVisits.get(i).getValue() * 100).setScale(2, RoundingMode.HALF_UP).doubleValue();
				counter++;
			}
			labels.add(totalVisits.get(i).getItem());
			values.add(result);
		}

		map.put("labels", labels);
		map.put("values", values);

		return map;
	}
}
