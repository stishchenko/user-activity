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
	public Integer getTotalVisitsAmount() {
		return null;
	}

	@Override
	public List<Visit> getUniqueVisitsAmount() {
		return null;
	}

	@Override
	public List<IntegerStatisticsPair> getVisitsAmountByPeriod(String fromDate, String toDate) {
		return null;
	}

	@Override
	public List<IntegerStatisticsPair> getTargetVisitsByPeriod(String fromDate, String toDate) {
		return null;
	}

	@Override
	public List<DoubleStatisticsPair> getAvgVisitTimeByPeriod(String fromDate, String toDate) {
		return null;
	}

	@Override
	public List<DoubleStatisticsPair> getAvgVisitTimeByPage(String fromDate, String toDate) {
		return null;
	}

	@Override
	public Integer getCancellationAmount() {
		return null;
	}
}
