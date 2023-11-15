package com.tish.daos;

import com.tish.models.StatisticsPair;

import java.util.List;

public interface LocationDao {

	List<StatisticsPair> getCountryStatisticsByUsers();

	List<StatisticsPair> getCountryStatisticsByVisits();

	List<StatisticsPair> getCityStatisticsByUsers();

	List<StatisticsPair> getCityStatisticsByVisits();
}
