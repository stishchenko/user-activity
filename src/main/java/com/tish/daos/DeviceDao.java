package com.tish.daos;

import com.tish.models.IntegerStatisticsPair;

import java.util.List;

public interface DeviceDao {

	Integer getDevicesAmountWithTimePeriod(String fromDate, String toDate, String webApp);

	List<IntegerStatisticsPair> getDeviceStatisticsByTypeWithTimePeriod(String fromDate, String toDate, String webApp);

	List<IntegerStatisticsPair> getDeviceStatisticsByOSWithTimePeriod(String fromDate, String toDate, String webApp);

	List<IntegerStatisticsPair> getDeviceStatisticsByBrowserWithTimePeriod(String fromDate, String toDate, String webApp);

}
