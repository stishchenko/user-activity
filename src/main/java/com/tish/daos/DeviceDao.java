package com.tish.daos;

import com.tish.models.IntegerStatisticsPair;

import java.util.List;

public interface DeviceDao {

	Integer getDevicesAmountWithTimePeriod(String fromDate, String toDate);

	List<IntegerStatisticsPair> getDeviceStatisticsByTypeWithTimePeriod(String fromDate, String toDate);

	List<IntegerStatisticsPair> getDeviceStatisticsByOSWithTimePeriod(String fromDate, String toDate);

	List<IntegerStatisticsPair> getDeviceStatisticsByBrowserWithTimePeriod(String fromDate, String toDate);

}
