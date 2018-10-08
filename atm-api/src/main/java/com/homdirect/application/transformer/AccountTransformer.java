package com.homdirect.application.transformer;

import com.homdirect.application.entity.Account;
import com.homdirect.application.request.AccountRequest;
import com.homdirect.application.response.AccountResponse;
import com.homdirect.application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountTransformer {

    private final AccountService service;

    @Autowired
    public AccountTransformer(AccountService service) {
        this.service = service;
    }

    public Account toAccount(AccountRequest request) throws NoSuchAlgorithmException {
        Account newAccount = new Account();
        newAccount.setId(request.getId());
        newAccount.setAccountNumber(service.generateAccountNumber());
        newAccount.setUsername(request.getUsername());
        newAccount.setPassword(request.getPassword());
        newAccount.setAmount(Account.Constant.DEFAULT_AMOUNT);
        return newAccount;
    }

    public AccountResponse toResponse(Account account) {
        AccountResponse response = new AccountResponse();
        response.setId(account.getId());
        response.setAccountNumber(account.getAccountNumber());
        response.setUsername(account.getUsername());
        response.setAmount(account.getAmount());
        return response;
    }

    public List<AccountResponse> toResponseList(List<Account> accounts) {
        return accounts.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Page<AccountResponse> toResponse(Page<Account> page) {
        return page.map(account -> toResponse(account));
    }

}