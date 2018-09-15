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
import static com.homedirect.atm.commons.ConfigConstants.*;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	private @Autowired AccountValidator accountValidator;

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
	public Account signIn(AccountRequest request) {
			Account account = accountRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
			if (request.getUsername().equals(account.getUsername()) && request.getPassword().equals(account.getPassword())) {
				return account;
			}
			
		return null;
	}

	@Override
	public Account changePassword(PasswordRequest request) {
		Optional<Account> account = accountRepository.findById(request.getId());
		if (!isValidateChangePassword(request.getOldPassword(), account.get())) {
			System.out.println(" \n You have entered the wrong old password! \n");
			return null;
		}

		 account.get().setPassword(request.getNewPassword());
		System.out.println(" \n Change password successfully! \n");
		return account.get();
	}

	private boolean isValidateChangePassword(String oldPassword, Account account) {
		return oldPassword.equals(account.getPassword());
	}

	@Override
	public Account getAccountById(int accountId) {
		Optional<Account> accountToFind = accountRepository.findById(accountId);
		return accountToFind.orElse(null);
	}
}
