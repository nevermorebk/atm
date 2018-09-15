package com.homedirect.atm.converter;

import com.homedirect.atm.model.Account;
import com.homedirect.atm.request.AccountRequest;
import com.homedirect.atm.response.AccountResponse;

public interface AccountConverter {

    Account toAccount(AccountRequest request);

    AccountResponse toResponse(Account account);

}
