package com.tish.services;

import com.tish.daos.DeviceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

	private final DeviceDao deviceDao;

	public DeviceService(@Autowired DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}
}
