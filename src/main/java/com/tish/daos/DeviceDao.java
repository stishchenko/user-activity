package com.tish.daos;

import com.tish.mappers.DeviceMapper;
import com.tish.models.StatisticsPair;

import java.util.List;

public interface DeviceDao {

	Integer getDevicesAmount();

	List<StatisticsPair> getDeviceStatisticsByType();
	List<StatisticsPair> getDeviceStatisticsByOS();
	List<StatisticsPair> getDeviceStatisticsByBrowser();

}
