package com.tish.daos;

import com.tish.models.DoubleStatisticsPair;
import com.tish.models.IntegerStatisticsPair;
import com.tish.models.Visit;

import java.util.List;

public interface VisitDao {

	Integer getTotalVisitsAmount();

	List<Visit> getUniqueVisitsAmount();

	List<IntegerStatisticsPair> getVisitsAmountByPeriod(String fromDate, String toDate);

	List<IntegerStatisticsPair> getTargetVisitsByPeriod(String fromDate, String toDate);
	List<DoubleStatisticsPair> getAvgVisitTimeByPeriod(String fromDate, String toDate);
	List<DoubleStatisticsPair> getAvgVisitTimeByPage(String fromDate, String toDate);

	Integer getCancellationAmount();
}
