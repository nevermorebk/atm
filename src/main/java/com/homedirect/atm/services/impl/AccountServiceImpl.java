package com.homedirect.atm.services.impl;

import com.homedirect.atm.converter.AccountConverter;
import com.homedirect.atm.model.Account;
import com.homedirect.atm.repository.AccountRepository;
import com.homedirect.atm.request.AccountRequest;
import com.homedirect.atm.request.PasswordRequest;
import com.homedirect.atm.response.AccountResponse;
import com.homedirect.atm.services.AccountService;
import com.homedirect.atm.validator.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountValidator accountValidator;

	@Autowired
	private AccountConverter accountConverter;

	@Override
	public AccountResponse createAccount(AccountRequest request) {
		accountValidator.isvalidCreateAccount(request.getUsername(), request.getPassword());
		Account account = accountConverter.toAccount(request);
		accountRepository.save(account);
		return accountConverter.toResponse(account);
	}

	@Override
	public AccountResponse signIn(AccountRequest request) {
			Account account = accountRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
			if (request.getUsername().equals(account.getUsername()) && request.getPassword().equals(account.getPassword())) {
				return accountConverter.toResponse(account);
			}
			
		return null;
	}

	@Override
	public Account changePassword(PasswordRequest request) {
		Account account = accountRepository.findById(request.getId());
		if (!isValidateChangePassword(request.getOldPassword(), account)) {
			System.out.println(" \n You have entered the wrong old password! \n");
			return null;
		}

		 account.setPassword(request.getNewPassword());
		System.out.println(" \n Change password successfully! \n");
		return account;
	}

	private boolean isValidateChangePassword(String oldPassword, Account account) {
		return oldPassword.equals(account.getPassword());
	}

	@Override
	public AccountResponse getAccountById(int accountId) {
		Account findAccountById = accountRepository.findById(accountId);
		return accountConverter.toResponse(findAccountById);
	}
}
