package com.tish.services;

import com.tish.daos.LocationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

	private final LocationDao locationDao;

	public LocationService(@Autowired LocationDao locationDao) {
		this.locationDao = locationDao;
	}
}
