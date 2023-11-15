package com.tish.daos;

import com.tish.mappers.LocationMapper;
import com.tish.models.StatisticsPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationDaoImpl implements LocationDao {

	private final LocationMapper locationMapper;

	public LocationDaoImpl(@Autowired LocationMapper locationMapper) {
		this.locationMapper = locationMapper;
	}

	@Override
	public List<StatisticsPair> getCountryStatisticsByUsers() {
		return null;
	}

	@Override
	public List<StatisticsPair> getCountryStatisticsByVisits() {
		return null;
	}

	@Override
	public List<StatisticsPair> getCityStatisticsByUsers() {
		return null;
	}

	@Override
	public List<StatisticsPair> getCityStatisticsByVisits() {
		return null;
	}
}
