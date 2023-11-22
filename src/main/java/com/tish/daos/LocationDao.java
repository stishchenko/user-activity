package com.tish.daos;

import com.tish.models.IntegerStatisticsPair;

import java.util.List;

public interface LocationDao {

	List<IntegerStatisticsPair> getCountryStatisticsByUsers();

	List<IntegerStatisticsPair> getCountryStatisticsByVisits();

	List<IntegerStatisticsPair> getCityStatisticsByUsers();

	List<IntegerStatisticsPair> getCityStatisticsByVisits();
}
