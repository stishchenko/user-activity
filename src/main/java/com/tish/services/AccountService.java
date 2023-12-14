package com.tish.services;

import com.tish.daos.AccountDao;
import com.tish.models.Account;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	private final AccountDao accountDao;

	public AccountService(@Autowired AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public Account checkIfAccountExists(String login) {
		return accountDao.checkIfAccountExists(login);
	}

	public void registerAccount(Account account) {
		account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()));
		accountDao.registerAccount(account);
	}

	public void loginByAccount(Account account) {
		accountDao.loginByAccount(account);
	}

	public void logoutByEmail(String email) {
		accountDao.logoutByEmail(email);
	}

	public Account checkIfLoggedAccountExists() {
		return accountDao.checkIfLoggedAccountExists();
	}

	public boolean checkPassword(String currentPassword, String hashedPassword) {
		return BCrypt.checkpw(currentPassword, hashedPassword);
	}
}
