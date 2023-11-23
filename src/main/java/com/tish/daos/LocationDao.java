package com.tish.daos;

import com.tish.models.IntegerStatisticsPair;

import java.util.List;

public interface LocationDao {

	List<IntegerStatisticsPair> getCountryStatisticsByUsersWithTimePeriod(String fromDate, String toDate, String webApp);

	List<IntegerStatisticsPair> getCountryStatisticsByVisitsWithTimePeriod(String fromDate, String toDate, String webApp);

	List<IntegerStatisticsPair> getCityStatisticsByUsersWithTimePeriod(String fromDate, String toDate, String webApp);

	List<IntegerStatisticsPair> getCityStatisticsByVisitsWithTimePeriod(String fromDate, String toDate, String webApp);
}
