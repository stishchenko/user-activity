package com.tish.daos;

import com.tish.mappers.AccountMapper;
import com.tish.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {

	private final AccountMapper accountMapper;

	public AccountDaoImpl(@Autowired AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	@Override
	public Account checkIfAccountExists(String login) {
		return accountMapper.checkIfAccountExists(login);
	}

	@Override
	public void registerAccount(Account account) {
		accountMapper.registerAccount(account);
	}

	@Override
	public void loginByAccount(Account account) {
		accountMapper.loginByAccount(account);
	}

	@Override
	public void logoutByEmail(String email) {
		accountMapper.logoutByEmail(email);
	}

	@Override
	public Account checkIfLoggedAccountExists() {
		return accountMapper.checkIfLoggedAccountExists();
	}
}
