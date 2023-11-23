package com.tish.services;

import com.tish.daos.VisitDao;
import com.tish.models.IntegerStatisticsPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConversionService {

	private final VisitDao visitDao;

	public ConversionService(@Autowired VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	public Map<String, Double> getConversion(String periodType, String fromDate, String toDate, String webApp) {
		Map<String, Double> map = new HashMap<>();
		List<IntegerStatisticsPair> totalVisits = visitDao.getVisitsAmountByPeriods(periodType, fromDate, toDate, webApp);
		List<IntegerStatisticsPair> targetVisits = visitDao.getTargetVisitsAmountByPeriods(periodType, fromDate, toDate, webApp);

		for (int i = 0; i < targetVisits.size(); i++) {
			IntegerStatisticsPair pair = targetVisits.get(i);
			map.put(pair.getItem(), pair.getValue() / totalVisits.get(i).getValue() * 100.0);
		}

		return map;
	}
}
