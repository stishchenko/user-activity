package com.tish.services;

import com.tish.daos.VisitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitTimeService {

	private final VisitDao visitDao;

	public VisitTimeService(@Autowired VisitDao visitDao) {
		this.visitDao = visitDao;
	}
}
