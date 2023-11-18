package com.tish.daos;

import com.tish.mappers.DeviceMapper;
import com.tish.models.StatisticsPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceDaoImpl implements DeviceDao {

	private final DeviceMapper deviceMapper;

	public DeviceDaoImpl(@Autowired DeviceMapper deviceMapper) {
		this.deviceMapper = deviceMapper;
	}

	@Override
	public Integer getDevicesAmount() {
		return null;
	}

	@Override
	public List<StatisticsPair> getDeviceStatisticsByType() {
		return null;
	}

	@Override
	public List<StatisticsPair> getDeviceStatisticsByOS() {
		return null;
	}

	@Override
	public List<StatisticsPair> getDeviceStatisticsByBrowser() {
		return null;
	}
}
