package com.tish.daos;

import com.tish.mappers.DeviceMapper;
import com.tish.models.IntegerStatisticsPair;
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
	public Integer getDevicesAmountWithTimePeriod(String fromDate, String toDate, String webApp) {
		return deviceMapper.getDevicesAmount(fromDate, toDate, webApp);
	}

	@Override
	public List<IntegerStatisticsPair> getDeviceStatisticsByTypeWithTimePeriod(String fromDate, String toDate, String webApp) {
		return deviceMapper.getDeviceStatisticsByType(fromDate, toDate, webApp);
	}

	@Override
	public List<IntegerStatisticsPair> getDeviceStatisticsByOSWithTimePeriod(String fromDate, String toDate, String webApp) {
		return deviceMapper.getDeviceStatisticsByOS(fromDate, toDate, webApp);
	}

	@Override
	public List<IntegerStatisticsPair> getDeviceStatisticsByBrowserWithTimePeriod(String fromDate, String toDate, String webApp) {
		return deviceMapper.getDeviceStatisticsByBrowser(fromDate, toDate, webApp);
	}
}
