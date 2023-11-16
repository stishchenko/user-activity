package com.tish.daos;

import com.tish.models.StatisticsPair;

import java.util.List;

public interface VisitDao {

	Integer getTotalVisitsAmount();

	List<StatisticsPair> getVisitsAmountByPeriod(String fromDate, String toDate);

	List<StatisticsPair> getTargetVisitsByPeriod(String fromDate, String toDate);
}
