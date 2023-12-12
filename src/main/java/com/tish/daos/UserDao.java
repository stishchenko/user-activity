package com.tish.daos;

public interface UserDao {

	Integer getTotalUsersAmountWithTimePeriod(String fromDate, String toDate, String webApp);

	Integer getSingleUsersListWithTimePeriod(String fromDate, String toDate, String webApp);

	Integer getFirstPageUsersAmountWithTimePeriod(String fromDate, String toDate, String webApp);
}
