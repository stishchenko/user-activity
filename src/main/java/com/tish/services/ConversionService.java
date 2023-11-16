package com.tish.services;

import com.tish.daos.VisitDao;
import com.tish.models.StatisticsPair;
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

	public Map<String, Double> getConversion(String fromDate, String toDate) {
		Map<String, Double> map = new HashMap<>();
		List<StatisticsPair> totalVisits = visitDao.getVisitsAmountByPeriod(fromDate, toDate);
		List<StatisticsPair> targetVisits = visitDao.getTargetVisitsByPeriod(fromDate, toDate);

		for (int i = 0; i < targetVisits.size(); i++) {
			StatisticsPair pair = targetVisits.get(i);
			map.put(pair.getItem(), pair.getValue() / totalVisits.get(i).getValue() * 100.0);
		}

		return map;
	}
}
