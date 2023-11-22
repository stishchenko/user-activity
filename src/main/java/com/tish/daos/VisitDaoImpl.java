package com.tish.daos;

import com.tish.mappers.VisitMapper;
import com.tish.models.DoubleStatisticsPair;
import com.tish.models.IntegerStatisticsPair;
import com.tish.models.Visit;
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
	public Integer getTotalVisitsAmountWithTimePeriod(String fromDate, String toDate) {
		return visitMapper.getTotalVisitsAmountWithTimePeriod(fromDate, toDate);
	}

	@Override
	public List<Visit> getUniqueVisitsAmountWithTimePeriod(String fromDate, String toDate) {
		return visitMapper.getUniqueVisitsAmountWithTimePeriod(fromDate, toDate);
	}

	@Override
	public List<IntegerStatisticsPair> getVisitsAmountByPeriods(String periodType, String fromDate, String toDate) {
		return visitMapper.getVisitsAmountByPeriods(periodType, fromDate, toDate);
	}

	@Override
	public List<IntegerStatisticsPair> getTargetVisitsAmountByPeriods(String periodType, String fromDate, String toDate) {
		return visitMapper.getTargetVisitsAmountByPeriods(periodType, fromDate, toDate);
	}

	@Override
	public List<DoubleStatisticsPair> getAvgVisitTimeByPeriod(String fromDate, String toDate) {
		return visitMapper.getAvgVisitTimeByPeriod(fromDate, toDate);
	}

	@Override
	public List<DoubleStatisticsPair> getAvgVisitTimeByPage(String fromDate, String toDate) {
		return visitMapper.getAvgVisitTimeByPage(fromDate, toDate);
	}

	@Override
	public Integer getCancellationAmountWithTimePeriod(String fromDate, String toDate) {
		return visitMapper.getCancellationAmountWithTimePeriod(fromDate, toDate);
	}
}
