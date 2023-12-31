package com.tish.daos;

import com.tish.mappers.VisitMapper;
import com.tish.models.DoubleStatisticsPair;
import com.tish.models.IntegerStatisticsPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class VisitDaoImpl implements VisitDao {

	private final VisitMapper visitMapper;

	public VisitDaoImpl(@Autowired VisitMapper visitMapper) {
		this.visitMapper = visitMapper;
	}

	@Override
	public Integer getTotalVisitsAmountWithTimePeriod(String fromDate, String toDate, String webApp) {
		return visitMapper.getTotalVisitsAmount(fromDate, toDate, webApp);
	}

	@Override
	public Integer getUniqueVisitsAmountWithTimePeriod(String fromDate, String toDate, String webApp) {
		return visitMapper.getUniqueVisitsAmount(fromDate, toDate, webApp);
	}

	@Override
	public List<IntegerStatisticsPair> getVisitsAmountByPeriods(String periodType, String fromDate, String toDate, String webApp) {
		return visitMapper.getVisitsAmountByPeriods(periodType, fromDate, toDate, webApp);
	}

	@Override
	public List<IntegerStatisticsPair> getTargetVisitsAmountByPeriods(String periodType, String fromDate, String toDate, String webApp) {
		return visitMapper.getTargetVisitsAmountByPeriods(periodType, fromDate, toDate, webApp);
	}

	@Override
	public List<DoubleStatisticsPair> getAvgVisitTimeByPeriod(String periodType, String fromDate, String toDate, String webApp) {
		return visitMapper.getAvgVisitTimeByPeriod(periodType, fromDate, toDate, webApp);
	}

	@Override
	public List<DoubleStatisticsPair> getAvgVisitTimeByPage(String fromDate, String toDate, String webApp) {
		return visitMapper.getAvgVisitTimeByPage(fromDate, toDate, webApp);
	}

	@Override
	public Integer getCancellationAmountWithTimePeriod(String fromDate, String toDate, String webApp) {
		return visitMapper.getCancellationAmount(fromDate, toDate, webApp);
	}
}
