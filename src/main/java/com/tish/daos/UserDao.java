package com.tish.daos;

import com.tish.models.Visit;

import java.util.List;

public interface UserDao {

	Integer getTotalUsersAmountWithTimePeriod(String fromDate, String toDate);

	List<Visit> getSingleUsersListWithTimePeriod(String fromDate, String toDate);

	Integer getFirstPageUsersAmountWithTimePeriod(String fromDate, String toDate);
}
