package com.tish.daos;

import com.tish.models.Visit;

import java.util.List;

public interface UserDao {

	Integer getTotalUsersAmount();

	List<Visit> getSingleUsersList();

	Integer getFirstPageUsersAmount();
}
