package com.tish.services;

import com.tish.daos.VisitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<Map<String, Double>> getVisitAmount() {
		Integer totalVisitsAmount = visitDao.getTotalVisitsAmount();
		Integer uniqueVisitsAmount = visitDao.getUniqueVisitsAmount().size();
		Integer repeatVisitsAmount = totalVisitsAmount - uniqueVisitsAmount;

		List<Map<String, Double>> mapList = new ArrayList<>();
		Map<String, Double> valueMap = new HashMap<>();
		valueMap.put("uniqueVisits", uniqueVisitsAmount.doubleValue());
		valueMap.put("repeatVisits", repeatVisitsAmount.doubleValue());
		mapList.add(valueMap);
		Map<String, Double> percentMap = new HashMap<>();
		percentMap.put("uniqueVisitsPercent", uniqueVisitsAmount / totalVisitsAmount * 100.0);
		percentMap.put("repeatVisitsPercent", repeatVisitsAmount / totalVisitsAmount * 100.0);
		mapList.add(percentMap);
		return mapList;
	}

}
