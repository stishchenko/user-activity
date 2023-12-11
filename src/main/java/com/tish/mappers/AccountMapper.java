package com.tish.mappers;

import com.tish.models.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

	Account checkIfAccountExists(String login);

	void registerAccount(Account account);

	void loginByAccount(Account account);

	void logoutByEmail(String email);

	Account checkIfLoggedAccountExists();
}
