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
	public List<IntegerStatisticsPair> getCountryStatisticsByUsersWithTimePeriod(String fromDate, String toDate, String webApp) {
		return locationMapper.getCountryStatisticsByUsers(fromDate, toDate, webApp);
	}

	@Override
	public List<IntegerStatisticsPair> getCountryStatisticsByVisitsWithTimePeriod(String fromDate, String toDate, String webApp) {
		return locationMapper.getCountryStatisticsByVisits(fromDate, toDate, webApp);
	}

	@Override
	public List<IntegerStatisticsPair> getCityStatisticsByUsersWithTimePeriod(String fromDate, String toDate, String webApp) {
		return locationMapper.getCityStatisticsByUsers(fromDate, toDate, webApp);
	}

	@Override
	public List<IntegerStatisticsPair> getCityStatisticsByVisitsWithTimePeriod(String fromDate, String toDate, String webApp) {
		return locationMapper.getCityStatisticsByVisits(fromDate, toDate, webApp);
	}
}
