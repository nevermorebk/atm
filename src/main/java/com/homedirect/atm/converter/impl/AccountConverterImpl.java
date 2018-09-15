package com.homedirect.atm.converter.impl;

import com.homedirect.atm.converter.AccountConverter;
import com.homedirect.atm.model.Account;
import com.homedirect.atm.request.AccountRequest;
import com.homedirect.atm.response.AccountResponse;
import org.springframework.stereotype.Component;

import static com.homedirect.atm.commons.ConfigConstants.DEFAULT_AMOUNT;

@Component
public class AccountConverterImpl implements AccountConverter {

    @Override
    public Account toAccount(AccountRequest request) {
        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setPassword(request.getPassword());
        account.setAmount(DEFAULT_AMOUNT);
        return account;
    }

    @Override
    public AccountResponse toResponse(Account account) {
        AccountResponse response = new AccountResponse();
        response.setId(account.getId());
        response.setUsername(account.getUsername());
        response.setAmount(account.getAmount());
        return response;
    }
}
