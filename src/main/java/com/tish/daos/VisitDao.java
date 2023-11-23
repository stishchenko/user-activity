package com.tish.daos;

import com.tish.models.DoubleStatisticsPair;
import com.tish.models.IntegerStatisticsPair;
import com.tish.models.Visit;

import java.util.List;

public interface VisitDao {

	Integer getTotalVisitsAmountWithTimePeriod(String fromDate, String toDate, String webApp);

	List<Visit> getUniqueVisitsAmountWithTimePeriod(String fromDate, String toDate, String webApp);

	List<IntegerStatisticsPair> getVisitsAmountByPeriods(String periodType, String fromDate, String toDate, String webApp);

	List<IntegerStatisticsPair> getTargetVisitsAmountByPeriods(String periodType, String fromDate, String toDate, String webApp);

	List<DoubleStatisticsPair> getAvgVisitTimeByPeriod(String fromDate, String toDate, String webApp);

	List<DoubleStatisticsPair> getAvgVisitTimeByPage(String fromDate, String toDate, String webApp);

	Integer getCancellationAmountWithTimePeriod(String fromDate, String toDate, String webApp);
}
