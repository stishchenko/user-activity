package com.tish.daos;

import com.tish.mappers.LocationMapper;
import com.tish.models.IntegerStatisticsPair;
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
	public List<IntegerStatisticsPair> getCountryStatisticsByUsers() {
		return null;
	}

	@Override
	public List<IntegerStatisticsPair> getCountryStatisticsByVisits() {
		return null;
	}

	@Override
	public List<IntegerStatisticsPair> getCityStatisticsByUsers() {
		return null;
	}

	@Override
	public List<IntegerStatisticsPair> getCityStatisticsByVisits() {
		return null;
	}
}
