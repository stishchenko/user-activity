package com.tish.daos;

import com.tish.models.Account;

public interface AccountDao {

	Account checkIfAccountExists(String login);

	void registerAccount(Account account);

	void loginByAccount(Account account);

	void logoutByEmail(String email);

	Account checkIfLoggedAccountExists();

}
