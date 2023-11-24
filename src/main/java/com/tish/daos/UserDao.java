package com.tish.daos;

import com.tish.models.Visit;

import java.util.List;

public interface UserDao {

	Integer getTotalUsersAmountWithTimePeriod(String fromDate, String toDate, String webApp);

	Integer getSingleUsersListWithTimePeriod(String fromDate, String toDate, String webApp);

	Integer getFirstPageUsersAmountWithTimePeriod(String fromDate, String toDate, String webApp);
}
