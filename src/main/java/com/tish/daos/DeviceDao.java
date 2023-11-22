package com.tish.daos;

import com.tish.models.IntegerStatisticsPair;

import java.util.List;

public interface DeviceDao {

	Integer getDevicesAmount();

	List<IntegerStatisticsPair> getDeviceStatisticsByType();
	List<IntegerStatisticsPair> getDeviceStatisticsByOS();
	List<IntegerStatisticsPair> getDeviceStatisticsByBrowser();

}
