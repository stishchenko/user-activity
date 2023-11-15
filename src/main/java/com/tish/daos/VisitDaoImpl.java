package com.tish.daos;

import com.tish.mappers.VisitMapper;
import com.tish.models.StatisticsPair;
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
}
