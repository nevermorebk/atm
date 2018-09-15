package com.homedirect.atm.services;

import com.homedirect.atm.model.Account;
import com.homedirect.atm.request.AccountRequest;
import com.homedirect.atm.request.PasswordRequest;
import com.homedirect.atm.response.AccountResponse;

public interface AccountService {

    AccountResponse createAccount(AccountRequest request);

    Account signIn(AccountRequest request);

    Account changePassword(PasswordRequest request);

    Account getAccountById(int accountId);
}
