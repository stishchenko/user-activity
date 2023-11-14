package com.tish.services;

import com.tish.daos.VisitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitAmountService {

	private final VisitDao visitDao;

	public VisitAmountService(@Autowired VisitDao visitDao) {
		this.visitDao = visitDao;
	}
}
