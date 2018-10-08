package com.homdirect.application.service;

import com.homdirect.application.entity.Account;
import com.homdirect.application.exception.ATMException;
import com.homdirect.application.request.AccountRequest;
import com.homdirect.application.request.ChangePassRequest;

public interface AccountService {

    Account creatAcc(AccountRequest request);

    Account login(AccountRequest request) throws ATMException;

    Account changePassword(ChangePassRequest changePassRequest) throws ATMException;

//	Page<Account> search(String username, int pageNo, int pageSize);

    Account findByAccountNumber(String accountNumber);

//	Page<Account> findAll(int pageNo, int pageSize);

    Account findById(int id) throws ATMException;

//	List<Account> findAlls();

    String generateAccountNumber();

    Account save(Account account);

    Account updateAccount(Account account, String username);

    void deleteAccount(Account account);
}
