package com.homedirect.service;

import java.util.List;

import com.homedirect.entity.Account;
import com.homedirect.entity.Page;
import com.homedirect.exception.ATMException;
import com.homedirect.request.AccountRequest;
import com.homedirect.request.ChangePassRequest;
import com.homedirect.request.SearchAccountRequest;

public interface AccountService {

	Account creatAcc(AccountRequest request) throws ATMException;

	Account login(AccountRequest request) throws ATMException;

	Account changePassword(ChangePassRequest changePassRequest) throws ATMException;
	
	Page<Account> search(SearchAccountRequest request);
	
	Account findByAccountNumber(String accountNumber);

	List<Account> findAll();
	
	Account findById(int id) throws ATMException;
	
	String generateAccountNumber();
}
