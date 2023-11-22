package com.tish.daos;

import com.tish.models.DoubleStatisticsPair;
import com.tish.models.IntegerStatisticsPair;
import com.tish.models.Visit;

import java.util.List;

public interface VisitDao {

	Integer getTotalVisitsAmountWithTimePeriod(String fromDate, String toDate);

	List<Visit> getUniqueVisitsAmountWithTimePeriod(String fromDate, String toDate);

	List<IntegerStatisticsPair> getVisitsAmountByPeriods(String periodType, String fromDate, String toDate);

	List<IntegerStatisticsPair> getTargetVisitsAmountByPeriods(String periodType, String fromDate, String toDate);

	List<DoubleStatisticsPair> getAvgVisitTimeByPeriod(String fromDate, String toDate);

	List<DoubleStatisticsPair> getAvgVisitTimeByPage(String fromDate, String toDate);

	Integer getCancellationAmountWithTimePeriod(String fromDate, String toDate);
}
